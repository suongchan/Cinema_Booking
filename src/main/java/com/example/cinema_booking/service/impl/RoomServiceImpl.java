package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.repository.ChairRepository;
import com.example.cinema_booking.repository.RoomRepository;
import com.example.cinema_booking.service.ChairService;
import com.example.cinema_booking.service.RoomService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class RoomServiceImpl implements RoomService {


    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ChairService chairService;

    @Autowired
    private ChairRepository chairRepository;

    @Autowired
    private EntityManager entityManager;

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

    @Transactional
    @Override
    public CinemaRoomEntity update(CinemaRoomEntity cinemaRoom) {
        Optional<CinemaRoomEntity> existingRoom = roomRepository.findById(cinemaRoom.getIdCinemaRoom());

        if (existingRoom.isPresent()) {
            // Cập nhật thông tin phim với thông tin mới
            CinemaRoomEntity updatedRoom = existingRoom.get();
            deleteAllChairsForCinemaRoom(updatedRoom);
            updatedRoom.setNameCinemaRoom(cinemaRoom.getNameCinemaRoom());
            updatedRoom.setCinemaRoomDesc(cinemaRoom.getCinemaRoomDesc());
            updatedRoom.setNumberChair(cinemaRoom.getNumberChair());
            updatedRoom.setCinemaEntity(cinemaRoom.getCinemaEntity());

            chairService.createChair(updatedRoom);
            return roomRepository.save(updatedRoom);
        } else {

            return null;
        }
    }

    private void deleteAllChairsForCinemaRoom(CinemaRoomEntity cinemaRoom) {
        chairRepository.deleteAllByCinemaRoom(cinemaRoom);
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

    @Override
    public Optional<CinemaRoomEntity> getRoomByCinemaId(Long id) {
        return roomRepository.findById(id);
    }
}
