package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows")
@Getter
@Setter
public class ShowtimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShow;
//    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate dateShow;
//    @DateTimeFormat(pattern ="HH:mm")
    private LocalTime start;
//    @DateTimeFormat(pattern ="HH:mm")
    private LocalTime end;

    @ManyToOne
    @JoinColumn(name = "idFilm")
    private FilmEntity filmEntity;

    @ManyToOne
    @JoinColumn(name = "idCinema")
    private CinemaEntity cinemaEntity;

    @ManyToOne
    @JoinColumn(name = "idCinemaRoom")
    private CinemaRoomEntity cinemaRoomEntity;

//    @ManyToOne
//    @JoinColumn(name = "idCinema")
//    private CinemaRoomEntity cinemaRoom;


}