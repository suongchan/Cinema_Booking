package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.Remote.PaymentAPI;
import com.example.cinema_booking.domain.Order;
import com.example.cinema_booking.entity.OrderEntity;
import com.example.cinema_booking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentAPI paymentAPI;


    @GetMapping("{idOrder}")
    public String payment(@PathVariable Long idOrder) {
        OrderEntity orderEntity = orderService.getOrderById(idOrder);
        Order order = orderService.setOrder(orderEntity);

        // call API thanh toan, mo 2 dong nay thi tat dong duoi
        String url = paymentAPI.getQrFromOtherClient(order);
        return "redirect:" + url;

//        return "redirect:/payment/success";
    }

    @GetMapping("success")
    public String success() {
        return "success";
    }
}
