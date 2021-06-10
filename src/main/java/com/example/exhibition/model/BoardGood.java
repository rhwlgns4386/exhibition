package com.example.exhibition.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Setter
@Getter
@Entity
public class BoardGood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "exhibitionBoardId")
    private ExhibitionBoard boardId;

    @Column
    private Integer goodNum;
}
