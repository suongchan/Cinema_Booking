package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.OrderDetailServiceEntity;
import com.example.cinema_booking.entity.OrderEntity;
import com.example.cinema_booking.repository.OrderDetailServiceRepository;
import com.example.cinema_booking.service.OrderDetailServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderDetailServiceImpl implements OrderDetailServiceService {
    @Autowired
    private OrderDetailServiceRepository orderDetailServiceRepository;

    @Override
    public List<OrderDetailServiceEntity> getAllByOrderId(OrderEntity orderEntity) {
        return orderDetailServiceRepository.findAllByOrderEntity(orderEntity);
    }
}
