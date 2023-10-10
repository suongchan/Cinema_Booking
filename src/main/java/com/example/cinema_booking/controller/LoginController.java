package com.example.cinema_booking.controller;

import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("")
    public String login(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        if (servletPath.startsWith("/admin")) {
            return "redirect:/login/admin_staff";
        } else if (servletPath.startsWith("/customer")) {
            return "redirect:/login/customer";
        } else if (servletPath.startsWith("/staff")) {
            return "redirect:/login/staff";
        } else {
            return "redirect:/login/customer";
        }
    }
}
