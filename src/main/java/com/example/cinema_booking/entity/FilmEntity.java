package com.example.cinema_booking.entity;

import com.example.cinema_booking.domain.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "films")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFilms", nullable = false)

    private Long idFilm;
    private String nameFilm;

    @ManyToOne
    @JoinColumn(name= "idCategory")
    private CategoryEntity categoryEntity;

    private Long time;
    private String introduce;
    private String trailer;
    private Long rating;
    private String imageFilm;
    private String status;
    private Date showtime;

}
