package de.erethon.caliburn.recipe.ingredient;

import org.bukkit.inventory.RecipeChoice;

/**
 * Represents an RecipeChoice for an {@link org.bukkit.inventory.Recipe}.
 *
 * @see ItemIngredient
 * @see MaterialIngredient
 */
public interface RecipeIngredient {

    RecipeChoice getRecipeChoice();

    String getSerialized();

}
