package com.company.assignment.concert.domain.entity;

import com.company.assignment.common.domian.entity.BaseEntity;
import com.company.assignment.user.domian.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Seat seat;

    @Column(nullable = false)
    private LocalDateTime reservedAt;
    @Column(nullable = false)
    private LocalDateTime canceledAt;

    @Column(nullable = false)
    private boolean active;
}
