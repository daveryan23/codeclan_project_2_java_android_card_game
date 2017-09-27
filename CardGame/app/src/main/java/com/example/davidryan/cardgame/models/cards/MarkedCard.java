package com.example.davidryan.cardgame.models.cards;

import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;

/**
 * Created by davidryan on 22/09/2017.
 */

public class MarkedCard extends PlayingCard {
    private String marking;

    public MarkedCard(Values value, Suits suit, String text) {
        super(value, suit);
        this.marking = text;
    }

    public MarkedCard(Values value, Suits suit) {
        this(value, suit, "");
    }

    public String getMarking() {
        return marking;
    }

    @Override
    public String toString() {
        // returns marking similar to '5Ca'
        return super.toString() + getMarking();
    }

    @Override
    public String toVerboseString() {
        String extraText = getMarking();
        if (extraText.length() > 0) {
            extraText = " (" + extraText + ")";
        }
        // returns marking similar to 'FIVE of CLUBS (a)'
        return super.toString() + extraText;
    }

    @Override
    public String describeFaceUp() {
        return toString();
    }

    @Override
    public String describeFaceDown() {
        return "??" + getMarking();
    }

}
