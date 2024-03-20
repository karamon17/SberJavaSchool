package org.example;

import org.example.entity.Category;
import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.services.CategoryService;
import org.example.services.RecipeService;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class ConsoleUI {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public ConsoleUI(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    public void searchRecipe(Scanner scanner) {
        try {
            System.out.print("Enter recipe name or part of the name: ");
            scanner.nextLine();
            String name = scanner.nextLine();
            System.out.println("Recipes found:");
            for (Recipe recipe : recipeService.findRecipeByName(name)) {
                System.out.println(recipe);
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }

    }

    public void addRecipe(Scanner scanner) {
        try {
            System.out.print("Enter recipe name: ");
            scanner.nextLine();
            String name = scanner.nextLine();
            Recipe recipe = new Recipe();
            recipe.setName(name);

            System.out.print("Enter recipe category: ");
            name = scanner.nextLine();
            Category category = new Category();
            category.setName(name);
            recipe.setCategory(category);
            categoryService.createCategory(category);

            System.out.print("Enter the number of ingredients: ");
            int numIngredients = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numIngredients; i++) {
                Ingredient ingredient = new Ingredient();

                System.out.print("Enter ingredient name: ");
                name = scanner.nextLine();
                ingredient.setName(name);

                System.out.print("Enter ingredient amount: ");
                String amount = scanner.nextLine();
                ingredient.setAmount(amount);

                ingredient.setRecipe(recipe);
                recipe.getIngredients().add(ingredient);
            }

            recipeService.addRecipe(recipe);
            category.getRecipes().add(recipe);
            System.out.println("Recipe added successfully");
        } catch (NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }

    }

    public void deleteRecipe(Scanner scanner) {
        try {
            System.out.print("Enter recipe ID that you would like to delete: ");
            scanner.nextLine();
            String name = scanner.nextLine();
            recipeService.deleteRecipe(Long.parseLong(name));
            System.out.println("Recipe deleted successfully");
        } catch (NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
