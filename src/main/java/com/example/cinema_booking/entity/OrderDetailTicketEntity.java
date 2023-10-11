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

    @ManyToOne
    @JoinColumn(name= "idShowTime")
    private ShowtimeEntity showtimeEntity;

    @ManyToOne
    @JoinColumn(name = "idChair")
    private ChairEntity chairEntity;

    private int quantity;
}
