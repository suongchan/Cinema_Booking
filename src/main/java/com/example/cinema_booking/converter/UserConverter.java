package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.domain.User;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserConverter {
    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userEntity.setName(user.getName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setPhone(user.getPhone());
        userEntity.setAddress(user.getAddress());
        userEntity.setRole("ROLE_STAFF");
        return userEntity;
    }
}
