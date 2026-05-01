package com.dailic.main.service;

import com.dailic.main.dto.habitTemplate.GetHabitTemplateResponse;
import com.dailic.main.dto.PageRequestDto;
import com.dailic.main.dto.habitTemplate.PostHabitTemplateRequest;
import com.dailic.main.mapper.HabitTemplateMapper;
import com.dailic.main.model.HabitTemplate;
import com.dailic.main.repository.CategoryRepository;
import com.dailic.main.repository.HabitTemplateRepository;
import com.dailic.main.util.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitTemplateService {

    private final HabitTemplateRepository habitTemplateRepository;
    private final HabitTemplateMapper habitTemplateMapper;
    private final CategoryRepository categoryRepository;

    public PagedResponse<GetHabitTemplateResponse> getList(final PageRequestDto pageRequest) {
        Page<HabitTemplate> pageResult = habitTemplateRepository.findAll(pageRequest.toPageable());
        Page<GetHabitTemplateResponse> results = pageResult.map(habitTemplateMapper::toDto);
        return PagedResponse.of(results);
    }

    public void addHabitTemplate(final PostHabitTemplateRequest postHabitTemplateRequest) {
        var category = categoryRepository.findById(postHabitTemplateRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("habitTemplate not found"));

        HabitTemplate habitTemplate = HabitTemplate.builder()
                .name(postHabitTemplateRequest.getName())
                .description(postHabitTemplateRequest.getDescription())
                .category(category)
                .sourceUrl(postHabitTemplateRequest.getSourceUrl())
                .build();

        habitTemplateRepository.save(habitTemplate);
    }
}
