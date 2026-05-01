package com.dailic.main.controller.userHabit;

import com.dailic.main.dto.PageRequestDto;
import com.dailic.main.dto.userHabit.UserHabitResponse;
import com.dailic.main.service.UserHabitService;
import com.dailic.main.util.CustomUserPrincipal;
import com.dailic.main.util.PagedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/private/personal")
@RequiredArgsConstructor
@Slf4j
public class UserHabitPrivateController {

    private final UserHabitService userHabitService;

    @GetMapping("/habits")
    public ResponseEntity<PagedResponse<UserHabitResponse>> getList(
            @Valid PageRequestDto pageRequest,
            @AuthenticationPrincipal CustomUserPrincipal userPrincipal) {

        var userId = userPrincipal.getUserId(); // UUID пользователя из Keycloak
        return ResponseEntity.ok(userHabitService.getList(pageRequest, userId));
    }

    @PostMapping("/habits/{habitTemplateId}")
    public ResponseEntity<Void> addHabit(
            @PathVariable UUID habitTemplateId,
            @AuthenticationPrincipal CustomUserPrincipal userPrincipal) {

        userHabitService.addHabit(habitTemplateId, userPrincipal.getUserId());
        return ResponseEntity.ok().build();
    }

}
