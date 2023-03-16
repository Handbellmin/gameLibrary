package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Board {
    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="boardCategory_id")
    private BoardCategory boardCategory;

    @OneToMany(mappedBy = "board")
    private List<BoardComment> boardCommentList = new ArrayList<>();

    private String ttl;

    private String content;

    private String createId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String popupYn;

    //==연관관계 메서드 ==//
    public void setBoardCategory(BoardCategory boardCategory) {
        this.boardCategory = boardCategory;
        boardCategory.getBoard().add(this);
    }
    public void setUser(User user) {
        this.user = user;
        user.getBoardList().add(this);
    }
    //==생성 메서드 ==//
    /*public static class Bulider {
        private final User user;
        private final BoardCategory category;
        private final

    }*/





}
