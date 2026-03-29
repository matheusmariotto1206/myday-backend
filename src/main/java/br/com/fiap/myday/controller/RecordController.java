package br.com.fiap.myday.controller;

import br.com.fiap.myday.entity.Habit;
import br.com.fiap.myday.entity.HabitRecord;
import br.com.fiap.myday.entity.User;
import br.com.fiap.myday.repository.HabitRecordRepo;
import br.com.fiap.myday.repository.HabitRepo;
import br.com.fiap.myday.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

    private final HabitRecordRepo recordRepo;
    private final HabitRepo habitRepo;
    private final UserRepo userRepo;

    @PostMapping("/{habitId}")
    public ResponseEntity<?> toggleCheckIn(@PathVariable Long habitId, Authentication auth) {
        User user = userRepo.findByEmail(auth.getName()).orElseThrow();
        Habit habit = habitRepo.findById(habitId).orElseThrow();

        if (!habit.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).body("Hábito não pertence ao usuário");
        }

        LocalDate today = LocalDate.now();
        Optional<HabitRecord> existing = recordRepo.findByHabitAndRecordDate(habit, today);

        if (existing.isPresent()) {
            recordRepo.delete(existing.get());
            return ResponseEntity.ok("desmarcado");
        } else {
            HabitRecord record = new HabitRecord();
            record.setHabit(habit);
            record.setRecordDate(today);
            recordRepo.save(record);
            return ResponseEntity.ok("marcado");
        }
    }
}
