package com.example.davidryan.cardgame.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.davidryan.cardgame.R;
import com.example.davidryan.cardgame.models.cards.Cardy;
import com.example.davidryan.cardgame.models.decks.Decky;

import java.util.ArrayList;

/**
 * Created by davidryan on 04/10/2017.
 */

public class DeckAdapter extends ArrayAdapter {

    public DeckAdapter(Context context, ArrayList<Cardy> cards ) {
        super(context, 0, cards);
    }

    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView==null) {
            listItemView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.card_item, parent, false);
        }

        Cardy card = (Cardy) getItem(position);

        // Setup the text description box
        TextView cardText = listItemView.findViewById(R.id.the_card);
        cardText.setText(card.toVerboseString());

        // Setup the tag, used when clicking the list item
        listItemView.setTag(card);

        return listItemView;
    }

}
