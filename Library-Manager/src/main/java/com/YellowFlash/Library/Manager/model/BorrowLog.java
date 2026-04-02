package com.YellowFlash.Library.Manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "borrow_logs")
public class BorrowLog {
    @Id
    @GeneratedValue
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    private BorrowAction action;
    public enum BorrowAction {Borrowed,Returned}
    LocalDateTime timeStamp;

    @PrePersist
    void prePersist(){
        this.timeStamp = LocalDateTime.now();
    }
}
