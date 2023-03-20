package com.project.gamelibrary.controller;

import com.project.gamelibrary.Form.GameForm;
import com.project.gamelibrary.domain.Game;
import com.project.gamelibrary.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        String string_ID = "76561197960265728";
        String url = "https://store.steampowered.com/charts/topselling/KR";
        try {
            // Steam 사이트 접속하기
            Document doc = Jsoup.connect(url).get();

            // 인기 라이브러리 목록 가져오기
            Elements actiongame  = doc.select("#application_root > div > div > div > div > div.weeklytopsellers_ChartPlaceholder_3sJkw > table > tbody > tr:nth-child(1) > td.weeklytopsellers_CapsuleCell_18kGH > a > div");

            Element link = actiongame.select("a").first();

            // 인기 라이브러리 출력하기

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home";
    }
}
