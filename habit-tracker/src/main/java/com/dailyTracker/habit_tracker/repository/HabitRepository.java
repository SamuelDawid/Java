package com.dailyTracker.habit_tracker.repository;

import com.dailyTracker.habit_tracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit,Long> {
    List<Habit> findByIsCore(boolean isCore);
}
