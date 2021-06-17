package com.example.exhibition.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class ExhibitionBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibitionBoardId")
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId")
    private User author;

    @NotNull
    @Lob
    private String content;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name="imgId")
    private ImgFiles imgId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name="videoId")
    private VideoFiles videoId;

    @OneToMany(mappedBy = "board" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    private List<BoardGood> boardGood=new ArrayList<>();

    @Column
    private Integer goodCount;

    public void setAuthor(User author) {
        this.author = author;
        author.getExhibitionBoards().add(this);
    }
}
