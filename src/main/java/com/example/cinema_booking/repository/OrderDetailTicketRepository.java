package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.OrderDetailTicketEntity;
import com.example.cinema_booking.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailTicketRepository extends JpaRepository<OrderDetailTicketEntity, Long> {

    List<OrderDetailTicketEntity> findAllByOrderEntity(OrderEntity orderEntity);

}
