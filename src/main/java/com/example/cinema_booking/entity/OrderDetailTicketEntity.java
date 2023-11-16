package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OrderDetailTicket")
@Getter
@Setter
public class OrderDetailTicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderDetailTicket;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private OrderEntity orderEntity;

    @OneToOne
    @JoinColumn(name = "idSeat")
    private SeatStatusEntity seatStatusEntity;

    @ManyToOne
    @JoinColumn(name = "idShowtime")
    private ShowtimeEntity showtimeEntity;
}
