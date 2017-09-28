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

    @Override
    public Suits getSuit() {
        return suit;
    }

    @Override
    public Values getValue() {
        return value;
    }

    @Override
    public boolean isAce() {
        return value==Values.ACE;
    }

    @Override
    public int getOrder() {
        return value.ORDER();
    }

    @Override
    public int getScore() {
        return value.SCORE();
    }

    private String colourStart() {
        switch (getSuit()) {
            case DIAMONDS:
            case HEARTS:
                return red();
            case SPADES:
            case CLUBS:
                return blue();
        }
        return "";
    }

    private String colourEnd() {
        return clearFormats();
    }

    private String red() {
        return (char)27 + "[31m";
    }

    private String blue() {
        return (char)27 + "[34m";
    }

    private String clearFormats() {
        return (char)27 + "[0m";
    }

    @Override
    public String toString() {
        return colourStart() + value.SYMBOL() + suit.SYMBOL() + colourEnd();
    }

    @Override
    public String toVerboseString() {
        return value + " of " + suit;
    }

    @Override
    public String describeFaceUp() {
        return toString();
    }

    @Override
    public String describeFaceDown() {
        return "??";
    }

}
