package com.dailic.main.dto.personal;

import com.dailic.main.dto.habits.HabitTemplateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserHabitResponse {

    private UUID id;
    private HabitTemplateResponse habitTemplate;
    private Integer streak;
    private String note;
    private LocalDate startedAt;
}
