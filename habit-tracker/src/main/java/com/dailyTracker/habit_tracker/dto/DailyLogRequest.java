package com.dailyTracker.habit_tracker.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DailyLogRequest {
    private Map<Long, Boolean> habitCompletions;
    private Double weightKg;
    private String notes;
}
