package com.example.exhibition.repository;

import com.example.exhibition.model.BoardGood;
import com.example.exhibition.model.ExhibitionBoard;

import java.util.List;
import java.util.Optional;

import com.example.exhibition.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BoardGoodRepository  extends JpaRepository<BoardGood,Integer> {

    Optional<BoardGood> findByUserIdAndBoardId(User userId, ExhibitionBoard exhibitionBoardId);

    List<BoardGood> findByBoardId(Integer boardId);
}
