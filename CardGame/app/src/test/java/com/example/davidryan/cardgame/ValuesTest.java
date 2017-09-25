package com.example.davidryan.cardgame;

import com.example.davidryan.cardgame.cardattributes.Values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidryan on 22/09/2017.
 */

public class ValuesTest {

    @Test
    public void testValueNumbers(){
        assertEquals(3, Values.THREE.ORDER());
        assertEquals(11, Values.JACK.ORDER());
        assertEquals(14, Values.ACE.ORDER());
    }
}
