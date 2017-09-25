package com.example.davidryan.cardgame.cards;

import com.example.davidryan.cardgame.cardattributes.Suits;
import com.example.davidryan.cardgame.cardattributes.Values;

/**
 * Created by davidryan on 22/09/2017.
 */

public class MarkedCard extends PlayingCard {
    private String text;

    public MarkedCard(Values value, Suits suit, String text) {
        super(value, suit);
        this.text = text;
    }

    public MarkedCard(Values value, Suits suit) {
        this(value, suit, "");
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        // returns text similar to '5Ca'
        return super.toString() + getText();
    }

    @Override
    public String toVerboseString() {
        String extraText = getText();
        if (extraText.length() > 0) {
            extraText = " (" + extraText + ")";
        }
        // returns text similar to 'FIVE of CLUBS (a)'
        return super.toString() + extraText;
    }

}
