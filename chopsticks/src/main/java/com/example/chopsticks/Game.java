package com.example.chopsticks;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Game {
    // Viewで表示するメッセージ
    private List<String> messages;

    public Game() {
        messages = new ArrayList<String>();
    }

    public void start(Player player1, Player player2) {
        // ==========
        // 初期状態
        // ==========
        this.addCurrentHandsMessage(player1);
        this.addCurrentHandsMessage(player2);

        // ==========
        // 1ターン目
        // ==========

        this.messages.add("Turn 1");

        // プレイヤー1にどちらの手を出すか聞く
        Hand selectedPlayer1Hand = player1.show();

        // プレイヤー1にプレイヤー2のどちらの手に攻撃するか聞く
        Hand attackedPlayer2Hand = player1.select(player2.getLeftHand(), player2.getRightHand());

        this.addSelectedHandMessage(player1, player2, selectedPlayer1Hand, attackedPlayer2Hand);

        // 選択されたそれぞれの手に基づきプレイヤー2の手を更新
        attackedPlayer2Hand.update(selectedPlayer1Hand);
        // プレイヤー2の残りの指の数を聞く
        // int player2TotalFingerNumber = player2.getTotalFingerNumber();
        // 0ならゲーム終了
        // if (player2TotalFingerNumber == 0) {
        // }

        this.addCurrentHandsMessage(player1);
        this.addCurrentHandsMessage(player2);

        // ==========
        // 2ターン目
        // ==========

        this.messages.add("Turn 2");

        // プレイヤー2にどちらの手を出すか聞く
        Hand selectedPlayer2Hand = player2.show();

        // プレイヤー2にプレイヤー1のどちらの手に攻撃するか聞く
        Hand attackedPlayer1Hand = player2.select(player1.getLeftHand(), player1.getRightHand());

        this.addSelectedHandMessage(player2, player1, selectedPlayer2Hand, attackedPlayer1Hand);

        // 選択されたそれぞれの手に基づきプレイヤー1の手を更新
        attackedPlayer1Hand.update(selectedPlayer2Hand);
        // プレイヤー1の残りの指の数を聞く
        // int player1TotalFingerNumber = player1.getTotalFingerNumber();
        // 0ならゲーム終了
        // if (player1TotalFingerNumber == 0) {
        // }

        this.addCurrentHandsMessage(player1);
        this.addCurrentHandsMessage(player2);
    }

    public String messageToString() {
        // 文字列はThymeleafのth:textでサニタイズされる
        String message = String.join("\n", this.messages);
        return message;
    }

    public void addCurrentHandsMessage(Player player) {
        this.messages.add(String.format("%s: L = %d, R = %d",
                player.getName(),
                player.getLeftHand().getFingerNumber(),
                player.getRightHand().getFingerNumber()));
    }

    public void addSelectedHandMessage(Player firstPlayer, Player secondPlayer, Hand firstHand, Hand secondHand) {
        String message = String.format("%s %s -> %s %s",
                firstPlayer.getName(),
                firstHand.getId() == Player.LEFT ? 'L' : 'R',
                secondPlayer.getName(),
                secondHand.getId() == Player.LEFT ? 'L' : 'R');
        this.messages.add(message);
    }
}
