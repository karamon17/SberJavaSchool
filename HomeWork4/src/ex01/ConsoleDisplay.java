package ex01;

public class ConsoleDisplay implements MessageDisplay {
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
