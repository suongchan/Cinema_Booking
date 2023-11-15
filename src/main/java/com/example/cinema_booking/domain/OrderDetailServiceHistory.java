package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.OrderHistoryEntity;
import com.example.cinema_booking.entity.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetailServiceHistory {
    private Long idOrderDetailService;
    private OrderHistoryEntity orderHistoryEntity;
    private ServiceEntity serviceEntity;
    private int quantity;
}
