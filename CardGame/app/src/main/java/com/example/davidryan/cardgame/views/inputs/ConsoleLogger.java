package com.example.davidryan.cardgame.views.inputs;

/**
 * Created by davidryan on 15/09/2017.
 */

public class ConsoleLogger implements Loggy {

    @Override
    public void outputString(String message) {
        System.out.print(message);
    }

    @Override
    public void outputLine(String message) {
        System.out.println(message);
    }

}
