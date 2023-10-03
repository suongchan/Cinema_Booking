package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.entity.ShowEntity;
import com.example.cinema_booking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class ShowController {

    @Autowired
    private ShowService showService;


    @GetMapping("listShow")
    public String listShow(){
        List<ShowEntity> show = showService.getShow();

        return "customerHtml/show";
    }

}
