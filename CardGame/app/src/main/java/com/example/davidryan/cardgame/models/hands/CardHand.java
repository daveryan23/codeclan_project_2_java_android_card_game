package com.example.davidryan.cardgame.models.hands;

import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.players.Playery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidryan on 25/09/2017.
 */

public class CardHand implements Handy {
    private String label;
    private int bet;
    private List<Cardy> cards;
    private List<Boolean> visibilities;
    private boolean scoresUpToDate;
    private int lowerScore;
    private int higherScore;

    public CardHand(String label, int bet) {
        this.label = label;
        this.bet = bet;
        cards = new ArrayList<>();
        visibilities = new ArrayList<>();
        scoresUpToDate = false;
        lowerScore = 0;
        higherScore = 0;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public String toString() {
        return label + " has "  + describeCards();
    }

    @Override
    public String describeCards() {
        String theDescription = "";
        int numberOfCards = countCards();
        for (int i = 0; i < numberOfCards; i++) {
            Cardy card = cards.get(i);
            boolean visible = visibilities.get(i);
            String textToAdd = (visible) ? card.describeFaceUp() : card.describeFaceDown();
            theDescription += textToAdd + ", ";
        }
        if (0<numberOfCards) {
            theDescription = theDescription.substring(0, theDescription.length() - 2);  // Lose the trailing ', '
        }
        if (handIsBust()) {
            theDescription += " (" + lowerScore() + ", BUST!)";
        } else if (handIsBlackJack()) {
            theDescription += " ***** BLACKJACK! PARTY TIME!! *****";
        } else if (countCards()==1) {
            // no extra description needed
        } else if (lowerScore()==higherScore()) {
            theDescription += " (" + lowerScore() + ")";
        } else {
            theDescription += " (" + lowerScore() + "/" + higherScore() + ")";
        }
        return theDescription;
    }

    @Override
    public int countCards() {
        return cards.size();
    }

    private void updateScores() {
        int lowScore = 0;
        boolean anAce = false;
        for (Cardy card : cards) {
            lowScore += card.getScore();
            if (card.isAce()) {
                anAce = true;
            }
        }
        int highScore = lowScore;
        if (anAce) {
            highScore += 10;
        }
        if (highScore>21) {
            highScore = lowScore;
        }
        lowerScore = lowScore;
        higherScore = highScore;
        scoresUpToDate = true;
    }

    @Override
    public int lowerScore() {
        // Also called the 'hard' score
        if (!scoresUpToDate) {
            updateScores();
        }
        return lowerScore;
    }

    @Override
    public int higherScore() {
        // Also called the 'soft' score
        if (!scoresUpToDate) {
            updateScores();
        }
        return higherScore;
    }

    private boolean handIsBust() {
        return (lowerScore() > 21);
    }

    private boolean handIsBlackJack() {
        // Blackjack is getting a higherScore of 21 on 2 cards only.
        // This has to be an Ace and one of 10, J, Q, K
        return ( (countCards()==2) && (higherScore()==21) );
    }

    @Override
    public int finalScore() {
        // This is the higherScore except for
        // 1) Bust (score 0)
        // 2) Blackjack (score 22 so it beats a hand of 21)
        if (handIsBust()) {
            return 0;
        }
        if (handIsBlackJack()) {
            return 22;
        }
        return higherScore();
    }

    private void receive(Cardy card, boolean visible) {
        cards.add(card);
        visibilities.add(visible);
        scoresUpToDate = false;
    }

    @Override
    public void receiveFaceUp(Cardy card) {
        receive(card, true);
    }

    @Override
    public void receiveFaceDown(Cardy card) {
        receive(card, false);
    }

    @Override
    public List<Cardy> returnCards() {
        List<Cardy> theCardsToReturn = new ArrayList<>(cards);
        cards.clear();
        visibilities.clear();
        return theCardsToReturn;
    }

    @Override
    public int returnMoney() {
        int moneyReturned = bet;
        bet = 0;
        return moneyReturned;
    }

    @Override
    public final int countChoices(Playery player) {
        int count = 1;   // Can always stand!
        if (canHit(player)) {count++;}
        if (canDouble(player)) {count++;}
        if (canSplit(player)) {count++;}
        return count;
    }

    @Override
    public final boolean canStand(Playery player) {
        // Dummy method!
        // It is always possible to stand!
        return true;
    }

    @Override
    public boolean canHit(Playery player) {
        if (handIsBlackJack()) {
            // Cannot hit on a blackjack
            return false;
        }
        if (lowerScore() <= 20) {
            // Can hit up to hard 20 (although you wouldn't!)
            return true;
        }
        // (Method could be rewritten with 1 clause - no hitting on soft 21,
        // however some nutters might want to hit on a 5 6 J combo...
        // so for now let them.)
        return false;
    }

    @Override
    public boolean canDouble(Playery player) {
        // Dealers don't double
        if (player.isDealer()) {
            return false;
        }
        // Can't double on any soft 21, including blackjack!
        if (higherScore()==21) {
            return false;
        }
        // Player needs money available to double
        if (player.moneyAvailable() < bet) {
            return false;
        }
        // Allow players to double only on a 9, 10 or 11 (counting an ace as 1)
        int score = lowerScore();
        return (score>=9 && score<=11);
    }

    @Override
    public boolean canSplit(Playery player) {
        if (player.isDealer()) {
            // Dealers don't split
            return false;
        }
        if (cards.size() != 2) {
            // Can only split on 2 cards
            return false;
        }
        if (cards.get(0).getScore() != cards.get(1).getScore()) {
            // Can only split cards of equal score.
            // This includes 10, J, Q, K as a set.
            return false;
        }
        if (player.moneyAvailable() < bet) {
            // Player does not have available funds to bet on the split cards
            return false;
        }
        // Having fulfilled all these conditions, a split is possible
        return true;
    }

    @Override
    public boolean playHand(Gamey game, Playery player) {
        game.outputLine(toString() + " with bet " + game.formatMoney(getBet()) + " and dealer score " + game.dealerTopCardScore() + " - hand starts");
        String playerName = player.getName();
        HandDecisions playerDecision;
        boolean splitRequested = false;
        boolean stillLooping = true;
        while (stillLooping) {
            int choices = countChoices(player);
//            game.outputLine(getLabel() + " has " + choices + " choices");
            if (choices<2) {
                // Player can only Stand. Exit loop.
                stillLooping = false;
                game.outputLine(playerName + " ends with " + describeCards());
            } else {
                playerDecision = player.makeDecision(game, this);
                Cardy card;
                switch (playerDecision) {
                    case STAND:
                        stillLooping = false;
                        game.outputLine(playerName + " STANDS on " + describeCards());
                        break;
                    case SPLIT:
                        stillLooping = false;
                        splitRequested = true;
                        game.outputLine(playerName + " SPLITS " + describeCards() + " into new hands");
                        break;
                    case DOUBLE:
                        // Have to double bet, take exactly one more card, and then stop
                        int betIncrease = bet;
                        bet += betIncrease;
                        player.riskMoney(betIncrease);
                        card = game.dealCardFromDeck();
                        receiveFaceUp(card);
                        stillLooping = false;
                        game.outputLine(playerName + " DOUBLES bet, receives " + card.describeFaceUp() + " to finish on " + describeCards());
                        break;
                    case HIT:
                        // Deal 1 more card, and continue loop
                        card = game.dealCardFromDeck();
                        receiveFaceUp(card);
                        game.outputLine(playerName + " HITS, receives " + card.describeFaceUp() + " to give " + describeCards());
                        break;
                    default:
                        // We should not be here!
                        game.outputLine("ERROR! DEBUG ME PLEASE");
                        break;
                }
            }
        }
        game.outputLine(toString() + " with bet " + game.formatMoney(getBet()) + " and dealer score " + game.dealerTopCardScore() + " - hand ends");
        return splitRequested;
    }

    @Override
    public int resolveBet(Gamey game, Playery player, int dealerScore) {
        if (dealerScore==22) {
            game.outputString("Dealer has BLACKJACK, ");
        } else if (dealerScore==0) {
            game.outputString("Dealer is BUST, ");
        } else {
            game.outputString("Dealer has " + dealerScore + ", ");
        }
        String betText = game.formatMoney(bet);
        game.outputString(toString() + ", bet " + betText + ", ");
        int handScore = finalScore();
        int moneyWonByPlayer;
        if (handScore == 0) {
            // Player bust (irrespective of whether dealer bust)
            moneyWonByPlayer = -bet;
            game.outputLine("loses " + betText + " by going bust");
        } else if (handScore == dealerScore) {
            // Equal score > 0, its a Push (including player and dealer blackjacks)
            moneyWonByPlayer = 0;
            game.outputLine("their bet of " + betText + " is returned");
        } else if (handScore < dealerScore) {
            // Dealer wins, including dealer blackjack
            moneyWonByPlayer = -bet;
            game.outputLine("loses " + betText + " to the dealer");
        } else if (handScore == 22) {
            // Player wins with blackjack, paying 3:2
            moneyWonByPlayer = (int) (1.5 * bet);
            game.outputLine("wins an extra " + game.formatMoney(moneyWonByPlayer) + " from the dealer");
        } else if (handScore > dealerScore) {
            // Player wins with better non-blackjack hand than dealer, paying 1:1
            moneyWonByPlayer = bet;
            game.outputLine("wins an extra " + betText + " from the dealer");
        } else {
            // Shouldn't be any more cases here! Do this just in case.
            moneyWonByPlayer = 0;
            game.outputLine("ERROR! Please debug");
        }
        bet = 0;
//        if (moneyWonByPlayer==0) {
//            game.outputLine("Finished resolving hand " + toString() + ". Its a push.");
//        } else if (moneyWonByPlayer>0) {
//            game.outputLine("Finished resolving hand " + toString() + ". Player has won " + game.formatMoney(moneyWonByPlayer));
//        } else {
//            game.outputLine("Finished resolving hand " + toString() + ". Player has lost " + game.formatMoney(-moneyWonByPlayer));
//        }
        return moneyWonByPlayer;
    }

    @Override
    public int topCardScore() {
        if (cards.size()==0) {
            return 0;
        }
        Cardy card = cards.get(0);
        if (card.isAce()) {
            return 11;
        }
        return card.getScore();
    }
}





















