package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeDAO recipeDAO;

    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public List<Recipe> findRecipeByName(String name) {
        return recipeDAO.findByName(name);
    }

    public void addRecipe(Recipe recipe) {
        recipeDAO.addRecipe(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeDAO.deleteRecipe(id);
    }
}
