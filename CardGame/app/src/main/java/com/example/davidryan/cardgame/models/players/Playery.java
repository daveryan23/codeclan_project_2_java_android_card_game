package com.example.davidryan.cardgame.models.players;

import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.hands.HandDecisions;
import com.example.davidryan.cardgame.models.hands.Handy;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Playery {

    // General methods
    String getName();
    int moneyAvailable();
    boolean isDealer();
    void reset(Gamey game);
    boolean setupInitialBetAndHand(Gamey game);
    void dealInitialCard(Gamey game, Cardy card);
    void playTurn(Gamey game);
    void resolveBets(Gamey game, int score);
    void incrementMoney(int money);
    void riskMoney(int moneyAtRisk);

    // Methods mainly for the dealer
    int topCardScore();
    int getScoreOfFirstHand();
    String describeFirstHand();

    // Methods to override in concrete subclasses
    int getInitialBetAmount(Gamey game);
    HandDecisions makeDecision(Gamey game, Handy hand);

}
