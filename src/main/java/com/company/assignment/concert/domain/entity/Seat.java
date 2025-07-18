package com.company.assignment.concert.domain.entity;

import com.company.assignment.common.domian.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_grade_id")
    private SeatGrade grade;

    @Column(nullable = false)
    private int rowNumber;
    @Column(nullable = false)
    private int colNumber;
    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private boolean reserved = false;

    @Version
    private int version;
}
