package com.example.davidryan.cardgame.models.players;

import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.hands.HandDecisions;
import com.example.davidryan.cardgame.models.hands.Handy;

/**
 * Created by davidryan on 25/09/2017.
 */

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name, int money) {
        super(name, money);
    }

    @Override
    public int getInitialBetAmount(Gamey game) {

        String playerName = getName();
        int minBet = game.minimumBet();
        int betInc = game.betIncrement();
        int betAmount = 0;

        if (moneyAvailable() < minBet) {
            game.outputLine("");
            game.outputLine(playerName + " is very poor and cannot afford the minimum bet!");
        } else {
            // Get user input
            game.outputLine("");
            game.outputLine("Game minimum bet is " + minBet + " pence, going up in steps of " + betInc + ".");
            String betAmountString = game.askQuestion(playerName + ": how much do you want to bet? (in pence) ");

            // Parse user input
            betAmount = Integer.parseInt(betAmountString);
            if (betAmount<minBet) {
                betAmount = minBet;
            }
            int theRes = betAmount % betInc;
            betAmount -= theRes;
            // Should now have a bet amount compatible with the minimum bet and bet increment

            game.outputLine("");
            game.outputLine(playerName + " is playing a hand for " + betAmount + " pence.");
        }

        return betAmount;
    }

    @Override
    public HandDecisions makeDecision(Handy hand) {

        // THIS IS THE HUMAN PLAYER
        // WANT TO USE INPUT TO SELECT BETWEEN SEVERAL OPTIONS
        
        // If no subclass has overridden this, do nothing!
        return HandDecisions.STAND;
    }

}
