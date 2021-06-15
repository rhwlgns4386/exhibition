package com.example.exhibition.controller.api;


import com.example.exhibition.model.User;
import com.example.exhibition.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/user")
    public  String user(){
        return "login";
    }
    @GetMapping("/registration")
    public  String registration(){
        return "registration";
    }

    @PostMapping("/login")
    public User login(@RequestBody User user, HttpServletRequest request){

        return userService.getUser(user,request);
    }

    @ResponseBody
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
