package com.example.chopsticks;

import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Player {
    static final int LEFT = 0;
    static final int RIGHT = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // プレイヤー名
    private String name;
    // 左手
    @OneToOne(cascade = CascadeType.ALL)
    private Hand leftHand;
    // 右手
    @OneToOne(cascade = CascadeType.ALL)
    private Hand rightHand;

    public Player(String name) {
        this.name = name;
        this.leftHand = new Hand();
        this.rightHand = new Hand();
    }

    public int getTotalFingerNumber() {
        // 残りの指の数を取得
        return this.leftHand.getFingerNumber() + this.rightHand.getFingerNumber();
    }

    public Hand show() {
        // ランダムに自分の手を選択
        Random rand = new Random();
        int index = rand.nextInt(2);
        // 選択された手の残りの指の数を取得
        Hand selectedHand = index == Player.LEFT ? this.leftHand : this.rightHand;
        int fingerNumber = selectedHand.getFingerNumber();
        if (fingerNumber == 0) {
            // 選択した手が既に無効になっている場合
            // indexの0, 1をNOTで反転
            index = ~index;
            // 再代入
            selectedHand = index == Player.LEFT ? this.leftHand : this.rightHand;
        }
        // 選択した自分の手を返却
        return selectedHand;
    }

    public Hand select(Hand enemyLeftHand, Hand enemyRightHand) {
        // ランダムに相手の手を選択
        Random rand = new Random();
        int index = rand.nextInt(2);
        // 選択された手の残りの指の数を取得
        Hand selectedHand = index == Player.LEFT ? enemyLeftHand : enemyRightHand;
        int fingerNumber = selectedHand.getFingerNumber();
        if (fingerNumber == 0) {
            // 選択した手が既に無効になっている場合
            // indexの0, 1をNOTで反転
            index = ~index;
            // 再代入
            selectedHand = index == Player.LEFT ? enemyLeftHand : enemyRightHand;
        }
        // 選択した相手の手を返却
        return selectedHand;
    }
}
