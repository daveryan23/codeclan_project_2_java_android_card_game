package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.decks.DeterministicDeck;
import com.example.davidryan.cardgame.models.decks.RandomDeck;
import com.example.davidryan.cardgame.models.games.Gamey;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidryan on 22/09/2017.
 */

public class DeckTest {
    private DeterministicDeck fixedDeck;
    private RandomDeck randomDeck;
    private Gamey gameMock;

    @Before
    public void before() {
        fixedDeck = new DeterministicDeck();
        randomDeck = new RandomDeck();
        gameMock = Mockito.mock(Gamey.class);
    }

    @Test
    public void testDeck() {
        fixedDeck.addTestSet();
        assertEquals(8, fixedDeck.numberOfCards());
        Cardy card = fixedDeck.deal();
        assertEquals(7, fixedDeck.numberOfCards());
        assertEquals(Values.TEN, card.getValue());
        assertEquals(Suits.CLUBS, card.getSuit());
    }

    @Test
    public void testRandomDeckWithEightCards() {
        randomDeck.addTestSet();
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
        randomDeck.addPacksOfCards(1);
        System.out.println("Underneath fifty two different cards should be dealt in a random order, then null.");
        for (int i=0; i<53; i++) {
            System.out.println(randomDeck.deal());
        }
    }

    @Test
    public void testFixedDeckWithEightCards() {
        fixedDeck.addTestSet();
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
        fixedDeck.addPacksOfCards(1);
        System.out.println("Underneath fifty two different cards should be dealt in a fixed order, then start repeating");
        for (int i=0; i<56; i++) {
            System.out.println(fixedDeck.deal());
        }
    }


    @Test
    public void testReturningMultipleCards() {
        fixedDeck.addTestSet();
        randomDeck.addTestSet();
        Cardy card1 = randomDeck.deal();
        Cardy card2 = randomDeck.deal();
        List<Cardy> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        fixedDeck.returnCards(cards);
        assertEquals(10, fixedDeck.numberOfCards());
    }


    @Test
    public void testSetupMarkedPacks() {
        fixedDeck.addMarkedPacksOfCards(2);
        assertEquals(104, fixedDeck.numberOfCards());
    }

    @Test
    public void testSneakPeakBeforeAndAfterCardsAddedToDeck() {
        fixedDeck.sneakAPeekAtTheCards(gameMock);
        fixedDeck.addTestSet();
        fixedDeck.sneakAPeekAtTheCards(gameMock);
        assertEquals(1, 1);
    }

    @Test
    public void testShuffle() {
        randomDeck.shuffle();
        randomDeck.addTestSet();
        randomDeck.shuffle();
        fixedDeck.shuffle();
        fixedDeck.addTestSet();
        fixedDeck.shuffle();
        assertEquals(1, 1);
    }

//    //WRITE MORE TESTS!
//
//    @Test
//    public void testX() {
//        assertEquals(1, 1);
//    }
//


}
