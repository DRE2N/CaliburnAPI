package de.erethon.caliburn.recipe.ingredient;

import de.erethon.caliburn.item.ExItem;
import org.bukkit.inventory.RecipeChoice;

public class ItemIngredient implements RecipeIngredient {

    private final String serialized;
    private final RecipeChoice choice;

    public ItemIngredient(ExItem item) {
        this.serialized = item.getId();
        this.choice = new RecipeChoice.ExactChoice(item.toItemStack());
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
