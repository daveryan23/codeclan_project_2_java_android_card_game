package com.example.davidryan.cardgame.models.players;

import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.hands.CardHand;
import com.example.davidryan.cardgame.models.hands.HandDecisions;
import com.example.davidryan.cardgame.models.hands.Handy;
import com.example.davidryan.cardgame.models.hands.SplitHand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public abstract class AbstractPlayer implements Playery {
    protected boolean isDealer;
    private String name;
    private int money;
    private int moneyAtRisk;
    private List<Handy> hands;

    public AbstractPlayer(String name, int money) {
        this.name = name;
        this.money = money;
        isDealer = false;
        moneyAtRisk = 0;
        hands = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDealer() {
        return isDealer;
    }

    @Override
    public void reset(Gamey game) {
        for (Handy hand: hands) {
            money += hand.returnMoney();
            game.returnCards(hand.returnCards());
        }
        hands.clear();
        moneyAtRisk = 0;
    }

    @Override
    public int moneyAvailable() {
        return money - moneyAtRisk;
    }

    @Override
    public void incrementMoney(int money) {
        this.money += money;
    }

    @Override
    public void riskMoney(int moneyAtRisk) {
        this.moneyAtRisk += moneyAtRisk;
    }

    @Override
    public boolean setupInitialBetAndHand(Gamey game) {
        int theBet = getInitialBetAmount(game);
        if (moneyAvailable()<theBet) {
            return false;
        }
        Handy hand = new CardHand(getName(), theBet);
        hands.add(hand);
        moneyAtRisk += theBet;
        return true;
    }

    @Override
    public void dealInitialCard(Gamey game, Cardy card) {
        // This will go to the first (and presumably only) hand
        hands.get(0).receiveFaceUp(card);
    }

    @Override
    public void playTurn(Gamey game) {
        game.outputLine("");
        game.outputLine(getName() + " is about to start a turn");
        int handIndex = 0;
        // Use a While loop, since hands could dynamically increase in size
        // e.g. if hands are added due to splits
        while (handIndex < hands.size()) {
            game.outputLine(getName() + " is about to start hand " + (handIndex+1));
            Handy hand = hands.get(handIndex);
            boolean splitRequested = hand.playHand(game, this);
            if (splitRequested) {
                // Assume split is checked on the Hand!
                splitHand(game, hand);
                game.outputLine(getName() + " has finished hand " + (handIndex+1) + " by splitting it");
            } else {
                game.outputLine(getName() + " has finished hand " + (handIndex+1));
            }
            handIndex++;
        }
        game.outputLine(getName() + " has finished a turn");
    }

    private void splitHand(Gamey game, Handy hand) {
        game.outputLine("Splitting a hand");
        // Get all bets and cards back from the Hand
        List<Cardy> cards = hand.returnCards();
        int bet = hand.returnMoney();
        game.outputLine("There are " + cards.size() + " cards to split and " + game.formatMoney(bet) + " to place on each");
        moneyAtRisk -= bet;
        // For each returned card, set up a new Hand
        // This should have been checked earlier when requesting split.
        for (Cardy splitCard: cards) {
            String handLabel = getName() + " (split " + hands.size() + ")";
            Handy newHand = new SplitHand(handLabel, bet);
            moneyAtRisk += bet;
            newHand.receiveFaceUp(splitCard);
            Cardy dealtCard = game.dealCardFromDeck();
            newHand.receiveFaceUp(dealtCard);
            hands.add(newHand);
            game.outputLine("New hand:" + newHand.getLabel() + ", " +
                    "bet " + game.formatMoney(newHand.getBet()) + ", " +
                    "new hand is " + newHand.describeCards()
            );
//            game.outputLine("Finished creating a new hand: " + hand.toString());
        }
        game.outputLine("Finished splitting a hand");
    }

    @Override
    public void resolveBets(Gamey game, int score) {
        game.outputLine("");
        game.outputLine(getName() + " starts on " + game.formatMoney(money));
        for (Handy hand: hands) {
            if (0==hand.getBet()) {
                // Ignore empty hands, hands with no bet or cards.
                // This occurs after a split
            } else {
                int moneyWonByPlayer = hand.resolveBet(game, this, score);
                // This amount can be positive or negative!
                incrementMoney(moneyWonByPlayer);
                game.reduceDealerMoney(moneyWonByPlayer);
            }
        }
        moneyAtRisk = 0;
        game.outputLine(getName() + " finishes on " + game.formatMoney(money));
    }

    // DEALER METHODS

    @Override
    public int topCardScore() {
        if (hands.size()==0) {
            return 0;
        }
        return hands.get(0).topCardScore();
    }

    @Override
    public int getScoreOfFirstHand() {
        int score = 0;
        if (hands.size() > 0) {
            score = hands.get(0).finalScore();
        }
        return score;
    }

    @Override
    public String describeFirstHand() {
        if (1<=hands.size()) {
            return hands.get(0).toString();
        }
        return getName() + " does not have a hand of cards";
    }

    // METHODS TO OVERRIDE IN CONCRETE SUBCLASSES

    @Override
    public int getInitialBetAmount(Gamey game) {
        return game.minimumBet();
    }

    @Override
    public HandDecisions makeDecision(Gamey game, Handy hand) {
        return HandDecisions.STAND;
    }

}
