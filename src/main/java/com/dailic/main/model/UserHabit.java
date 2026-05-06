package com.dailic.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Builder.Default
    private Integer streak = 0;

    @NotNull
    @Builder.Default
    private Integer longestStreak = 0;

    @Size(max = 100)
    private String description;

    @Column(nullable = false)
    private LocalDate startedAt;

    private LocalDate lastCompletedAt; // Для расчета стриков

    @Column(nullable = false)
    @Builder.Default
    private Integer targetPerDay = 1; // todo-feat метод выполнения плана введу

    @PrePersist
    public void prePersist() {
        startedAt = LocalDate.now();
    }
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