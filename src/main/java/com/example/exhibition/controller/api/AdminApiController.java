package com.example.exhibition.controller.api;

import com.example.exhibition.service.ExhibitionBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AdminApiController {

    private final ExhibitionBoardService exhibitionBoardService;

    @PutMapping("/acceptUserBoard")
    public String acceptUserBoard(@RequestBody Integer id){
        return exhibitionBoardService.setApply(id);
    }
}
