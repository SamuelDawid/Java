package com.dailyTracker.habit_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long totalXp;
    @Column(unique = true,nullable = false)
    private String userName,email;
    @JsonIgnore
    private String passwordHash;
    private Double startWeight,targetWeight;
    @CreationTimestamp
    private LocalDateTime createdAt;


}
