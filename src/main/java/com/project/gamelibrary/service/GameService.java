package com.project.gamelibrary.service;

import com.project.gamelibrary.domain.BoardCategory;
import com.project.gamelibrary.domain.Game;
import com.project.gamelibrary.repository.BoardCategoryRepository;
import com.project.gamelibrary.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    private final BoardCategoryRepository boardCategoryRepository;

    @Transactional
    public void gameSave(Game game) {
        //게임 정보 등록
        gameRepository.save(game);
        
        // 해당 게임 보드 카테고리 생성
        BoardCategory boardCategory = new BoardCategory();
        boardCategory.setCreateDate(LocalDateTime.now());
        boardCategory.setGame(game);
        boardCategoryRepository.save(boardCategory);

    }

    public Game findOne(Long gameId) {
        return gameRepository.findOne(gameId);
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }
}
