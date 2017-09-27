package com.example.davidryan.cardgame.models.players;

import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.hands.HandDecisions;
import com.example.davidryan.cardgame.models.hands.Handy;

/**
 * Created by davidryan on 26/09/2017.
 */

public class DealerBot extends BotPlayer {

    public DealerBot(String name, int money) {
        super(name, money);
        isDealer = true;
    }

    @Override
    public int getInitialBetAmount(Gamey game) {
        // A dealer cannot bet independently
        String playerName = getName();
        String fundsText = game.formatMoney(moneyAvailable());
        int betAmount = 0;
        game.outputLine(playerName + " is a dealer with " + fundsText + " and cannot bet independently");
        return betAmount;
    }

    @Override
    public HandDecisions makeDecision(Gamey game, Handy hand) {

        // Dealer hand decisions here
        // Default is to hit
        HandDecisions result = HandDecisions.HIT;

        if (18<=hand.higherScore()) {
            // Stand on a soft 18
            result = HandDecisions.STAND;
        }
        if (17<=hand.lowerScore()) {
            // Stand on a hard 17
            result = HandDecisions.STAND;
        }

        return result;
    }

}
