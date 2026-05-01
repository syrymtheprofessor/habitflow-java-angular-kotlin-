package com.dailic.main.service;

import com.dailic.main.dto.PageRequestDto;
import com.dailic.main.dto.userHabit.UserHabitResponse;
import com.dailic.main.mapper.UserHabitMapper;
import com.dailic.main.model.HabitTemplate;
import com.dailic.main.model.User;
import com.dailic.main.model.UserHabit;
import com.dailic.main.repository.HabitTemplateRepository;
import com.dailic.main.repository.UserHabitRepository;
import com.dailic.main.repository.UserRepository;
import com.dailic.main.util.PagedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserHabitService {

    private final UserHabitRepository userHabitRepository;
    private final UserHabitMapper userHabitMapper;
    private final HabitTemplateRepository habitTemplateRepository;
    private final UserRepository userRepository;

    public PagedResponse<UserHabitResponse> getList(@Valid PageRequestDto pageRequest, UUID userId) {
        Page<UserHabit> userHabits = userHabitRepository.findByUserId(userId, pageRequest.toPageable());
        Page<UserHabitResponse> results = userHabits.map(userHabitMapper::toDto);
        return PagedResponse.of(results);
    }

    public void addHabit(UUID habitTemplateId, UUID userId) {
        HabitTemplate habitTemplate = habitTemplateRepository.findById(habitTemplateId)
                .orElseThrow(() -> new RuntimeException("habitTemplate not found"));
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var userHabit = UserHabit.builder()
                .user(user)
                .template(habitTemplate)
                .build();
        userHabitRepository.save(userHabit);
    }
}
