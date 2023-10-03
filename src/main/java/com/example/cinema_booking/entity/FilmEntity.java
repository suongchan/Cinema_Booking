package com.example.cinema_booking.entity;

import com.example.cinema_booking.domain.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
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
    private List<ShowEntity> showEntities;




//    @DateTimeFormat(pattern ="yyyy-MM-dd")
//    private Date showtime;

}
