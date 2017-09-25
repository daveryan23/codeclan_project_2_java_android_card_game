package com.example.davidryan.cardgame.players;

import com.example.davidryan.cardgame.cards.Cardy;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Playery {

    int countCards();
    int scoreCards();
    boolean handIsBust();
    boolean handHasAHigherSoftScoreAvailable();
    boolean handIsBlackJack();

    void receive(Cardy card, boolean visible);
    void receiveFaceUp(Cardy card);
    void receiveFaceDown(Cardy card);

    List<Cardy> returnCards();

    String describeCards();
    String toString();
}
