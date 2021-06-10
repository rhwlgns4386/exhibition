package com.example.exhibition.controller;


import com.example.exhibition.model.User;
import com.example.exhibition.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public  void user(){}

    @GetMapping("/login/{id}/{password}")
    public void login(@PathVariable("id")String id, @PathVariable("password")String password, HttpServletRequest request){
        HttpSession session = request.getSession();
        userService.getUser(id,password,session);
    }

    @PostMapping("/registration")
    public void registration(@RequestBody User user){
        userService.insertUser(user);
    }

    @PutMapping("/userUpdate")
    public void update(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("userDelete/{id}")
    public void delete(@PathVariable("id")Integer id){
        userService.deleteUser(id);
    }

}
