package com.example.exhibition.service;


import com.example.exhibition.model.ExhibitionBoard;
import com.example.exhibition.model.Files;
import com.example.exhibition.model.ImgFiles;
import com.example.exhibition.model.VideoFiles;
import com.example.exhibition.repository.ExhibitionBoardRepository;
import com.example.exhibition.repository.ImgFileRepository;
import com.example.exhibition.repository.VideoFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExhibitionBoardService {

    private final ImgFileRepository imgFileRepository;

    private final VideoFileRepository videoFileRepository;

    private final ExhibitionBoardRepository exhibitionBoardRepository;

    @Transactional
    public void saveBoard(MultipartFile img, MultipartFile video, ExhibitionBoard exhibitionBoard, HttpServletRequest request) throws IOException {
        //exhibitionBoard.setAuthor(request.getSession().getAttribute("userId").toString());
        exhibitionBoard.setImgId(getImgFileId(img,request));
        exhibitionBoard.setVideoId(getVideoFileId(video,request));
        exhibitionBoardRepository.save(exhibitionBoard);
    }

    private Long getVideoFileId(MultipartFile video, HttpServletRequest request) throws IOException {
        VideoFiles videoFile=new VideoFiles();
        String path="/video/";
        setFileData(video, request, videoFile, path);
        videoFileRepository.save(videoFile);
        return videoFile.getId();
    }

    private Long getImgFileId(MultipartFile img, HttpServletRequest request) throws IOException {
        ImgFiles imgFiles=new ImgFiles();
        String path="/img/";
        setFileData(img, request, imgFiles, path);
        imgFileRepository.save(imgFiles);
        return imgFiles.getId();
    }

    private void setFileData(MultipartFile video, HttpServletRequest request, Files files, String path) throws IOException {
        files.setOrigFilename(video.getOriginalFilename());
        files.setFilePath(getFilePath(video, request, path));
        files.setFilename(video.getOriginalFilename());
    }

    private String getFilePath(@RequestParam("file") MultipartFile file, HttpServletRequest request, String url) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletContext().getRealPath("/static")
                + url + file.getOriginalFilename();
        File saveFile = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();
        return path;
    }

    public List<ExhibitionBoard> getAll() {
        return exhibitionBoardRepository.findAll();
    }

    public Optional<ExhibitionBoard> getBoard(Integer id) {
        return exhibitionBoardRepository.findById(id);
    }
}
