package br.com.fiap.myday.controller;

import br.com.fiap.myday.entity.Habit;
import br.com.fiap.myday.entity.User;
import br.com.fiap.myday.repository.HabitRepo;
import br.com.fiap.myday.repository.HabitRecordRepo;
import br.com.fiap.myday.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitRepo habitRepo;
    private final UserRepo userRepo;
    private final HabitRecordRepo recordRepo;

    @GetMapping
    public List<Habit> getAll(Authentication auth) {
        User user = userRepo.findByEmail(auth.getName()).orElseThrow();
        List<Habit> habits = habitRepo.findByUser(user);
        LocalDate today = LocalDate.now();
        for (Habit h : habits) {
            boolean done = recordRepo.findByHabitAndRecordDate(h, today).isPresent();
            h.setCompletedToday(done);
        }
        return habits;
    }

    @PostMapping
    public Habit create(@RequestBody Habit habit, Authentication auth) {
        User user = userRepo.findByEmail(auth.getName()).orElseThrow();
        habit.setUser(user);
        return habitRepo.save(habit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Habit updatedHabit, Authentication auth) {
        User user = userRepo.findByEmail(auth.getName()).orElseThrow();
        Habit habit = habitRepo.findById(id).orElseThrow();
        if (!habit.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).body("Não autorizado");
        }
        habit.setName(updatedHabit.getName());
        habit.setDescription(updatedHabit.getDescription());
        return ResponseEntity.ok(habitRepo.save(habit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) {
        User user = userRepo.findByEmail(auth.getName()).orElseThrow();
        Habit habit = habitRepo.findById(id).orElseThrow();
        if (!habit.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).body("Não autorizado");
        }
        habitRepo.delete(habit);
        return ResponseEntity.ok().build();
    }
}
