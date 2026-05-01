package com.dailic.main.mapper;

import com.dailic.main.dto.userHabit.UserHabitResponse;
import com.dailic.main.model.UserHabit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {HabitTemplateMapper.class}) // componentModel = "spring" — чтобы MapStruct сгенерировал Spring bean и его можно было инжектить через @RequiredArgsConstructor.
public interface UserHabitMapper {

    @Mapping(source = "template", target = "habitTemplate")
    UserHabitResponse toDto(UserHabit userHabit);
}
