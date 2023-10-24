package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


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
    private boolean status;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    @OneToMany(mappedBy = "filmEntity", fetch = FetchType.EAGER)
    private List<ShowtimeEntity> showsEntities;
    private Long price;

//    @DateTimeFormat(pattern ="yyyy-MM-dd")
//    private Date showtime;

}
