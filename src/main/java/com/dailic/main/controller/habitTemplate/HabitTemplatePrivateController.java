package com.dailic.main.controller.habitTemplate;

import com.dailic.main.dto.habitTemplate.PostHabitTemplateRequest;
import com.dailic.main.service.HabitTemplateService;
import com.dailic.main.util.CustomUserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/habit-template")
@RequiredArgsConstructor
public class HabitTemplatePrivateController {

    private final HabitTemplateService habitTemplateService;

    @PostMapping("/addHabitTemplate")
    public ResponseEntity<Void> addHabitTemplate(
            @Valid PostHabitTemplateRequest postHabitTemplateRequest,
            @AuthenticationPrincipal CustomUserPrincipal userPrincipal) {

        if (!userPrincipal.getRoles().contains("moderator"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        habitTemplateService.addHabitTemplate(postHabitTemplateRequest);
        return ResponseEntity.ok().build();
    }
}