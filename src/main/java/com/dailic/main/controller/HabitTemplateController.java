package com.dailic.main.controller;

import com.dailic.main.dto.HabitTemplate.HabitTemplateResponse;
import com.dailic.main.dto.PageRequestDto;
import com.dailic.main.service.HabitTemplateService;
import com.dailic.main.util.PagedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class HabitTemplateController {

    private final HabitTemplateService habitTemplateService;

    @GetMapping("/getList")
    public ResponseEntity<PagedResponse<HabitTemplateResponse>> getList(
            @Valid PageRequestDto pageRequest) {

        return ResponseEntity.ok(habitTemplateService.getList(pageRequest));
    }
}
