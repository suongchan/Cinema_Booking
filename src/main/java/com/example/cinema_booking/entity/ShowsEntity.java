package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "shows")

public class ShowsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShow;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDateTime start;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "idFilm")
    private FilmEntity filmEntity;

    @ManyToOne
    @JoinColumn(name = "id_cinema_room")
    private CinemaRoomEntity room;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    private CinemaEntity cinemaEntity;

    public Long getIdShow() {
        return idShow;
    }

    public void setIdShow(Long idShow) {
        this.idShow = idShow;
    }

    public LocalDateTime getStart() {
        return start;
    }


    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public FilmEntity getFilmEntity() {
        return filmEntity;
    }

    public void setFilmEntity(FilmEntity filmEntity) {
        this.filmEntity = filmEntity;
    }

    public CinemaRoomEntity getCinemaRoomEntity() {
        return room;
    }

    public void setCinemaRoomEntity(CinemaRoomEntity cinemaRoomEntity) {
        this.room = cinemaRoomEntity;
    }



    public CinemaEntity getCinemaEntity() {
        return cinemaEntity;
    }

    public void setCinemaEntity(CinemaEntity cinemaEntity) {
        this.cinemaEntity = cinemaEntity;
    }
}
