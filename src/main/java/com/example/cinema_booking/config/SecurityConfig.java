package com.example.cinema_booking.config;

import com.example.cinema_booking.handler.CustomAuthenticationFailureHandler;
import com.example.cinema_booking.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/admin/**")
                                        .hasRole("ADMIN")
                                        .requestMatchers("/staff/**")
                                        .hasRole("STAFF")
                                        .requestMatchers("/customer/**")
                                        .hasRole("CUSTOMER")
//                        .requestMatchers("/anonymous*")
//                        .anonymous()
                                        .requestMatchers("/login/**","/register", "/home_customer*", "/static/**", "/film/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Trang đăng nhập chung
                        .loginProcessingUrl("/perform_login")
                        .successHandler(authenticationSuccessHandler())
                        .failureHandler(authenticationFailureHandler())
                )

//                .rememberMe(me -> {}) //TODO search them tren mang
        ;

//        http.addFilterBefore(filter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public IPFilter filter() {
//        return new IPFilter();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService);
//    }
    @Bean
    public CustomAuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public CustomAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
