package com.example.davidryan.cardgame.models.hands;

import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.players.Playery;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Handy {

    String getLabel();
    int getBet();
    String toString();
    String describeCards();

    int countCards();
    int returnMoney();
    List<Cardy> returnCards();

    void receiveFaceUp(Cardy card);
    void receiveFaceDown(Cardy card);

    boolean playHand(Gamey game, Playery player);
    int resolveBet(Gamey game, Playery player, int score);

    int countChoices(Playery player);
    boolean canHit(Playery player);
    boolean canStand(Playery player);
    boolean canDouble(Playery player);

    boolean canSplit(Playery player);
    int lowerScore();
    int higherScore();

    int finalScore();

    int topCardScore();     // Ace = 11. K, Q, J = 10. Others are face value. Missing = 0.

}
