package com.example.davidryan.cardgame.models.games;

import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.decks.Decky;
import com.example.davidryan.cardgame.views.inputs.Logging;
import com.example.davidryan.cardgame.views.outputs.Scanning;

import java.util.List;

/**
 * Created by davidryan on 25/09/2017.
 */

public abstract class CardGame implements Gamey {
    private Decky deck;
    private Logging logger;
    private Scanning scanner;

    public CardGame(Decky deck, Logging logger, Scanning scanner){
        this.deck = deck;
        this.logger = logger;
        this.scanner = scanner;
    }

    public boolean loopGameUntilFalse() {
        boolean result = false;
        outputLine("");
        outputString("Play another time? (y/n) ");
        String userCommand = inputLine();
        if ( (userCommand.equalsIgnoreCase("n"))
                || (userCommand.equalsIgnoreCase("no"))
                || (userCommand.equalsIgnoreCase("quit")) ) {
            result = true;
        }
        outputLine(String.format("Your command is '%s', exit is " + result, userCommand));
        return result;
    }

    @Override
    public int minimumBet() {
        return 0;
    }

    @Override
    public Decky getDeck() {
        return deck;
    }

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
    public void returnCards(List<Cardy> cards) {
        deck.returnCards(cards);
    }
}
