package com.dailyTracker.habit_tracker.controller;

import com.dailyTracker.habit_tracker.dto.DailyLogRequest;
import com.dailyTracker.habit_tracker.model.DailyLog;
import com.dailyTracker.habit_tracker.model.HabitEntry;
import com.dailyTracker.habit_tracker.service.DailyLogService;
import com.dailyTracker.habit_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static com.dailyTracker.habit_tracker.Constants.BEARER_PREFIX_LENGTH;
@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class DailyLogController {
    private final DailyLogService dailyLogService;
    private final UserService userService;

    @GetMapping("/{date}/entries")
    public ResponseEntity<List<HabitEntry>> getEntries(@PathVariable LocalDate date,@RequestHeader("Authorization") String authHeader) throws Exception{
            Long userId = userService.getUserIdFromToken(authHeader.substring(BEARER_PREFIX_LENGTH)).orElseThrow(() -> new Exception("Invalid Token"));
            return ResponseEntity.ok(dailyLogService.getEntriesForDate(userId, date));

    }

    @GetMapping("/{date}")
    private ResponseEntity<DailyLog> getDailyLog(@PathVariable LocalDate date,@RequestHeader("Authorization") String authHeader) throws Exception {
            Long userId = userService.getUserIdFromToken(authHeader.substring(BEARER_PREFIX_LENGTH)).orElseThrow(() -> new Exception("Invalid Token"));
            return ResponseEntity.ok(dailyLogService.getOrCreateLog(userId, date));


    }

    @PostMapping("/{date}")
    public ResponseEntity<Void> submitDailyHabits(@PathVariable LocalDate date, @RequestBody DailyLogRequest request, @RequestHeader("Authorization") String authHeader) throws Exception {
        Long userId = userService.getUserIdFromToken(authHeader.substring(BEARER_PREFIX_LENGTH)).orElseThrow(() -> new Exception("Invalid Token"));
        dailyLogService.submitHabits(userId, date, request.getHabitCompletions());
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{date}/weight")
    public ResponseEntity<Void> saveWeight(@RequestBody Map<String, Double> weightMap, @PathVariable LocalDate date,@RequestHeader("Authorization") String authHeader) throws Exception{
            Long userId = userService.getUserIdFromToken(authHeader.substring(BEARER_PREFIX_LENGTH)).orElseThrow(() -> new Exception("Invalid Token"));
            dailyLogService.updateWeight(userId, date, weightMap.get("weightKg"));
            return ResponseEntity.noContent().build();

    }
}
