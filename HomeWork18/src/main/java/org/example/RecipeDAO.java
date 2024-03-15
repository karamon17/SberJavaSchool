package org.example;

import java.util.List;

public interface RecipeDAO {
    /**
     * Find recipe by name
     * @param name - name of the recipe
     * @return - list of recipes with the same name
     */
    List<Recipe> findByName(String name);
    /**
     * Add recipe to the source
     * @param recipe - recipe to add
     */
    void addRecipe(Recipe recipe);
    /**
     * Delete recipe from the source
     * @param id - id of the recipe to delete
     */
    void deleteRecipe(Long id);
}
