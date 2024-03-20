package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.entity.Recipe;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeDAOImpl implements RecipeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Recipe> findByName(String name) {
        String jpql = "SELECT r FROM Recipe r WHERE r.name LIKE :name";
        return entityManager.createQuery(jpql, Recipe.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    public void addRecipe(Recipe recipe) {
        entityManager.persist(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        Recipe recipe = entityManager.find(Recipe.class, id);
        if (recipe != null) {
            entityManager.remove(recipe);
        }
    }
}
