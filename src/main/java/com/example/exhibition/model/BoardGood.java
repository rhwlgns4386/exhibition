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
    private User user;

    @ManyToOne
    @JoinColumn(name = "exhibitionBoardId")
    private ExhibitionBoard board;

    @Column
    private Integer goodNum;

    public void setBoard(ExhibitionBoard board) {
        this.board = board;
        board.getBoardGood().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getBoardGood().add(this);
    }
}
