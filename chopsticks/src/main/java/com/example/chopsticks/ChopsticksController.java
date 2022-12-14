package com.example.chopsticks;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChopsticksController {
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/")
    public String index(Model model) {
        // DBからIDをキーにプレイヤーを検索
        Optional<Player> player1Optional = playerRepository.findById(1);
        Optional<Player> player2Optional = playerRepository.findById(2);

        // プレイヤーが登録済みの場合取得
        // プレイヤーが未登録の場合はデフォルト値を設定
        Player player1 = player1Optional.orElse(new Player("Player 1"));
        Player player2 = player2Optional.orElse(new Player("Player 2"));

        // Viewにプレイヤー名を設定
        model.addAttribute("player1Name", player1.getName());
        model.addAttribute("player2Name", player2.getName());

        return "index";
    }

    @GetMapping("/result")
    public String result(@RequestParam String player1Name, @RequestParam String player2Name, Model model) {
        Player player1 = null;
        Player player2 = null;

        // DBからIDをキーにプレイヤーを検索
        Optional<Player> player1Optional = playerRepository.findById(1);
        Optional<Player> player2Optional = playerRepository.findById(2);

        if (player1Optional.isPresent() && player2Optional.isPresent()) {
            // プレイヤーがDBに登録済みであれば取得
            player1 = player1Optional.get();
            player2 = player2Optional.get();
        } else {
            // プレイヤーがDBに未登録であれば生成
            player1 = new Player("Player 1");
            player2 = new Player("Player 2");
        }

        // プレイヤー名を更新
        player1.setName(player1Name);
        player2.setName(player2Name);

        // プレイヤーをDBに登録
        playerRepository.save(player1);
        playerRepository.save(player2);

        // ゲーム開始
        Game game = new Game();
        game.start(player1, player2);

        // ゲームの結果をメッセージとしてViewに設定
        model.addAttribute("messages", game.getMessages());

        // Viewを返却
        return "result";
    }
}
