package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.domain.User;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.UserEntity;
import com.example.cinema_booking.repository.UserRepository;
import com.example.cinema_booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
@RequestMapping("admin")
public class Admin_StaffController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("staffList")
    public String accountList(Model model) {
        List<UserEntity> users = userService.getUser();
        model.addAttribute("users", users);
        return "adminHtml/adminAccount";
    }

    @GetMapping("addStaff")
    public String addStaff(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "adminHtml/addStaff";
    }

    @PostMapping("addStaff")
    public String creatUser(@ModelAttribute User user, Model model) {
        Long idUser = userService.createUser(user);
        user.setIdUser(idUser);
        model.addAttribute("user", user);
        return "redirect:/admin/staffList";
    }

    @GetMapping("editStaff/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        UserEntity user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "adminHtml/editStaff";
        } else {
            // Xử lý khi không tìm thấy khách hàng
            return "redirect:/admin/staffList";
        }
    }

    @PostMapping("saveEditedStaff")
    public String saveEditedStaff(@ModelAttribute("user") User user, Model model) throws UserPrincipalNotFoundException {
        try {
            // Gọi phương thức service để cập nhật thông tin khách hàng
            userService.updateUser(user);
            return "redirect:/admin/staffList";
        } catch (UserPrincipalNotFoundException e) {
            // Xử lý khi không tìm thấy khách hàng
            model.addAttribute("messageError", "Không tìm thấy khách hàng.");
            return "adminHtml/editStaff";
        }
    }

    @GetMapping("/deleteStaff/{id}")
    public String deleteCustomer(@PathVariable Long id) throws UserPrincipalNotFoundException {
        // Gọi dịch vụ để xóa khách hàng từ cơ sở dữ liệu
        userService.deleteUser(id);
        return "redirect:/admin/staffList"; // Chuyển hướng đến danh sách khách hàng hoặc trang khác
    }
}
