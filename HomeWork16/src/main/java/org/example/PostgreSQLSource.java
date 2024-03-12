package org.example;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PostgreSQLSource implements Source {
    private final Connection connection;

    public PostgreSQLSource(Connection connection) {
        this.connection = connection;
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS cache (key TEXT PRIMARY KEY, result INTEGER[])");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void saveData(String key, List<Integer> result) {
        String insertQuery = "INSERT INTO cache" + "(key, result) VALUES (?, ?) ON CONFLICT (key) DO NOTHING";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, key);
            Array array = connection.createArrayOf("integer", result.toArray());
            preparedStatement.setArray(2, array);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getData(String key) {
        String selectQuery = "SELECT result FROM cache" + " WHERE key = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, key);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Array array = resultSet.getArray("result");
                Integer[] integers = (Integer[]) array.getArray();
                return Arrays.asList(integers);
            }
            return List.of();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
