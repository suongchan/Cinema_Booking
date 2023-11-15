package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.OrderDetailServiceHistoryEntity;
import com.example.cinema_booking.entity.OrderDetailTicketHistoryEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderHistory {
    private Long idOrder;
    private LocalDateTime orderDateTime;
    private Long amount;
    private Long totalPrice;
    private Integer point;
    private ShowtimeEntity showtimeEntity;
    private CustomerEntity customerEntity;
    private List<OrderDetailTicketHistoryEntity> orderDetailTicketHistoryEntities;
    private List<OrderDetailServiceHistoryEntity> orderDetailServiceHistoryEntities;

}
