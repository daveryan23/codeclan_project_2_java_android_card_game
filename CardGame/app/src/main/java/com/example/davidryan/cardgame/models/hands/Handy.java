package com.example.davidryan.cardgame.models.hands;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.players.Playery;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Handy {

    int getBet();
    int countCards();
    int returnMoney();
    List<Cardy> returnCards();

    void receiveFaceUp(Cardy card);
    void receiveFaceDown(Cardy card);

    boolean playHand(Gamey game, Playery player);

    int countChoices(Playery player);
    boolean canHit(Playery player);
    boolean canStand(Playery player);
    boolean canDouble(Playery player);
    boolean canSplit(Playery player);

    int lowerScore();
    int higherScore();
    int finalScore();

    int resolveBet(Playery player, int score);

}
