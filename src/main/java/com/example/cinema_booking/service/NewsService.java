package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.NewsEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface NewsService {
    List<NewsEntity> getAllNews();

    NewsEntity createNews(NewsEntity news);

    NewsEntity getNewsById(Long id);

    void updateNews(NewsEntity news) throws UserPrincipalNotFoundException;

    void deleteNews(Long id) throws UserPrincipalNotFoundException;
}
