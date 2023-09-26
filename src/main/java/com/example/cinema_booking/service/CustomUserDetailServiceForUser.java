package com.example.cinema_booking.service;


import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.UserEntity;
import com.example.cinema_booking.repository.UserRepository;
import com.example.cinema_booking.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
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

public class CustomUserDetailServiceForUser implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameUser) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(usernameUser)
                .orElseThrow(() -> new BadCredentialsException("Không tìm thấy user"));

        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setId(userEntity.getIdUser());
        userPrincipal.setUsername(usernameUser);
        userPrincipal.setPassword(userEntity.getPasswordUser());
        userPrincipal.setAuthorities(List.of(new SimpleGrantedAuthority(userEntity.getRole())));
        return userPrincipal;
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("11"));
    }
}
