package br.com.fiap.myday.service;

import br.com.fiap.myday.entity.Habit;
import br.com.fiap.myday.entity.User;
import br.com.fiap.myday.repository.HabitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepo repo;

    public List<Habit> getByUser(User user) {
        return repo.findByUser(user);
    }

    public Habit save(Habit habit) {
        return repo.save(habit);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
