package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
public class SeatStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private ShowtimeEntity showtime;

    @ManyToOne
    @JoinColumn(name = "chair_id")
    private ChairEntity chair;

    private boolean isOccupied;

    private LocalTime time;

    private  Boolean status;

    @OneToOne(mappedBy = "seatStatusEntity", cascade = CascadeType.ALL)
    private OrderDetailTicketEntity orderDetailTicketEntities;

    public void setIdSeatStatus(Long seatStatusId) {
    }
}
