package com.example.davidryan.cardgame.models.decks;

import com.example.davidryan.cardgame.models.cards.*;
import com.example.davidryan.cardgame.models.cardattributes.*;
import com.example.davidryan.cardgame.models.games.Gamey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by davidryan on 22/09/2017.
 */

public class RandomDeck implements Decky {
    protected List<Cardy> cards;

    public RandomDeck() {
        cards = new ArrayList<>();
    }

    @Override
    public int numberOfCards() {
        return cards.size();
    }

    @Override
    public Cardy deal() {
        if (numberOfCards()<1) {return null;}
        Cardy chosenCard = cards.get(0);
        cards.remove(0);
        return chosenCard;
    }

    @Override
    public void returnCards(List<Cardy> cards) {
        this.cards.addAll(cards);
    }

    @Override
    public void setupWithPacks(int numberOfPacks) {
        privateSetupWithPacks(numberOfPacks, false);
    }

    @Override
    public void setupWithMarkedPacks(int numberOfPacks) {
        privateSetupWithPacks(numberOfPacks, true);
    }

    private void privateSetupWithPacks(int numberOfPacks, boolean marked) {
        Cardy newCard;
        String marking;
        for (int i=0; i<numberOfPacks; i++) {
            marking = Character.toString((char) (97 + i));    // a, b, c, d...
            for (Values value : Values.values()){
                for (Suits suit : Suits.values()){
                    newCard = (marked) ? new MarkedCard(value, suit, marking) : new PlayingCard(value, suit);
                    cards.add(newCard);
                }
            }
        }
    }

    @Override
    public void setupTestSet() {
        cards.add(new PlayingCard(Values.TEN, Suits.CLUBS));
        cards.add(new PlayingCard(Values.ACE, Suits.SPADES));
        cards.add(new MarkedCard(Values.ACE, Suits.CLUBS));
        cards.add(new PlayingCard(Values.THREE, Suits.DIAMONDS));
        cards.add(new MarkedCard(Values.NINE, Suits.HEARTS, "x"));
        cards.add(new PlayingCard(Values.THREE, Suits.CLUBS));
        cards.add(new MarkedCard(Values.QUEEN, Suits.SPADES, "z"));
        cards.add(new PlayingCard(Values.EIGHT, Suits.DIAMONDS));
    }

    @Override
    public String sneakAPeekAtTheCards(Gamey game) {
        String text = "";
        int numCards = numberOfCards();
        if (numCards>0) {
            text = "Deck has " + numCards + " cards in it. Naughty! You are sneaking a look at the cards! :) ";
            for (Cardy card: cards) {
//                text += card.describeFaceDown() + " ";
                text += card.describeFaceUp() + " ";
            }
            text = text.substring(0, text.length()-1);
        } else {
            text = "Deck does not have any cards in it yet";
        }
        return text;
    }

    // Can override this method for a deterministic pack

    @Override
    public void shuffle() {
        Random random = new Random();
        List<Cardy> tempCardArray = new ArrayList<Cardy>(cards);
        cards.clear();
        int iterations = tempCardArray.size();
        Cardy transferCard;
        for (int i=0; i<iterations; i++) {
            int randomIndex = random.nextInt(iterations-i);
            transferCard = tempCardArray.get(randomIndex);
            cards.add(transferCard);
            tempCardArray.remove(randomIndex);
        }
    }

}
