package com.YellowFlash.Library.Manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String title,author;
    @Column(unique = true)
    String isbn;
    @Builder.Default
    private boolean isAvailable = true;
    LocalDate addedAt;

    @PrePersist
    void prePersist(){
        this.addedAt = LocalDate.now();
    }



}
