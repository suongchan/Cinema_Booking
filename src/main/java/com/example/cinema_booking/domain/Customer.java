package com.example.cinema_booking.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter

public class Customer {
    private Long idCustomer;
    private String nameCustomer;
    private String usernameCustomer;
    private String passwordCustomer;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dateOfBirth;
    private String phone;
    private String email;
    private String address;
    private String rePassword;

    public String getPassword() {
        return passwordCustomer;
    }

    public void setPassword(String password) {
        this.passwordCustomer = passwordCustomer;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
