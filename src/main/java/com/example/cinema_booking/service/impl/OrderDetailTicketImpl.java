package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.OrderDetailTicketEntity;
import com.example.cinema_booking.entity.OrderDetailTicketHistoryEntity;
import com.example.cinema_booking.entity.OrderEntity;
import com.example.cinema_booking.entity.OrderHistoryEntity;
import com.example.cinema_booking.repository.OrderDetailTicketHistoryRepository;
import com.example.cinema_booking.repository.OrderDetailTicketRepository;
import com.example.cinema_booking.service.OrderDetailTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderDetailTicketImpl implements OrderDetailTicketService {
    @Autowired
    private OrderDetailTicketRepository orderDetailTicketRepository;

    @Autowired
    private OrderDetailTicketHistoryRepository orderDetailTicketHistoryRepository;
    @Override
    public List<OrderDetailTicketEntity> getAllByOrderId(OrderEntity orderEntity) {
        return orderDetailTicketRepository.findAllByOrderEntity(orderEntity);
    }

    @Override
    public void saveOrderDetailTicketHistory(OrderEntity orderEntity, OrderHistoryEntity orderHistoryEntity){
        for (OrderDetailTicketEntity orderDetailTicketEntity : orderEntity.getOrderDetailTicketEntities()){
            OrderDetailTicketHistoryEntity orderDetailTicketHistoryEntity = new OrderDetailTicketHistoryEntity();
            orderDetailTicketHistoryEntity.setOrderHistoryEntity(orderHistoryEntity);
            orderDetailTicketHistoryEntity.setIdOrderDetailTicket(orderDetailTicketEntity.getIdOrderDetailTicket());
            orderDetailTicketHistoryEntity.setFilm(orderDetailTicketEntity.getShowtimeEntity().getFilmEntity().getNameFilm());
//            orderDetailTicketHistoryEntity.setPriceTicket(orderDetailTicketEntity.getShowtimeEntity().getFilmEntity().getPrice());
            orderDetailTicketHistoryEntity.setCinema(orderDetailTicketEntity.getShowtimeEntity().getCinemaEntity().getNameCinema());
            orderDetailTicketHistoryEntity.setCinemaRoom(orderDetailTicketEntity.getShowtimeEntity().getCinemaRoomEntity().getNameCinemaRoom());
            orderDetailTicketHistoryEntity.setDate(orderDetailTicketEntity.getShowtimeEntity().getDateShow());
            orderDetailTicketHistoryEntity.setTimeStart(orderDetailTicketEntity.getShowtimeEntity().getStart());
            orderDetailTicketHistoryEntity.setNameSeat(orderDetailTicketEntity.getSeatStatusEntity().getChair().getNameChair());
            orderDetailTicketHistoryRepository.save(orderDetailTicketHistoryEntity);
            System.out.println(orderDetailTicketHistoryEntity.getPriceTicket());
        }
    }
}
