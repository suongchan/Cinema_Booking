package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.FilmConverter;
import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.FilmEntity;
import com.example.cinema_booking.repository.FilmRepository;
import com.example.cinema_booking.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;
    @Override
    public List<FilmEntity> getAllFilm() {
        return filmRepository.findAll() ;
    }

    @Override
    public FilmEntity addFilm(FilmEntity film) {
        return filmRepository.save(film);
    }

    @Override
    public FilmEntity getFilmById(Long id) {
        return filmRepository.getById(id);
    }

    @Override
    public FilmEntity updateFilm(FilmEntity film) throws UserPrincipalNotFoundException {
        Optional<FilmEntity> existingFilm = filmRepository.findById(film.getIdFilm());

        if (existingFilm.isPresent()) {
            // Cập nhật thông tin phim với thông tin mới
            FilmEntity updatedFilm = existingFilm.get();
            updatedFilm.setNameFilm(film.getNameFilm());
            updatedFilm.setCategoryEntity(film.getCategoryEntity());
            updatedFilm.setImageFilm(film.getImageFilm());
//            updatedFilm.setStatus(film.getStatus());
            updatedFilm.setTrailer(film.getTrailer());
            updatedFilm.setRating(film.getRating());
//            updatedFilm.setShowtime(film.getShowtime());
            updatedFilm.setIntroduce(film.getIntroduce());
            updatedFilm.setTime(film.getTime());
            updatedFilm.setPrice(film.getPrice());

            return filmRepository.save(updatedFilm);
        } else {

            return null;
        }


    }

    @Override
    public void deleteFilm(Long id) throws UserPrincipalNotFoundException {
        Optional<FilmEntity> existingFilm = filmRepository.findById(id);

        if (existingFilm.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            filmRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }

    @Override
    public List<Film> getAllFilmDC(LocalDate now) {
        List<Film> films = filmRepository.findAllByDateRange(now).stream().map(FilmConverter::toModel).toList();
        List<Film> filmsDC = new ArrayList<>();
        for (Film f: films) {
            if (f.isStatus()) {
                filmsDC.add(f);
            }
        }
        return filmsDC;
    }

    @Override
    public List<Film> getAllFilmSC(LocalDate now) {
        return filmRepository.findAllByDateSC(now.plusDays(1), now.plusDays(20)).stream().map(FilmConverter::toModel).toList();
    }

    @Override
    public List<FilmEntity> searchFilmsByName(String filmName) {
        return filmRepository.findByNameFilmContaining(filmName);
    }
}
