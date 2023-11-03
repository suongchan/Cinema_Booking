package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.Order;
import com.example.cinema_booking.entity.OrderEntity;

public interface OrderService {
    OrderEntity getOrderById(Long idOrder);

    Order setOrder(OrderEntity orderEntity);

    void createOrder(OrderEntity orderEntity);

}
