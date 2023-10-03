package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNew", nullable = false)

    private Long idNew;
    private String imageNew;
    private String titleNew;
    private String newDesc;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dateCreate;
}
