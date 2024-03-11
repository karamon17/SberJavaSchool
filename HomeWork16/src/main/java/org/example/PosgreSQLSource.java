package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PosgreSQLSource implements Source {
    private final Connection connection;
    private final String name;

    public PosgreSQLSource(Connection connection, String name) {
        this.connection = connection;
        this.name = name;
        try {
            connection.createStatement().executeUpdate("create table if not exists " + name + " (args text primary key, result text)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void put(String args, String result) {
        String insertQuery = "INSERT INTO " + name + "(args, result) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, args);
            preparedStatement.setString(2, result);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(String args) {
        String selectQuery = "SELECT result FROM " + name + " WHERE args = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, args);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("result");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
