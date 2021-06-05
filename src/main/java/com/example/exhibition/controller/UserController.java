package com.example.exhibition.controller;


import com.example.exhibition.dto.UserDto;
import com.example.exhibition.model.User;
import com.example.exhibition.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login/{id}/{password}")
    public void login(@PathVariable("id")String id,@PathVariable("password")String password){
        userService.getUser(id,password);
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
