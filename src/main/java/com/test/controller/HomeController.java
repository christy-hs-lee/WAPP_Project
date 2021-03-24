package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    @GetMapping("/") // 홈
    public String main(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/qna")
    public String qna(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "qna";
    }

    @GetMapping("/signIn")
    public String signIn(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "signIn";
    }
    @PostMapping("/signIn.do")
    public String signInPost(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "signIn";
    }


}
