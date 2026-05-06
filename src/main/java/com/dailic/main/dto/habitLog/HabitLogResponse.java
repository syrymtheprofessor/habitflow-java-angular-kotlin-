package com.dailic.main.dto.habitLog;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class HabitLogResponse {
    private String habitName;

    private LocalDate date;
    private Boolean completed;
    private LocalDateTime activeStatus;
}
