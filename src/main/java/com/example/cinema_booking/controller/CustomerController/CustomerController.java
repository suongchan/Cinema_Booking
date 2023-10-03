package com.example.cinema_booking.controller.CustomerController;

import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.exception.CustomerNotFoundException;
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
        return "redirect:film/dang-chieu";
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
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("ReNewPassword") String ReNewPassword, Principal principal, Model model) {

        String message = customerService.updatePassword(principal.getName(), oldPassword, newPassword, ReNewPassword);

        if (message == null) {
            return "customerHtml/SuccessChangePassword";
        } else {
            model.addAttribute("message", message);
            return "customerHtml/changePassword";
        }
    }
}
