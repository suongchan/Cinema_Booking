package com.example.cinema_booking.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    private LocalDateTime orderDateTime;

    private Long amount;

    private Long totalPrice;

    private Integer point;
    @ManyToOne
    @JoinColumn(name= "idShowTime")
    private ShowtimeEntity showtimeEntity;

    @ManyToOne
    @JoinColumn(name= "idCustomer")
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderDetailTicketEntity> orderDetailTicketEntities;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderDetailServiceEntity> orderDetailServiceEntities;
}
