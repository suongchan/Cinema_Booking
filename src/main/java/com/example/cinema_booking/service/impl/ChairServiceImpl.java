package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.repository.ChairRepository;
import com.example.cinema_booking.service.ChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChairServiceImpl implements ChairService {
    @Autowired
    private ChairRepository chairRepository;

    @Override
    public void createChair(CinemaRoomEntity cinemaRoom) {
        int numChairs = cinemaRoom.getNumberChair();

        // Tính toán số hàng và cột
        int maxChairsPerRow = 10;
        int numRows = (int) Math.ceil((double) numChairs / maxChairsPerRow);
        int numColumns = maxChairsPerRow;

        List<ChairEntity> chairEntities = new ArrayList<>();

        Long idCinema = cinemaRoom.getCinemaEntity().getIdCinema();

        int chairCount = 1;

        for (int row = 1; row <= numRows; row++) {
            for (int col = 1; col <= numColumns; col++) {
                ChairEntity chairEntity = new ChairEntity();
                chairEntity.setCinemaRoom(cinemaRoom);
                chairEntity.setOccupied(false);
                chairEntity.setCinemaEntity(new CinemaEntity(idCinema));

                // Tạo nameChair dựa trên hàng và cột
                String nameChair = String.format("%c%d", (char) ('A' + row - 1), col);
                chairEntity.setNameChair(nameChair);

                chairRepository.save(chairEntity);
                chairEntities.add(chairEntity);

                chairCount++;

                // Kiểm tra nếu đã tạo đủ số ghế thì dừng
                if (chairCount > numChairs) {
                    break;
                }
            }
        }
    }


    @Override
    public void saveAllChairs(List<ChairEntity> chairs) {
        chairRepository.saveAll(chairs);
    }



}
