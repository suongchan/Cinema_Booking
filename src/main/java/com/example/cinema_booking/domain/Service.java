package com.example.cinema_booking.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Service {
    private Long idService;
    private String image;
    private String nameService;
    private String price;
    private String status;

}
