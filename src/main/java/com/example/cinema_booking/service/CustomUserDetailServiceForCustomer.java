package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.repository.CustomerRepository;
import com.example.cinema_booking.repository.UserRepository;
import com.example.cinema_booking.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary

public class CustomUserDetailServiceForCustomer implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameCustomer) throws UsernameNotFoundException {
        CustomerEntity customerEntity = customerRepository.findByUsernameCustomer(usernameCustomer)
                .orElseThrow(() -> new BadCredentialsException("Không tìm thấy customer"));

        UserPrincipal principal = new UserPrincipal();
        principal.setId(customerEntity.getIdCustomer());
        principal.setUsername(usernameCustomer);
        principal.setPassword(customerEntity.getPasswordCustomer());
        principal.setAuthorities(List.of(new SimpleGrantedAuthority(customerEntity.getRole())));
        return principal;
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("1"));
    }
}
