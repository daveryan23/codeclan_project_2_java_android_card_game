package com.example.davidryan.cardgame.models.decks;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Decky {

    // Modelling the game
    Cardy deal();
    void returnCards(List<Cardy> cards);
    void addTestSet();
    void addPacksOfCards(int numberOfPacks);
    void addMarkedPacksOfCards(int numberOfPacks);
    int numberOfCards();
    void shuffle();
    String sneakAPeekAtTheCards(Gamey game);

    // UI needs this
    ArrayList<Cardy> getCardArray();

}
