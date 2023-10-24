package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.News;
import com.example.cinema_booking.entity.NewsEntity;
import com.example.cinema_booking.service.NewsService;
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
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Value("${imagePath}")
    private String imagePath;

    @GetMapping("newsList")
    public String listNews(Model model) {
        List<NewsEntity> news = newsService.getAllNews();
        model.addAttribute("news", news);
        return "adminHtml/adminNews";
    }

    @GetMapping("addNews")
    public String addNewsForm(@ModelAttribute News news, Model model) {
        model.addAttribute("news", news );
        return "adminHtml/addNews";
    }

    @PostMapping("addNews")
    public String addNews(@ModelAttribute("news") NewsEntity news, @RequestParam("file") MultipartFile file, Model model) throws IOException, URISyntaxException {
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
        news.setImageNew(file.getOriginalFilename());
        newsService.createNews(news);
        return "redirect:/admin/newsList";
    }

    @GetMapping("editNews/{id}")
    public String showEditNewsForm(@PathVariable Long id, Model model) {
        NewsEntity news = newsService.getNewsById(id);
        if (news != null) {
            model.addAttribute("news", news);
            return "adminHtml/editNews";
        } else {
            return "redirect:/admin/newsList";
        }
    }

    @PostMapping("saveEditedNews")
        public String saveEditedCinema(@ModelAttribute("news") NewsEntity news, @RequestParam("file") MultipartFile file,Model model) throws UserPrincipalNotFoundException {
        try {
            // Gọi phương thức service để cập nhật thông tin khách hàng
            news.setImageNew(file.getOriginalFilename());
            newsService.updateNews(news);
            return "redirect:/admin/newsList";
        } catch (UserPrincipalNotFoundException e) {
            // Xử lý khi không tìm thấy khách hàng
            model.addAttribute("messageError", "Không tìm thấy cinema.");
            return "adminHtml/editNews";
        }
    }

    @GetMapping("/deleteNews/{id}")
    public String deleteNews(@PathVariable Long id) throws UserPrincipalNotFoundException {
        newsService.deleteNews(id);
        return "redirect:/admin/newsList";
    }

}
