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

    private boolean handIsBlackJack() {
        // Split hands cannot be a BlackJack
        // e.g. Ace and Ten are counted as 21, not as BlackJack
        // and pay at 1:1, not 3:2
        return false;
    }

}
