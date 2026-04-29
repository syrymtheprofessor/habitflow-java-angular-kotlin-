package com.dailic.main.mapper;

import com.dailic.main.dto.personal.UserHabitResponse;
import com.dailic.main.model.Category;
import com.dailic.main.model.HabitTemplate;
import com.dailic.main.model.User;
import com.dailic.main.model.UserHabit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserHabitMapperTest {

    @Spy // @Spy создаёт реальный объект HabitTemplateMapper
    private HabitTemplateMapperImpl habitTemplateMapper;

    @InjectMocks //  и Mockito инжектит его в UserHabitMapperImpl
    private UserHabitMapperImpl mapperImpl;

    @Test
    void toDto_shouldMapUserHabitToResponse() {

        // 1. Создаем данные
        var category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("Fitness");
        category.setDescription("Fitness description");

        var template = new HabitTemplate(); // userHabit(habitTemplate)
        template.setId(UUID.randomUUID());
        template.setName("Morning run");
        template.setDescription("Run every morning");
            template.setCategory(category);
        template.setSourceUrl("https://example.com");

        User user = new User(); // userHabit(user)
        user.setId(UUID.randomUUID());

        UserHabit userHabit = new UserHabit();
        userHabit.setId(UUID.randomUUID());
            userHabit.setUser(user); // first one here
            userHabit.setTemplate(template); // second one here
        userHabit.setStreak(5);
        userHabit.setNote("Feeling good");
        userHabit.setStartedAt(LocalDate.now());

        // 2. Маппинг
        UserHabitResponse result = mapperImpl.toDto(userHabit);

        // 3. Проверки
        assertNotNull(result);

        assertEquals(userHabit.getId(), result.getId());
        assertEquals("Morning run", result.getHabitTemplate().getName());
        assertEquals("Fitness", result.getHabitTemplate().getCategoryName()); // it is fine
        assertEquals(5, result.getStreak());
        assertEquals("Feeling good", result.getNote());
    }

}
