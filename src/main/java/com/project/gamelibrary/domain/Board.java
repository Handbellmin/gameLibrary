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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="boardCategory_id")
    private BoardCategory boardCategory;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;

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
    //==생성 메서드 ==//
    public static class Builder {
        private final String ttl;
        private final String content;
        private final String createId;
        private final String PopupYn;

        private final LocalDateTime createDate;


        public Builder( String ttl, String content, String createId, String popupYn, LocalDateTime createDate) {
            this.ttl = ttl;
            this.content = content;
            this.createId = createId;
            this.PopupYn = popupYn;
            this.createDate = createDate;
        }

        public Board build() { return new Board(this);}

    }
    protected Board() {}
    private Board(Builder builder) {
        this.ttl = builder.ttl;
        this.content = builder.content;
        this.createId = builder.createId;
        this.createDate = builder.createDate;
    }

    //== 비즈니스 로직 ==//




}
