package com.example.davidryan.cardgame.models.players;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Playery {

    // From stuff above (Game)
    void reset(Gamey game);
    void placeBet(Gamey game);
    void dealCard(Gamey game, Cardy card);
    int playTurn(Gamey game);
    void resolveBets(Gamey game, int score);




    // From stuff below...


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
