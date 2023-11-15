package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OrderDetailServiceHistory")
@Getter
@Setter

public class OrderDetailServiceHistoryEntity {

    @Id
    private Long idOrderDetailService;

    @ManyToOne
    @JoinColumn(name = "idOrderHistory")
    private OrderHistoryEntity orderHistoryEntity ;

    private Long priceService;
    private String nameService;
    private int quantity;

}
