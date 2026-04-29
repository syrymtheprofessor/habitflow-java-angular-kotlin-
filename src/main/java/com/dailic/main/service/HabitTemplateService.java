package com.dailic.main.service;

import com.dailic.main.dto.habits.HabitTemplateResponse;
import com.dailic.main.dto.PageRequestDto;
import com.dailic.main.mapper.HabitTemplateMapper;
import com.dailic.main.model.HabitTemplate;
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

    public PagedResponse<HabitTemplateResponse> getList(final PageRequestDto pageRequest) {
        Page<HabitTemplate> pageResult = habitTemplateRepository.findAll(pageRequest.toPageable());
        Page<HabitTemplateResponse> results = pageResult.map(habitTemplateMapper::toDto);
        return PagedResponse.of(results);
    }

}
