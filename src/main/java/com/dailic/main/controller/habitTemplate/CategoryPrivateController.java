package com.dailic.main.controller.habitTemplate;

import com.dailic.main.dto.habitTemplate.PostCategoryRequest;
import com.dailic.main.service.CategoryService;
import com.dailic.main.util.CustomUserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
@RequiredArgsConstructor
public class CategoryPrivateController {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<Void> addCategory(
            @Valid PostCategoryRequest postCategoryRequest,
            @AuthenticationPrincipal CustomUserPrincipal userPrincipal) {

        if (!userPrincipal.getRoles().contains("moderator"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        categoryService.addCategory(postCategoryRequest);
        return ResponseEntity.ok().build();
    }
}