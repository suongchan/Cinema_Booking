package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.domain.User;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.UserEntity;
import com.example.cinema_booking.repository.CustomerRepository;
import com.example.cinema_booking.repository.UserRepository;
import com.example.cinema_booking.service.CustomerService;
import com.example.cinema_booking.service.UserService;
import com.example.cinema_booking.validator.CustomerRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
@RequestMapping("admin")
public class Admin_CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping()
    public String adminHome() {

        return "adminHtml/admin";
    }



    @GetMapping("customerList")
    public String listCustomer(Model model) {
        List<CustomerEntity> customers = customerService.getCustomerByRole();
        model.addAttribute("customers", customers);
        return "adminHtml/adminCustomer";
    }

    @GetMapping("addCustomer")
    public String register(@ModelAttribute Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "adminHtml/addCustomer";
    }

    @PostMapping("addCustomer")
    public String creatUser(@ModelAttribute Customer customer, Model model) {
        Long id = customerService.createCustomer(customer);
        customer.setIdCustomer(id);
        model.addAttribute("customer", customer);
        return "redirect:/admin/customerList";
    }

    @GetMapping("editCustomer/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        CustomerEntity customer = customerService.getCustomerById(id);
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "adminHtml/editCustomer";
        } else {
            // Xử lý khi không tìm thấy khách hàng
            return "redirect:/admin/customerList";
        }
    }

    @PostMapping("saveEditedCustomer")
    public String saveEditedCustomer(@ModelAttribute("customer") Customer customer, Model model) throws UserPrincipalNotFoundException {
        try {
            // Gọi phương thức service để cập nhật thông tin khách hàng
            customerService.updateCustomer(customer);
            return "redirect:/admin/customerList";
        } catch (UserPrincipalNotFoundException e) {
            // Xử lý khi không tìm thấy khách hàng
            model.addAttribute("messageError", "Không tìm thấy khách hàng.");
            return "adminHtml/editCustomer";
        }
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) throws UserPrincipalNotFoundException {
        // Gọi dịch vụ để xóa khách hàng từ cơ sở dữ liệu
        customerService.deleteCustomer(id);
        return "redirect:/admin/customerList"; // Chuyển hướng đến danh sách khách hàng hoặc trang khác
    }

    @GetMapping("customer-search")
    public String searchPhone(@RequestParam("phone") String phone, Model model) {

        List<CustomerEntity> customers = customerService.searchCustomersByPhone(phone);
        model.addAttribute("customers", customers);
        return "adminHtml/adminCustomer";
    }
}


