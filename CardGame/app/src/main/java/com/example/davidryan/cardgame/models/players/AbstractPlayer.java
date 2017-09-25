package com.example.davidryan.cardgame.models.players;

/**
 * Created by davidryan on 22/09/2017.
 */

public abstract class AbstractPlayer implements Playery {
    private String name;
    private int money;
    private List<Handy> hands;

    public AbstractPlayer(String name, int money) {
        this.name = name;
        this.money = money;
        hands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public int scoreCards() {
        int result = 0;
        for (Cardy card: cards) {
            result += card.getScore();
        }
        return result;
    }

    @Override
    public boolean handIsBust() {
        return (scoreCards()>21);
    }

    @Override
    public boolean handHasAHigherSoftScoreAvailable() {
        if (scoreCards()>11) {
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

    @Override
    public boolean handIsBlackJack() {
        // Blackjack is:
        // 2 cards only
        // Scoring 11
        // With a higher soft score (of 21) available
        if (countCards()>2) {
            return false;
        }
        if (scoreCards()==11) {
            if (handHasAHigherSoftScoreAvailable()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int countCards() {
        return cards.size();
    }

    @Override
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

    @Override
    public String toString() {
        return name + ": " + describeCards();
    }

    @Override
    public void receive(Cardy card, boolean visible) {
        cards.add(card);
        visibilities.add(visible);
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

}
