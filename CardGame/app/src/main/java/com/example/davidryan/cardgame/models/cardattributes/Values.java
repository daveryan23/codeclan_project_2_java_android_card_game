package com.example.davidryan.cardgame.models.cardattributes;

/**
 * Created by davidryan on 22/09/2017.
 */

public enum Values {
    TWO("2", 2, 2),
    THREE("3", 3, 3),
    FOUR("4", 4, 4),
    FIVE("5", 5, 5),
    SIX("6", 6, 6),
    SEVEN("7", 7, 7),
    EIGHT("8", 8, 8),
    NINE("9", 9, 9),
    TEN("10", 10, 10),
    JACK("J", 11, 10),
    QUEEN("Q", 12, 10),
    KING("K", 13, 10),
    ACE("A", 14, 1);

    private final String symbol;
    private final int order;
    private final int blackjackScore;

    Values(String symbol, int order, int blackjackScore) {
        this.symbol = symbol;
        this.order = order;
        this.blackjackScore = blackjackScore;
    }

    public String SYMBOL() {
        return symbol;
    }

    public int ORDER() {
        return order;
    }

    public int SCORE() {
        return blackjackScore;
    }

}
