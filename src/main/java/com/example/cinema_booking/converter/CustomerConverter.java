package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.Customer;

import com.example.cinema_booking.entity.CustomerEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class CustomerConverter {
    public static CustomerEntity toEntity(Customer customer){
        CustomerEntity customerEntity = new CustomerEntity();
        PasswordEncoder passwordEncoder = new  BCryptPasswordEncoder();
        customerEntity.setName(customer.getName());
        customerEntity.setUsername(customer.getUsername());
        customerEntity.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerEntity.setDateOfBirth(customer.getDateOfBirth());
        customerEntity.setPhone(customer.getPhone());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setRole("ROLE_CUSTOMER");
        customerEntity.setStar(0L);
        return customerEntity;
    }
}
