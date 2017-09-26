package com.example.davidryan.cardgame.models.hands;

/**
 * Created by davidryan on 25/09/2017.
 */

public class SplitHand extends CardHand {

    public SplitHand(String label, int bet) {
        super(label, bet);
    }

    // Only function of a split hand is to score 21 rather than 22 on a blackjack!

    @Override
    public int finalScore() {
        int theScore = super.finalScore();
        if (theScore==22) {
            return 21;
        } else {
            return theScore;
        }
    }
}
