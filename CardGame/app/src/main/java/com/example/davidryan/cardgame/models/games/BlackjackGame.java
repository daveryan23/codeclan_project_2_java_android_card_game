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
    private int betIncrement;
    private Playery dealer;
    private List<Playery> players;

    public BlackjackGame(int minimumBet, int betIncrement, Decky deck, Playery dealer, Loggy logger, Scanny scanner) {
        super(deck, logger, scanner);
        this.minimumBet = minimumBet;
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
        checkPlayersAndDealerReset();
        placeInitialBets();
        dealFaceUpTo(players);
        dealFaceUpTo(dealer);
        dealFaceUpTo(players);
        playersPlay();
        dealFaceUpTo(dealer);
        int score = dealerPlays();
        resolveAllBets(score);
        returnAllPlayerAndDealerCards();
    }

    private void checkPlayersAndDealerReset() {
        for (Playery player: players) {
            player.reset(this);
        }
        dealer.reset(this);
    }

    private void placeInitialBets() {
        for (Playery player: players) {
            boolean playerPlaying = player.setupInitialBetAndHand(this);

            // HOW TO STOP PLAYER FROM DOING ANYTHING FURTHER
            // IF false, E.G. NO BET HAS BEEN PLACED?

        }
    }

    private void dealFaceUpTo(Playery player) {
        player.dealInitialCard(this, getDeck().deal());
    }

    private void dealFaceUpTo(List<Playery> players) {
        for (Playery player: players) {
            dealFaceUpTo(player);
        }
    }

    private void playersPlay() {
        for (Playery player: players) {
            player.playTurn(this);
        }
    }

    private int dealerPlays() {
        dealer.playTurn(this);
        return dealer.getScoreOfFirstHand();
    }

    private void resolveAllBets(int score) {
        for (Playery player: players) {
            player.resolveBets(this, score);
        }
    }

    private void returnAllPlayerAndDealerCards() {
        for (Playery player: players) {
            player.reset(this);
        }
        dealer.reset(this);
    }

}
