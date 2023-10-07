package com.example.cinema_booking.service.impl;
import com.example.cinema_booking.entity.ShowtimeEntity;
import com.example.cinema_booking.repository.ShowtimeRepository;
import com.example.cinema_booking.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class ShowtimeServiceImpl implements ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public ShowtimeEntity createShows(ShowtimeEntity showtime) {

        if (!isShowtimeConflict(showtime)) {
           return showtimeRepository.save(showtime);
        } else {
            throw new IllegalArgumentException("Lịch chiếu bị trùng lặp");
        }
    }

    private boolean isShowtimeConflict(ShowtimeEntity showtime) {
        List<ShowtimeEntity> conflictShowtimes = showtimeRepository.findShowsByRoomAndTimeRange(
                showtime.getCinemaRoomEntity(), showtime.getStart(), showtime.getEnd(), showtime.getDateShow());

        return !conflictShowtimes.isEmpty();
    }

    @Override
    public List<ShowtimeEntity> getAllShows() {
        return  showtimeRepository.findAll();
    }

    @Override
    public void deleteShow(Long id) throws UserPrincipalNotFoundException {
        Optional<ShowtimeEntity> existingShowtime= showtimeRepository.findById(id);

        if (existingShowtime.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            showtimeRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }

    @Override
    public ShowtimeEntity update(ShowtimeEntity showtime) {
       Optional<ShowtimeEntity> existingShow = showtimeRepository.findById(showtime.getIdShow());

        if (existingShow.isPresent()) {
            // Cập nhật thông tin phim với thông tin mới
            ShowtimeEntity updatedShow = existingShow.get();
            updatedShow.setDateShow(showtime.getDateShow());
            updatedShow.setStart(showtime.getStart());
            updatedShow.setEnd(showtime.getEnd());
            updatedShow.setFilmEntity(showtime.getFilmEntity());
            updatedShow.setCinemaEntity(showtime.getCinemaEntity());
            updatedShow.setCinemaRoomEntity(showtime.getCinemaRoomEntity());

            return showtimeRepository.save(updatedShow);
        } else {

            return null;
        }
    }

    @Override
    public Optional<ShowtimeEntity> getShowtimeById(Long id) {
     return showtimeRepository.findById(id);
    }

}
