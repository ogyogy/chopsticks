package com.example.chopsticks;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Hand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int fingerNumber;

    public Hand() {
        this.fingerNumber = 1;
    }

    public void update(Hand hand) {
        // 引数は相手の手
        // 更新後の指の数を計算
        int newFingerNumber = this.fingerNumber + hand.getFingerNumber();
        if (newFingerNumber == 5) {
            // 5本ちょうどなら0本に更新
            this.fingerNumber = 0;
        } else if (newFingerNumber > 5) {
            // 5本を超える場合は繰越す
            this.fingerNumber = newFingerNumber - 5;
        } else {
            // 4本以下ならそのまま更新
            this.fingerNumber = newFingerNumber;
        }
    }
}
