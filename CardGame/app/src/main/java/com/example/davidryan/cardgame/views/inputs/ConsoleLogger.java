package com.example.davidryan.cardgame.views.inputs;

/**
 * Created by davidryan on 15/09/2017.
 */

public class ConsoleLogger implements Loggy {

    // Try / Sleep / Catch / Finally has been used
    // to slow down the output
    // and to prevent error messages from getting mixed up
    // with messages sent earlier

    @Override
    public void outputString(String message) {

        try {
            Thread.sleep(25);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.print(message);
        }

    }

    @Override
    public void outputLine(String message) {

        try {
            Thread.sleep(50);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(message);
        }

    }

}
