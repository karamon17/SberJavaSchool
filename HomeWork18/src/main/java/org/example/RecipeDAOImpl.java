package org.example;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class RecipeDAOImpl implements RecipeDAO {

    private final JdbcTemplate jdbcTemplate;

    public RecipeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> findByName(String name) {
        try {
            String sql = "SELECT * FROM recipes WHERE name LIKE ?";
            return jdbcTemplate.query(sql, new Object[]{"%" + name + "%"}, new RecipeMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void addRecipe(Recipe recipe) {
        try {
            String sql = "INSERT INTO recipes (name, ingredients, instructions) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, recipe.getName(), recipe.getIngredients(), recipe.getInstructions());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteRecipe(Long id) {
        try {
            String sql = "DELETE FROM recipes WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }

    private static final class RecipeMapper implements RowMapper<Recipe> {
        @Override
        public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
            Recipe recipe = new Recipe();
            recipe.setId(rs.getLong("id"));
            recipe.setName(rs.getString("name"));
            recipe.setIngredients(rs.getString("ingredients"));
            recipe.setInstructions(rs.getString("instructions"));
            return recipe;
        }
    }
}
