package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Entity
@Getter
@Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String amount;


    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Override
    public String toString() {
        return new StringJoiner(", ", Ingredient.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("amount='" + amount + "'")
                .toString();
    }
}