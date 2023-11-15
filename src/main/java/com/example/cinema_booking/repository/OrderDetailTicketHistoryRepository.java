package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.OrderDetailTicketHistoryEntity;
import com.example.cinema_booking.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailTicketHistoryRepository extends JpaRepository<OrderDetailTicketHistoryEntity, Long> {
    List<OrderDetailTicketHistoryEntity> findAllByOrderHistoryEntity(OrderHistoryEntity orderHistoryEntity);
}
