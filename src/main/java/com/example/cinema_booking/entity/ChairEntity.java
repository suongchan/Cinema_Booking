package com.example.cinema_booking.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Chairs")
@Getter
@Setter
public class ChairEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChair;

    private boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "cinema_room_id")
    private CinemaRoomEntity cinemaRoom;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    private CinemaEntity cinemaEntity;

    @OneToMany(mappedBy = "chairEntity", cascade = CascadeType.ALL)
    private List<OrderDetailTicketEntity> orderDetailTicketEntities;


}
