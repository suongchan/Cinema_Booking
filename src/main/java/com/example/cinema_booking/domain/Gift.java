package com.example.cinema_booking.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Gift {
    private Long id;
    private String image;
    private String title;
    private String desc;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dateCreate;

    public Gift(Long id, String image, String title, String desc)
    {
        this.id = id;
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.dateCreate = new Date();
    }

}
