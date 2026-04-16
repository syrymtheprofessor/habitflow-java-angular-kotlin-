package com.dailic.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class UserHabit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY) // user при загрузке UserHabit скорее всего не нужен — мы уже знаем userId (по нему и искали)
    @JoinColumn(name = "user_id") // - это название колонки в таблице базы данных, не в Java классе. Чтобы не путать (пока что путаю)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "template_id")
    private HabitTemplate template;

    @NotNull
    private Integer streak;

    @NotNull
    @Size(max = 100)
    private String note; // todo-feat дневник мб буду добавлять

    private LocalDate startedAt;
}

/*

Без @JoinColumn Hibernate сам придумает имя — обычно user_id и так, но явно всегда лучше чем неявно.

CREATE TABLE user_habit (
    id UUID,
    user_id UUID,       -- ← вот это name = "user_id"
    template_id UUID,   -- ← вот это name = "template_id"
    streak INTEGER,
    note VARCHAR
);

 */