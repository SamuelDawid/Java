package com.dailyTracker.habit_tracker.repository;

import com.dailyTracker.habit_tracker.model.HabitEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitEntryRepository extends JpaRepository<HabitEntry, Long> {
    List<HabitEntry> findByDailyLogId(Long dailyLogId);
}
