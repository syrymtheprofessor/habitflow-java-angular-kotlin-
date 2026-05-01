package com.dailic.main.controller.habitTemplate;

import com.dailic.main.dto.habitTemplate.GetHabitTemplateResponse;
import com.dailic.main.dto.PageRequestDto;
import com.dailic.main.service.HabitTemplateService;
import com.dailic.main.util.PagedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class HabitTemplateController {

    private final HabitTemplateService habitTemplateService;

    @GetMapping("/getList")
    public ResponseEntity<PagedResponse<GetHabitTemplateResponse>> getList(
            @Valid PageRequestDto pageRequest) {

        return ResponseEntity.ok(habitTemplateService.getList(pageRequest));
    }
}
