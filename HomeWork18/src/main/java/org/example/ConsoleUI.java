package org.example;

import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class ConsoleUI {
    private final RecipeService recipeService;

    public ConsoleUI(RecipeService recipeService) {
        this.recipeService = recipeService;
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
            System.out.print("Enter recipe ingredients: ");
            recipe.setIngredients(scanner.nextLine());
            System.out.print("Enter recipe instructions: ");
            recipe.setInstructions(scanner.nextLine());
            recipeService.addRecipe(recipe);
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
