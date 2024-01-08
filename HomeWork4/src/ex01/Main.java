package ex01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TerminalImpl terminal = new TerminalImpl("1234");

        while (!terminal.getPinValidator().isAccountOpen()) {
            terminal.inputPinAndValidate();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int userInput;
            while (true) {
                System.out.println("""
                        \nВыберите действие:
                        1 - Проверить баланс
                        2 - Пополнить счет
                        3 - Снять деньги
                        0 - Выход
                        """);
                System.out.print("Введите цифру от 0 до 3: ");
                if (scanner.hasNextInt()) {
                    userInput = scanner.nextInt();
                    break;
                }
                else {
                    System.out.print("Некорректное значение.");
                    scanner.next();
                }
            }
            switch (userInput) {
                case 1 -> terminal.checkBalance();
                case 2 -> {
                    int amount = input(scanner);
                    try {
                        terminal.put(amount);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    int amount = input(scanner);
                    try {
                        terminal.withdraw(amount);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 0 -> System.exit(0);
                default -> System.out.println("Нет такого варианта!");
            }
        }
    }

    private static int input(Scanner scanner) {
        int amount;
        while (true) {
            System.out.print("Введите сумму: ");
            if (scanner.hasNextInt()) {
                amount = scanner.nextInt();
                return amount;
            }
            else {
                System.out.println("Некорректное значение.");
                scanner.next();
            }
        }
    }
}