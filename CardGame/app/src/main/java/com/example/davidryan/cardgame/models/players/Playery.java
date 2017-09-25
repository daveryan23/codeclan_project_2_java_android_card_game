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
    boolean placeInitialBet(Gamey game);
    void dealInitialCard(Gamey game, Cardy card);
    int playTurn(Gamey game, boolean isDealer);
    void resolveBets(Gamey game, int score);
    void incrementMoney(int money);




    // From stuff below...


//    int countCards();
//    int scoreCards();
//    boolean handIsBust();
//    boolean handHasAHigherSoftScoreAvailable();
//    boolean handIsBlackJack();
//
//
//    List<Cardy> returnCards();
//
//    String describeCards();
//    String toString();
}
