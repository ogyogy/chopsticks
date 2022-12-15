package com.example.chopsticks;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int leftHand;
    // 右手
    private int rightHand;

    public Player(String name) {
        this.name = name;
        this.leftHand = 1;
        this.rightHand = 1;
    }

    public int getTotalFingerNumber() {
        // 残りの指の数を取得
        return this.leftHand + this.rightHand;
    }

    public int show() {
        // ランダムに自分の手を選択
        Random rand = new Random();
        int handDirection = rand.nextInt(2);
        // 選択された手の残りの指の数を取得
        int selectedHand = handDirection == Player.LEFT ? this.leftHand : this.rightHand;
        if (selectedHand == 0) {
            // 選択した手が既に無効になっている場合
            // indexの0, 1をNOTで反転
            handDirection = ~handDirection;
        }
        // 選択した自分の手を返却
        return selectedHand;
    }

    public int select(Player enemy) {
        // ランダムに相手の手を選択
        Random rand = new Random();
        int handDirection = rand.nextInt(2);
        // 選択された手の残りの指の数を取得
        int selectedHand = handDirection == Player.LEFT ? enemy.getLeftHand() : enemy.getRightHand();
        if (selectedHand == 0) {
            // 選択した手が既に無効になっている場合
            // indexの0, 1をNOTで反転
            handDirection = ~handDirection;
        }
        // 選択した相手の手を返却
        return handDirection;
    }

    public void update(int handDirection, int fingerNumber) {
        // 更新する手を取得
        int updatedHand = handDirection == Player.LEFT ? this.getLeftHand() : this.getRightHand();
        // 更新後の指の数を計算
        int newFingerNumber = updatedHand + fingerNumber;
        if (newFingerNumber == 5) {
            // 5本ちょうどなら0本に更新
            newFingerNumber = 0;
        } else if (newFingerNumber > 5) {
            // 5本を超える場合は繰越す
            newFingerNumber -= 5;
        }
        // 4本以下ならそのまま更新
        // 実際に更新する
        if (handDirection == Player.LEFT) {
            this.setLeftHand(newFingerNumber);
        } else {
            this.setRightHand(newFingerNumber);
        }
    }
}
