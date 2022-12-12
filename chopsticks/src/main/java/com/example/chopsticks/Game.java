package com.example.chopsticks;

import lombok.Data;

@Data
public class Game {
    public void start(Player player1, Player player2) {
        // プレイヤー1にどちらの手を出すか聞く
        Hand selectedPlayer1Hand = player1.show();
        // プレイヤー1にプレイヤー2のどちらの手に攻撃するか聞く
        Hand attackedPlayer2Hand = player1.select(player2.getHands());
        // 選択されたそれぞれの手に基づきプレイヤー2の手を更新
        attackedPlayer2Hand.update(selectedPlayer1Hand);
        // プレイヤー2の残りの指の数を聞く
        // int player2TotalFingerNumber = player2.getTotalFingerNumber();
        // 0ならゲーム終了
        // if (player2TotalFingerNumber == 0) {
        // }
        // 現在の手を表示
        player1.printHands();
        player2.printHands();
    }
}
