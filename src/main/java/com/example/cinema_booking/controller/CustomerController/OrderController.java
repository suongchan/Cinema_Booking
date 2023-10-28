package com.example.cinema_booking.controller.CustomerController;


import com.example.cinema_booking.domain.Order;
import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.exception.CustomerNotFoundException;
import com.example.cinema_booking.repository.CustomerRepository;
import com.example.cinema_booking.repository.OrderRepository;
import com.example.cinema_booking.repository.ShowtimeRepository;
import com.example.cinema_booking.service.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailTicketService orderDetailTicketService;

    @Autowired
    private OrderDetailServiceService orderDetailServiceService;

    @GetMapping("checkout/{id}")
    public String processCheckout(@PathVariable Long id, Principal principal, Model model) throws CustomerNotFoundException {
        String username = principal.getName(); // Lấy tên người dùng hiện tại
        CustomerEntity customer = customerService.getCustomerByUsername(username);

        Long customerId = customer.getIdCustomer();
        ShowtimeEntity showtime = showtimeService.getShowtimeById(id);

        model.addAttribute("customer", customer);
        model.addAttribute("showtime", showtime);
//        model.addAttribute("showtime", showtime);

        // Thêm các thông tin dịch vụ và thông tin khác nếu cần

        // Trả về trang `checkout`
        return "customerHtml/checkout";
    }

    @GetMapping("payment")
    public String paymentPage()
    {
        return "customerHtml/payment";
    }

}







