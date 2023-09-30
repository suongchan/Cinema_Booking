package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.Category;
import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CategoryEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface CategoryService {
    Long createCategory(Category category );

    List<CategoryEntity> getAllCategory();

    CategoryEntity getCategoryById(Long id);

    void updateCategory(Category category) throws UserPrincipalNotFoundException;

    void deleteCategory(Long id) throws UserPrincipalNotFoundException;
}
