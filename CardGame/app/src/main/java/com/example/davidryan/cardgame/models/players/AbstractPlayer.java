package com.example.davidryan.cardgame.models.players;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.hands.CardHand;
import com.example.davidryan.cardgame.models.hands.Handy;
import com.example.davidryan.cardgame.models.hands.SplitHand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public abstract class AbstractPlayer implements Playery {
    private String name;
    private int money;
    private int moneyAtRisk;
    private List<Handy> hands;

    public AbstractPlayer(String name, int money) {
        this.name = name;
        this.money = money;
        moneyAtRisk = 0;
        hands = new ArrayList<>();
    }

    public void reset(Gamey game) {
        for (Handy hand: hands) {
            money += hand.returnMoney();
            game.returnCards(hand.returnCards());
        }
        hands.clear();
        moneyAtRisk = 0;
    }

    private int moneyAvailable() {
        return money - moneyAtRisk;
    }

    public boolean placeInitialBet(Gamey game) {
        // If player doesn't have the minimum bet then false
        // This will remove player from table
        // Otherwise true
        int minBet = game.minimumBet();
        // Default behaviour is to do minimum bet
        // Can override this in human player classes
        int theBet = minBet;
        if (moneyAvailable()<theBet) {
            return false;
        }
        Handy hand = new CardHand(theBet);
        hands.add(hand);
        moneyAtRisk += theBet;
        return true;
    }

    @Override
    public void dealInitialCard(Gamey game, Cardy card) {
        // This will go to the first (and presumably only) hand
        hands.get(0).receiveFaceUp(card);
    }

    public int playTurn(Gamey game, boolean isDealer) {

        // Rules are slightly different on a dealer
        // Can factor out to a subclass of Bot, perhaps?

        int handIndex = 0;
        while (handIndex < hands.size()) {
            Handy hand = hands.get(handIndex);
            boolean handSplittable = hand.playHand();
            if (handSplittable) {
                if (!isDealer) {
                    // Split the hand
                    int splitBet = hand.getBet();
                    if (splitBet<=moneyAvailable()) {
                        splitBet = hand.returnMoney();
                        moneyAtRisk += splitBet;
                        Handy newHand1 = new SplitHand(splitBet);
                        Handy newHand2 = new SplitHand(splitBet);
                        newHand1.receiveFaceUp(hand.);


                    }
                }
            }

            handIndex++;
        }
        boolean turnFinished = false;


        // NEED TO WORK OUT SCORE ON TURN - MAINLY FOR DEALER SIDE

        return 0;
    }

    public void resolveBets(Gamey game, int score) {
        for (Handy hand: hands) {
            int moneyWonByPlayer = hand.resolveBet(this, score);
            money += moneyWonByPlayer;
            game.reduceDealerMoney(moneyWonByPlayer);
        }
        moneyAtRisk = 0;
    }

    public void incrementMoney(int money) {
        this.money += money;
    }



}
