package com.example.cinema_booking.controller;

import com.example.cinema_booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String listFilm() {
        return "customerHtml/film";
    }

    @GetMapping("info")
    public String infoAccount(){
        return "customerHtml/info";
    }

    @GetMapping("changePassword")
    public String showChangePassword() {
        return "customerHtml/changePassword";
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Principal principal, Model model){

        if (customerService.updatePassword(principal.getName(), oldPassword, newPassword)){
            return "customerHtml/info";
        } else {
            model.addAttribute("message", "Sai rồi mày");
            return "customerHtml/changePassword";
        }
    }
}
