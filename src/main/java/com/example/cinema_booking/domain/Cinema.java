package com.example.cinema_booking.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cinema {
    private Long idCinema;
    private String nameCinema;
    private String addressCinema;
    private String imageCinema;
    private String city;

}
