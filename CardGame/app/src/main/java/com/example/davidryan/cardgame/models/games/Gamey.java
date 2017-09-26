package com.example.davidryan.cardgame.models.games;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.decks.Decky;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Gamey {

    Decky getDeck();
    void returnCards(List<Cardy> cards);

    int minimumBet();
    int betIncrement();
    void reduceDealerMoney(int moneyWonByPlayer);
    Cardy dealCardFromDeck();

    void outputString(String message);
    void outputLine(String message);
    String inputLine();
    String askQuestion(String message);

}
