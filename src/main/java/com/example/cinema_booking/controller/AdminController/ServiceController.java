package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.domain.Service;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.ServiceEntity;
import com.example.cinema_booking.service.ServiceService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
@RequestMapping("admin")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Value("${imagePath}")
    private String imagePath;

    @GetMapping("serviceList")
    public String listService(Model model) {
        List<ServiceEntity> service = serviceService.getAllService();
        model.addAttribute("services", service);
        return "adminHtml/adminService";
    }
    @GetMapping("addService")
    public String addServiceForm(@ModelAttribute Service service, Model model) {
        model.addAttribute("services", service);
        return "adminHtml/addService";
    }
    @PostMapping("addService")
    public String addService(@ModelAttribute("services")ServiceEntity service, @RequestParam("file") MultipartFile file, Model model) throws IOException, URISyntaxException
    {
        String message = "";
        try {
            File file1 = new File(imagePath + file.getOriginalFilename());
            try (OutputStream os = new FileOutputStream(file1)) {
                os.write(file.getBytes());
            }

            message = "Uploaded the file successfully: " +file.getOriginalFilename();
            model.addAttribute("message", message);
        } catch (Exception e){
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }
        service.setImage(file.getOriginalFilename());
        serviceService.createService(service);
        return "redirect:/admin/serviceList";
    }

    @GetMapping("editService/{id}")
    public String showEditServiceForm(@PathVariable Long id, Model model) {
        ServiceEntity service = serviceService.getServiceById(id);
        if (service != null) {
            model.addAttribute("service", service);
            return "adminHtml/editService";
        } else {
            return "redirect:/admin/serviceList";
        }
    }

    @PostMapping("saveEditedService")
    public String saveEditedService(@ModelAttribute("service") ServiceEntity service, Model model) throws UserPrincipalNotFoundException {
        try {
            // Gọi phương thức service để cập nhật thông tin khách hàng
            serviceService.updateService(service);
            return "redirect:/admin/serviceList";
        } catch (UserPrincipalNotFoundException e) {
            // Xử lý khi không tìm thấy khách hàng
            model.addAttribute("messageError", "Không tìm thấy cinema.");
            return "adminHtml/editService";
        }
    }

    @GetMapping("/deleteService/{id}")
    public String deleteService(@PathVariable Long id) throws UserPrincipalNotFoundException {
        serviceService.deleteService(id);
        return "redirect:/admin/serviceList";
    }

}
