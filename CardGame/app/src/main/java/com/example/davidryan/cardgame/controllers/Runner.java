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

        boolean outputOnlyHighPriority = true;
        Playery dealer = new DealerBot("Big Tom (Dealerbot)", 1000000);

        // Bets are in pence, e.g. 0.01GBP
        // Can display this properly in the UI
        game = new BlackjackGame(200, 10000, 50, deck, dealer, new KeyboardScanner(), new ConsoleLogger(), outputOnlyHighPriority);

        Playery bob = new BotPlayer("Bob Richrobot", 45000);
        Playery dave = new HumanPlayer("Dave Human", 10000);
        Playery phil = new BotPlayer("Phillip Poorbot", 2000);
//        Playery bot1 = new BotPlayer("Bot1", 50000);
//        Playery bot2 = new BotPlayer("Bot2", 50000);
//        Playery bot3 = new BotPlayer("Bot3", 50000);
//        Playery bot4 = new BotPlayer("Bot4", 50000);
//        Playery bot5 = new BotPlayer("Bot5", 50000);
//        Playery bot6 = new BotPlayer("Bot6", 50000);
//        Playery bot7 = new BotPlayer("Bot7", 50000);
//        Playery bot8 = new BotPlayer("Bot8", 50000);
//        Playery bot9 = new BotPlayer("Bot9", 50000);
//        Playery botA = new BotPlayer("BotA", 50000);
//        Playery botB = new BotPlayer("BotB", 50000);
//        Playery botC = new BotPlayer("BotC", 50000);
        game.add(bob);
        game.add(dave);
        game.add(phil);
//        game.add(bot1);
//        game.add(bot2);
//        game.add(bot3);
//        game.add(bot4);
//        game.add(bot5);
//        game.add(bot6);
//        game.add(bot7);
//        game.add(bot8);
//        game.add(bot9);
//        game.add(botA);
//        game.add(botB);
//        game.add(botC);
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
