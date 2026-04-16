package com.dailic.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
public class Category {

    @Id
    private UUID id;

    @NotNull
    @Size(min = 100)
    private String name;

    @NotNull
    @Size(min = 100)
    private String description;
}
