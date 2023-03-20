package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class Game {
    @Id @GeneratedValue
    @Column(name="game_id")
    private Long id;

    @OneToMany(mappedBy = "game")
    private List<SubScribe> subScribeList = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    private BoardCategory boardCategory;

    @OneToMany(mappedBy = "game")
    private List<PublishSite> publishSites = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @OneToOne(fetch = LAZY)
    private PriorityGame priorityGame;

    private String gameName;
    private int gamePrice;

    private Long gameSatis;

    private String gameDescription;



    //==연관관계 메서드==//
    public void setCategory(Category category) {
        this.category = category;
        category.getGameList().add(this);
    }
    public void setBoardCategory(BoardCategory boardCategory) {
        this.boardCategory = boardCategory;
        boardCategory.setGame(this);
    }

    //==생성 메서드 ==//
    public static class Builder {
        private final String gameName;
        private int gamePrice;
        private Long gameSatis;
        private String gameDescription;


        public Builder(String gameName) {
            this.gameName = gameName;
        }
        public Builder setGamePrice(int gamePrice) {
            this.gamePrice = gamePrice;
            return this;
        }
        public Builder setGameSatis(Long gameSatis) {
            this.gameSatis = gameSatis;
            return this;
        }
        public Builder setGameDescription(String gameDescription) {
            this.gameDescription = gameDescription;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
    protected Game() {}

    private Game(Builder builder) {
        this.gameName = builder.gameName;
        this.gamePrice = builder.gamePrice;
        this.gameSatis = builder.gameSatis;
        this.gameDescription = builder.gameDescription;
    }
}
