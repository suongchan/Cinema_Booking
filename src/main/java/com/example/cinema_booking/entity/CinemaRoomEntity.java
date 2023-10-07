package com.example.cinema_booking.entity;

import com.example.cinema_booking.domain.CinemaRoom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cinemaRooms")
public class CinemaRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCinemaRoom", nullable = false)
    private Long idCinemaRoom;

    @ManyToOne
    @JoinColumn(name = "idCinema")
    private CinemaEntity cinemaEntity;
    private int numberChair;
    private String nameCinemaRoom;
    private String cinemaRoomDesc;

    @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL)
    private List<ChairEntity> chairs;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<ShowsEntity> showEntities;


}
