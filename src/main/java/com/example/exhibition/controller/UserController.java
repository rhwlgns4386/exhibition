package com.example.exhibition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class UserController {

    @GetMapping("/")
    public String mainboard(){
        return "user";
    }

    @GetMapping("/login")
    public String loginpage(){
        return "login";
    }

    @GetMapping("/registration")
    public  String registration(){
        return "registration";
    }

    @GetMapping("/user/update")
    public  String update(){
        return "userUpdate";
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
        return "redirect:/";
    }



}
