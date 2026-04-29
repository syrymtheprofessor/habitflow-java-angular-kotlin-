package com.dailic.main.repository;

import com.dailic.main.model.UserHabit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserHabitRepository extends JpaRepository<UserHabit, UUID> {

    Page<UserHabit> findByUserId(UUID userId, Pageable pageable); // Spring Data видит user (поле в UserHabit) → .id (поле в User). Чисто и понятно

}
