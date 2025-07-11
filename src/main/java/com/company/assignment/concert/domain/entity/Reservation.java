package com.company.assignment.concert.domain.entity;

import com.company.assignment.user.domian.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Seat seat;

    private LocalDateTime reservedAt;
    private LocalDateTime canceledAt;

    private boolean active;
}
