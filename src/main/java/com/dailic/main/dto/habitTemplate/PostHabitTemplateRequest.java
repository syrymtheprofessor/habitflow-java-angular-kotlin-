package com.dailic.main.dto.habitTemplate;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PostHabitTemplateRequest {
    private String name;
    private String description;
    private UUID categoryId;
    private String sourceUrl;
}
