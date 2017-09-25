package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.cards.PlayingCard;
import com.example.davidryan.cardgame.cardattributes.Suits;
import com.example.davidryan.cardgame.cardattributes.Values;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidryan on 22/09/2017.
 */

public class CardTest {
    PlayingCard aceOfSpades;
    PlayingCard fourOfClubs;

    @Before
    public void before() {
        aceOfSpades = new PlayingCard(Values.ACE, Suits.SPADES);
        fourOfClubs = new PlayingCard(Values.FOUR, Suits.CLUBS);
    }

    @Test
    public void testAce() {
        assertEquals(Values.ACE, aceOfSpades.getValue());
        assertEquals(Suits.SPADES, aceOfSpades.getSuit());
    }

    @Test
    public void testFour() {
        assertEquals(Values.FOUR, fourOfClubs.getValue());
        assertEquals(Suits.CLUBS, fourOfClubs.getSuit());
    }
}
