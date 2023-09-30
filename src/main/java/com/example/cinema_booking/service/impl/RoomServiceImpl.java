package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.RoomConverter;
import com.example.cinema_booking.domain.CinemaRoom;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.repository.RoomRepository;
import com.example.cinema_booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {


    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<CinemaRoomEntity> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Long createCinemaRoom(CinemaRoom cinemaRoom) {
        return roomRepository.save(RoomConverter.toEntity(cinemaRoom)).getIdCinemaRoom();
    }

//    @Override
//    public Long createRoom(String nameCinemaRoom, Long idCinema) {
//        CinemaRoomEntity cinemaRoom = new CinemaRoomEntity();
//        cinemaRoom.setNameCinemaRoom(nameCinemaRoom);
//
//        // Lấy thông tin rạp chiếu từ cơ sở dữ liệu bằng cách gọi repository
//        CinemaEntity cinema = getCinemaById(idCinema);
//        cinemaRoom.setCinemaEntity(cinema);
//
//        CinemaRoomEntity savedCinemaRoom = roomRepository.save(cinemaRoom);
//        return savedCinemaRoom.getIdCinemaRoom();
//    }
//
//    private CinemaEntity getCinemaById(Long cinemaId) {
//        return cinemaRepository.findById(cinemaId)
//                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy rạp chiếu với ID: " + cinemaId));
//    }
//    }
//
//
//    @Override
//    public CinemaRoomEntity getRoomById(Long id) {
//        Optional<CinemaRoomEntity> roomOptional = roomRepository.findById(id);
//
//        if (roomOptional.isPresent()) {
//            return roomOptional.get();
//        } else {
//            // Xử lý khi không tìm thấy phòng chiếu
//            throw new EntityNotFoundException("Không tìm thấy phòng chiếu với ID: " + id);
//        }
//    }
//
//    @Override
//    public void updateRoom(CinemaRoom cinemaRoom) throws UserPrincipalNotFoundException {
//        Optional<CinemaRoomEntity> existingRoomOptional = roomRepository.findById(cinemaRoom.getIdCinemaRoom());
//
//        if (existingRoomOptional.isPresent()) {
//            CinemaRoomEntity existingRoom = existingRoomOptional.get();
//            existingRoom.setNameCinemaRoom(cinemaRoom.getNameCinemaRoom());
//            existingRoom.setCinemaRoomDesc(cinemaRoom.getCinemaRoomDesc());
//
//            CinemaEntity cinemaEntity = cinemaRepository.findByCinemaName(cinemaRoom.getNameCinema());
//            if (cinemaEntity != null) {
//                existingRoom.setCinemaEntity(cinemaEntity);
//            } else {
//                // Xử lý khi không tìm thấy rạp chiếu
//                throw new EntityNotFoundException("Không tìm thấy rạp chiếu với tên: " + cinemaRoom.getNameCinema());
//            }
//
//            roomRepository.save(existingRoom);
//        } else {
//            // Xử lý khi không tìm thấy phòng chiếu
//            throw new EntityNotFoundException("Không tìm thấy phòng chiếu với ID: " + cinemaRoom.getIdCinemaRoom());
//        }
//    }
//
//    @Override
//    public void deleteRoom(Long id) throws UserPrincipalNotFoundException {
//        Optional<CinemaRoomEntity> existingRoomOptional = roomRepository.findById(id);
//
//        if (existingRoomOptional.isPresent()) {
//            // Nếu phòng chiếu tồn tại, thực hiện thao tác xóa
//            roomRepository.deleteById(id);
//        } else {
//            // Xử lý khi không tìm thấy phòng chiếu
//            throw new EntityNotFoundException("Không tìm thấy phòng chiếu với ID: " + id);
//        }
//    }
}
