package com.example.cinema_booking.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OrderDetailService")
@Getter
@Setter
public class OrderDetailServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderDetailService;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private OrderEntity orderEntity;
    @ManyToOne
    @JoinColumn(name= "idService")
    private ServiceEntity serviceEntity;
    private int quantity;

}
