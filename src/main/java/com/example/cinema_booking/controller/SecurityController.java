package com.example.cinema_booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")

public class SecurityController {
    @GetMapping("home_customer")
    public String index1(){
        return "homeCustomer";
    }
}
