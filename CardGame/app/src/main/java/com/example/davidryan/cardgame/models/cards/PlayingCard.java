package com.example.davidryan.cardgame.models.cards;

import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;

/**
 * Created by davidryan on 22/09/2017.
 */

public class PlayingCard implements Cardy {
    private Suits suit;
    private Values value;

    public PlayingCard(Values value, Suits suit){
        this.value = value;
        this.suit = suit;
    }

    public Suits getSuit() {
        return suit;
    }

    public Values getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.SYMBOL() + suit.SYMBOL();
    }

    @Override
    public String toVerboseString() {
        return value + " of " + suit;
    }

    @Override
    public int getOrder() {
        return value.ORDER();
    }

    @Override
    public int getScore() {
        return value.SCORE();
    }

    @Override
    public boolean isAce() {
        return value==Values.ACE;
    }

    public static void main(String[] args) {
        // Test the different suits
        System.out.println("The suits are: Spade \u2660 Heart \u2665 Diamond \u2666 Club \u2663 .");
    }

}
