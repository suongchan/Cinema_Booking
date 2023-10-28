package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.ServiceEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface ServiceService {

    List<ServiceEntity> getAllService();

    ServiceEntity createService(ServiceEntity service);

    ServiceEntity getServiceById(Long id);

    void updateService(ServiceEntity service) throws UserPrincipalNotFoundException;

    void deleteService(Long id) throws UserPrincipalNotFoundException;
}
