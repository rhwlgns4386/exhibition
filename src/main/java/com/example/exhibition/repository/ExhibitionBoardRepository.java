package com.example.exhibition.repository;

import com.example.exhibition.model.ExhibitionBoard;
import com.example.exhibition.model.VideoFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExhibitionBoardRepository extends JpaRepository<ExhibitionBoard,Integer> {


}
