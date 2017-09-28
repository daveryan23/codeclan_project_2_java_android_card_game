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
    private boolean outputOnlyHighPriority;

    public CardGame(Decky deck, Scanny scanner, Loggy logger, boolean outputOnlyHighPriority){
        this.deck = deck;
        this.scanner = scanner;
        this.logger = logger;
        this.outputOnlyHighPriority = outputOnlyHighPriority;
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
        outputString(message, false);
    }

    @Override
    public void outputLine(String message) {
        outputLine(message, false);
    }

    @Override
    public void outputString(String message, boolean highPriority) {
        if (!outputOnlyHighPriority || highPriority) {
            logger.outputString(message);
        }
    }

    @Override
    public void outputLine(String message, boolean highPriority) {
        if (!outputOnlyHighPriority || highPriority) {
            logger.outputLine(message);
        }
    }

    @Override
    public String inputLine() {
        return scanner.inputLine();
    }

    @Override
    public String askQuestion(String message) {
        outputString(message + " ", true);
        return inputLine();
    }

    public String formatMoney(int moneyUnits) {
        String result = "£" + (0.01*moneyUnits) + "0";
        // Use String.format
        // Even better, use Currency from locale!
        return result;
    }

    public int convertBackToMoneyUnits(String inputAmount) {
        int moneyUnits = Math.round(100 * Float.parseFloat(inputAmount));
        return moneyUnits;
    }

}
