package com.example.davidryan.cardgame.models.decks;

/**
 * Created by davidryan on 22/09/2017.
 */

public class DeterministicDeck extends RandomDeck {

    @Override
    public void shuffle() {
        // Cannot randomly shuffle a deterministic deck
        // Do nothing!
    }

}
