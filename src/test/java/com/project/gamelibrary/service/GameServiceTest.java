package com.project.gamelibrary.service;

import com.project.gamelibrary.domain.BoardCategory;
import com.project.gamelibrary.domain.Game;
import com.project.gamelibrary.repository.BoardCategoryRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@SpringBootTest
@Transactional
public class GameServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    GameService gameService;

    @Autowired
    BoardCategoryRepository boardCategoryRepository;
    
    @Test
    public void saveGame1() throws Exception {
        //given
        Game game = new Game.Builder("다크소울")
                .setGamePrice(36000)
                .setGameSatis(5L)
                .setGameDescription("정말 어려운 게임")
                .build();
        //when
        gameService.gameSave(game);
        //then
        List<BoardCategory> boardCategory = boardCategoryRepository.findAll();
    }
}