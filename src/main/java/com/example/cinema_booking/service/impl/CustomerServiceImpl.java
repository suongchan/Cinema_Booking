package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.converter.CustomerConverter;
import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.UserEntity;
import com.example.cinema_booking.repository.CustomerRepository;
import com.example.cinema_booking.security.UserPrincipal;
import com.example.cinema_booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Long createCustomer(Customer customer) {
        return customerRepository.save(CustomerConverter.toEntity(customer)).getIdCustomer();
    }

    @Override
    public void register(Customer customer) {
        customerRepository.save(CustomerConverter.toEntity(customer));
    }

    @Override
    public List<CustomerEntity> getCustomerByRole() {
        return customerRepository.findByRole("ROLE_CUSTOMER");
    }

    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(username);
        CustomerEntity entity = customerRepository.findByUsername(username).orElseThrow();
        if (passwordEncoder.matches(oldPassword, userPrincipal.getPassword())){
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            entity.setPassword(encodedNewPassword);
            customerRepository.save(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CustomerEntity getCustomerById(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public void updateCustomer(Customer customer) throws UserPrincipalNotFoundException {


        Optional<CustomerEntity> existingCustomer = customerRepository.findById(customer.getIdCustomer());

        if (existingCustomer.isPresent()) {
            // Lấy khách hàng hiện có từ Optional
            CustomerEntity updatedCustomer = existingCustomer.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setUsername(customer.getUsername());
            updatedCustomer.setPassword(customer.getPassword());
            updatedCustomer.setDateOfBirth(customer.getDateOfBirth());
            updatedCustomer.setPhone(customer.getPhone());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setAddress(customer.getAddress());
            customerRepository.save(updatedCustomer);

        } else {
            // Xử lý khi không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID: " + customer.getIdCustomer());
        }

    }

    @Override
    public void deleteCustomer(Long id) throws UserPrincipalNotFoundException {
        // Kiểm tra xem khách hàng tồn tại trong cơ sở dữ liệu không
        Optional<CustomerEntity> existingCustomer = customerRepository.findById(id);

        if (existingCustomer.isPresent()) {
            // Nếu khách hàng tồn tại, thực hiện thao tác xóa
            customerRepository.deleteById(id);
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng
            throw new UserPrincipalNotFoundException("Không tìm thấy khách hàng với ID " + id);
        }
    }
}
