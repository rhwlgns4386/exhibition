package com.example.exhibition.controller;

import com.example.exhibition.model.ExhibitionBoard;
import com.example.exhibition.service.ExhibitionBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdminController {

    private final ExhibitionBoardService exhibitionBoardService;

    @GetMapping("/userApplyList")
    public ModelAndView exhibitionBoardList(){

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("userApplyList");
        modelAndView.addObject("boardList",exhibitionBoardService.getAll("NoN"));
        return modelAndView;
    }

    @GetMapping("/selectApply/{boardId}")
    public  ModelAndView getBoard(@PathVariable("boardId")Integer id){
        ExhibitionBoard exhibitionBoard=exhibitionBoardService.getBoard(id).get();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("userApplyBoard");
        modelAndView.addObject("board",exhibitionBoard );
        modelAndView.addObject("imgUrl",exhibitionBoard.getImgId().getFilename());
        modelAndView.addObject("videoUrl",exhibitionBoard.getVideoId().getFilename());
        return modelAndView;
    }
}
