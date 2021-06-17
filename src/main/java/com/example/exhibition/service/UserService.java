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
            return "idErr";
        }
        if(!user.get().getPassword().equals(users.getPassword())){
            System.out.println("Login fail");
            return "pwErr";
        }
        HttpSession session = request.getSession();
        session.setAttribute("userId",user.get().getName());
        session.setAttribute("password",user.get().getPassword());
        if(user.get().getAdmin().equals("yes")){
            session.setAttribute("admin",user.get().getAdmin());
        }
        return "ok";
    }

    @Transactional
    public String insertUser(User user) {
        System.out.println(user.getName());
        if(!userRepository.findByName(user.getName()).isPresent()){
            userRepository.save(user);
            return "ok";
        }
        return "no";
    }

    @Transactional
    public String updateUser(User user) {
        userRepository.findByName(user.getName()).get().setPassword(user.getPassword());
        return "ok";
    }

    @Transactional
    public String deleteUser(String id,HttpServletRequest request) {
        HttpSession session=request.getSession();
        Optional<User> user = userRepository.findByName(id);
        userRepository.delete(user.get());
        session.invalidate();
        return "ok";
    }

}
