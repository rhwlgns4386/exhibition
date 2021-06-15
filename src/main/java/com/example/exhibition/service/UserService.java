package com.example.exhibition.service;


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
    public User getUser(User users, HttpServletRequest request) {
        List<Optional<User>> user=userRepository.findByNameAndPassword(users.getName(),users.getPassword());
        if(user.get(0).isPresent()){
            HttpSession session = request.getSession();
            session.setAttribute("userId",user.get(0).get().getName());
            session.setAttribute("password",user.get(0).get().getPassword());
        }
        return user.get(0).get();
    }

    @Transactional
    public void insertUser(User user) {
        userRepository.save(user);
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
