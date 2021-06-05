package com.example.exhibition.service;


import com.example.exhibition.dto.UserDto;
import com.example.exhibition.model.User;
import com.example.exhibition.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void getUser(String id, String password) {
        List<User> user=userRepository.findByNameAndPassword(id,password);
        System.out.println(user);
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
