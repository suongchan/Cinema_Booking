package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.Order;
import com.example.cinema_booking.entity.OrderEntity;
import com.example.cinema_booking.entity.OrderHistoryEntity;

public interface OrderService {
    OrderEntity getOrderById(Long idOrder);

    Order setOrder(OrderEntity orderEntity);

    OrderHistoryEntity saveOrderHistory(OrderEntity orderEntity);

    void createOrder(OrderEntity orderEntity);

    OrderEntity setStatus(OrderEntity orderEntity);
}
