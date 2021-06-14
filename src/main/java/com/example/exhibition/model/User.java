package com.example.exhibition.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity(name="userinfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer id;

    private String name;

    private String password;


    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<BoardGood> boardGood=new ArrayList<>();

    @OneToMany(mappedBy = "author" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<ExhibitionBoard> exhibitionBoards=new ArrayList<>();


}
