package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.models.cards.PlayingCard;
import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.players.AbstractPlayer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidryan on 22/09/2017.
 */

public class PlayerTest {
    AbstractPlayer david;
    PlayingCard aceOfSpades;
    PlayingCard queenOfDiamonds;

    @Before
    public void before(){
        david = new AbstractPlayer("David");
        aceOfSpades = new PlayingCard(Values.ACE, Suits.SPADES);
        queenOfDiamonds = new PlayingCard(Values.QUEEN, Suits.DIAMONDS);
    }

    @Test
    public void testCanGetName(){
        assertEquals("David", david.getName());
    }

    @Test
    public void testPlayerHasCard(){
        david.receive(aceOfSpades);
        PlayingCard card = david.getLatestCard();
        assertEquals(Values.ACE, card.getValue());
        assertEquals(Suits.SPADES, card.getSuit());
    }

    @Test
    public void testCanGetScore(){
        david.receive(queenOfDiamonds);
        david.receive(aceOfSpades);
        // This would fail if player can receive more than one card!
        assertEquals(14, david.getScore());
    }
}

