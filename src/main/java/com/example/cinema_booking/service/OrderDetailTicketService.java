package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.OrderDetailTicketEntity;
import com.example.cinema_booking.entity.OrderDetailTicketHistoryEntity;
import com.example.cinema_booking.entity.OrderEntity;
import com.example.cinema_booking.entity.OrderHistoryEntity;

import java.util.List;

public interface OrderDetailTicketService {
    List<OrderDetailTicketEntity> getAllByOrderId(OrderEntity orderEntity);

    void saveOrderDetailTicketHistory(OrderEntity orderEntity, OrderHistoryEntity orderHistoryEntity);
}
