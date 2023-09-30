package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.CategoryConverter;
import com.example.cinema_booking.converter.CustomerConverter;
import com.example.cinema_booking.domain.Category;
import com.example.cinema_booking.entity.CategoryEntity;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.repository.CategoryRepository;
import com.example.cinema_booking.repository.CustomerRepository;
import com.example.cinema_booking.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Long createCategory(Category category) {
        return categoryRepository.save(CategoryConverter.toEntity(category)).getIdCategory();
    }

    @Override
    public List<CategoryEntity> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public void updateCategory(Category category) throws UserPrincipalNotFoundException {
        Optional<CategoryEntity> existingCategory = categoryRepository.findById(category.getIdCategory());

        if (existingCategory.isPresent()) {
            // Lấy khách hàng hiện có từ Optional
            CategoryEntity updatedCategory = existingCategory.get();
            updatedCategory.setNameCategory(category.getNameCategory());
            updatedCategory.setCategoryDesc(category.getCategoryDesc());

            categoryRepository.save(updatedCategory);

        } else {
            // Xử lý khi không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID: " + category.getIdCategory());
        }

    }

    @Override
    public void deleteCategory(Long id) throws UserPrincipalNotFoundException {
        Optional<CategoryEntity> existingCategory = categoryRepository.findById(id);

        if (existingCategory.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            categoryRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }
}
