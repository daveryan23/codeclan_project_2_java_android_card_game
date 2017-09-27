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

        String playerName = getName();
        String fundsText = game.formatMoney(moneyAvailable());
        int minBet = game.minimumBet();
        int betAmount = 0;

        if (moneyAvailable() < minBet) {
            game.outputLine(playerName + " has " + fundsText + " which is not enough money for the minimum bet");
        } else {
            betAmount = minBet;
            game.outputLine(playerName + " has " + fundsText + " and has bet " + game.formatMoney(betAmount) + " on this hand");
        }

        return betAmount;
    }

    @Override
    public HandDecisions makeDecision(Gamey game, Handy hand) {

        // Standard bot hand decisions here
        // Could override in subclass for a CleverBot
        HandDecisions result = HandDecisions.HIT;
        int lowerScore = hand.lowerScore();
        int higherScore = hand.higherScore();
        int dealerTopCardScore = game.dealerTopCardScore();
        boolean canSplit = hand.canSplit(this);
        int splitCardScore = hand.firstCardScore();

        // Go through many cases and do sensible automated things
        // Try and cover all four options, to show that the Bot Can.
        if (dealerTopCardScore<7) {
            // Dealer has a bad card and is more likely to go bust - 2, 3, 4, 5, 6
            if ( canSplit && (9<=splitCardScore) ) {
                // Split Aces and 10s and 9
                result = HandDecisions.SPLIT;
            } else if (lowerScore==11 || lowerScore==10) {
                // Double on 10 or 11 if dealer has a bad card
                result = HandDecisions.DOUBLE;
            } else if (19<=higherScore) {
                // Stand on a soft 19 if dealer has a bad card
                result = HandDecisions.STAND;
            } else if (12<=lowerScore) {
                // Stand on a hard 12 if dealer has a bad card
                result = HandDecisions.STAND;
            }
        } else {
            // Dealer has a good card and is less likely to go bust - 7, 8, 9, 10, J, Q, K, A
            if ( canSplit && (11<=splitCardScore) ) {
                // Split Aces only
                result = HandDecisions.SPLIT;
            } else if (18<=higherScore) {
                // Stand on a soft 18 if dealer has a good card
                result = HandDecisions.STAND;
            } else if (17<=lowerScore) {
                // Stand on a hard 17 if dealer has a good card.
                result = HandDecisions.STAND;
            }
        }
        return result;
    }

}
