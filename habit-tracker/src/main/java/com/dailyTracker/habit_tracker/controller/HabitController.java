package com.dailyTracker.habit_tracker.controller;

import com.dailyTracker.habit_tracker.model.Habit;
import com.dailyTracker.habit_tracker.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/habits")
public class HabitController {
    private final HabitService habitService;

    @GetMapping
    private ResponseEntity<List<Habit>> getAllHabits() {
    return ResponseEntity.ok(habitService.findAll());
    }
    @PostMapping
    private ResponseEntity<Habit> createNewHabit(@RequestBody Habit habit){
        return ResponseEntity.status(201).body(habitService.save(habit));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteHabit(@PathVariable Long id){
         habitService.deleteById(id);
         return ResponseEntity.noContent().build();
    }
}
