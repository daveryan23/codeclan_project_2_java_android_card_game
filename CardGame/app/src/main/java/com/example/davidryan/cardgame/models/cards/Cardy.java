package com.example.davidryan.cardgame.models.cards;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Cardy {
    String toString();
    String toVerboseString();
    int getOrder();
    int getScore();
    boolean isAce();
}
