package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidryan on 22/09/2017.
 */

public class CardAttributesTest {

    @Test
    public void testSuitSymbolSpades(){
        assertEquals("\u2660", Suits.SPADES.SYMBOL());
    }

    @Test
    public void testSuitSymbolHearts(){
        assertEquals("\u2665", Suits.HEARTS.SYMBOL());
    }

    @Test
    public void testSuitSymbolDiamonds(){
        assertEquals("\u2666", Suits.DIAMONDS.SYMBOL());
    }

    @Test
    public void testSuitSymbolClubs(){
        assertEquals("\u2663", Suits.CLUBS.SYMBOL());
    }

    @Test
    public void testValuesSymbols(){
        assertEquals("A", Values.ACE.SYMBOL());
        assertEquals("Q", Values.QUEEN.SYMBOL());
        assertEquals("10", Values.TEN.SYMBOL());
        assertEquals("6", Values.SIX.SYMBOL());
        assertEquals("3", Values.THREE.SYMBOL());
    }

    @Test
    public void testValuesOrders(){
        assertEquals(14, Values.ACE.ORDER());
        assertEquals(12, Values.QUEEN.ORDER());
        assertEquals(10, Values.TEN.ORDER());
        assertEquals(5, Values.FIVE.ORDER());
        assertEquals(2, Values.TWO.ORDER());
    }

    @Test
    public void testValuesScores(){
        assertEquals(1, Values.ACE.SCORE());
        assertEquals(10, Values.KING.SCORE());
        assertEquals(10, Values.TEN.SCORE());
        assertEquals(7, Values.SEVEN.SCORE());
        assertEquals(2, Values.TWO.SCORE());
    }

}
