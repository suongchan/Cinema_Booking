package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Film {
    private Long idFilm;
    private String nameFilm;
    private CategoryEntity categoryEntity;
    private Long time;
    private String introduce;
    private String trailer;
    private Long rating;
    private String imageFilm;
    private String status;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private  Date showtime;
}
