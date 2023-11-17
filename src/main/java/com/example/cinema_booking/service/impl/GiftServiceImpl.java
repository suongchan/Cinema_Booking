package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.GiftEntity;
import com.example.cinema_booking.entity.NewsEntity;
import com.example.cinema_booking.repository.GiftRepository;
import com.example.cinema_booking.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GiftServiceImpl implements GiftService {
    @Autowired
    private GiftRepository giftRepository;
    @Override
    public List<GiftEntity> getAllGift() {
        return giftRepository.findAll();
    }

    @Override
    public GiftEntity createGift(GiftEntity gift) {
        return giftRepository.save(gift);
    }

    @Override
    public GiftEntity getGiftById(Long id) {
        return giftRepository.getById(id);
    }



    @Override
    public void updateGift(GiftEntity gift) throws UserPrincipalNotFoundException {
        Optional<GiftEntity> existingGift = giftRepository.findById(gift.getId());

        if (existingGift.isPresent()) {
            // Lấy khách hàng hiện có từ Optional
            GiftEntity updatedGift = existingGift.get();
            updatedGift.setImage(gift.getImage());
            updatedGift.setTitle(gift.getTitle());
            updatedGift.setDesc(gift.getDesc());
            updatedGift.setDateCreate(gift.getDateCreate());

            giftRepository.save(updatedGift);

        } else {
            // Xử lý khi không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID: " + gift.getId());
        }
    }

    @Override
    public void deleteGift(Long id) throws UserPrincipalNotFoundException {
        Optional<GiftEntity> existingGift = giftRepository.findById(id);

        if (existingGift.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            giftRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }

}
