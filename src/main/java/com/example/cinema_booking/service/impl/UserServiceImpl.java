package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.UserConverter;
import com.example.cinema_booking.domain.User;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.UserEntity;
import com.example.cinema_booking.repository.UserRepository;
import com.example.cinema_booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Long createUser(User user) {
        return userRepository.save(UserConverter.toEntity(user)).getIdUser();
    }
    @Override
    public UserEntity getUserById(Long idUser) {
        return userRepository.getById(idUser);
    }

    @Override
    public List<UserEntity> getUser() {
        return userRepository.findByRole("ROLE_STAFF");
    }

    @Override
    public void updateUser(User user) throws UserPrincipalNotFoundException {
        Optional<UserEntity> existingUser = userRepository.findById(user.getIdUser());

        if (existingUser.isPresent()) {
            // Lấy khách hàng hiện có từ Optional
            UserEntity updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setDateOfBirth(user.getDateOfBirth());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setAddress(user.getAddress());
            userRepository.save(updatedUser);

        } else {
            // Xử lý khi không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID: " + user.getIdUser());
        }
    }

    @Override
    public void deleteUser(Long id) throws UserPrincipalNotFoundException {
        // Kiểm tra xem khách hàng tồn tại trong cơ sở dữ liệu không
        Optional<UserEntity> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            userRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }

    @Override
    public List<UserEntity> searchUsersByPhone(String phoneNumber) {
        return userRepository.findByPhoneContaining(phoneNumber);
    }

}
