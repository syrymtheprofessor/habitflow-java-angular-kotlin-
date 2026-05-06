package com.dailic.main.service;

import com.dailic.main.dto.habitLog.HabitLogResponse;
import com.dailic.main.mapper.HabitLogMapper;
import com.dailic.main.model.HabitLog;
import com.dailic.main.repository.HabitLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitLogMapper habitLogMapper;

    public Map<String, List<HabitLogResponse>> getYearLogs(UUID userId, int year) {
        LocalDate from = LocalDate.of(year, 1, 1);
        LocalDate to = LocalDate.of(year, 12, 31);

        List<HabitLog> logs = habitLogRepository
                .findByUserHabitUserIdAndDateBetween(userId, from, to);

        return logs.stream()
                .collect(Collectors.groupingBy(
                        log -> log.getDate().toString(),
                        Collectors.mapping(habitLogMapper::toDto, Collectors.toList())
                ));
    }
}
