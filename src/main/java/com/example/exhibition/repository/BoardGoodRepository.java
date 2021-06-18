package com.example.exhibition.repository;

import com.example.exhibition.model.BoardGood;
import com.example.exhibition.model.ExhibitionBoard;

import java.util.List;
import java.util.Optional;

import com.example.exhibition.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BoardGoodRepository  extends JpaRepository<BoardGood,Integer> {

    Optional<BoardGood> findByUserAndBoard(User user,ExhibitionBoard board);

    List<BoardGood> findByBoardId(Integer boardId);

    List<BoardGood> findByUser(User user);
}
