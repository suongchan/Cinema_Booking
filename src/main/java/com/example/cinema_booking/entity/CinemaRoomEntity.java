package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private String nameCinemaRoom;
    private String cinemaRoomDesc;

}
