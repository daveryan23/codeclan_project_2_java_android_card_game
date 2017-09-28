package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.cards.MarkedCard;
import com.example.davidryan.cardgame.models.cards.PlayingCard;
import com.example.davidryan.cardgame.models.games.Gamey;
import com.example.davidryan.cardgame.models.hands.CardHand;
import com.example.davidryan.cardgame.models.hands.HandDecisions;
import com.example.davidryan.cardgame.models.hands.SplitHand;
import com.example.davidryan.cardgame.models.players.HumanPlayer;
import com.example.davidryan.cardgame.models.players.Playery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidryan on 27/09/2017.
 */

public class HandTest {

    private PlayingCard ace;
    private PlayingCard king;
    private PlayingCard jack;
    private PlayingCard seven;
    private PlayingCard fiveClubs;
    private PlayingCard fiveDiamonds;
    private PlayingCard four;
    private MarkedCard twoMarked;

    private CardHand hand;
    private SplitHand splitHand;

    private Playery humanPlayer;
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

        // Setup empty hands
        hand = new CardHand("Harry", 23);
        splitHand = new SplitHand("Sid", 45);

        humanPlayer = new HumanPlayer("Andrew", 10000);
        gameMock = Mockito.mock(Gamey.class);

    }

    @Test
    public void testHandDecisions() {
        HandDecisions decision1 = HandDecisions.DOUBLE;
        HandDecisions decision2 = HandDecisions.SPLIT;
        boolean result = (decision1==decision2);
        assertEquals(false, result);
    }

    @Test
    public void testEmptyHandScore() {
        assertEquals(0, hand.finalScore());
    }

    @Test
    public void testEmptySplitHandScore() {
        assertEquals(0, splitHand.finalScore());
    }

    @Test
    public void testGetLabel() {
        assertEquals("Harry", hand.getLabel());
    }

    @Test
    public void testGetBet() {
        assertEquals(23, hand.getBet());
        assertEquals(23, hand.returnMoney());
        assertEquals(0, hand.getBet());
    }

    @Test
    public void testDescribeHandEmpty() {
        assertEquals(0, hand.countCards());
        assertEquals(false, hand.isBlackjack());
        assertEquals(false, hand.isBust());
        assertEquals(0, hand.finalScore());
        assertEquals(true, hand.canStand(humanPlayer));
        assertEquals(true, hand.canHit(humanPlayer));
        assertEquals(false, hand.canDouble(humanPlayer));
        assertEquals(false, hand.canSplit(humanPlayer));
        assertEquals(0, hand.firstCardScore());
        //        assertEquals(" (0)", hand.describeCards());
    }

    @Test
    public void testDescribeHandOneCard() {
        hand.receiveFaceUp(ace);
        assertEquals(1, hand.countCards());
        assertEquals(false, hand.isBlackjack());
        assertEquals(false, hand.isBust());
        assertEquals(11, hand.finalScore());
        assertEquals(true, hand.canStand(humanPlayer));
        assertEquals(true, hand.canHit(humanPlayer));
        assertEquals(false, hand.canDouble(humanPlayer));
        assertEquals(false, hand.canSplit(humanPlayer));
        assertEquals(11, hand.firstCardScore());
        assertEquals("A♣", hand.describeCards());
    }

    @Test
    public void testDescribeHandBlackJack() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(ace);
        assertEquals(2, hand.countCards());
        assertEquals(true, hand.isBlackjack());
        assertEquals(false, hand.isBust());
        assertEquals(22, hand.finalScore());
        assertEquals(false, hand.canHit(humanPlayer));
        assertEquals(false, hand.canDouble(humanPlayer));
        assertEquals(false, hand.canSplit(humanPlayer));
        assertEquals("K♥, A♣ (BLACKJACK)", hand.describeCards());
    }

    @Test
    public void testDescribeSplitHandNotBeingBlackJack() {
        splitHand.receiveFaceUp(ace);
        splitHand.receiveFaceUp(king);
        assertEquals(2, splitHand.countCards());
        assertEquals(false, splitHand.isBlackjack());
        assertEquals(false, splitHand.isBust());
        assertEquals(21, splitHand.finalScore());
        assertEquals(true, hand.canHit(humanPlayer));
        assertEquals(false, hand.canDouble(humanPlayer));
        assertEquals(false, hand.canSplit(humanPlayer));
        assertEquals("A♣, K♥ (11/21)", splitHand.describeCards());
    }

    @Test
    public void testDescribeSoftHand() {
        hand.receiveFaceUp(ace);
        hand.receiveFaceUp(seven);
        assertEquals(2, hand.countCards());
        assertEquals(false, hand.isBlackjack());
        assertEquals(false, hand.isBust());
        assertEquals(18, hand.finalScore());
        assertEquals(true, hand.canHit(humanPlayer));
        assertEquals(false, hand.canDouble(humanPlayer));
        assertEquals(false, hand.canSplit(humanPlayer));
        assertEquals("A♣, 7♠ (8/18)", hand.describeCards());
    }

    @Test
    public void testDescribeSplittableHand() {
        hand.receiveFaceUp(jack);
        hand.receiveFaceUp(king);
        assertEquals(2, hand.countCards());
        assertEquals(false, hand.isBlackjack());
        assertEquals(false, hand.isBust());
        assertEquals(20, hand.finalScore());
        assertEquals(true, hand.canHit(humanPlayer));
        assertEquals(false, hand.canDouble(humanPlayer));
        assertEquals(true, hand.canSplit(humanPlayer));
        assertEquals("J♦, K♥ (20)", hand.describeCards());
    }


    @Test
    public void testDescribeDoublableHand() {
        hand.receiveFaceUp(fiveDiamonds);
        hand.receiveFaceUp(twoMarked);
        hand.receiveFaceUp(four);
        assertEquals(3, hand.countCards());
        assertEquals(false, hand.isBlackjack());
        assertEquals(false, hand.isBust());
        assertEquals(11, hand.finalScore());
        assertEquals(true, hand.canHit(humanPlayer));
        assertEquals(true, hand.canDouble(humanPlayer));
        assertEquals(false, hand.canSplit(humanPlayer));
        assertEquals(5, hand.firstCardScore());
        assertEquals("5♦, 2♣x, 4♥ (11)", hand.describeCards());
    }


    @Test
    public void testDescribeDoublableAndSplittableHand() {
        hand.receiveFaceUp(fiveDiamonds);
        hand.receiveFaceUp(fiveClubs);
        assertEquals(2, hand.countCards());
        assertEquals(false, hand.isBlackjack());
        assertEquals(false, hand.isBust());
        assertEquals(10, hand.finalScore());
        assertEquals(true, hand.canHit(humanPlayer));
        assertEquals(true, hand.canDouble(humanPlayer));
        assertEquals(true, hand.canSplit(humanPlayer));
        assertEquals(4, hand.countChoices(humanPlayer));
        assertEquals("5♦, 5♣ (10)", hand.describeCards());
    }

    @Test
    public void testDescribeHardHand() {
        hand.receiveFaceUp(jack);
        hand.receiveFaceUp(seven);
        assertEquals(2, hand.countCards());
        assertEquals(false, hand.isBlackjack());
        assertEquals(false, hand.isBust());
        assertEquals(17, hand.finalScore());
        assertEquals(true, hand.canHit(humanPlayer));
        assertEquals(false, hand.canDouble(humanPlayer));
        assertEquals(false, hand.canSplit(humanPlayer));
        assertEquals("J♦, 7♠ (17)", hand.describeCards());
    }

    @Test
    public void testDescribeHardHandWithHiddenCard() {
        hand.receiveFaceDown(jack);
        hand.receiveFaceUp(seven);
        hand.receiveFaceDown(twoMarked);
        assertEquals(3, hand.countCards());
        assertEquals(false, hand.isBlackjack());
        assertEquals(false, hand.isBust());
        assertEquals(19, hand.finalScore());
        assertEquals(true, hand.canHit(humanPlayer));
        assertEquals(false, hand.canDouble(humanPlayer));
        assertEquals(false, hand.canSplit(humanPlayer));
        assertEquals(10, hand.firstCardScore());
        assertEquals("??, 7♠, ??x (19)", hand.describeCards());
    }

    @Test
    public void testDescribeBustHand() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(jack);
        hand.receiveFaceUp(seven);
        assertEquals(3, hand.countCards());
        assertEquals(false, hand.isBlackjack());
        assertEquals(true, hand.isBust());
        assertEquals(0, hand.finalScore());   // BUST scores zero
        assertEquals(false, hand.canHit(humanPlayer));
        assertEquals(false, hand.canDouble(humanPlayer));
        assertEquals(false, hand.canSplit(humanPlayer));
        assertEquals("K♥, J♦, 7♠ (27, BUST)", hand.describeCards());
    }

    @Test
    public void testReturningCards() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(twoMarked);
        hand.receiveFaceUp(seven);
        assertEquals(3, hand.countCards());
        hand.returnCards();
        assertEquals(0, hand.countCards());
    }

    @Test
    public void testResolveBetWon() {
        hand.receiveFaceUp(king);      // Hand has bet of 23 money units.
        hand.receiveFaceUp(twoMarked);
        hand.receiveFaceUp(seven);
        assertEquals(23, hand.resolveBet(gameMock, humanPlayer, 18));
    }

    @Test
    public void testResolveBetPushed() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(twoMarked);
        hand.receiveFaceUp(seven);
        assertEquals(0, hand.resolveBet(gameMock, humanPlayer, 19));
    }

    @Test
    public void testResolveBetLost() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(twoMarked);
        hand.receiveFaceUp(seven);
        assertEquals(-23, hand.resolveBet(gameMock, humanPlayer, 20));
    }

    @Test
    public void testDealerBust() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(twoMarked);
        hand.receiveFaceUp(seven);
        assertEquals(23, hand.resolveBet(gameMock, humanPlayer, 0));
    }

    @Test
    public void testDealerBustVsPlayerBlackjack() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(ace);
        assertEquals(34, hand.resolveBet(gameMock, humanPlayer, 0));
    }

    @Test
    public void testDealerBlackjack() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(twoMarked);
        hand.receiveFaceUp(seven);
        assertEquals(-23, hand.resolveBet(gameMock, humanPlayer, 22));
    }

    @Test
    public void testPlayerBust() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(jack);
        hand.receiveFaceUp(seven);
        assertEquals(-23, hand.resolveBet(gameMock, humanPlayer, 20));
    }

    @Test
    public void testPlayerBlackjack() {
        hand.receiveFaceUp(king);   // 23 at 3:2 gives 34.5, which rounds down to 34
        hand.receiveFaceUp(ace);
        assertEquals(34, hand.resolveBet(gameMock, humanPlayer, 20));
    }

    @Test
    public void testPlayerBlackjackVsDealer21() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(ace);
        assertEquals(34, hand.resolveBet(gameMock, humanPlayer, 21));
    }

    @Test
    public void testPlayerBlackjackVsDealerBlackJack() {
        hand.receiveFaceUp(king);
        hand.receiveFaceUp(ace);
        assertEquals(0, hand.resolveBet(gameMock, humanPlayer, 22));
    }

    @Test
    public void testPlayerSplitBlackjackVsDealerBust() {
        splitHand.receiveFaceUp(king);      // Split hand is betting 45 money units
        splitHand.receiveFaceUp(ace);
        assertEquals(45, splitHand.resolveBet(gameMock, humanPlayer, 0));
    }

    @Test
    public void testPlayerSplitBlackjackVsDealer20() {
        splitHand.receiveFaceUp(king);
        splitHand.receiveFaceUp(ace);
        assertEquals(45, splitHand.resolveBet(gameMock, humanPlayer, 20));
    }

    @Test
    public void testPlayerSplitBlackjackVsDealer21() {
        splitHand.receiveFaceUp(king);
        splitHand.receiveFaceUp(ace);
        assertEquals(0, splitHand.resolveBet(gameMock, humanPlayer, 21));
    }

    @Test
    public void testPlayerSplitBlackjackVsDealerBlackJack() {
        splitHand.receiveFaceUp(king);        // Split hand bets 45, not 23!
        splitHand.receiveFaceUp(ace);
        assertEquals(-45, splitHand.resolveBet(gameMock, humanPlayer, 22));
    }

//    //WRITE MORE TESTS!
//
//    @Test
//    public void testX() {
//        assertEquals(1, 1);
//    }
//

}
