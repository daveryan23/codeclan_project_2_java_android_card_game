package com.example.davidryan.cardgame.models.players;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.hands.HandDecisions;
import com.example.davidryan.cardgame.models.hands.Handy;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Playery {

    String getName();
    int moneyAvailable();
    boolean isDealer();
    void reset(Gamey game);
    boolean setupInitialBetAndHand(Gamey game);
    int getInitialBetAmount(Gamey game);
    void dealInitialCard(Gamey game, Cardy card);
    void playTurn(Gamey game);
    int getScoreOfFirstHand();
    void resolveBets(Gamey game, int score);
    void incrementMoney(int money);
    void riskMoney(int moneyAtRisk);

    // Implement this on concrete subclasses.
    // Main ones are HumanPlayer and BotPlayer
    HandDecisions makeDecision(Handy hand);

    //    String toString();

}
