package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.OrderDetailTicketEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class SeatStatus {
    private Long id;
    private ShowtimeEntity showtime;
    private LocalTime time;

    private  Boolean status;
    private ChairEntity chair;

    private boolean isOccupied;
    private OrderDetailTicketEntity orderDetailTicketEntities;

}
