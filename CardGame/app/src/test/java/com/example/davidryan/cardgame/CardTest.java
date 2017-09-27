package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.cards.MarkedCard;
import com.example.davidryan.cardgame.models.cards.PlayingCard;
import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidryan on 22/09/2017.
 */

public class CardTest {
    PlayingCard aceOfSpades;
    PlayingCard fourOfClubs;
    MarkedCard markedNineOfDiamonds;
    Cardy jackOfHearts;

    @Before
    public void before() {
        aceOfSpades = new PlayingCard(Values.ACE, Suits.SPADES);
        fourOfClubs = new PlayingCard(Values.FOUR, Suits.CLUBS);
        markedNineOfDiamonds = new MarkedCard(Values.NINE, Suits.DIAMONDS, "x");
        jackOfHearts = new MarkedCard(Values.JACK, Suits.HEARTS);
    }

//    SPADES("\u2660"),
//    HEARTS("\u2665"),
//    DIAMONDS("\u2666"),
//    CLUBS("\u2663");


    @Test
    public void testAceValue() {
        assertEquals(Values.ACE, aceOfSpades.getValue());
    }

    @Test
    public void testFourSuit() {
        assertEquals(Suits.CLUBS, fourOfClubs.getSuit());
    }

    @Test
    public void testIsAce() {
        assertEquals(true, aceOfSpades.isAce());
    }

    @Test
    public void testIsNotAce() {
        assertEquals(false, jackOfHearts.isAce());
    }

    @Test
    public void testGetOrder() {
        assertEquals(14, aceOfSpades.getOrder());
    }

    @Test
    public void testGetOrderMarked() {
        assertEquals(9, markedNineOfDiamonds.getOrder());
    }

    @Test
    public void testGetOrderInterface() {
        assertEquals(11, jackOfHearts.getOrder());
    }

    @Test
    public void testGetScore() {
        assertEquals(1, aceOfSpades.getScore());
    }

    @Test
    public void testToString() {
        assertEquals("4\u2663", fourOfClubs.toString());
    }

    @Test
    public void testToVerboseString() {
        assertEquals("FOUR of CLUBS", fourOfClubs.toVerboseString());
    }

    @Test
    public void testDescribeFaceUp() {
        assertEquals("A\u2660", aceOfSpades.describeFaceUp());
    }

    @Test
    public void testDescribeUnmarkedFaceDown() {
        assertEquals("??", aceOfSpades.describeFaceDown());
    }

    @Test
    public void testDescribeMarkedFaceDown() {
        assertEquals("??x", markedNineOfDiamonds.describeFaceDown());
    }

    @Test
    public void testMarkedToString() {
        assertEquals("9\u2666x", markedNineOfDiamonds.toString());
    }

    @Test
    public void testMarkedToVerboseString() {
        assertEquals("NINE of DIAMONDS (x)", markedNineOfDiamonds.toVerboseString());
    }

}
