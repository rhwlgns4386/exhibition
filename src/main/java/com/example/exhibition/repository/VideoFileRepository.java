package com.example.exhibition.repository;

import com.example.exhibition.model.ImgFiles;
import com.example.exhibition.model.VideoFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoFileRepository extends JpaRepository<VideoFiles,Integer> {
}
