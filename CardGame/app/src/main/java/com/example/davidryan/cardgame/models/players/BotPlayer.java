package com.example.davidryan.cardgame.models.players;

import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.hands.HandDecisions;
import com.example.davidryan.cardgame.models.hands.Handy;

/**
 * Created by davidryan on 25/09/2017.
 */

public class BotPlayer extends AbstractPlayer {

    public BotPlayer(String name, int money) {
        super(name, money);
    }

    @Override
    public int getInitialBetAmount(Gamey game) {
        // A bot plays always the minimum bet.
        return game.minimumBet();
    }

    @Override
    public HandDecisions makeDecision(Handy hand) {

        // THIS IS THE BOT

        // WANT TO MAKE SOME SENSIBLE BOT DECISIONS HERE

        return HandDecisions.STAND;
    }

}
