package com.example.davidryan.cardgame.models.cardattributes;

/**
 * Created by davidryan on 22/09/2017.
 */

public enum Suits {
    SPADES("\u2660"),
    HEARTS("\u2665"),
    DIAMONDS("\u2666"),
    CLUBS("\u2663");

    private final String unicodeSymbol;

    Suits(String unicodeSymbol) {
        this.unicodeSymbol = unicodeSymbol;
    }

    public String SYMBOL() {
        return unicodeSymbol;
    }

}
