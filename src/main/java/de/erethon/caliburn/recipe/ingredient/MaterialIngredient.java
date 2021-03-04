package de.erethon.caliburn.recipe.ingredient;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;

public class MaterialIngredient implements RecipeIngredient {

    private final String serialized;
    private final RecipeChoice choice;

    public MaterialIngredient(Material material) {
        this.serialized = material.name();
        this.choice = new RecipeChoice.MaterialChoice(material);
    }

    @Override
    public RecipeChoice getRecipeChoice() {
        return choice;
    }

    @Override
    public String getSerialized() {
        return serialized;
    }

}
