package com.example.exhibition.controller;



import com.example.exhibition.model.ExhibitionBoard;
import com.example.exhibition.service.ExhibitionBoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ExhibitionBoardController {

    private final ExhibitionBoardService exhibitionBoardService;

    @GetMapping("/boardAll")
    public List<ExhibitionBoard> getBoardAll(){
        return exhibitionBoardService.getAll();
    }

    @GetMapping("/selectBoard/{boardId}")
    public @ResponseBody ExhibitionBoard getBoard(@PathVariable("boardId")Integer id){
        return exhibitionBoardService.getBoard(id).get();
    }

    @PostMapping("/uploadBoard")
    public void uploadBoard(@RequestParam("img") MultipartFile img,@RequestParam("video") MultipartFile video,ExhibitionBoard exhibitionBoard,HttpServletRequest request) throws IOException
    {
        exhibitionBoardService.saveBoard(img,video,exhibitionBoard,request);
    }

    @DeleteMapping("/deleteBoard/{boardId}")
    public void deleteBoard(@PathVariable("boardId")Integer id) {
        exhibitionBoardService.deleteBoard(id);
    }

    @PutMapping("/clickGood/{boardId}")
    public void clickGood(@PathVariable("boardId")Integer boardId,HttpServletRequest request){
        HttpSession session=request.getSession();
        boolean check=exhibitionBoardService.goodCheck(boardId,session);
        if(!check){
            exhibitionBoardService.saveGood( boardId,session);
        }
        else{
            exhibitionBoardService.deleteGood(boardId,session);
        }
    }
}
