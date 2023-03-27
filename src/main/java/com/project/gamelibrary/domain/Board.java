package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class Board {
    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="boardCategory_id")
    private BoardCategory boardCategory;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<BoardComment> boardCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Files> files = new ArrayList<>();

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
    //==생성 메서드 ==//
    public static class Builder {
        private final String ttl;
        private final String content;
        private final String createId;
        private final String popupYn;

        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public Builder( String ttl, String content, String createId, String popupYn) {
            this.ttl = ttl;
            this.content = content;
            this.createId = createId;
            this.popupYn = popupYn;
        }
        public Builder setCreateDate() {
            this.createDate = LocalDateTime.now();
            return this;
        }
        public Builder setUpdateDate() {
            this.updateDate = LocalDateTime.now();
            return this;
        }
        public Board build() { return new Board(this);}

    }
    protected Board() {}
    private Board(Builder builder) {
        this.ttl = builder.ttl;
        this.content = builder.content;
        this.createId = builder.createId;
        this.createDate = builder.createDate;
        this.popupYn = builder.popupYn;
        this.updateDate = builder.updateDate;
    }
    public void addFile(Files files) {
        this.files.add(files);

        if(files.getBoard() != this){
            files.setBoard(this);
        }
    }
    //== 비즈니스 로직 ==//




}
