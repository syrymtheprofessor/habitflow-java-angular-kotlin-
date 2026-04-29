package com.dailic.main.dto.HabitTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HabitTemplateResponse {

    private String name;
    private String description;
    private String category;
    private String sourceUrl;
}
