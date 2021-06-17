package com.example.exhibition.controller.api;



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
public class ExhibitionBoardApiController {

    private final ExhibitionBoardService exhibitionBoardService;

    @GetMapping("/boardAll")
    public List<ExhibitionBoard> getBoardAll(){
        return exhibitionBoardService.getAll();
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
