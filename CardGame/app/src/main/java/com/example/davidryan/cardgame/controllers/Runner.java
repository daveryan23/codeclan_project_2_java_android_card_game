package com.example.davidryan.cardgame.controllers;

import com.example.davidryan.cardgame.models.decks.Decky;
import com.example.davidryan.cardgame.models.decks.RandomDeck;
import com.example.davidryan.cardgame.models.games.BlackjackGame;
import com.example.davidryan.cardgame.models.players.BotPlayer;
import com.example.davidryan.cardgame.models.players.DealerBot;
import com.example.davidryan.cardgame.models.players.HumanPlayer;
import com.example.davidryan.cardgame.models.players.Playery;
import com.example.davidryan.cardgame.views.inputs.ConsoleLogger;
import com.example.davidryan.cardgame.views.outputs.KeyboardScanner;

import java.util.concurrent.TimeUnit;

/**
 * Created by davidryan on 25/09/2017.
 */

public class Runner {
    private BlackjackGame game;

    public Runner() {
        Decky deck = new RandomDeck();
//        deck.setupWithMarkedPacks(4);
        deck.setupWithPacks(1);
        deck.shuffle();

        // Money is in pence.
        // Give dealer a lot of money (£10000)
        // Give players between £10 and £1000 each

        Playery dealer = new DealerBot("Big Tom (Dealerbot)", 1000000);

        // Bets are in pence, e.g. 0.01GBP
        // Can display this properly in the UI
        game = new BlackjackGame(200, 10000, 50, deck, dealer, new ConsoleLogger(), new KeyboardScanner());

        Playery bob = new BotPlayer("Rich Bobbot", 45000);
        Playery dave = new HumanPlayer("Davehuman", 10000);
        Playery phil = new BotPlayer("Poor Philbot", 2000);
        game.add(bob);
        game.add(dave);
        game.add(phil);
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();
    }

    public void run() {
        game.outputLine("Welcome to the Blackjack game!");
        game.playGame();
//        while (!game.askUserToPlayAgain()) {
//            // Might want to shuffle the cards every N games?
//            game.playGame();
//        }
        game.outputLine("Goodbye!");
    }

}
