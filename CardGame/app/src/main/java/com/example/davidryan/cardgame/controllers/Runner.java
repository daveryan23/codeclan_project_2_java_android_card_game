package com.example.davidryan.cardgame.controllers;

import com.example.davidryan.cardgame.models.cards.MarkedCard;
import com.example.davidryan.cardgame.models.decks.Decky;
import com.example.davidryan.cardgame.models.decks.RandomDeck;
import com.example.davidryan.cardgame.models.games.BlackjackGame;
import com.example.davidryan.cardgame.models.players.BotPlayer;
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
        Decky deck = new RandomDeck();
        deck.setupWithMarkedPacks(2);
        Playery dealer = new BotPlayer("Big Tom", 100000);

        game = new BlackjackGame(deck, dealer, new ConsoleLogger(), new KeyboardScanner());

        Playery bob = new BotPlayer("Bob", 350);
        Playery dave = new HumanPlayer("Dave", 100);
        Playery phil = new BotPlayer("Philip", 200);
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
        game.outputLine("Welcome to the Blackjack game!");
        game.playGame();
        while (!game.loopGameUntilFalse()) {
            game.playGame();
        }
        game.outputLine("");
        game.outputLine("You have now finished the Blackjack game! Thanks for your custom :)");
    }

}
