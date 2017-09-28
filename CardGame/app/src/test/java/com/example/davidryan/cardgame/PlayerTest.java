package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.cards.MarkedCard;
import com.example.davidryan.cardgame.models.cards.PlayingCard;
import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.players.HumanPlayer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidryan on 22/09/2017.
 */

public class PlayerTest {

    private PlayingCard ace;
    private PlayingCard king;
    private PlayingCard jack;
    private PlayingCard seven;
    private PlayingCard fiveClubs;
    private PlayingCard fiveDiamonds;
    private PlayingCard four;
    private MarkedCard twoMarked;

    private HumanPlayer harry;
    private Gamey gameMock;

    @Before
    public void before() {

        // Setup cards
        ace = new PlayingCard(Values.ACE, Suits.CLUBS);
        king = new PlayingCard(Values.KING, Suits.HEARTS);
        jack = new PlayingCard(Values.JACK, Suits.DIAMONDS);
        seven = new PlayingCard(Values.SEVEN, Suits.SPADES);
        fiveClubs = new PlayingCard(Values.FIVE, Suits.CLUBS);
        fiveDiamonds = new PlayingCard(Values.FIVE, Suits.DIAMONDS);
        four = new PlayingCard(Values.FOUR, Suits.HEARTS);
        twoMarked = new MarkedCard(Values.TWO, Suits.CLUBS, "x");

        harry = new HumanPlayer("Harry", 10000);

        gameMock = Mockito.mock(Gamey.class);

    }

    @Test
    public void testGetName() {
        assertEquals("Harry", harry.getName());
    }

//    @Test
//    public void testGetInitialBetAmount() {
//        // Also need to mock the bet minimum, maximum, increment...
//        Mockito.when(gameMock.askQuestion("")).thenReturn("7.00");
//        Mockito.when(gameMock.convertBackToMoneyUnits("7.00")).thenReturn(700);
//        assertEquals(700, harry.getInitialBetAmount(gameMock));
//    }

    @Test
    public void testMoneyAvailable() {
        assertEquals(10000, harry.moneyAvailable());
    }

    @Test
    public void testReset() {
        harry.reset(gameMock);
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

