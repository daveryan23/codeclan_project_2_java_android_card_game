package com.example.davidryan.cardgame.models.games;

import com.example.davidryan.cardgame.models.decks.Decky;
import com.example.davidryan.cardgame.models.players.Playery;
import com.example.davidryan.cardgame.views.inputs.Logging;
import com.example.davidryan.cardgame.views.outputs.Scanning;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public class BlackjackGame extends CardGame {
    private Playery dealer;
    private List<Playery> players;

    public BlackjackGame(Decky deck, Playery dealer, Logging logger, Scanning scanner) {
        super(deck, logger, scanner);
        this.dealer = dealer;
        players = new ArrayList<>();
    }


    public void add(Playery player) {
        players.add(player);
    }

    public void remove(Playery player) {
        players.remove(player);
    }

    public void playGame() {
        checkPlayersAndDealerReset();
        placeBets();
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

    private void placeBets() {
        for (Playery player: players) {
            player.placeBet(this);
        }
    }

    private void dealFaceUpTo(Playery player) {
        player.dealCard(this, getDeck().deal());
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
        return dealer.playTurn(this);
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
