package com.dailic.main.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CustomUserPrincipal {

    private UUID userId;
    private String email;
    private Boolean emailVerified;
    private String firstName;
    private String lastName;
    private List<String> roles;
}