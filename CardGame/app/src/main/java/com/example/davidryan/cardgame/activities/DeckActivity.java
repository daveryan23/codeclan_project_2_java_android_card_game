package com.example.davidryan.cardgame.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.davidryan.cardgame.R;
import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.decks.Decky;
import com.example.davidryan.cardgame.models.decks.RandomDeck;

import java.util.ArrayList;

public class DeckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);

        // Make the random Deck, setup with test cards
        Decky deck = new RandomDeck();
        deck.addTestSet();
        deck.addPacksOfCards(1);
        deck.shuffle();

        // Get the list of cards in the deck, and fit an adapter to them
        ArrayList<Cardy> list = deck.getCardArray();
        DeckAdapter deckAdapter = new DeckAdapter(this, list);

        // Make sure the main list view is using this adapter
        ListView listView = (ListView) findViewById(R.id.list_deck);
        listView.setAdapter(deckAdapter);
    }

    public void getCard(View listItem) {
        Cardy card = (Cardy) listItem.getTag();
        Log.d("Card description:", card.toVerboseString());
    }

}