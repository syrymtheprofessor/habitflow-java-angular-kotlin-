package com.dailic.main.controller.habitLog;

import com.dailic.main.dto.habitLog.HabitLogResponse;
import com.dailic.main.service.HabitLogService;
import com.dailic.main.util.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/private/habitlogs")
@RequiredArgsConstructor
public class HabitLogPrivateController {

    private final HabitLogService habitLogService;

    @GetMapping("/getHabitLogList/{year}")
    public ResponseEntity<Map<String, List<HabitLogResponse>>> getList(
            @PathVariable int year,
            @AuthenticationPrincipal CustomUserPrincipal userPrincipal
    ) {

        return ResponseEntity.ok(habitLogService.getYearLogs(userPrincipal.getUserId(), year));
    }
}

