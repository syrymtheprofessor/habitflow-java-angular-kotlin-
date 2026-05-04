package com.dailic.main.controller.habitTemplate;

import com.dailic.main.model.Category;
import com.dailic.main.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/getCategoriesList")
    public ResponseEntity<List<Category>> getList() {
        return ResponseEntity.ok(categoryService.getList());
    }
}
