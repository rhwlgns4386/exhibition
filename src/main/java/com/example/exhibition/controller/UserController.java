package com.example.exhibition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
