package com.dailic.main.service;

import com.dailic.main.dto.PageRequestDto;
import com.dailic.main.dto.personal.UserHabitResponse;
import com.dailic.main.mapper.UserHabitMapper;
import com.dailic.main.model.UserHabit;
import com.dailic.main.repository.UserHabitRepository;
import com.dailic.main.util.PagedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserHabitService {

    private final UserHabitRepository userHabitRepository;
    private final UserHabitMapper userHabitMapper;

    public PagedResponse<UserHabitResponse> getList(@Valid PageRequestDto pageRequest,
                                                    UUID userId) {
        Page<UserHabit> userHabits = userHabitRepository.findByUserId(userId, pageRequest.toPageable());
        Page<UserHabitResponse> results = userHabits.map(userHabitMapper::toDto);
        return PagedResponse.of(results);
    }

    public void addHabit(UUID templateId) {

    }
}
