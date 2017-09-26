package com.example.davidryan.cardgame.models.decks;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Decky {

    Cardy deal();
    void returnCards(List<Cardy> cards);
    void setupWithPacks(int numberOfPacks);
    void setupWithMarkedPacks(int numberOfPacks);
    void setupTestSet();
    int numberOfCards();
    void shuffle();
    String sneakAPeekAtTheCards(Gamey game);

}
