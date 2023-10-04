package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.ShowsEntity;
import com.example.cinema_booking.exception.ScheduleConflictException;
import com.example.cinema_booking.repository.ShowsRepository;
import com.example.cinema_booking.service.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowsServiceImpl implements ShowsService {

    @Override
    public List<ShowsEntity> getShow() {
        return null;
    }
    @Autowired
    private ShowsRepository showsRepository;

    @Override
    public ShowsEntity createShow(ShowsEntity show) throws ScheduleConflictException {
        // Kiểm tra trùng lịch chiếu
        if (isScheduleConflict(show)) {
            throw new ScheduleConflictException("Lịch chiếu bị trùng");
        }

        // Lưu ShowEntity vào cơ sở dữ liệu
        return showsRepository.save(show);
    }

    @Override
    public List<ShowsEntity> getAllShow() {
        return showsRepository.findAll();
    }

    private boolean isScheduleConflict(ShowsEntity newShow) {
        // Kiểm tra xem có buổi chiếu nào khác cho cùng phòng chiếu và trùng thời gian không
        List<ShowsEntity> conflictingShows = showsRepository.findByRoomAndStartBetween(newShow.getIdShow(),newShow.getCinemaRoomEntity(), newShow.getFilmEntity(), newShow.getStart(), newShow.getEnd());

        // Trả về true nếu có xung đột, ngược lại trả về false
        return !conflictingShows.isEmpty();
    }
}
