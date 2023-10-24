package com.example.cinema_booking.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Service {
    private Long idService;
    private String image;
    private String nameService;
    private Long price;
    private String status;

}
