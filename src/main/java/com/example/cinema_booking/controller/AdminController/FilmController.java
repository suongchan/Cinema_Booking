package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Category;
import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.service.CategoryService;
import com.example.cinema_booking.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
@RequestMapping("admin")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("filmList")
    public String listFilm(Model model) {
        List<FilmEntity> film = filmService.getAllFilm();
        model.addAttribute("films", film);
        return "adminHtml/adminFilm";
    }

    @GetMapping("addFilmPage")
    public String addFilmPage(Model model, Film film) {
        List<CategoryEntity> categories = categoryService.getAllCategory();
        model.addAttribute("films", film);
        model.addAttribute("categories", categories);
        return "adminHtml/addFilm"; // Trả về trang thêm phim (addFilm.html)
    }

    @PostMapping("addFilm")
    public String addFilm(@ModelAttribute("films") FilmEntity film) {

        filmService.addFilm(film);

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
    public String editFilm(@ModelAttribute("films") FilmEntity film) throws UserPrincipalNotFoundException {
        filmService.updateFilm(film);
        return "redirect:/admin/filmList"; // Chuyển hướng sau khi chỉnh sửa phim
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
