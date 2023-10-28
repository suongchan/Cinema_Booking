package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "orderDetailTicketEntity", cascade = CascadeType.ALL)
    private List<SeatStatusEntity> seatStatusEntities;

    @ManyToOne
    @JoinColumn(name = "idShowtime")
    private ShowtimeEntity showtimeEntity;
}
