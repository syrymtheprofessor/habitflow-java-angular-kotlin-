package com.dailic.main.repository;

import com.dailic.main.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog, UUID> {

    List<HabitLog> findByUserHabitUserIdAndDateBetween(
            UUID userId,
            LocalDate dateFrom,
            LocalDate dateTo
    );

    List<HabitLog> findByUserHabitIdOrderByDateDesc(UUID userHabitId);

}