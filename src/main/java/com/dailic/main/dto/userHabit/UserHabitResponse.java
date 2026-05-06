package com.dailic.main.dto.userHabit;

import com.dailic.main.dto.habitTemplate.GetHabitTemplateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserHabitResponse {

    private UUID id;
    private GetHabitTemplateResponse habitTemplate;
    private Integer streak;
    private String description;
    private LocalDate startedAt;
}
