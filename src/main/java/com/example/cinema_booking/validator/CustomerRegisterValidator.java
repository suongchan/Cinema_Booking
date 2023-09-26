package com.example.cinema_booking.validator;


import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerRegisterValidator {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean validateRegisterUser(Customer customer) {

        CustomerEntity entity = customerRepository.findByUsername(customer.getUsername()).orElse(null);
        CustomerEntity email = customerRepository.findByEmail(customer.getEmail()).orElse(null);
        CustomerEntity phone = customerRepository.findByPhone(customer.getPhone()).orElse(null);

        if (Objects.equals(customer.getPassword(), customer.getRePassword()) && customer.getPassword().length() >= 8 && entity == null && email == null && phone == null) {
            return true;
//            throw new FieldMissMatchException("Password miss match");
        }
        return false;
    }
}
