package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {


    @GetMapping("/admin/home") // 어드민 홈
    public String adminHome(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/home";
    }

    @GetMapping("/admin/logout") // 어드민 홈
    public String adminLogout(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }


}
