package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipe {
    private Long id;
    private String name;
    private String ingredients;
    private String instructions;

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
