package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.models.cards.PlayingCard;
import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.decks.RandomDeck;
import com.example.davidryan.cardgame.models.decks.DeterministicDeck;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidryan on 22/09/2017.
 */

public class DeckTest {
    DeterministicDeck fixedDeck;
    RandomDeck randomDeck;

    @Before
    public void before() {
        fixedDeck = new DeterministicDeck();
        randomDeck = new RandomDeck();
    }

    @Test
    public void testDeck() {
        fixedDeck.generateTestSet();
        PlayingCard card = fixedDeck.deal();
        assertEquals(Values.TEN, card.getValue());
        assertEquals(Suits.CLUBS, card.getSuit());
    }

    @Test
    public void testRandomDeckWithEightCards() {
        randomDeck.generateTestSet();
        System.out.println("Underneath eight cards should be dealt in a random order, then null.");
        System.out.println(randomDeck.deal());
        System.out.println(randomDeck.deal());
        System.out.println(randomDeck.deal());
        System.out.println(randomDeck.deal());
        System.out.println(randomDeck.deal());
        System.out.println(randomDeck.deal());
        System.out.println(randomDeck.deal());
        System.out.println(randomDeck.deal());
        System.out.println(randomDeck.deal());
    }

    @Test
    public void testRandomDeckWithFiftyTwoCards() {
        randomDeck.generate();
        System.out.println("Underneath fifty two different cards should be dealt in a random order, then null.");
        for (int i=0; i<53; i++) {
            System.out.println(randomDeck.deal());
        }
    }

    @Test
    public void testFixedDeckWithEightCards() {
        fixedDeck.generateTestSet();
        System.out.println("Underneath eight cards should be dealt in a fixed order, then start repeating");
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
        System.out.println(fixedDeck.deal());
    }

    @Test
    public void testFixedDeckWithFiftyTwoCards() {
        fixedDeck.generate();
        System.out.println("Underneath fifty two different cards should be dealt in a fixed order, then start repeating");
        for (int i=0; i<56; i++) {
            System.out.println(fixedDeck.deal());
        }
    }
}
