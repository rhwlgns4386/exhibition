package com.example.exhibition.repository;

import com.example.exhibition.model.ImgFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgFileRepository extends JpaRepository<ImgFiles,Integer> {
}
