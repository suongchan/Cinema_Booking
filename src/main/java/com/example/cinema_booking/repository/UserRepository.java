package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    List<UserEntity> findByRole(String role);

    List<UserEntity> findByPhoneContaining(String phoneNumber);
}
