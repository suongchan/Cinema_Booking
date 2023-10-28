package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.domain.Service;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.ServiceEntity;

public class ServiceConverter {
    public static ServiceEntity toEntity(Service service) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setNameService(service.getNameService());
        serviceEntity.setImage(service.getImage());
        serviceEntity.setPrice(service.getPrice());


        return serviceEntity;
    }
}
