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
            int attackHandDirection = attackPlayer.show();
            // 攻撃プレイヤーに守備プレイヤーのどちらの手に攻撃するか聞く
            int defenseHandDirection = attackPlayer.select(defensePlayer);
            // 選択結果をメッセージに追加
            this.addSelectedHandMessage(attackPlayer, defensePlayer, attackHandDirection, defenseHandDirection);
            // 攻撃プレイヤーの選択した手の指の数を取得
            int attackHand = attackHandDirection == Player.LEFT ? attackPlayer.getLeftHand()
                    : attackPlayer.getRightHand();
            // 選択結果に基づき守備プレイヤーの手を更新
            defensePlayer.update(defenseHandDirection, attackHand);
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
                player.getLeftHand(),
                player.getRightHand()));
    }

    public void addSelectedHandMessage(Player firstPlayer, Player secondPlayer, int firstHandDirection,
            int secondHandDirection) {
        String message = String.format("%s %s -> %s %s",
                firstPlayer.getName(),
                firstHandDirection == Player.LEFT ? 'L' : 'R',
                secondPlayer.getName(),
                secondHandDirection == Player.LEFT ? 'L' : 'R');
        this.messages.add(message);
    }
}
