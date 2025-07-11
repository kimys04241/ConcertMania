package com.company.assignment.concert.domain.entity;

import com.company.assignment.user.domian.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Seat {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_grade_id")
    private SeatGrade grade;

    private int row;
    private int col;
    private String seatNumber;

    @Column(nullable = false)
    private boolean reserved = false;

    @Column(nullable = false)
    private LocalDateTime registryAt;
}
