package com.example.chopsticks;

import java.util.Random;

import lombok.Data;

@Data
public class Player {
    // プレイヤー名
    private String name;
    // 両手
    // 数値は残りの指の数
    private Hand[] hands;

    public Player(String name) {
        this.name = name;
        this.hands = new Hand[2];
        for (int i = 0; i < this.hands.length; i++) {
            this.hands[i] = new Hand();
        }
    }

    public int getTotalFingerNumber() {
        // 残りの指の数を取得
        return hands[0].getFingerNumber() + hands[1].getFingerNumber();
    }

    public Hand show() {
        // ランダムに自分の手を選択
        Random rand = new Random();
        int index = rand.nextInt(2);
        // 選択された手の残りの指の数を取得
        int fingerNumber = this.hands[index].getFingerNumber();
        if (fingerNumber == 0) {
            // 選択した手が既に無効になっている場合
            // indexの0, 1をNOTで反転
            index = ~index;
        }
        // 選択した自分の手を返却
        return this.hands[index];
    }

    public Hand select(Hand[] hands) {
        // ランダムに相手の手を選択
        Random rand = new Random();
        int index = rand.nextInt(2);
        // 選択された手の残りの指の数を取得
        int fingerNumber = hands[index].getFingerNumber();
        if (fingerNumber == 0) {
            // 選択した手が既に無効になっている場合
            // indexの0, 1をNOTで反転
            index = ~index;
        }
        // 選択した相手の手を返却
        return hands[index];
    }

    public void printHands() {
        System.out.println(String.format("%s: L = %d, R = %d",
                this.name,
                this.hands[0].getFingerNumber(),
                this.hands[1].getFingerNumber()));
    }
}
