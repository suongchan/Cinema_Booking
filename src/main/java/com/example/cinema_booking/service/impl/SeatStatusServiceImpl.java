package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.OrderDetailTicketEntity;
import com.example.cinema_booking.entity.OrderEntity;
import com.example.cinema_booking.entity.SeatStatusEntity;
import com.example.cinema_booking.repository.OrderRepository;
import com.example.cinema_booking.repository.SeatStatusRepository;
import com.example.cinema_booking.service.SeatStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatStatusServiceImpl implements SeatStatusService {
    @Autowired
    private SeatStatusRepository seatStatusRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public SeatStatusEntity createSeatStatus(SeatStatusEntity seatStatus) {
        return seatStatusRepository.save(seatStatus);
    }


    public SeatStatusEntity getSeats(Long showtimeId, String selectedChairs ) {
           return seatStatusRepository.findByShowtimeIdShowAndChairNameChair(showtimeId, selectedChairs);

    }



    @Override
    public SeatStatusEntity getSeatStatusById(Long seatId) {
        return seatStatusRepository.getById(seatId);
    }

    @Override
    public void setStatus(Long idOrder) {
        OrderEntity orderEntity = orderRepository.findById(idOrder).orElseThrow();
        List<OrderDetailTicketEntity> orderDetailTicketEntities = orderEntity.getOrderDetailTicketEntities();
        for (OrderDetailTicketEntity orderDetailTicketEntity: orderDetailTicketEntities) {
            SeatStatusEntity seatStatus =  orderDetailTicketEntity.getSeatStatusEntity();
            seatStatus.setOccupied(false);
            seatStatusRepository.save(seatStatus);
        }
    }


    @Override
    public List<SeatStatusEntity> getSeatStatusEntitiesByShowtime (Long id){
        return seatStatusRepository.findByShowtimeIdShow(id);
    }

    @Override
    public void updateSeatStatus (Long showtimeId, List < String > selectedChairs){
        for (String chairName : selectedChairs) {
            SeatStatusEntity seatStatus = seatStatusRepository.findByShowtimeIdShowAndChairNameChair(showtimeId, chairName);
            if (seatStatus != null) {
                seatStatus.setOccupied(true);
                seatStatusRepository.save(seatStatus);
            } else {
                // Xử lý lỗi nếu không tìm thấy ghế
            }
        }
    }
}
