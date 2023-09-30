package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.Category;
import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CategoryEntity;
import com.example.cinema_booking.entity.CustomerEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CategoryConverter {
    public static CategoryEntity toEntity(Category category){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setNameCategory(category.getNameCategory());
        categoryEntity.setCategoryDesc(category.getCategoryDesc());

        return categoryEntity;
    }
}
