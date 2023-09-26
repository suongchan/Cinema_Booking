package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.CustomerConverter;
import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.repository.CustomerRepository;
import com.example.cinema_booking.security.UserPrincipal;
import com.example.cinema_booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier

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

    public boolean updatePassword(String usernameCustomer, String oldPassword, String newPassword) {
        UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(usernameCustomer);
        CustomerEntity customerEntity = customerRepository.findByUsernameCustomer(usernameCustomer).orElseThrow();
        if (passwordEncoder.matches(oldPassword, userPrincipal.getPassword())){
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            customerEntity.setPasswordCustomer(encodedNewPassword);
            customerRepository.save(customerEntity);
            return true;
        } else {
            return false;
        }
    }

//    @Autowired
//    public CustomerServiceImpl(@Qualifier("customUserDetailServiceForCustomer") UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
}
