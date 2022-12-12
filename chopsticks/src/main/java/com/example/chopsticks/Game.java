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
        this.messages.add(String.format("%s: L = %d, R = %d",
                player1.getName(),
                player1.getHand(0).getFingerNumber(),
                player1.getHand(1).getFingerNumber()));

        this.messages.add(String.format("%s: L = %d, R = %d",
                player2.getName(),
                player2.getHand(0).getFingerNumber(),
                player2.getHand(1).getFingerNumber()));

        // ==========
        // 1ターン目
        // ==========

        this.messages.add("Turn 1");

        // プレイヤー1にどちらの手を出すか聞く
        int indexPlayer1 = player1.show();
        Hand selectedPlayer1Hand = player1.getHand(indexPlayer1);

        // プレイヤー1にプレイヤー2のどちらの手に攻撃するか聞く
        int indexPlayer2 = player1.select(player2.getHands());
        Hand attackedPlayer2Hand = player2.getHand(indexPlayer2);

        String message = String.format("%s %s -> %s %s",
                player1.getName(),
                indexPlayer1 == 0 ? 'L' : 'R',
                player2.getName(),
                indexPlayer2 == 0 ? 'L' : 'R');
        this.messages.add(message);

        // 選択されたそれぞれの手に基づきプレイヤー2の手を更新
        attackedPlayer2Hand.update(selectedPlayer1Hand);
        // プレイヤー2の残りの指の数を聞く
        // int player2TotalFingerNumber = player2.getTotalFingerNumber();
        // 0ならゲーム終了
        // if (player2TotalFingerNumber == 0) {
        // }

        this.messages.add(String.format("%s: L = %d, R = %d",
                player1.getName(),
                player1.getHand(0).getFingerNumber(),
                player1.getHand(1).getFingerNumber()));

        this.messages.add(String.format("%s: L = %d, R = %d",
                player2.getName(),
                player2.getHand(0).getFingerNumber(),
                player2.getHand(1).getFingerNumber()));

        // ==========
        // 2ターン目
        // ==========

        this.messages.add("Turn 2");

        // プレイヤー2にどちらの手を出すか聞く
        indexPlayer2 = player2.show();
        Hand selectedPlayer2Hand = player2.getHand(indexPlayer2);

        // プレイヤー2にプレイヤー1のどちらの手に攻撃するか聞く
        indexPlayer1 = player2.select(player1.getHands());
        Hand attackedPlayer1Hand = player2.getHand(indexPlayer1);

        message = String.format("%s %s -> %s %s",
                player2.getName(),
                indexPlayer2 == 0 ? 'L' : 'R',
                player1.getName(),
                indexPlayer1 == 0 ? 'L' : 'R');
        this.messages.add(message);

        // 選択されたそれぞれの手に基づきプレイヤー1の手を更新
        attackedPlayer1Hand.update(selectedPlayer2Hand);
        // プレイヤー1の残りの指の数を聞く
        // int player1TotalFingerNumber = player1.getTotalFingerNumber();
        // 0ならゲーム終了
        // if (player1TotalFingerNumber == 0) {
        // }

        this.messages.add(String.format("%s: L = %d, R = %d",
                player1.getName(),
                player1.getHand(0).getFingerNumber(),
                player1.getHand(1).getFingerNumber()));

        this.messages.add(String.format("%s: L = %d, R = %d",
                player2.getName(),
                player2.getHand(0).getFingerNumber(),
                player2.getHand(1).getFingerNumber()));
    }

    public String messageToString() {
        // 文字列はThymeleafのth:textでサニタイズされる
        String message = String.join("\n", this.messages);
        return message;
    }
}
