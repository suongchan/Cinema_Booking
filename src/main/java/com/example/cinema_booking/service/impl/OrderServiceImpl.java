package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.SignatureGenerator;
import com.example.cinema_booking.domain.Order;
import com.example.cinema_booking.domain.OrderDetail;
import com.example.cinema_booking.entity.OrderDetailServiceEntity;
import com.example.cinema_booking.entity.OrderDetailTicketEntity;
import com.example.cinema_booking.entity.OrderEntity;
import com.example.cinema_booking.entity.OrderHistoryEntity;
import com.example.cinema_booking.repository.OrderHistoryRepository;
import com.example.cinema_booking.repository.OrderRepository;
import com.example.cinema_booking.service.OrderDetailServiceService;
import com.example.cinema_booking.service.OrderDetailTicketService;
import com.example.cinema_booking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailTicketService orderDetailTicketService;

    @Autowired
    private OrderDetailServiceService orderDetailServiceService;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;


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
        order.setReturnUrl("http://localhost:8080/payment/success/" + order.getOrderCode());
        order.setCancelUrl("http://localhost:8080/payment/failed");

        List<OrderDetail> orderDetails = new ArrayList<>();
        List<OrderDetailTicketEntity> orderDetailTicketEntities = orderDetailTicketService.getAllByOrderId(orderEntity);
        List<OrderDetailServiceEntity> orderDetailServiceEntities = orderDetailServiceService.getAllByOrderId(orderEntity);

        for (OrderDetailTicketEntity orderDetailTicketEntity: orderDetailTicketEntities) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(orderDetailTicketEntity.getShowtimeEntity().getFilmEntity().getNameFilm());
            orderDetail.setPrice(orderDetailTicketEntity.getShowtimeEntity().getFilmEntity().getPrice());
            orderDetail.setQuantity(1);
            orderDetails.add(orderDetail);
        }

        for (OrderDetailServiceEntity orderDetailServiceEntity: orderDetailServiceEntities) {
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

        String ChecksumKey = "55b9653213f256262c00ea11cd1c2e6e8de10eee36345f36209daff395f15060";
        String signature = SignatureGenerator.generateSignature(params, ChecksumKey);
        order.setSignature(signature);
        return order;
    }

    @Override
    public OrderHistoryEntity saveOrderHistory(OrderEntity orderEntity){
        OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
        orderHistoryEntity.setId(orderEntity.getIdOrder());
        orderHistoryEntity.setOrderDateTime(orderEntity.getOrderDateTime());
        orderHistoryEntity.setPoint(orderEntity.getPoint());
        orderHistoryEntity.setCustomerId(orderEntity.getCustomerEntity().getIdCustomer());
        orderHistoryEntity.setTotalPrice(orderEntity.getTotalPrice());
        orderHistoryEntity.setAmount(orderEntity.getAmount());
        orderHistoryEntity.setIdShowtime(orderEntity.getShowtimeEntity().getIdShow());
        return orderHistoryRepository.save(orderHistoryEntity);
    }

    @Override
    public void createOrder(OrderEntity orderEntity) {
        orderEntity.setStatus(false);
        orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity setStatus(OrderEntity orderEntity) {
        orderEntity.setStatus(true);
        return orderRepository.save(orderEntity);
    }



}
