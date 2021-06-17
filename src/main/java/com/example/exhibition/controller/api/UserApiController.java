package com.example.exhibition.controller.api;


import com.example.exhibition.dto.UserDto;
import com.example.exhibition.model.User;
import com.example.exhibition.repository.UserRepository;
import com.example.exhibition.service.UserService;
import lombok.Delegate;
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
    private  final UserRepository userRepository;


    @PostMapping(value = "/login")
    public String login(@RequestBody User user, HttpServletRequest request){
        return userService.getUser(user,request);
    }

    @GetMapping("/findUser/{userName}")
    @ResponseBody
    public String findUser(@PathVariable("userName")String userName){
        if(userRepository.findByName(userName).isPresent()){
            return "No";
        }
        else {
            return "ok";
        }
    }

    @PostMapping("/registration")
    public String registration(@RequestBody User user){
        return userService.insertUser(user);
    }

    @PutMapping("/userUpdate")
    public String update(@RequestBody User user){
        System.out.println(user.getPassword());
        if(user.getName()=="" || user.getPassword()==""){
            return "no";
        }
        return userService.updateUser(user);
    }

    @DeleteMapping("userDelete/{id}")
    public String delete(@PathVariable("id")String id,HttpServletRequest request){
        return userService.deleteUser(id,request);
    }


}
