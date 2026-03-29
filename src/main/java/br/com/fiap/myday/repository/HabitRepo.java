package br.com.fiap.myday.repository;

import br.com.fiap.myday.entity.Habit;
import br.com.fiap.myday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepo extends JpaRepository<Habit, Long> {
    List<Habit> findByUser(User user);
}
