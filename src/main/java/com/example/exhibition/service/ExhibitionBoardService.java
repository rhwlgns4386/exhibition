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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

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
        Optional<User> user=userRepository.findByNameAndPassword(session.getAttribute("userId").toString(),session.getAttribute("password").toString());
        System.out.println(user.get());
        exhibitionBoard.setAuthor(user.get());
        exhibitionBoard.setApply("NoN");
        exhibitionBoard.setGoodCount(0);
        exhibitionBoard.setImgId(getImgFileId(img,request));
        exhibitionBoard.setVideoId(getVideoFileId(video,request));
        exhibitionBoardRepository.save(exhibitionBoard);
    }

    @Transactional
    public List<ExhibitionBoard> getAll(String apply) {
        return exhibitionBoardRepository.findByApply(apply);
    }

    @Transactional
    public Optional<ExhibitionBoard> getBoard(Integer id) {
        System.out.println(exhibitionBoardRepository.findById(id).get().getImgId().getFilePath());
        return exhibitionBoardRepository.findById(id);
    }

    @Transactional
    public void deleteBoard(Integer id) {
        ExhibitionBoard exhibitionBoard=exhibitionBoardRepository.findById(id).get();
        exhibitionBoardRepository.delete(exhibitionBoard);
    }

    @Transactional
    public Integer goodCheck(Integer boardId, HttpSession session) {
        Optional<ExhibitionBoard> board=exhibitionBoardRepository.findById(boardId);
        Optional<ExhibitionBoard> exhibitionBoard=exhibitionBoardRepository.findById(boardId);
        Optional<User> user=userRepository.findByNameAndPassword(session.getAttribute("userId").toString(),session.getAttribute("password").toString());
        Optional<BoardGood> boardGood=boardGoodRepository.findByUserAndBoard(user.get(),exhibitionBoard.get());
        if(boardGood.isPresent()){
            boardGoodRepository.delete(boardGood.get());
            List<BoardGood> boardGoods=boardGoodRepository.findByBoardId(boardId);
            exhibitionBoard.get().setGoodCount(boardGoods.size());
            return boardGoods.size();
        }
        else{
            return saveGood(boardId,user.get(),exhibitionBoard.get());
        }

    }
    public Integer saveGood(Integer boardId, User user,ExhibitionBoard exhibitionBoard) {
        BoardGood boardGood=new BoardGood();
        boardGood.setUser(user);
        boardGood.setBoard(exhibitionBoard);
        boardGood.setGoodNum(1);
        boardGoodRepository.save(boardGood);
        List<BoardGood> boardGoods=boardGoodRepository.findByBoardId(boardId);
        exhibitionBoard.setGoodCount(boardGoods.size());
        return boardGoods.size();
    }

    @Transactional
    public String setApply(Integer id) {
        System.out.println(id);
        Optional<ExhibitionBoard> exhibitionBoard=exhibitionBoardRepository.findById(id);
        exhibitionBoard.get().setApply("yes");
        return "ok";
    }

    private VideoFiles getVideoFileId(MultipartFile video, HttpServletRequest request) throws IOException {
        VideoFiles videoFile=new VideoFiles();
        String path="/video/";
        String resource="/videos/video/";
        setFileData(video, request, videoFile, path,resource);
        videoFileRepository.save(videoFile);
        return videoFile;
    }

    private ImgFiles getImgFileId(MultipartFile img, HttpServletRequest request) throws IOException {
        ImgFiles imgFiles=new ImgFiles();
        String path="/img/";
        String resource="/images/img/";
        setFileData(img, request, imgFiles, path,resource);
        imgFileRepository.save(imgFiles);
        return imgFiles;
    }

    private void setFileData(MultipartFile video, HttpServletRequest request, Files files, String path,String resource) throws IOException {
        String filePath[]=getFilePath(video, request, path);
        files.setOrigFilename(video.getOriginalFilename());
        files.setFilePath(filePath[0]);
        files.setFilename(resource+filePath[1]);
    }

    private String[] getFilePath(@RequestParam("file") MultipartFile file, HttpServletRequest request, String url) throws IOException {
        TimeZone timeZone;
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd-HH-mm-ss");
        timeZone= TimeZone.getTimeZone("Asia/Seoul");
        format1.setTimeZone(timeZone);
        Integer index=file.getOriginalFilename().lastIndexOf('.');
        String extension=file.getOriginalFilename().substring(index,file.getOriginalFilename().length());
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletContext().getRealPath("/static")+url;
        String fileName="exehibition"+format1.format(new Date())+"_"+(int)(Math.random()*100)+extension;
        String filePath=path + fileName;
        File saveFile = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();
        String list[]={path,fileName};
        return list;
    }
}
