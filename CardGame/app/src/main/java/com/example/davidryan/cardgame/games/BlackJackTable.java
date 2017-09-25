package com.example.davidryan.cardgame.games;

import com.example.davidryan.cardgame.decks.Decky;
import com.example.davidryan.cardgame.players.BotPlayer;
import com.example.davidryan.cardgame.players.Playery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidryan on 22/09/2017.
 */

public class BlackJackTable implements Gamey {
    Decky deck;
    Playery dealer;
    List<Playery> players;

    public BlackJackTable(Decky deck, Playery dealer){
        this.deck = deck;
        this.dealer = dealer;
        players = new ArrayList<>();
    }

    public Gamey add(Playery player) {
        players.add(player);
        return this;
    }

    public Gamey remove(Playery player) {
        players.remove(player);
        return this;
    }

    public void playGame() {
        checkPlayersReset();
        dealFaceUpTo(players);
        dealFaceUpTo(dealer);
        dealFaceUpTo(players);
        playersPlay();
        dealTo(dealer);
        int score = dealerPlays();
        resolveAllBets(score);
        returnAllCards();
    }

}
