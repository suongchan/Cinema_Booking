package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.Remote.PaymentAPI;
import com.example.cinema_booking.domain.Order;
import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.repository.OrderDetailServiceRepository;
import com.example.cinema_booking.service.OrderDetailServiceService;
import com.example.cinema_booking.service.OrderDetailTicketService;
import com.example.cinema_booking.service.OrderService;
import com.example.cinema_booking.service.SeatStatusService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private OrderDetailTicketService orderDetailTicketService;

    @Autowired
    private OrderDetailServiceService orderDetailServiceService;

    @Autowired
    private SeatStatusService seatStatusService;


    @GetMapping("{idOrder}")
    public String payment(@PathVariable Long idOrder) {
        OrderEntity orderEntity = orderService.getOrderById(idOrder);
        Order order = orderService.setOrder(orderEntity);

        // call API thanh toan, mo 2 dong nay thi tat dong duoi
        String url = paymentAPI.getQrFromOtherClient(order);
        return "redirect:" + url;

//        return "redirect:/payment/success";
    }

    @GetMapping("success/{idOrder}")
    public String success(@PathVariable String idOrder, Model model) {
        try {
            Long orderId = Long.parseLong(idOrder);
            OrderEntity orderEntity = orderService.getOrderById(orderId);
            orderEntity = orderService.setStatus(orderEntity);

            OrderHistoryEntity orderHistoryEntity = orderService.saveOrderHistory(orderEntity); // Sửa dòng này
            orderDetailTicketService.saveOrderDetailTicketHistory(orderEntity, orderHistoryEntity);
            orderDetailServiceService.saveOrderDetailServiceHistory(orderEntity, orderHistoryEntity);

            model.addAttribute("orderHistory", orderHistoryEntity);
//            model.addAttribute("orderDetailServiceHistoryEntity", orderDetailServiceHistoryEntity);
//            model.addAttribute("orderDetailTicketHistoryEntity", orderDetailTicketHistoryEntity);
            return "success";
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @GetMapping("failed/{idOrder}")
    public String failed(@PathVariable String idOrder){
        Long orderId = Long.parseLong(idOrder);
        seatStatusService.setStatus(orderId);
        return "failed";
    }


}
