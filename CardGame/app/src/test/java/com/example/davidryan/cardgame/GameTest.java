package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.models.cards.PlayingCard;
import com.example.davidryan.cardgame.models.decks.Decky;
import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.decks.RandomDeck;
import com.example.davidryan.cardgame.models.decks.DeterministicDeck;
import com.example.davidryan.cardgame.models.players.AbstractPlayer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by davidryan on 22/09/2017.
 */

public class GameTest {
//    AbstractPlayer david;
//    AbstractPlayer gaby;
//    AbstractPlayer jaguar;
//    ArrayList<AbstractPlayer> players;
//    Decky deckDetermTest;
//    BlackJackTable gameDetermTest;
//    Decky deckRandomFull;
//    BlackJackTable gameRandomFull;
//    Decky deckMock;
//    BlackJackTable gameMock;
//
//    @Before
//    public void before(){
//        // Set up players
//        david = new AbstractPlayer("David");
//        gaby = new AbstractPlayer("Gaby");
//        jaguar = new AbstractPlayer("Leaping Jaguar");
//        players = new ArrayList<>();
//        players.add(david);
//        players.add(gaby);
//        players.add(jaguar);
//        // Set up deterministic game with 8 cards
//        deckDetermTest = new DeterministicDeck();
//        deckDetermTest.generateTestSet();
//        gameDetermTest = new BlackJackTable(deckDetermTest);
//        gameDetermTest.add(players);
//        // Set up random game with 52 cards (full pack)
//        deckRandomFull = new RandomDeck();
//        deckRandomFull.generate();
//        gameRandomFull = new BlackJackTable(deckRandomFull);
//        gameRandomFull.add(players);
//        // Set up mock game
//        deckMock = Mockito.mock(Decky.class);
//        gameMock = new BlackJackTable(deckMock);
//        gameMock.add(players);
//    }
//
//    @Test
//    public void testRemoveAndAddPlayers(){
//        assertEquals(3, gameDetermTest.numberOfPlayers());
//        gameDetermTest.remove(jaguar);
//        assertEquals(2, gameDetermTest.numberOfPlayers());
//        gameDetermTest.add(jaguar);
//        assertEquals(3, gameDetermTest.numberOfPlayers());
//    }
//
//    @Test
//    public void testPlayersDetermGame(){
//        assertEquals(3, gameDetermTest.numberOfPlayers());
//    }
//
//    @Test
//    public void testPlayersRandomGame(){
//        assertEquals(3, gameRandomFull.numberOfPlayers());
//    }
//
//    @Test
//    public void testCardsDetermGame(){
//        assertEquals(8, gameDetermTest.getDeck().numberOfCards());
//    }
//
//    @Test
//    public void testCardsRandomGame(){
//        assertEquals(52, gameRandomFull.getDeck().numberOfCards());
//    }
//
//    @Test
//    public void testDetermDealAfterNoDiscards() {
//        gameDetermTest.discard(0);
//        gameDetermTest.dealRound();
//        AbstractPlayer winner = gameDetermTest.winner();
//        // Cards 1, 2, 3 are TEN, ACE, ACE so no winner!
//        assertNull(winner);
//        assertEquals(0, david.getWinCount());
//        assertEquals(0, gaby.getWinCount());
//        assertEquals(0, jaguar.getWinCount());
//    }
//
//    @Test
//    public void testDetermDealAfter3Discards() {
//        gameDetermTest.discard(3);
//        gameDetermTest.dealRound();
//        AbstractPlayer winner = gameDetermTest.winner();
//        // Cards 4, 5, 6 are THREE, NINE, THREE so middle player is winner
//        assertEquals("Gaby", winner.getName());
//        assertEquals(0, david.getWinCount());
//        assertEquals(1, gaby.getWinCount());
//        assertEquals(0, jaguar.getWinCount());
//    }
//
//    @Test
//    public void testMockGame() {
//        // Set up cards
//        PlayingCard card1 = new PlayingCard(Values.QUEEN, Suits.HEARTS);
//        PlayingCard card2 = new PlayingCard(Values.JACK, Suits.CLUBS);
//        PlayingCard card3 = new PlayingCard(Values.KING, Suits.DIAMONDS);
//        // Do a mock game where we tell it what cards to deal
//        Mockito.when(deckMock.deal()).thenReturn(card1).thenReturn(card2).thenReturn(card3);
//        gameMock.dealRound();
//        AbstractPlayer winner = gameMock.winner();
//        // Test the cards given to each player
//        assertEquals(jaguar, winner);
//        assertEquals(card1, david.getLatestCard());
//        assertEquals(card2, gaby.getLatestCard());
//        assertEquals(card3, jaguar.getLatestCard());
//        assertEquals(0, david.getWinCount());
//        assertEquals(0, gaby.getWinCount());
//        assertEquals(1, jaguar.getWinCount());
//    }

}
