package org.example.services;

import jakarta.transaction.Transactional;
import org.example.entity.Recipe;
import org.example.repository.RecipeDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
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
