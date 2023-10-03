package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUsername(String username);
    List<CustomerEntity> findByRole(String role);

    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findByPhone(String phone);

    List<CustomerEntity> findByPhoneContaining(String phoneNumber);


}
