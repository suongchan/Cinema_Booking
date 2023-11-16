package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "OrderHistory")
@Getter
@Setter
public class OrderHistoryEntity {

    @Id
    private Long id;
    private LocalDateTime orderDateTime;
    private Long amount;
    private Long totalPrice;
    private Integer point;
    private Long customerId;

    private Long idShowtime;


  private Long idOrder;

    @OneToMany(mappedBy = "orderHistoryEntity", cascade = CascadeType.ALL)
    private List<OrderDetailTicketHistoryEntity> orderDetailTicketHistoryEntities;

    @OneToMany(mappedBy = "orderHistoryEntity", cascade = CascadeType.ALL)
    private List<OrderDetailServiceHistoryEntity> orderDetailServiceHistoryEntities;

}
