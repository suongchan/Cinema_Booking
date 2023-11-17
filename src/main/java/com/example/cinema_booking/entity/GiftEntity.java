package com.example.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "gifts")
public class GiftEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idGift", nullable = false)
        private Long id;

        private String image;
        private String title;

        @Column(name = "`desc`") // Use backticks to escape the reserved keyword
        private String desc;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date dateCreate;


}
