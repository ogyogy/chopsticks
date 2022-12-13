package com.example.chopsticks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChopsticksController {
    @GetMapping("/")
    public String index(Model model) {
        // プレイヤー生成
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        // ゲーム開始
        Game game = new Game();
        game.start(player1, player2);
        // メッセージをViewに設定
        model.addAttribute("messages", game.getMessages());
        // Viewを返却
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
