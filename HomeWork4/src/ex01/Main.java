package ex01;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TerminalImpl terminal = new TerminalImpl("1234");
        //terminal.inputPinAndValidate();
        terminal.put(-100);
        terminal.put(200);
        terminal.withdraw(50);
        terminal.put(1000);
        terminal.checkBalance();
        terminal.withdraw(300);
        terminal.withdraw(3000);
        //terminal.inputPinAndValidate();
        //Thread.sleep(10000);
        //terminal.inputPinAndValidate();
        terminal.checkBalance();
    }
}