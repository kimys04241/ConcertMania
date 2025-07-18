package com.company.assignment.concert.domain.entity;

import com.company.assignment.common.domian.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Concert extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private LocalDateTime location;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String seatMapJson;

    @Column(nullable = false)
    private LocalDateTime reserveStartAt;

    @Column(nullable = false)
    private LocalDateTime reserveEndAt;

    @Column(nullable = false)
    private LocalDateTime concertAt;
}
