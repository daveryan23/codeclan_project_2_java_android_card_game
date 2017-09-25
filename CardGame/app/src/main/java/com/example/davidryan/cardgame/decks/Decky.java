package com.example.davidryan.cardgame.decks;

import com.example.davidryan.cardgame.cards.Cardy;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Decky {

    int numberOfCards();

    Cardy deal();
    void returnToBottom(Cardy card);

    void shuffle();

    void setupWithPacks(int numberOfPacks);
    void setupTestSet();

}
