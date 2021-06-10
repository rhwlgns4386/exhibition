package com.example.exhibition.service;


import com.example.exhibition.model.*;
import com.example.exhibition.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    private final BoardGoodRepository boardGoodRepository;

    private final UserRepository userRepository;

    @Transactional
    public void saveBoard(MultipartFile img, MultipartFile video, ExhibitionBoard exhibitionBoard, HttpServletRequest request) throws IOException {
        HttpSession session=request.getSession();
        User user=userRepository.findByNameAndPassword(session.getAttribute("userId").toString(),session.getAttribute("password").toString()).get(0).get();
        exhibitionBoard.setAuthor(user);
        exhibitionBoard.setGoodCount(0);
        exhibitionBoard.setImgId(getImgFileId(img,request));
        exhibitionBoard.setVideoId(getVideoFileId(video,request));
        exhibitionBoardRepository.save(exhibitionBoard);
    }

    @Transactional
    public List<ExhibitionBoard> getAll() {
        return exhibitionBoardRepository.findAll();
    }

    @Transactional
    public Optional<ExhibitionBoard> getBoard(Integer id) {
        return exhibitionBoardRepository.findById(id);
    }

    @Transactional
    public void deleteBoard(Integer id) {
        exhibitionBoardRepository.delete(exhibitionBoardRepository.findById(id).get());
    }

    @Transactional
    public boolean goodCheck(Integer boardId, HttpSession session) {
        User user=userRepository.findByNameAndPassword(session.getAttribute("userId").toString(),session.getAttribute("password").toString()).get(0).get();
        Optional<BoardGood> check = boardGoodRepository.findByUserIdAndBoardId(user, exhibitionBoardRepository.findById(boardId).get());
       return check.isPresent();
    }
    @Transactional
    public void saveGood(Integer boardId, HttpSession session) {
        BoardGood boardGood=new BoardGood();
        User user=userRepository.findByNameAndPassword(session.getAttribute("userId").toString(),session.getAttribute("password").toString()).get(0).get();
        Optional<ExhibitionBoard> exhibitionBoard=exhibitionBoardRepository.findById(boardId);
        boardGood.setUserId(user);
        boardGood.setBoardId(exhibitionBoard.get());
        boardGood.getBoardId().getBoardGood().add(boardGood);
        boardGood.setGoodNum(1);
        boardGoodRepository.save(boardGood);
        //exhibitionBoard.get().setGoodCount(boardGoodRepository.findByBoardId(boardId).size());
    }
    @Transactional
    public void deleteGood(Integer boardId, HttpSession session) {
        User user=userRepository.findByNameAndPassword(session.getAttribute("userId").toString(),session.getAttribute("password").toString()).get(0).get();
        boardGoodRepository.delete(boardGoodRepository.findByUserIdAndBoardId(user,exhibitionBoardRepository.findById(boardId).get()).get());
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
}
