package com.company.assignment.concert.domain.entity;

import com.company.assignment.common.domian.entity.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SeatGrade extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "grade")
    private List<Seat> seats = new ArrayList<>();
}
