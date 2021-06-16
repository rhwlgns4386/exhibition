package com.example.exhibition.service;


import com.example.exhibition.dto.UserDto;
import com.example.exhibition.model.User;
import com.example.exhibition.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public String getUser(User users, HttpServletRequest request) {
        System.out.println(users.getName());
        Optional<User> user=userRepository.findByName(users.getName());
        if(!user.isPresent()){
            return "redirect:/users/loginForm";
        }
        if(!user.get().getPassword().equals(users.getPassword())){
            System.out.println("Login fail");
            return "redirect:/users/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute("userId",user.get().getName());
        session.setAttribute("password",user.get().getPassword());
        return "redirect:/";
    }

    @Transactional
    public String insertUser(User user) {
        if(!userRepository.findByName(user.getName()).isPresent()){
            userRepository.save(user);
            return "redirect:/";
        }
        return "registration";
    }

    @Transactional
    public void updateUser(User user) {
        Optional<User>updateUser=userRepository.findById(user.getId());
        if(updateUser.isPresent()){
            User users=updateUser.get();
            users.setPassword(user.getPassword());
        }
    }

    @Transactional
    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
    }

}
