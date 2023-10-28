package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.SignatureGenerator;
import com.example.cinema_booking.domain.Order;
import com.example.cinema_booking.domain.OrderDetail;
import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.repository.*;
import com.example.cinema_booking.service.OrderDetailServiceService;
import com.example.cinema_booking.service.OrderDetailTicketService;
import com.example.cinema_booking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;


    @Autowired
    private OrderDetailTicketService orderDetailTicketService;

    @Autowired
    private OrderDetailServiceService orderDetailServiceService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderDetailTicketRepository orderDetailTicketRepository;

    @Autowired
    private OrderDetailServiceRepository orderDetailServiceRepository;


    @Override
    public OrderEntity getOrderById(Long idOrder) {
        return orderRepository.findById(idOrder).orElseThrow();
    }

    @Override
    public Order setOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setOrderCode(orderEntity.getIdOrder());
//        order.setOrderCode(99999999L);
        order.setAmount(orderEntity.getAmount());
        order.setDescription(orderEntity.getCustomerEntity().getEmail());
        order.setCustomer_id(orderEntity.getCustomerEntity().getIdCustomer());
        order.setBuyerName(orderEntity.getCustomerEntity().getName());
        order.setBuyerPhone(orderEntity.getCustomerEntity().getPhone());
        order.setReturnUrl("http://localhost:8080/payment/success");
        order.setCancelUrl("Url huy thanh toan");

        List<OrderDetail> orderDetails = new ArrayList<>();
        List<OrderDetailTicketEntity> orderDetailTicketEntities = orderDetailTicketService.getAllByOrderId(orderEntity);
        List<OrderDetailServiceEntity> orderDetailServiceEntities = orderDetailServiceService.getAllByOrderId(orderEntity);

        for (OrderDetailTicketEntity orderDetailTicketEntity : orderDetailTicketEntities) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(orderDetailTicketEntity.getOrderEntity().getShowtimeEntity().getFilmEntity().getNameFilm());
            orderDetail.setPrice(orderDetailTicketEntity.getOrderEntity().getShowtimeEntity().getFilmEntity().getPrice());
            orderDetail.setQuantity(1);
            orderDetails.add(orderDetail);
        }

        for (OrderDetailServiceEntity orderDetailServiceEntity : orderDetailServiceEntities) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(orderDetailServiceEntity.getServiceEntity().getNameService());
            orderDetail.setPrice(orderDetailServiceEntity.getServiceEntity().getPrice());
            orderDetail.setQuantity(1);
            orderDetails.add(orderDetail);
        }

        order.setItems(orderDetails);

        Map<String, String> params = Map.of(
                "amount", String.valueOf(order.getAmount()),
                "cancelUrl", order.getCancelUrl(),
                "description", order.getDescription(),
                "orderCode", String.valueOf(order.getOrderCode()),
                "returnUrl", order.getReturnUrl()
//                  "orderCode", "99999999"
        );

        String ChecksumKey = "22ee21ab306b80fac1782bb426e6140498bc4b5b9f483f30d4883f320731e29e";
        String signature = SignatureGenerator.generateSignature(params, ChecksumKey);
        order.setSignature(signature);
        return order;
    }




}












