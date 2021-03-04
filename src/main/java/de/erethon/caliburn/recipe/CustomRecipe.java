package de.erethon.caliburn.recipe;

import de.erethon.caliburn.recipe.result.RecipeResult;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;

/**
 * @author Fyreum
 *
 * @see CustomShapedRecipe
 * @see CustomShapelessRecipe
 */
public interface CustomRecipe extends Recipe {

    NamespacedKey getKey();

    String getId();

    RecipeResult getRecipeResult();

    int getAmount();

    String toIngredientString();

}
