package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cinemas")
public class CinemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCinema", nullable = false)

    private Long idCinema;
    private String nameCinema;
    private String addressCinema;
    private String imageCinema;
    private String city;
}
