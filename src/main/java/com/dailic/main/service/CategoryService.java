package com.dailic.main.service;

import com.dailic.main.dto.habitTemplate.PostCategoryRequest;
import com.dailic.main.model.Category;
import com.dailic.main.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getList() {
        return categoryRepository.findAll();
    }

    public void addCategory(final PostCategoryRequest postCategoryRequest) {
        if (categoryRepository.existsByName(postCategoryRequest.getName()))
            throw new RuntimeException("Category already exists");

        var category = new Category()
                .setName(postCategoryRequest.getName())
                .setDescription(postCategoryRequest.getDescription());

        categoryRepository.save(category);
    }

}
