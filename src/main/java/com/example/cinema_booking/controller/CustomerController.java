package com.example.cinema_booking.controller;

import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.exception.CustomerNotFoundException;
import com.example.cinema_booking.repository.CustomerRepository;
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
    public String infoAccount(Principal principal, Model model) throws CustomerNotFoundException {
        String username = principal.getName(); // Lấy tên người dùng hiện tại
        CustomerEntity customer = customerService.getCustomerByUsername(username);

        Long customerId = customer.getIdCustomer();
        model.addAttribute("customer", customer);
        return "customerHtml/info";
    }

    @GetMapping("changePassword")
    public String showChangePassword() {
        return "customerHtml/changePassword";
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Principal principal, Model model){

        if (customerService.updatePassword(principal.getName(), oldPassword, newPassword)){
            return "customerHtml/SuccessChangePassword";
        } else {
            model.addAttribute("message", "Sai rồi mày");
            return "customerHtml/changePassword";
        }
    }
}
