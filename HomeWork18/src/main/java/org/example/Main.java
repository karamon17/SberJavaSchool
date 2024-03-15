package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

@Component
public class Main {

    private final JdbcTemplate jdbcTemplate;
    private final ConsoleUI consoleUI;

    public Main(JdbcTemplate jdbcTemplate, ConsoleUI consoleUI) {
        this.jdbcTemplate = jdbcTemplate;
        this.consoleUI = consoleUI;
    }

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, ConsoleUI.class, RecipeService.class, RecipeDAOImpl.class, Main.class);
        Main main = context.getBean(Main.class);
        main.start();
    }

    public void start() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS recipes (" +
                "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                "name VARCHAR(255) NOT NULL," +
                "ingredients TEXT NOT NULL," +
                "instructions TEXT)";
        jdbcTemplate.execute(sqlCreateTable);

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
