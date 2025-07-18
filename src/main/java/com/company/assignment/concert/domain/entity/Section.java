package com.company.assignment.concert.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Section {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Concert concert;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();
}
