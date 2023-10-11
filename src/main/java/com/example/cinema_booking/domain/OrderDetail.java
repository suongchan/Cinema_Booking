package com.example.cinema_booking.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {
    private Long id;
    private Long order_id;
    private Long id_detail;
    private String name;
    private long price;
    private int quantity;
}
