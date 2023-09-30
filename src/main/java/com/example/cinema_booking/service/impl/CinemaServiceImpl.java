package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.CinemaConverter;
import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.repository.CinemaRepository;
import com.example.cinema_booking.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;
    @Override
    public List<CinemaEntity> getAllCinema() {
        return cinemaRepository.findAll();
    }

    @Override
    public Long createCinema(Cinema cinema) {
        return cinemaRepository.save(CinemaConverter.toEntity(cinema)).getIdCinema();
    }

    @Override
    public CinemaEntity getCinemaById(Long id) {
        return cinemaRepository.getById(id);
    }

    @Override
    public void updateCinema(Cinema cinema) throws UserPrincipalNotFoundException {
        Optional<CinemaEntity> existingCinema = cinemaRepository.findById(cinema.getIdCinema());

        if (existingCinema.isPresent()) {
            // Lấy khách hàng hiện có từ Optional
            CinemaEntity updatedCinema = existingCinema.get();
            updatedCinema.setNameCinema(cinema.getNameCinema());
            updatedCinema.setAddressCinema(cinema.getAddressCinema());
            updatedCinema.setImageCinema(cinema.getImageCinema());
            updatedCinema.setCity(cinema.getCity());

            cinemaRepository.save(updatedCinema);

        } else {
            // Xử lý khi không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID: " + cinema.getIdCinema());
        }
    }

    @Override
    public void deleteCinema(Long id) throws UserPrincipalNotFoundException {
        Optional<CinemaEntity> existingCinema = cinemaRepository.findById(id);

        if (existingCinema.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            cinemaRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }

    @Override
    public Optional<CinemaEntity> findById(Long idCinema) {
        return cinemaRepository.findById(idCinema);
    }
}
