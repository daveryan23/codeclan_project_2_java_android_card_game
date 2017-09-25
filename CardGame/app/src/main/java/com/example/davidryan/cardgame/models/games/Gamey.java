package com.example.davidryan.cardgame.models.games;

import com.example.davidryan.cardgame.models.decks.Decky;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Gamey {

    Decky getDeck();

    void outputString(String message);
    void outputLine(String message);
    String inputLine();

}
