package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter

public class Customer {
    private Long idCustomer;
    private String name;
    private String username;
    private String password;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dateOfBirth;
    private String phone;
    private String email;
    private String address;
    private String rePassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }


}
