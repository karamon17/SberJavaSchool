package org.example;

public class ConsoleDisplay implements MessageDisplay {
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
