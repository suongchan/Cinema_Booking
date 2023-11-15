package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.OrderDetailServiceEntity;
import com.example.cinema_booking.entity.OrderDetailServiceHistoryEntity;
import com.example.cinema_booking.entity.OrderEntity;
import com.example.cinema_booking.entity.OrderHistoryEntity;
import com.example.cinema_booking.repository.OrderDetailServiceRepository;

import java.util.List;

public interface OrderDetailServiceService {
    List<OrderDetailServiceEntity> getAllByOrderId(OrderEntity orderEntity);

    void saveOrderDetailServiceHistory(OrderEntity orderEntity, OrderHistoryEntity orderHistoryEntity);
}
