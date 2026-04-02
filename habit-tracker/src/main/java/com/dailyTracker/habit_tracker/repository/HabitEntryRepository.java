package com.dailyTracker.habit_tracker.repository;

import com.dailyTracker.habit_tracker.model.HabitEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabitEntryRepository extends JpaRepository<HabitEntry, Long> {
    List<HabitEntry> findByDailyLogId(Long dailyLogId);
    Optional<HabitEntry> findByDailyLog_IdAndHabit_Id(Long logId, Long habitId);
    List<HabitEntry> findByDailyLog_UserId (Long userId);
}
