package com.example.davidryan.cardgame.views.outputs;

import java.util.Scanner;

/**
 * Created by davidryan on 15/09/2017.
 */

public class KeyboardScanner implements Scanning {
    private Scanner scanner;

    public KeyboardScanner() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String inputLine() {
        return scanner.nextLine();
    }

}
