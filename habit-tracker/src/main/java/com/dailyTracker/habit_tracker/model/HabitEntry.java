package com.dailyTracker.habit_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitEntry {
    @ManyToOne
    @JoinColumn(name = "daily_log_id")
    private DailyLog dailyLog;
    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean completed;
    private Integer xpEarned;
}
