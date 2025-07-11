package com.company.assignment.concert.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SeatGrade {

    @Id
    @GeneratedValue
    private Long id;

    private String name;    // 예: VIP, R, S
    private int price;

    @OneToMany(mappedBy = "grade")
    private List<Seat> seats = new ArrayList<>();
}
