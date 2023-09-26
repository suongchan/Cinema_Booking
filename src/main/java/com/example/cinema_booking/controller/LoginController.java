package com.example.cinema_booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")

public class LoginController {
    @GetMapping("admin_staff")
    public String loginAdminStaff(){
        return "adminHtml/loginAdminStaff";
    }

    @GetMapping("customer")
    public String loginCustomer(){
        return "customerHtml/loginCustomer";
    }
}
