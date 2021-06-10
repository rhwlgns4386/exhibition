package com.example.exhibition.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class ExhibitionBoardDto {
    private Long id;
    private String author;
    private String title;
    private String content;

}
