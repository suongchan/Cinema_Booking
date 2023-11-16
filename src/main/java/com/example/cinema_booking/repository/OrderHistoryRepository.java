package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long> {
//    List<OrderHistoryEntity> findAllByCustomerId(Long id);
//    List<OrderHistoryEntity> findOrderDetailHistoryEntitiesById(Long id);
}
