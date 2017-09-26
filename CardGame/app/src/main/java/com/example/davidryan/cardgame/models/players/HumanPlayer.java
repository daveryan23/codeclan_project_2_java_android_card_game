package com.example.davidryan.cardgame.models.players;

import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.hands.HandDecisions;
import com.example.davidryan.cardgame.models.hands.Handy;

import java.util.ArrayList;
import java.util.List;

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
            String betAmountString = game.askQuestion(playerName + ": how much do you want to bet? (in pence)");

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
    public HandDecisions makeDecision(Gamey game, Handy hand) {

        List<String> playerChoices = new ArrayList<>();
        playerChoices.add("Stand (S)");
        if (hand.canHit(this)) {playerChoices.add("Hit (H)");}
        if (hand.canDouble(this)) {playerChoices.add("Double (D)");}
        if (hand.canSplit(this)) {playerChoices.add("Split (Y)");}

        String questionText = "Do you want to: ";
        for (String choice: playerChoices) {
            questionText += choice + ", ";
        }
        questionText = questionText.substring(0, questionText.length()-2) + "?";

        HandDecisions result = HandDecisions.STAND;
        boolean needChoice = true;
        while (needChoice) {
            game.outputString("Dealer has " + game.describeDealerHand());
            game.outputString( ", " + hand.toString());
            String decision = game.askQuestion(questionText);

            if (decision.length()>0) {
                String leftChar = decision.substring(0, 0).toLowerCase();
                if ( hand.canHit(this) && leftChar.equalsIgnoreCase("h") ) {
                    result = HandDecisions.HIT;
                    needChoice = false;
                } else if ( hand.canDouble(this) && leftChar.equalsIgnoreCase("d") ) {
                    result = HandDecisions.DOUBLE;
                    needChoice = false;
                } else if ( hand.canSplit(this) && leftChar.equalsIgnoreCase("y") ) {
                    result = HandDecisions.SPLIT;
                    needChoice = false;
                } else if ( leftChar.equalsIgnoreCase("s") ) {
                    result = HandDecisions.STAND;
                    needChoice = false;
                }
            }
        }
        return result;
    }

}
