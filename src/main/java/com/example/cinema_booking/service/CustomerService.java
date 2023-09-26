package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    Long createCustomer(Customer customer );
    void register(Customer customer);

    List<CustomerEntity> getCustomers();

    boolean updatePassword(String name, String oldPassword, String newPassword);
}
