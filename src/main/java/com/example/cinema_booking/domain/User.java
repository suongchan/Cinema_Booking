package com.example.cinema_booking.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class User {
    private Long idUsr;
    private String nameUser;
    private String usernameUser;
    private String passwordUser;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dateOfBirth;
    private String phone;
    private String email;
    private String address;
    private String rePassword;

    public String getPassword() {
        return passwordUser;
    }

    public void setPassword(String password) {
        this.passwordUser = passwordUser;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
