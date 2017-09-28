package com.example.davidryan.cardgame.models.games;

import com.example.davidryan.cardgame.models.cardattributes.Values;
import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.decks.Decky;
import com.example.davidryan.cardgame.models.players.Playery;
import com.example.davidryan.cardgame.views.inputs.Loggy;
import com.example.davidryan.cardgame.views.outputs.Scanny;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public class BlackjackGame extends CardGame {
    private int minimumBet;
    private int maximumBet;
    private int betIncrement;
    private Playery dealer;
    private List<Playery> players;

    public BlackjackGame(int minimumBet, int maximumBet, int betIncrement, Decky deck, Playery dealer, Scanny scanner, Loggy logger, boolean outputOnlyHighPriority) {
        super(deck, scanner, logger, outputOnlyHighPriority);
        this.minimumBet = minimumBet;
        this.maximumBet = maximumBet;
        this.betIncrement = betIncrement;
        this.dealer = dealer;
        players = new ArrayList<>();
    }

    // Getters

    @Override
    public int minimumBet() {
        return minimumBet;
    }

    @Override
    public int maximumBet() {
        return maximumBet;
    }

    @Override
    public int betIncrement() {
        return betIncrement;
    }

    // Dealer interaction

    @Override
    public int dealerTopCardScore() {
        return dealer.topCardScore();
    }

    @Override
    public void reduceDealerMoney(int moneyWonByPlayer) {
        dealer.incrementMoney(-moneyWonByPlayer);
    }

    @Override
    public String describeDealerHand() {
        return dealer.describeFirstHand();
    }

    // Blackjack game-specific items

    public void add(Playery player) {
        players.add(player);
    }

    public void remove(Playery player) {
        players.remove(player);
    }

    public void playGame() {
        outputLine("", true);
        outputLine("Starting a new round of blackjack", true);
        // Might want to shuffle the cards every game?
        outputLine(getDeck().sneakAPeekAtTheCards(this));
        checkPlayersAndDealerReset();
        placeInitialBets();
        dealFaceUpTo(players);
        dealFaceUpTo(dealer);
        dealFaceUpTo(players);
        playersPlay();
        dealFaceUpTo(dealer);
        int score = dealerPlays();
        resolveAllBets(score);
        outputLine(getDeck().sneakAPeekAtTheCards(this));
        returnAllPlayerAndDealerCards();
        outputLine(getDeck().sneakAPeekAtTheCards(this));
        outputLine("This round of blackjack is complete!", true);
    }

    private void checkPlayersAndDealerReset() {
        outputLine("Resetting players and dealer");
        for (Playery player: players) {
            player.reset(this);
        }
        dealer.reset(this);
        outputLine("Players and dealer have been reset");
    }

    private void placeInitialBets() {
        outputLine("", true);
        outputLine("Placing initial bets now");
        for (Playery player: players) {
            boolean playerPlaying = player.setupInitialBetAndHand(this);

            // HOW TO STOP PLAYER FROM DOING ANYTHING FURTHER
            // IF false, E.G. NO BET HAS BEEN PLACED?

        }
        dealer.setupInitialBetAndHand(this);
        outputLine("Initial bets have been placed");
        outputLine("", true);
    }

    private void dealFaceUpTo(Playery player) {
//        outputLine("Dealing to " + player.getName());
        Cardy card = getDeck().deal();
//        outputLine("Card is " + card.toString());
        player.dealInitialCard(this, card);
        outputLine("Dealt " + card.toString() + " to " + player.getName());
    }

    private void dealFaceUpTo(List<Playery> players) {
        outputLine("Dealing 1 card face up to all players");
        for (Playery player: players) {
            dealFaceUpTo(player);
        }
        outputLine("Dealt 1 card to all players");
    }

    private void playersPlay() {
        outputLine("Players about to play");
        for (Playery player: players) {
            player.playTurn(this);
        }
        outputLine("Players have finished playing");
    }

    private int dealerPlays() {
        outputLine("Dealer is about to play");
        dealer.playTurn(this);
        int score = dealer.getScoreOfFirstHand();
        if (score==22) {
            outputLine("Dealer has finished playing, with blackjack!");
        } else {
            outputLine("Dealer has finished playing, with score " + score);
        }
        return score;
    }

    private void resolveAllBets(int score) {
        outputLine("", true);
        outputLine("About to resolve all the player bets - dealer has " + formatMoney(dealer.moneyAvailable()));
        for (Playery player: players) {
            player.resolveBets(this, score);
        }
        outputLine("");
        outputLine("Player bets have been resolved - dealer has " + formatMoney(dealer.moneyAvailable()), true);
        outputLine("", true);
    }

    private void returnAllPlayerAndDealerCards() {
        outputLine("About to reset the players and dealer");
        for (Playery player: players) {
            player.reset(this);
        }
        dealer.reset(this);
        outputLine("Players and dealer have been reset");
    }

}
