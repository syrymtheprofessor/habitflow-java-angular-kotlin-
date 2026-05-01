package com.dailic.main.mapper;

import com.dailic.main.dto.habitTemplate.GetHabitTemplateResponse;
import com.dailic.main.model.HabitTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // componentModel = "spring" — чтобы MapStruct сгенерировал Spring bean и его можно было инжектить через @RequiredArgsConstructor
public interface HabitTemplateMapper {

    @Mapping(source = "category.name", target = "categoryName")
    GetHabitTemplateResponse toDto(HabitTemplate habitTemplate);
}
