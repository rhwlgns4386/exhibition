package com.example.exhibition.controller;


import com.example.exhibition.dto.ExhibitionBoardDto;
import com.example.exhibition.model.ExhibitionBoard;
import com.example.exhibition.service.ExhibitionBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ExhibitionBoardController {

    private final ExhibitionBoardService exhibitionBoardService;

    @GetMapping(path = "/upload")
    public void upload(){}


    @PostMapping("/post")
    public void test(@RequestParam("img") MultipartFile img,@RequestParam("video") MultipartFile video,ExhibitionBoard exhibitionBoard
            ,HttpServletRequest request) throws IOException
    {
        exhibitionBoardService.saveBoard(img,video,exhibitionBoard,request);
    }

}
