package com.dailic.main.config;

import com.dailic.main.model.HabitLog;
import com.dailic.main.model.HabitTemplate;
import com.dailic.main.model.User;
import com.dailic.main.model.UserHabit;
import com.dailic.main.repository.HabitLogRepository;
import com.dailic.main.repository.HabitTemplateRepository;
import com.dailic.main.repository.UserHabitRepository;
import com.dailic.main.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataCreator {

    private final UserRepository userRepository;
    private final HabitTemplateRepository habitTemplateRepository;
    private final UserHabitRepository userHabitRepository;
    private final HabitLogRepository habitLogRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void createTestUserAndHabits() {
        User user = new User();
        user.setId(UUID.fromString("33beca72-ae7e-41e5-8cb1-26538241ca4f")); // temp: got from Keycloack (user_entity)
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("syrym.cappelo@gmail.com");
        user.setCountry("Kazakhstan");
        user.setAvatarKey("default-avatar.png");
        userRepository.saveAndFlush(user);

        List<HabitTemplate> allTemplates = habitTemplateRepository.findAll();
        List<UserHabit> userHabits = new ArrayList<>();
        for (var template : allTemplates) {
            UserHabit userHabit = UserHabit.builder()
                    .user(user)
                    .template(template)
                    .description("My Habit from today is: " + template.getName())
                    .build();
            userHabits.add(userHabit);
        }
        userHabitRepository.saveAll(userHabits);

        var startDate = LocalDate.now().minusDays(10);
        List<HabitLog> habitLogs = new ArrayList<>();
        Random random = new Random();
        for (var userHabit : userHabits) {
            for (int i = 0; i <= 10; i++) {
                LocalDate logDate = startDate.plusDays(i);

                // С вероятностью 70% привычка выполнена
                boolean completed = random.nextDouble() < 0.7;

                var habitLog = HabitLog.builder()
                        .userHabit(userHabit)
                        .date(logDate)
                        .completed(completed)
                        .activeStatus(completed ? LocalDateTime.now() : null)
                        .build();

                habitLogs.add(habitLog);
            }
        }
        habitLogRepository.saveAll(habitLogs);

        // No need right now
//        for (var userHabit : userHabits) {
//            updateStreak(userHabit);
//        }

        log.info("Test data creation completed!");
        log.info("====================================");
        log.info("Test user credentials:");
        log.info("Email: test@habitflow.com");
        log.info("User ID: {}", user.getId());
        log.info("====================================");
    }

//    private void updateStreak(UserHabit userHabit) {
//        List<HabitLog> logs = habitLogRepository.findByUserHabitIdOrderByDateDesc(userHabit.getId());
//
//        int streak = 0;
//        for (var log : logs) {
//            if (log.getCompleted()) {
//                streak++;
//            } else {
//                break;
//            }
//        }
//
//        userHabit.setStreak(streak);
//        userHabitRepository.save(userHabit);
//    }
}