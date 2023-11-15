package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "OrderDetailTicketHistory")
@Getter
@Setter

public class OrderDetailTicketHistoryEntity {
    @Id
    private Long idOrderDetailTicket;
    private String cinema;
    private String cinemaRoom;
    private String film;
    private LocalTime timeStart ;
    private LocalDate date;
    private Long priceTicket;
    private String nameSeat;


    @ManyToOne
    @JoinColumn(name = "idOrderHistory")
    private OrderHistoryEntity orderHistoryEntity ;



}
