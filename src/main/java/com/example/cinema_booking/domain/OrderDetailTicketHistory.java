package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.OrderHistoryEntity;
import com.example.cinema_booking.entity.SeatStatusEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetailTicketHistory {
    private Long idOrderDetailTicket;
    private OrderHistoryEntity orderHistoryEntity ;
    private SeatStatusEntity seatStatusEntity;
    private ShowtimeEntity showtimeEntity;
}
