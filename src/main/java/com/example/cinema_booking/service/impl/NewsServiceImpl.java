package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.NewsEntity;
import com.example.cinema_booking.repository.NewsRepository;
import com.example.cinema_booking.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public List<NewsEntity> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public NewsEntity createNews(NewsEntity news) {
        return newsRepository.save(news);
    }

    @Override
    public NewsEntity getNewsById(Long id) {
        return newsRepository.getById(id);
    }

    @Override
    public void updateNews(NewsEntity news) throws UserPrincipalNotFoundException {
        Optional<NewsEntity> existingNews = newsRepository.findById(news.getIdNew());

        if (existingNews.isPresent()) {
            // Lấy khách hàng hiện có từ Optional
            NewsEntity updatedNews = existingNews.get();
            updatedNews.setImageNew(news.getImageNew());
            updatedNews.setTitleNew(news.getTitleNew());
            updatedNews.setNewDesc(news.getNewDesc());
            updatedNews.setDateCreate(news.getDateCreate());

            newsRepository.save(updatedNews);

        } else {
            // Xử lý khi không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID: " + news.getIdNew());
        }
    }

    @Override
    public void deleteNews(Long id) throws UserPrincipalNotFoundException {
        Optional<NewsEntity> existingNews = newsRepository.findById(id);

        if (existingNews.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            newsRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }
}
