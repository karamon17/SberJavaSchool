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
            Source source = new PostgreSQLSource(connection);
            final var calculator = new Calculator(source);
            calculator.fibonachi(1);
            calculator.fibonachi(10);
            calculator.fibonachi(15);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
