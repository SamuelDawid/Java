package com.dailyTracker.habit_tracker.controller;

import com.dailyTracker.habit_tracker.dto.DailyLogRequest;
import com.dailyTracker.habit_tracker.model.DailyLog;
import com.dailyTracker.habit_tracker.model.HabitEntry;
import com.dailyTracker.habit_tracker.service.DailyLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class DailyLogController {
    private final DailyLogService dailyLogService;
    @GetMapping("/{date}/entries")
    public ResponseEntity<List<HabitEntry>> getEntries(@PathVariable LocalDate date) {
        return ResponseEntity.ok(dailyLogService.getEntriesForDate(1L, date));
    }
    @GetMapping("/{date}")
    private ResponseEntity<DailyLog> getDailyLog(@PathVariable LocalDate date){
        return ResponseEntity.ok(dailyLogService.getOrCreateLog(1L,date));

    }
    @PostMapping("/{date}")
    public ResponseEntity<Void> submitDailyHabits(@PathVariable LocalDate date, @RequestBody DailyLogRequest request){
         dailyLogService.submitHabits(1L,date,request.getHabitCompletions());
         return ResponseEntity.noContent().build();
    }
}
