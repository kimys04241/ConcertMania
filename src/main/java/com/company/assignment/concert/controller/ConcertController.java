package com.company.assignment.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concert")
public class ConcertController {

    @PostMapping
    public ResponseEntity<?> concert() {

        return ResponseEntity.ok(null);
    }
}
