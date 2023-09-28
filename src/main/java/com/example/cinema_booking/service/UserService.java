package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.domain.User;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.UserEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface UserService {
    Long createUser(User user );
    UserEntity getUserById(Long idUser);

    List<UserEntity> getUser();

    void updateUser(User user) throws UserPrincipalNotFoundException;

    void deleteUser(Long id) throws UserPrincipalNotFoundException;

}
