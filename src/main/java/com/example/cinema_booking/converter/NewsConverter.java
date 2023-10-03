package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.News;
import com.example.cinema_booking.entity.NewsEntity;

public class NewsConverter {
    public static NewsEntity toEntity(News news) {
        NewsEntity newEntity = new NewsEntity();
        newEntity.setImageNew(news.getImageNew());
        newEntity.setTitleNew(news.getTitleNew());
        newEntity.setNewDesc(news.getNewDesc());
        newEntity.setDateCreate(news.getDateCreate());

        return newEntity;
    }
}
