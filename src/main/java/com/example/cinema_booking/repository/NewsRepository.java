package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

}
