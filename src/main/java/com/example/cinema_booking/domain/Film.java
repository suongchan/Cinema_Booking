package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class Film {
    private Long idFilm;
    private String nameFilm;
    private Category category;
    private Long time;
    private String introduce;
    private String trailer;
    private Long rating;
    private String imageFilm;
    private boolean status;

    private LocalDate dateStart;
    private LocalDate dateEnd;

//    @DateTimeFormat(pattern ="yyyy-MM-dd")
//    private  Date showtime;
}
