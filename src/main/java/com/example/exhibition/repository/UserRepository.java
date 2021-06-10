package com.example.exhibition.repository;

import com.example.exhibition.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<Optional<User>> findByNameAndPassword(String name, String password);
}
