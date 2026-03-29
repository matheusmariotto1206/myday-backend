package br.com.fiap.myday.repository;

import br.com.fiap.myday.entity.Habit;
import br.com.fiap.myday.entity.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface HabitRecordRepo extends JpaRepository<HabitRecord, Long> {
    Optional<HabitRecord> findByHabitAndRecordDate(Habit habit, LocalDate recordDate);
}
