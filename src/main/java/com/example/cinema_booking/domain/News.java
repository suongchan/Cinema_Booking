package com.example.cinema_booking.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class News {
    private Long idNew;
    private String imageNew;
    private String titleNew;
    private String newDesc;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dateCreate;

    public News(Long idNew, String imageNew, String titleNew, String newDesc)
    {
        this.idNew = idNew;
        this.imageNew = imageNew;
        this.titleNew = titleNew;
        this.newDesc = newDesc;
        this.dateCreate = new Date();
    }

}
