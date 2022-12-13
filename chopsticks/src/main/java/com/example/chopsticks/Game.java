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
        // 攻撃プレイヤー
        Player attackPlayer = player1;
        // 守備プレイヤー
        Player defensePlayer = player2;

        // ゲーム開始をメッセージに追加
        this.messages.add("Game start");

        int turn = 0;
        while (true) {
            // ターン数をメッセージに追加
            this.messages.add(String.format("Turn %d", ++turn));
            // 現在の手をメッセージに追加
            this.addCurrentHandsMessage(player1);
            this.addCurrentHandsMessage(player2);
            // 攻撃プレイヤーにどちらの手を出すか聞く
            Hand attackHand = attackPlayer.show();
            // 攻撃プレイヤーに守備プレイヤーのどちらの手に攻撃するか聞く
            Hand defenseHand = attackPlayer.select(defensePlayer.getLeftHand(), defensePlayer.getRightHand());
            // 選択結果をメッセージに追加
            this.addSelectedHandMessage(attackPlayer, defensePlayer, attackHand, defenseHand);
            // 選択結果に基づき守備プレイヤーの手を更新
            defenseHand.update(attackHand);
            // 守備プレイヤーの残りの指の数を聞き0ならゲーム終了
            if (defensePlayer.getTotalFingerNumber() == 0) {
                this.messages.add(String.format("Winner is %s", attackPlayer.getName()));
                break;
            }
            // 攻撃と守備のプレイヤーを交代する
            Player tmp = attackPlayer;
            attackPlayer = defensePlayer;
            defensePlayer = tmp;
        }
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
