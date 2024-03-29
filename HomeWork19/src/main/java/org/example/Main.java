package org.example;

import org.example.repository.RecipeDAOImpl;
import org.example.services.RecipeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {

    private final ConsoleUI consoleUI;

    public Main(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        Main main = context.getBean(Main.class);
        main.start();
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            int choice = getUserChoice(scanner);
            switch (choice) {
                case 1:
                    consoleUI.searchRecipe(scanner);
                    break;
                case 2:
                    consoleUI.addRecipe(scanner);
                    break;
                case 3:
                    consoleUI.deleteRecipe(scanner);
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n====== Recipe Management System ======");
        System.out.println("1. Search Recipe");
        System.out.println("2. Add Recipe");
        System.out.println("3. Delete Recipe");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
