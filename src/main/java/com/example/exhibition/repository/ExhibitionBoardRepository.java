package com.example.exhibition.repository;

import com.example.exhibition.model.ExhibitionBoard;
import com.example.exhibition.model.VideoFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionBoardRepository extends JpaRepository<ExhibitionBoard,Integer> {
}
