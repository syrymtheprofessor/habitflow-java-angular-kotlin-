package com.dailic.main.model;

/*

Надо иметь основную страницу, где список я могу посмотреть список целей которые хочу добиться.

Например, стать лидером (Внутри будут Habit #1. Качаться. Habit #2. Skill Mastering)
Позже буду на определенном количество людей собирать данные что реально работает (global-feat #7)

*/

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HabitTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private String sourceUrl; // TODO (GLOBAL FEAT) Может другой сервис нужен будет для неё

    @Builder.Default
    private Boolean isPublic = true; // todo-feat в настройках профиля добавить возможность редактировать

//    @Builder.Default
//    private Integer difficulty = 1; // todo-feat добавлю форум для обсуждения достижения цели (пример: 10 000 часов для мастерства. Это примерно 10 лет усердных занятий по 3 часа в день или около 90 минут ежедневно в течение 20 лет.)
//
//    @Builder.Default
//    private Integer estimatedMinutes = 15; // todo-feat метод выполнения плана введу
}



