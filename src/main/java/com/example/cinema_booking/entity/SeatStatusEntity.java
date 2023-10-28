package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "idOrderDetailTicket")
    private OrderDetailTicketEntity orderDetailTicketEntity;

    public void setIdSeatStatus(Long seatStatusId) {
    }
}
