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

    public String validateRegisterUser(Customer customer) {
        CustomerEntity entity = customerRepository.findByUsername(customer.getUsername()).orElse(null);
        CustomerEntity email = customerRepository.findByEmail(customer.getEmail()).orElse(null);
        CustomerEntity phone = customerRepository.findByPhone(customer.getPhone()).orElse(null);

        if (!Objects.equals(customer.getPassword(), customer.getRePassword())) {
            return "Mật khẩu không khớp";
        }

        if (customer.getPassword().length() < 8) {
            return "Mật khẩu không đủ 8 ký tự";
        }

        if (entity != null) {
            return "Tên đăng nhập đã tồn tại";
        }

        if (email != null) {
            return "Email đã tồn tại";
        }

        if (phone != null) {
            return "Số điện thoại đã tồn tại";
        }

        return null; // Không có lỗi
    }

}
