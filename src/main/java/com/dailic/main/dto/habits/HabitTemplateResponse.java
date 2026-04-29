package com.dailic.main.dto.habits;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HabitTemplateResponse {

    private String name;
    private String description;
    private String categoryName;
    private String sourceUrl;
}
