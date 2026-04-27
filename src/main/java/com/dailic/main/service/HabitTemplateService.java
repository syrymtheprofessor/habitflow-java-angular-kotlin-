package com.dailic.main.service;

import com.dailic.main.dto.HabitTemplate.ListHabitTemplate;
import com.dailic.main.mapper.HabitTemplateMapper;
import com.dailic.main.repository.HabitTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitTemplateService {

    private final HabitTemplateRepository habitTemplateRepository;
    private final HabitTemplateMapper habitTemplateMapper;

    public PagedResponse<ListHabitTemplate> getList(){
        var list = habitTemplateRepository.findAll();
        var result = habitTemplateMapper.toDto(list);

        return result;
    }

}
