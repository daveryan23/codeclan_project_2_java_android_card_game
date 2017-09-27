package com.example.davidryan.cardgame.models.cards;

import com.example.davidryan.cardgame.models.cardattributes.Suits;
import com.example.davidryan.cardgame.models.cardattributes.Values;

/**
 * Created by davidryan on 22/09/2017.
 */

public interface Cardy {

    // Types of card
    Suits getSuit();
    Values getValue();
    boolean isAce();
    int getOrder();
    int getScore();

    // Descriptions
    String toString();
    String toVerboseString();
    String describeFaceUp();
    String describeFaceDown();

}
