package com.example.exhibition.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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

    @Column
    private Long imgId;

    @Column
    private Long videoId;

    @OneToMany(mappedBy = "boardId" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"boardId"})
    private List<BoardGood> boardGood;

    @Column
    private Integer goodCount;

}
