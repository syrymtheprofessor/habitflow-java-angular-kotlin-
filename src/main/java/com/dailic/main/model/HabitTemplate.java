package com.dailic.main.model;

/*

Надо иметь основную страницу, где список я могу посмотреть список целей которые хочу добиться.

Например, стать лидером (Внутри будут Habit #1. Качаться. Habit #2. Skill Mastering)
Позже буду на определенном количество людей собирать данные что реально работает (global-feat #7)

*/

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class HabitTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private String sourceUrl; // TODO (GLOBAL FEAT) Может другой сервис нужен будет для неё
}