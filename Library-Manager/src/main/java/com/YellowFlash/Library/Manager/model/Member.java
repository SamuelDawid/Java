package com.YellowFlash.Library.Manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String firstName;

    @Column(nullable = false)
    @NonNull
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;
    private String phoneNumber;
    private LocalDate memberSince;

    @Builder.Default
    private boolean isActive = true;

    @PrePersist
    void prePersist(){
        this.memberSince = LocalDate.now();
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
