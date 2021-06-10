package com.example.exhibition.model;


import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@Entity
public class ExhibitionBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    @Lob
    private String content;

    @Column
    private Long imgId;

    @Column
    private Long videoId;

}
