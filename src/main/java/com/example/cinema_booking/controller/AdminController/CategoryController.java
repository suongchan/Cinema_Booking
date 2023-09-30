package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Category;
import com.example.cinema_booking.domain.Customer;
import com.example.cinema_booking.entity.CategoryEntity;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
@RequestMapping("admin")
public class CategoryController {

    @Autowired
   private CategoryService categoryService;

    @GetMapping("categoryList")
    public String listCinema(Model model) {
        List<CategoryEntity> categories = categoryService.getAllCategory();
        model.addAttribute("category", categories);
        return "adminHtml/adminCategory";
    }
    @GetMapping("addCategory")
    public String addCategoryForm(@ModelAttribute Category category, Model model) {
        model.addAttribute("category", category);
        return "adminHtml/addCategory";
    }
    @PostMapping("addCategory")
    public String addCategory(@ModelAttribute("category")Category category)
    {
        Long id = categoryService.createCategory(category);
        category.setIdCategory(id);
        return "redirect:/admin/categoryList";
    }

    @GetMapping("editCategory/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        CategoryEntity category = categoryService.getCategoryById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "adminHtml/editCategory";
        } else {
            return "redirect:/admin/categoryList";
        }
    }

    @PostMapping("saveEditedCategory")
    public String saveEditedCategory(@ModelAttribute("category") Category category, Model model) throws UserPrincipalNotFoundException {
        try {
            // Gọi phương thức service để cập nhật thông tin khách hàng
            categoryService.updateCategory(category);
            return "redirect:/admin/categoryList";
        } catch (UserPrincipalNotFoundException e) {
            // Xử lý khi không tìm thấy khách hàng
            model.addAttribute("messageError", "Không tìm thấy category.");
            return "adminHtml/editCategory";
        }
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id) throws UserPrincipalNotFoundException {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categoryList";
    }


}
