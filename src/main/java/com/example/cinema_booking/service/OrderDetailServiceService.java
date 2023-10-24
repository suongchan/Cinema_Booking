package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.OrderDetailServiceEntity;
import com.example.cinema_booking.entity.OrderEntity;

import java.util.List;

public interface OrderDetailServiceService {
    List<OrderDetailServiceEntity> getAllByOrderId(OrderEntity orderEntity);
}
