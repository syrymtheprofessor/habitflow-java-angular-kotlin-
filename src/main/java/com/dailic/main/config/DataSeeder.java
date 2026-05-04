package com.dailic.main.config;

import com.dailic.main.model.Category;
import com.dailic.main.model.HabitTemplate;
import com.dailic.main.repository.CategoryRepository;
import com.dailic.main.repository.HabitTemplateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "habit-tracker.seed.enabled", havingValue = "true")
public class DataSeeder implements ApplicationRunner {

    private final CategoryRepository categoryRepository;
    private final HabitTemplateRepository habitTemplateRepository;
    private final ObjectMapper objectMapper;

    private static Map<String, UUID> categoryNameToIdMap = new HashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (categoryRepository.count() > 0) {
            log.info("Data already seeded, skipping.");
            return;
        }

        seedCategories();
        seedHabitTemplates();

        log.info("Seed data loaded successfully.");
    }

    private void seedCategories() throws Exception {
        var resource = new ClassPathResource("seed/categories.json");
        List<Category> categories = objectMapper.readValue(
                resource.getInputStream(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Category.class)
        );

        List<Category> savedCategories = categoryRepository.saveAll(categories);

        categoryNameToIdMap = savedCategories.stream()
                .collect(Collectors.toMap(Category::getName, Category::getId));

        log.info("Seeded {} categories", savedCategories.size());
    }

    private void seedHabitTemplates() throws Exception {
        var resource = new ClassPathResource("seed/habit_templates.json");
        List<HabitTemplateSeedDto> dtos = objectMapper.readValue(
                resource.getInputStream(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, HabitTemplateSeedDto.class)
        );

        List<HabitTemplate> templates = dtos.stream().map(dto -> {
            UUID categoryId = categoryNameToIdMap.get(dto.getCategoryName());
            if (categoryId == null) throw new RuntimeException("Category not found: " + dto.getCategoryName());

            var category = categoryRepository.findById(categoryId).orElseThrow();
            return HabitTemplate.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .sourceUrl(dto.getSourceUrl())
                    .category(category)
                    .build();
        }).toList();

        habitTemplateRepository.saveAll(templates);
        log.info("Seeded {} habit templates", templates.size());
    }

    @Getter
    public static class HabitTemplateSeedDto {
        private String name;
        private String description;
        private String sourceUrl;
        private String categoryName;

        public UUID getCategoryIdByName(String name) {
            return categoryNameToIdMap.get(name);
        }
    }
}
