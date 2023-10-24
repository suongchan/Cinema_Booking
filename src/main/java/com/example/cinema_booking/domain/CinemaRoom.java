package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class CinemaRoom {
    private CinemaEntity cinemaEntity;
    private Long idCinemaRoom;
    private String nameCinemaRoom;
    private String cinemaRoomDesc;
    private ChairEntity chairs;
    private int numberChair;


    private Chair[][] convertToChairArray(List<ChairEntity> chairEntities) {
        // Kiểm tra xem danh sách ghế có dữ liệu hay không
        if (chairEntities == null || chairEntities.isEmpty()) {
            return new Chair[0][0]; // Trả về mảng rỗng nếu không có ghế
        }

        // Xác định kích thước của mảng 2 chiều dựa trên số ghế và số hàng ghế
        int numRows = (int) Math.ceil(chairEntities.size() / (double) numberChair);
        int numCols = numberChair;

        Chair[][] chairsArray = new Chair[numRows][numCols];

        // Sắp xếp danh sách ghế theo thứ tự
        chairEntities.sort(Comparator.comparing(ChairEntity::getIdChair));

        int rowIndex = 0;
        int colIndex = 0;

        // Lặp qua danh sách ghế và gán chúng vào mảng 2 chiều
        for (ChairEntity chairEntity : chairEntities) {
            chairsArray[rowIndex][colIndex] = chairEntity.toChair(); // Điều này giả định rằng bạn có một phương thức toChair() trong ChairEntity để chuyển đổi sang đối tượng Chair
            colIndex++;

            if (colIndex == numCols) {
                rowIndex++;
                colIndex = 0;
            }
        }

        return chairsArray;
    }

}
