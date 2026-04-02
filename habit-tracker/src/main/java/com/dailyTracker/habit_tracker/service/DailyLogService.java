package com.dailyTracker.habit_tracker.service;

import com.dailyTracker.habit_tracker.model.DailyLog;
import com.dailyTracker.habit_tracker.model.Habit;
import com.dailyTracker.habit_tracker.model.HabitEntry;
import com.dailyTracker.habit_tracker.model.User;
import com.dailyTracker.habit_tracker.repository.DailyLogRepository;
import com.dailyTracker.habit_tracker.repository.HabitEntryRepository;
import com.dailyTracker.habit_tracker.repository.HabitRepository;
import com.dailyTracker.habit_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DailyLogService {
    private final DailyLogRepository dailyLogRepository;
    private final HabitEntryRepository habitEntryRepository;
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public DailyLog getOrCreateLog(Long userId, LocalDate date) {
        return dailyLogRepository.findByUserIdAndLogDate(userId, date)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
                    DailyLog log = DailyLog.builder()
                            .user(user)
                            .logDate(date)
                            .isSunday(date.getDayOfWeek().getValue() == 7)
                            .isDeloadWeek(false).build();

                    return dailyLogRepository.save(log);
                });
    }

    public void submitHabits(Long userId, LocalDate date, Map<Long, Boolean> completions) {
        DailyLog log = getOrCreateLog(userId, date);
        completions.forEach((habitId, completed) -> {
            Habit habit = habitRepository.findById(habitId).orElseThrow();

            HabitEntry entry = habitEntryRepository
                    .findByDailyLog_IdAndHabit_Id(log.getId(), habitId)
                    .orElse(HabitEntry.builder()
                            .dailyLog(log)
                            .habit(habit)
                            .build());

            entry.setCompleted(completed);
            entry.setXpEarned(completed ? habit.getXpReward() : -habit.getXpPenalty());
            habitEntryRepository.save(entry);
        });
    }

    public List<HabitEntry> getEntriesForDate(Long userId, LocalDate date) {
        return dailyLogRepository.findByUserIdAndLogDate(userId, date)
                .map(log -> habitEntryRepository.findByDailyLogId(log.getId()))
                .orElse(List.of());
    }
    public DailyLog updateWeight(Long userId,LocalDate date, Double weightKg){
        DailyLog updateLog = getOrCreateLog(userId,date);
        updateLog.setWeightKg(weightKg);
        return dailyLogRepository.save(updateLog);

    }
}
