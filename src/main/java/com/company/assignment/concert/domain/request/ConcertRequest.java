package com.company.assignment.concert.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ConcertRequest {

    private String title;
    private String location;
    private LocalDateTime concertDateTime;

    @Getter
    @Setter
    public static class SectionDto {
        private String section;
        private String row;
        private String col;
    }

    public static class SeatDto {
        private String seatNumber;
        private String seatGrade;
    }
}
