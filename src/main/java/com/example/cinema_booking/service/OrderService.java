package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.Order;
import com.example.cinema_booking.entity.OrderDetailServiceEntity;
import com.example.cinema_booking.entity.OrderDetailTicketEntity;
import com.example.cinema_booking.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderEntity getOrderById(Long idOrder);

    Order setOrder(OrderEntity orderEntity);



}
