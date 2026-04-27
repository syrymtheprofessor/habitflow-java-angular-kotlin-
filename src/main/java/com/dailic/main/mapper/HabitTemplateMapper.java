package com.dailic.main.mapper;

import com.dailic.main.dto.HabitTemplate.ListHabitTemplate;
import com.dailic.main.model.HabitTemplate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // componentModel = "spring" — чтобы MapStruct сгенерировал Spring bean и его можно было инжектить через @RequiredArgsConstructor
public interface HabitTemplateMapper {

    List<ListHabitTemplate> toDto(List<HabitTemplate> habitTemplates);
}
