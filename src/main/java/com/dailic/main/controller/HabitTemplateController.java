package com.dailic.main.controller;

import com.dailic.main.dto.HabitTemplate.ListHabitTemplate;
import com.dailic.main.service.HabitTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/")
@RequiredArgsConstructor
public class HabitTemplateController {

    private final HabitTemplateService habitTemplateService;

    @GetMapping("/getList")
    private ResponseEntity<PagedResponse<ListHabitTemplate>> getList(){
        var result = habitTemplateService.getList();
        return ResponseEntity.ok(result);
    }
}
