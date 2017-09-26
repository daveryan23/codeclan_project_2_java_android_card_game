package com.example.davidryan.cardgame.models.games;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.decks.Decky;
import com.example.davidryan.cardgame.views.inputs.Loggy;
import com.example.davidryan.cardgame.views.outputs.Scanny;

import java.util.List;

/**
 * Created by davidryan on 25/09/2017.
 */

public abstract class CardGame implements Gamey {
    private Decky deck;
    private Loggy logger;
    private Scanny scanner;

    public CardGame(Decky deck, Loggy logger, Scanny scanner){
        this.deck = deck;
        this.logger = logger;
        this.scanner = scanner;
    }

    @Override
    public boolean askUserToPlayAgain() {
        boolean result = false;
        String userCommand = askQuestion("Play another time? (y/n)");
        if ( (userCommand.equalsIgnoreCase("n"))
                || (userCommand.equalsIgnoreCase("no"))
                || (userCommand.equalsIgnoreCase("quit")) ) {
            result = true;
        }
        outputLine(String.format("Your command is '%s', exit is " + result, userCommand));
        return result;
    }

    // Deck interaction

    @Override
    public Decky getDeck() {
        return deck;
    }

    @Override
    public Cardy dealCardFromDeck() {
        return deck.deal();
    }

    @Override
    public void returnCards(List<Cardy> cards) {
        deck.returnCards(cards);
    }

    // Input / output

    @Override
    public void outputString(String message) {
        logger.outputString(message);
    }

    @Override
    public void outputLine(String message) {
        logger.outputLine(message);
    }

    @Override
    public String inputLine() {
        return scanner.inputLine();
    }

    @Override
    public String askQuestion(String message) {
        outputString(message + " ");
        return inputLine();
    }

}
