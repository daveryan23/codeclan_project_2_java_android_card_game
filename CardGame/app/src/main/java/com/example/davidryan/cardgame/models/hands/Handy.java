package com.example.davidryan.cardgame.models.hands;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.players.Playery;

import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Handy {

    int getBet();
    int returnMoney();
    List<Cardy> returnCards();

    void receive(Cardy card, boolean visible);
    void receiveFaceUp(Cardy card);
    void receiveFaceDown(Cardy card);

    Cardy returnCard();

    boolean playHand();

    int finalScore();

    int resolveBet(Playery player, int score);

}
