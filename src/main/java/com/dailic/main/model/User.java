package com.dailic.main.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    /*
    @GeneratedValue(strategy = GenerationType.UUID) // TOLEARN #1. IDENTITY, SEQUENCE, AUTO — чем отличаются на уровне SQL?

    IDENTITY --- BIGSERIAL / AUTO_INCREMENT — база сама инкрементирует. Просто, быстро
    SEQUENCE --- Hibernate вызывает nextval('sequence_name') до INSERT. Можно батчить - ?что значит можно батчить, за счёт чего
    AUTO --- Hibernate сам решает что использовать — непредсказуемо, для UUID создаёт hibernate_sequence таблицу
    UUID --- Генерирует UUID на стороне Hibernate, без обращения к БД - ?uuid точно уникальный?
    TABLE --- Хранит счётчик в отдельной таблице — устаревший, медленный, не используй
     */
    @Id
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String avatarKey; // take from minIO
    private String country; // по приколу из IP-Address попытаюсь вытащить

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist // LEARNT #4. JPA lifecycle: перед/после INSERT/UPDATE/DELETE и после загрузки из БД (PrePersist PostPersist PreUpdate PostUpdate PreRemove PostRemove PostLoad)
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); // Не нужно вручную писать user.setCreatedAt(LocalDateTime.now())
    }
}
