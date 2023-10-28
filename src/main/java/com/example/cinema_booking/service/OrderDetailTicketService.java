package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.OrderDetailServiceEntity;
import com.example.cinema_booking.entity.OrderDetailTicketEntity;
import com.example.cinema_booking.entity.OrderEntity;

import java.util.List;

public interface OrderDetailTicketService {
    List<OrderDetailTicketEntity> getAllByOrderId(OrderEntity orderEntity);



}
