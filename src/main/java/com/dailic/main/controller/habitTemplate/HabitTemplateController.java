package com.dailic.main.controller.habitTemplate;

import com.dailic.main.dto.habitTemplate.GetHabitTemplateResponse;
import com.dailic.main.dto.PageRequestDto;
import com.dailic.main.service.HabitTemplateService;
import com.dailic.main.dto.PagedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/habit-template")
@RequiredArgsConstructor
public class HabitTemplateController {

    private final HabitTemplateService habitTemplateService;

    @GetMapping("/getHabitTemplatesList")
    public ResponseEntity<PagedResponse<GetHabitTemplateResponse>> getList(
            @Valid PageRequestDto pageRequest) {

        return ResponseEntity.ok(habitTemplateService.getList(pageRequest));
    }
}
