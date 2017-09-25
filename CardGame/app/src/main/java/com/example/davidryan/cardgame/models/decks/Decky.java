package com.example.davidryan.cardgame.models.decks;

import com.example.davidryan.cardgame.models.cards.Cardy;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Decky {

    Cardy deal();

    void returnCards(List<Cardy> cards);



//    int numberOfCards();
//
//    void returnToBottom(Cardy card);
//
//    void shuffle();
//
    void setupWithPacks(int numberOfPacks);
    void setupWithMarkedPacks(int numberOfPacks);
    void setupTestSet();

}
