package com.dailic.main.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_habit_id")
    private UserHabit userHabit;

    private LocalDate date;
    private Boolean completed;
}