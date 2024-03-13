package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    private final TerminalImpl terminal;

    public Main(TerminalImpl terminal) {
        this.terminal = terminal;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = context.getBean(Main.class);
        main.start();
    }

    private void start() {

        while (!terminal.getPinValidator().isAccountOpen()) {
            terminal.inputPinAndValidate();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int userInput = inputCommand(scanner);
            switch (userInput) {
                case 1 -> terminal.checkBalance();
                case 2 -> {
                    int amount = inputAmount(scanner);
                    terminal.put(amount);
                }
                case 3 -> {
                    int amount = inputAmount(scanner);
                    terminal.withdraw(amount);
                }
                case 0 -> System.exit(0);
                default -> System.out.println("Нет такого варианта!");
            }
        }
    }

    private static int inputCommand(Scanner scanner) {
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
                return (scanner.nextInt());
            }
            else {
                System.out.print("Некорректное значение.");
                scanner.next();
            }
        }
    }

    private static int inputAmount(Scanner scanner) {
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