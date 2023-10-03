package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.FilmEntity;
import com.example.cinema_booking.repository.RoomRepository;
import com.example.cinema_booking.service.ChairService;
import com.example.cinema_booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {


    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ChairService chairService;

    @Override
    public List<CinemaRoomEntity> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public CinemaRoomEntity addRoom(CinemaRoomEntity cinemaRoom) {
        return roomRepository.save(cinemaRoom);
    }

    @Override
    public CinemaRoomEntity getRoomById(Long id) {
        return roomRepository.getById(id);
    }

    @Override
    public CinemaRoomEntity update(CinemaRoomEntity cinemaRoom) {
        Optional<CinemaRoomEntity> existingRoom = roomRepository.findById(cinemaRoom.getIdCinemaRoom());

        if (existingRoom.isPresent()) {
            // Cập nhật thông tin phim với thông tin mới
            CinemaRoomEntity updatedRoom = existingRoom.get();
            updatedRoom.setNameCinemaRoom(cinemaRoom.getNameCinemaRoom());
            updatedRoom.setCinemaRoomDesc(cinemaRoom.getCinemaRoomDesc());
            updatedRoom.setNumberChair(cinemaRoom.getNumberChair());
            updatedRoom.setCinemaEntity(cinemaRoom.getCinemaEntity());
            return roomRepository.save(updatedRoom);
        } else {

            return null;
        }


    }

    @Override
    public void deleteRoom(Long id) throws UserPrincipalNotFoundException {
        Optional<CinemaRoomEntity> existingRoom= roomRepository.findById(id);

        if (existingRoom.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            roomRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }
}
