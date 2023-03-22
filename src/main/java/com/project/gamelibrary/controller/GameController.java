package com.project.gamelibrary.controller;

import com.project.gamelibrary.Form.GameForm;
import com.project.gamelibrary.domain.Game;
import com.project.gamelibrary.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    @GetMapping("/games")
    public String gameList(Model model){
        List<Game> games = gameService.findAll();
        model.addAttribute("games",games);
        return "games/GameList";
    }

    @GetMapping("/games/new")
    public String createForm(Model model) {
        model.addAttribute("form", new GameForm());
        return "games/createGameForm";
    }

    //직접 게임 등록
    @PostMapping("/games/new")
    public String create(GameForm gameForm) {
        Game game = new Game.Builder(gameForm.getGameName())
                .setGamePrice(gameForm.getGamePrice())
                .setGameSatis(gameForm.getGameSatis())
                .setGameDescription(gameForm.getGameDescription())
                .build();
        gameService.gameSave(game);

        return "redirect:/games";
    }

    @GetMapping("/games/edit")
    public String edit(Long gameId, Model model) {
        model.addAttribute(gameService.findOne(gameId));
        return "games/editGameForm";
    }

    @PostMapping("/games/edit")
    public String edit(GameForm gameForm) {
        Game game = new Game.Builder(gameForm.getGameName())
                .setGamePrice(gameForm.getGamePrice())
                .setGameSatis(gameForm.getGameSatis())
                .setGameDescription(gameForm.getGameDescription())
                .build();
        gameService.gameSave(game);

        return "redirect:/games";
    }

    @GetMapping("/games/receive")
    public String receiveGames() {
        //액션 카테고리 크롤링

        return "home";
    }
}
