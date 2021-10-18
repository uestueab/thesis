package com.test.viewpagerfun.sm2;

import com.test.viewpagerfun.model.entity.FlashCard;

import lombok.Getter;

@Getter
public class Review {
    private FlashCard flashCard;
    private int score;

    public Review(FlashCard flashCard, int score) {
        this.flashCard = flashCard;
        this.score = score;
    }
    public boolean hasFailed(){
        return score < 2;
    }
}
