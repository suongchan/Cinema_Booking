package com.example.cinema_booking.service;

import com.example.cinema_booking.controller.AdminController.GiftController;
import com.example.cinema_booking.entity.GiftEntity;
import com.example.cinema_booking.entity.NewsEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface GiftService {
    List<GiftEntity> getAllGift();

    GiftEntity createGift(GiftEntity gift);

    GiftEntity getGiftById(Long id);

    void updateGift(GiftEntity gift) throws UserPrincipalNotFoundException;

    void deleteGift(Long id) throws UserPrincipalNotFoundException;
}
