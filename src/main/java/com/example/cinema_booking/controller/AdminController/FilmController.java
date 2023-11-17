package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Category;
import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.service.CategoryService;
import com.example.cinema_booking.service.FilmService;
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
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private CategoryService categoryService;

    @Value("${imagePath}")
    private String imagePath;

    @GetMapping("filmList")
    public String listFilm(Model model) {
        List<FilmEntity> film = filmService.getAllFilm();
        model.addAttribute("films", film);
        return "adminHtml/adminFilm";
    }

    @GetMapping("addFilmPage")
    public String addFilmPage(Model model) {
        List<CategoryEntity> categories = categoryService.getAllCategory();
        model.addAttribute("filmEntity", new FilmEntity());
        model.addAttribute("categories", categories);
        return "adminHtml/addFilm"; // Trả về trang thêm phim (addFilm.html)
    }

    @PostMapping("/addFilm")
    public String addFilm(@ModelAttribute FilmEntity filmEntity, @RequestParam("file") MultipartFile file, @RequestParam("file1") MultipartFile file1, Model model) throws IOException, URISyntaxException{
        String message = "";
        try {
            File fileImage = new File(imagePath + file.getOriginalFilename());
            try (OutputStream os = new FileOutputStream(fileImage)) {
                os.write(file.getBytes());
            }
            message = "Uploaded the file successfully: " +file.getOriginalFilename();
            model.addAttribute("message", message);
        } catch (Exception e){
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }

        String message2 = "";
        try {
            File fileImage1 = new File(imagePath + file1.getOriginalFilename());
            try (OutputStream os = new FileOutputStream(fileImage1)) {
                os.write(file1.getBytes());
            }
            message2 = "Uploaded the file successfully: " +file1.getOriginalFilename();
            model.addAttribute("message", message2);
        } catch (Exception e){
            message2 = "Could not upload the file: " + file1.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message2);
        }
        filmEntity.setImageFilm(file.getOriginalFilename());
        filmEntity.setImageFilm1(file1.getOriginalFilename());
        filmEntity.setStatus(true);
        filmService.addFilm(filmEntity);
        return "redirect:/admin/filmList";
    }

    @GetMapping("/editFilm/{id}")
    public String editFilmPage(@PathVariable Long id, Model model) {
        FilmEntity film = filmService.getFilmById(id);
        List<CategoryEntity> categories = categoryService.getAllCategory();
        model.addAttribute("films", film);
        model.addAttribute("categories", categories);
        return "adminHtml/editFilm";
    }

    @PostMapping("/editFilm")
    public String editFilm(@ModelAttribute("films") FilmEntity film, @RequestParam("file") MultipartFile file, Model model) throws UserPrincipalNotFoundException {
        try {
            if (!file.isEmpty()){
                // Nếu người dùng đã tải lên tệp mới, thì cập nhật trường imageFilm
                film.setImageFilm(file.getOriginalFilename());
            } else {
                // Nếu không tải lên tệp mới, giữ nguyên tệp hiện tại
                FilmEntity existingFilm = filmService.getFilmById(film.getIdFilm());
                if (existingFilm != null){
                    film.setImageFilm(existingFilm.getImageFilm());
                }
            }
            filmService.updateFilm(film);
            return "redirect:/admin/filmList";
        } catch (UserPrincipalNotFoundException e) {
            model.addAttribute("messageError", "Không tìm thấy phim");
            return "adminHtml/editFilm";
        }
    }

    @GetMapping("/deleteFilm/{id}")
    public String deleteFilm(@PathVariable Long id) throws UserPrincipalNotFoundException {
        filmService.deleteFilm(id);
        return "redirect:/admin/filmList";
    }

    @GetMapping("film-search")
    public String searchPhone(@RequestParam("name_film") String name_film, Model model) {

        List<FilmEntity> films = filmService.searchFilmsByName(name_film);
        model.addAttribute("films", films);
        return "adminHtml/adminFilm";
    }
}
