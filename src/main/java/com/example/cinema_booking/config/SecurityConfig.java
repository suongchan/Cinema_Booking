package com.example.cinema_booking.config;

import com.example.cinema_booking.handler.CustomAuthenticationFailureHandler;
import com.example.cinema_booking.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                                auth.requestMatchers("/admin*")
                                        .hasRole("ADMIN")
                                        .requestMatchers("/staff*")
                                        .hasRole("STAFF")
                                        .requestMatchers("/customer*")
                                        .hasRole("CUSTOMER")
//                        .requestMatchers("/anonymous*")
//                        .anonymous()
                                        .requestMatchers("/login/**","/register", "/home_customer*")
                                        .permitAll()
                                        .requestMatchers("/css/cssAdmin/**")
                                        .permitAll()
                                        .requestMatchers("/css/cssCustomer/**")
                                        .permitAll()
                                        .requestMatchers("/image/**")
                                        .permitAll()
                                        .requestMatchers("/ImageAdmin/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(formLogin -> formLogin.loginPage("/login/**")
                                .loginProcessingUrl("/perform_login")
//                        .defaultSuccessUrl("/admin")
                                .successHandler(authenticationSuccessHandler())
//                        .failureUrl("/login?error=true")
                                .failureHandler(authenticationFailureHandler())
                )
                .logout(logout -> logout.logoutUrl("/perform_logout")
                                .deleteCookies("JSESSIONID")
//                        .logoutSuccessHandler(logoutSuccessHandler())
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

    @Bean
    public CustomAuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public CustomAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

}
