package de.erethon.caliburn.recipe.result;

import de.erethon.caliburn.item.ExItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RecipeResult {

    private final ItemStack item;
    private final String key;

    public RecipeResult(ExItem ex, int amount) {
        this.item = ex.toItemStack(amount);
        this.key = ex.getId();
    }

    public RecipeResult(ItemStack item, String key) {
        this.item = item;
        this.key = key;
    }

    public RecipeResult(Material m, int amount) {
        this.item = new ItemStack(m, amount);
        this.key = m.name();
    }

    public ItemStack getItemStack() {
        return item;
    }

    public String getStringKey() {
        return key;
    }

}
