package com.example.exhibition.controller;

import com.example.exhibition.model.ExhibitionBoard;
import com.example.exhibition.service.ExhibitionBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ExhibitionBoardController {

    private final ExhibitionBoardService exhibitionBoardService;


    @GetMapping("/uploadBoard")
    public String uploadBoard(){
        return "wrightBoard";
    }

    @GetMapping("/exhibitionBoardList")
    public ModelAndView exhibitionBoardList(){

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("exhibitionBoardList");
        modelAndView.addObject("boardList",exhibitionBoardService.getAll("yes"));
        return modelAndView;
    }

    //    @GetMapping("/selectBoards")
//    public String selectBoard(){
//        return "exhibitionBoard";
//    }
    @GetMapping("/selectBoard/{boardId}")
    public  ModelAndView getBoard(@PathVariable("boardId")Integer id){
        ExhibitionBoard exhibitionBoard=exhibitionBoardService.getBoard(id).get();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("exhibitionBoard");
        modelAndView.addObject("board",exhibitionBoard );
        modelAndView.addObject("imgUrl",exhibitionBoard.getImgId().getFilename());
        modelAndView.addObject("videoUrl",exhibitionBoard.getVideoId().getFilename());
        return modelAndView;
    }

    @PostMapping("/uploadBoard")
    public String uploadBoard(@RequestParam("img") MultipartFile img, @RequestParam("video") MultipartFile video, ExhibitionBoard exhibitionBoard, HttpServletRequest request) throws IOException
    {
        exhibitionBoardService.saveBoard(img,video,exhibitionBoard,request);
        return "redirect:/";
    }
}
