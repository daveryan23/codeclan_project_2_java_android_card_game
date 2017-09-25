package com.example.davidryan.cardgame.models.hands;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.players.Playery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidryan on 25/09/2017.
 */

public class CardHand implements Handy {
    private int bet;
    private List<Cardy> cards;
    private List<Boolean> visibilities;

    public CardHand(int bet) {
        this.bet = bet;
        cards = new ArrayList<>();
        visibilities = new ArrayList<>();
    }

    @Override
    public int getBet() {
        return bet;
    }

    private int basicScore() {
        int result = 0;
        for (Cardy card: cards) {
            result += card.getScore();
        }
        return result;
    }

    private boolean handIsBust() {
        return (basicScore()>21);
    }

    private boolean handHasAHigherSoftScoreAvailable() {
        if (basicScore()>11) {
            return false;
        }
        for (Cardy card: cards) {
            if (card.isAce()) {
                // If player holds at least one Ace
                // and has 11 or less points
                // (when scoring Ace as 1)
                // then a soft score 10 points higher is available.
                return true;
            }
        }
        return false;
    }

    private boolean handIsBlackJack() {
        // Blackjack is:
        // 2 cards only
        // Scoring 11
        // With a higher soft score (of 21) available
        if (countCards()>2) {
            return false;
        }
        if (basicScore()==11) {
            if (handHasAHigherSoftScoreAvailable()) {
                return true;
            }
        }
        return false;
    }

    public int finalScore() {
        int basicScore = basicScore();
        if (handIsBust()) {
            return 0;
        }
        if (handIsBlackJack()) {
            return 22;
        }
        if (handHasAHigherSoftScoreAvailable()) {
            return basicScore+10;
        }
        return basicScore;
    }

    private int countCards() {
        return cards.size();
    }

    public String describeCards() {
        String theDescription = "";
        int numberOfCards = countCards();
        for (int i=0; i<numberOfCards; i++) {
            Cardy card = cards.get(i);
            boolean visible = visibilities.get(i);
            String textToAdd = (visible) ? card.toString() : "??";
            theDescription += textToAdd + ", ";
        }
        theDescription = theDescription.substring(0, theDescription.length()-2);  // Lose the trailing ', '
        return theDescription;
    }

//    public String toString() {
//        return name + ": " + describeCards();
//    }

    public void receive(Cardy card, boolean visible) {
        cards.add(card);
        visibilities.add(visible);
    }

    public void receiveFaceUp(Cardy card) {
        receive(card, true);
    }

    public void receiveFaceDown(Cardy card) {
        receive(card, false);
    }

    public List<Cardy> returnCards() {
        List<Cardy> theCardsToReturn = new ArrayList<>(cards);
        cards.clear();
        visibilities.clear();
        return theCardsToReturn;
    }

    public Cardy returnCard() {

        // Want to return the 1st card, and reduce both arrays by lenght 1
        return 1;
    }

    @Override
    public int returnMoney() {
        int moneyReturned = bet;
        bet = 0;
        return moneyReturned;
    }

    @Override
    public int resolveBet(Playery player, int dealerScore) {
        int handScore = finalScore();
        int moneyWonByPlayer;
        if (handScore==dealerScore) {
            moneyWonByPlayer = 0;
        } else if (handScore<dealerScore) {
            moneyWonByPlayer = -bet;
        } else if (handScore==22) {
            // Blackjack pays 3:2
            moneyWonByPlayer = (int) (1.5 * bet);
        } else if (handScore>dealerScore) {
            moneyWonByPlayer = bet;
        } else {
            // Shouldn't be any more cases here! Do this just in case.
            moneyWonByPlayer = 0;
        }
        bet = 0;
        return moneyWonByPlayer;
    }

}





















