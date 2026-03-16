package com.dailyTracker.habit_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long totalXp;
    @Column(unique = true,nullable = false)
    private String userName,email;
    private String passwordHash;
    private Double startWeight,targetWeight;
    private LocalDateTime createdAt;


}
