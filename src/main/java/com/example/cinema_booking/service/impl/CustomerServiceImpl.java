package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.CustomerConverter;
import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.repository.CustomerRepository;
import com.example.cinema_booking.security.UserPrincipal;
import com.example.cinema_booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Long createCustomer(Customer customer) {
        return customerRepository.save(CustomerConverter.toEntity(customer)).getIdCustomer();
    }

    @Override
    public void register(Customer customer) {
        customerRepository.save(CustomerConverter.toEntity(customer));
    }

    @Override
    public List<CustomerEntity> getCustomers() {
        return customerRepository.findByRole("ROLE_CUSTOMER");
    }

    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(username);
        CustomerEntity entity = customerRepository.findByUsername(username).orElseThrow();
        if (passwordEncoder.matches(oldPassword, userPrincipal.getPassword())){
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            entity.setPassword(encodedNewPassword);
            customerRepository.save(entity);
            return true;
        } else {
            return false;
        }
    }
}
