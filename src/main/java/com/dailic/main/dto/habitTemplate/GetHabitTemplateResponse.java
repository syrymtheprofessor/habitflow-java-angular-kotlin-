package com.dailic.main.dto.habitTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetHabitTemplateResponse {

    private String name;
    private String description;
    private String categoryName;
    private String sourceUrl;
}
