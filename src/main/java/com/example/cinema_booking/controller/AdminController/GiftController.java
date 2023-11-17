package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Gift;
import com.example.cinema_booking.domain.News;
import com.example.cinema_booking.entity.GiftEntity;
import com.example.cinema_booking.entity.NewsEntity;
import com.example.cinema_booking.service.GiftService;
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
public class GiftController {

    @Autowired
    private GiftService giftService;

    @Value("${imagePath}")
    private String imagePath;

    @GetMapping("giftList")
    public String listGift(Model model) {
        List<GiftEntity> gifts = giftService.getAllGift();
        model.addAttribute("gifts", gifts);
        return "adminHtml/adminGift";
    }

    @GetMapping("addGift")
    public String addGiftForm(@ModelAttribute Gift gifts, Model model) {
        model.addAttribute("gifts", gifts );
        return "adminHtml/addGift";
    }

    @PostMapping("addGift")
    public String addGift(@ModelAttribute("gifts") GiftEntity gifts, @RequestParam("file") MultipartFile file, Model model) throws IOException, URISyntaxException {
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
        gifts.setImage(file.getOriginalFilename());
        giftService.createGift(gifts);
        return "redirect:/admin/giftList";
    }

    @GetMapping("editGift/{id}")
    public String showEditGiftForm(@PathVariable Long id, Model model) {
        GiftEntity gift = giftService.getGiftById(id);
        if (gift != null) {
            model.addAttribute("gifts", gift);
            return "adminHtml/editGift";
        } else {
            return "redirect:/admin/giftList";
        }
    }

    @PostMapping("saveEditedGift")
    public String saveEditedGift(@ModelAttribute("gift") GiftEntity gift, @RequestParam("file") MultipartFile file, Model model) throws UserPrincipalNotFoundException {
        try {
            if (!file.isEmpty()) {
                // Nếu người dùng đã tải lên tệp mới, thì cập nhật trường imageNew
                gift.setImage(file.getOriginalFilename());
            } else {
                // Nếu không tải lên tệp mới, giữ nguyên tệp hiện tại
                GiftEntity existingNews = giftService.getGiftById(gift.getId());
                if (existingNews != null) {
                    gift.setImage(existingNews.getImage());
                }
            }
            giftService.updateGift(gift);
            return "redirect:/admin/giftList";
        } catch (UserPrincipalNotFoundException e) {
            model.addAttribute("messageError", "Không tìm thấy tin tức.");
            return "adminHtml/editGift";
        }
    }

    @GetMapping("/deleteGift/{id}")
    public String deleteGift(@PathVariable Long id) throws UserPrincipalNotFoundException {
        giftService.deleteGift(id);
        return "redirect:/admin/giftList";
    }

}
