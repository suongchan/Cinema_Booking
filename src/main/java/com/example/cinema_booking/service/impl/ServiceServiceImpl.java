package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.ServiceEntity;
import com.example.cinema_booking.repository.ServiceRepository;
import com.example.cinema_booking.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Override
    public List<ServiceEntity> getAllService() {
        return serviceRepository.findAll();
    }

    @Override
    public ServiceEntity createService(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    @Override
    public ServiceEntity getServiceById(Long id) {
        return serviceRepository.getById(id);
    }

    @Override
    public void updateService(ServiceEntity service) throws UserPrincipalNotFoundException {
        Optional<ServiceEntity> existingService = serviceRepository.findById(service.getIdService());

        if (existingService.isPresent()) {
            // Lấy khách hàng hiện có từ Optional
            ServiceEntity updatedService = existingService.get();
            updatedService.setNameService(service.getNameService());
            updatedService.setImage(service.getImage());
            updatedService.setPrice(service.getPrice());

            serviceRepository.save(updatedService);

        } else {
            // Xử lý khi không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID: " + service.getIdService());
        }
    }

    @Override
    public void deleteService(Long id) throws UserPrincipalNotFoundException {
        Optional<ServiceEntity> existingService = serviceRepository.findById(id);

        if (existingService.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            serviceRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }
}
