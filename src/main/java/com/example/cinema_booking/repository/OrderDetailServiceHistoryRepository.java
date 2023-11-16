package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.OrderDetailServiceHistoryEntity;
import com.example.cinema_booking.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailServiceHistoryRepository extends JpaRepository<OrderDetailServiceHistoryEntity, Long> {
    List<OrderDetailServiceHistoryEntity> findAllByOrderHistoryEntity(OrderHistoryEntity orderHistoryEntity);

}
