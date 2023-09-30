package com.example.cinema_booking.repository;

import com.example.cinema_booking.domain.Category;
import com.example.cinema_booking.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
