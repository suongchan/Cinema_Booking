package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.repository.OrderDetailServiceHistoryRepository;
import com.example.cinema_booking.repository.OrderDetailServiceRepository;
import com.example.cinema_booking.service.OrderDetailServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderDetailServiceImpl implements OrderDetailServiceService {

    @Autowired
    private OrderDetailServiceHistoryRepository orderDetailServiceHistoryRepository;

    @Autowired
    private OrderDetailServiceRepository orderDetailServiceRepository;

    @Override
    public List<OrderDetailServiceEntity> getAllByOrderId(OrderEntity orderEntity) {
        return orderDetailServiceRepository.findAllByOrderEntity(orderEntity);
    }

    @Override
    public void saveOrderDetailServiceHistory(OrderEntity orderEntity, OrderHistoryEntity orderHistoryEntity) {
        for (OrderDetailServiceEntity orderDetailServiceEntity : orderEntity.getOrderDetailServiceEntities()){
            OrderDetailServiceHistoryEntity orderDetailServiceHistoryEntity = new OrderDetailServiceHistoryEntity();
            orderDetailServiceHistoryEntity.setOrderHistoryEntity(orderHistoryEntity);
            orderDetailServiceHistoryEntity.setIdOrderDetailService(orderDetailServiceEntity.getIdOrderDetailService());
            orderDetailServiceHistoryEntity.setNameService(orderDetailServiceEntity.getServiceEntity().getNameService());
            orderDetailServiceHistoryEntity.setQuantity(orderDetailServiceEntity.getQuantity());
            orderDetailServiceHistoryEntity.setPriceService(orderDetailServiceEntity.getServiceEntity().getPrice());
            orderDetailServiceHistoryRepository.save(orderDetailServiceHistoryEntity);
        }
    }

}
