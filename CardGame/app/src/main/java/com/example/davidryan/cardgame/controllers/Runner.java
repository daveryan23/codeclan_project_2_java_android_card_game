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

/**
 * Created by davidryan on 25/09/2017.
 */

public class Runner {
    private BlackjackGame game;

    public Runner() {

        // USER OPTIONS - CHOOSE Deck, Cards, Output Verbosity

        // Choose type of deck
        Decky deck = new RandomDeck();
//        Decky deck = new DeterministicDeck();

        // Choose packs of cards:
//        deck.addPacksOfCards(1);
//        deck.addMarkedPacksOfCards(1);
        deck.addMarkedPacksOfCards(4);

        // Choose output verbosity:
//        boolean outputOnlyHighPriority = true;
        boolean outputOnlyHighPriority = false;

        // END OF USER OPTIONS

        // Setup deck, dealer, table
        deck.shuffle();
        Playery dealer = new DealerBot("Big Tom (Dealerbot)", 1000000);
        game = new BlackjackGame(200, 10000, 50, deck, dealer, new KeyboardScanner(), new ConsoleLogger(), outputOnlyHighPriority);

        // Setup the players
        Playery bob = new BotPlayer("Bob Richrobot", 45000);
        Playery dave = new HumanPlayer("Dave Human", 10000);
        Playery phil = new BotPlayer("Phillip Poorbot", 2000);
        game.add(bob);
        game.add(dave);
        game.add(phil);

    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();
    }

    public void run() {
        game.outputLine("");
        game.outputLine("Welcome to Dave Ryan's casino! Please have a go on our superb Blackjack game. Break a leg!", true);
        game.playGame();
        while (!game.askUserToPlayAgain()) {
            // Might want to shuffle the cards every N games?
            game.playGame();
        }
        game.outputLine("");
        game.outputLine("*** *** *** GAME OVER ! *** *** ***", true);
        game.outputLine("");
    }

}
