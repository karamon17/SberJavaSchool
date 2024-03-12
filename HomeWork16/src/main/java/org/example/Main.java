package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/homework16";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "demo12";
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            final var calculator = new Calculator();
            Source source = new PostgreSQLSource(connection);
            source.saveData("1", calculator.fibonachi(1));
            source.saveData("10", calculator.fibonachi(10));
            source.saveData("20", calculator.fibonachi(20));
            source.getData("1").forEach(System.out::println);
            System.out.println("-----");
            source.getData("10").forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
