package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.OrderDetailTicketEntity;
import com.example.cinema_booking.entity.OrderEntity;
import com.example.cinema_booking.repository.OrderDetailTicketRepository;
import com.example.cinema_booking.service.OrderDetailTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderDetailTicketImpl implements OrderDetailTicketService {
    @Autowired
    private OrderDetailTicketRepository orderDetailTicketRepository;
    @Override
    public List<OrderDetailTicketEntity> getAllByOrderId(OrderEntity orderEntity) {
        return orderDetailTicketRepository.findAllByOrderEntity(orderEntity);
    }
}
