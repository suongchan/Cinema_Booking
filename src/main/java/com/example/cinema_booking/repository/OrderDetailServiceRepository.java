package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.OrderDetailServiceEntity;
import com.example.cinema_booking.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailServiceRepository extends JpaRepository<OrderDetailServiceEntity, Long> {
    List<OrderDetailServiceEntity> findAllByOrderEntity(OrderEntity orderEntity);
}
