package com.example.davidryan.cardgame.models.games;

import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.decks.Decky;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Gamey {

    // Getters
    int minimumBet();
    int maximumBet();
    int betIncrement();

    // Deck interaction
    Decky getDeck();
    Cardy dealCardFromDeck();
    void returnCards(List<Cardy> cards);

    // Running a game
    boolean askUserToPlayAgain();

    // Dealer interaction
    int dealerTopCardScore();
    String describeDealerHand();
    void reduceDealerMoney(int moneyWonByPlayer);

    // Input/Output
    String formatBet(int moneyUnits);
    int convertBackToMoneyUnits(String inputAmount);
    void outputString(String message);
    void outputLine(String message);
    String inputLine();
    String askQuestion(String message);

}
