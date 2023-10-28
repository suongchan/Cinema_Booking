package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.exception.CustomerNotFoundException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface CustomerService {
    Long createCustomer(Customer customer );
    void register(Customer customer);

    List<CustomerEntity> getCustomerByRole();

    String updatePassword(String username, String oldPassword, String newPassword, String ReNewPassword);

    CustomerEntity getCustomerById(Long id);
    CustomerEntity getCustomerByUsername(String username) throws CustomerNotFoundException;

    void updateCustomer(Customer customer) throws UserPrincipalNotFoundException;

    void deleteCustomer(Long id) throws UserPrincipalNotFoundException;

    List<CustomerEntity> searchCustomersByPhone(String phone);

}
