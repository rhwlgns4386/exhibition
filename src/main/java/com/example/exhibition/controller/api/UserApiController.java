package com.example.exhibition.controller.api;


import com.example.exhibition.dto.UserDto;
import com.example.exhibition.model.User;
import com.example.exhibition.service.UserService;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;


    @PostMapping("/login")
    public String login(User user, HttpServletRequest request){
        return userService.getUser(user,request);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/registration")
    public String registration(User user){
        return userService.insertUser(user);
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
