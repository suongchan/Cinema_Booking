package com.example.cinema_booking.controller;

import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.service.CustomerService;
import com.example.cinema_booking.validator.CustomerRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")

public class RegisterController {
    @Autowired
    private CustomerRegisterValidator customerRegisterValidator;


    @Autowired
    private CustomerService customerService;
    @GetMapping("")
    public String register(@ModelAttribute Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "register/registration";
    }
    @PostMapping("")
    public String creatUser(@ModelAttribute Customer customer, Model model) {
        if (customerRegisterValidator.validateRegisterUser(customer)) {
            customerService.register(customer);
            return "customerHtml/loginCustomer";
        }
        model.addAttribute("messageError", "trùng gì đó rồi");
        System.out.println("trùng rồi");
        return "register/registration";
    }
}
