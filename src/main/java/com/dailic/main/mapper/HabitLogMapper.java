package com.dailic.main.mapper;

import com.dailic.main.dto.habitLog.HabitLogResponse;
import com.dailic.main.model.HabitLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HabitLogMapper {

    @Mapping(source = "userHabit.template.name", target = "habitName")
    HabitLogResponse toDto(HabitLog habitTemplate);
}
