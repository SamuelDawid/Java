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
@Table(name = "habit")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name,description;
    private Integer xpReward,xpPenalty;
    private Boolean isCore,isSundayRest,isDeloadRest,isInverted;

}
