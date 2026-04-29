package com.dailic.main.controller.personal;

import com.dailic.main.dto.PageRequestDto;
import com.dailic.main.dto.personal.UserHabitResponse;
import com.dailic.main.service.UserHabitService;
import com.dailic.main.util.CustomUserPrincipal;
import com.dailic.main.util.PagedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/personal")
@RequiredArgsConstructor
public class UserHabitController {

    private final UserHabitService userHabitService;

    @GetMapping("/habits")
    public ResponseEntity<PagedResponse<UserHabitResponse>> getList(
            @Valid PageRequestDto pageRequest,
            @AuthenticationPrincipal CustomUserPrincipal userPrincipal) {

        var userId = userPrincipal.getUserId(); // UUID пользователя из Keycloak
        return ResponseEntity.ok(userHabitService.getList(pageRequest, userId));
    }

//    @PostMapping("/habits/{templateId}")
//    public ResponseEntity<Void> addHabit(@PathVariable UUID templateId) {
//        userHabitService.addHabit(templateId);
//        return ResponseEntity.ok().build();
//    }

}
