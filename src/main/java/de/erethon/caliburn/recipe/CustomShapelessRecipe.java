package de.erethon.caliburn.recipe;

import de.erethon.caliburn.recipe.ingredient.RecipeIngredient;
import de.erethon.caliburn.recipe.result.RecipeResult;
import de.erethon.caliburn.util.StringUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Fyreum
 */
public class CustomShapelessRecipe extends ShapelessRecipe implements CustomRecipe {

    private final RecipeResult result;
    private final int amount;
    private final Map<RecipeIngredient, Integer> ingredients;

    /**
     * @param key the key to register the recipe
     * @param result the result of the crafting event
     * @param ingredients the ingredients of the recipe
     */
    public CustomShapelessRecipe(NamespacedKey key, RecipeResult result, Map<RecipeIngredient, Integer> ingredients) {
        this(key, result, ingredients, "");
    }

    /**
     * @param key the key to register the recipe
     * @param result the result of the crafting event
     * @param ingredients the ingredients of the recipe
     * @param group the recipe group
     */
    public CustomShapelessRecipe(NamespacedKey key, RecipeResult result, Map<RecipeIngredient, Integer> ingredients, String group) {
        super(key, result.getItemStack());
        this.result = result;
        this.amount = result.getItemStack().getAmount();
        this.ingredients = ingredients;
        ingredients.forEach(this::addIngredients);
        this.setGroup(group);
    }

    /**
     * Adds the amount of ingredients
     *
     * @param ingredient the ingredient to add
     * @param count the amount to add
     */
    public void addIngredients(RecipeIngredient ingredient, int count) {
        while (count-- > 0) {
            this.addIngredient(ingredient);
        }
    }

    /**
     * Adds the ingredient
     *
     * @param ingredient the ingredient to add
     */
    public void addIngredient(RecipeIngredient ingredient) {
        this.addIngredient(ingredient.getRecipeChoice());
    }

    /**
     * Removes the ingredient
     *
     * @param ingredient the ingredient to remove
     */
    public void removeIngredient(RecipeIngredient ingredient) {
        this.removeIngredient(ingredient.getRecipeChoice());
    }

    /**
     * @return the crafting result
     */
    @Override
    public RecipeResult getRecipeResult() {
        return result;
    }

    /**
     * @return the recipe id
     */
    @Override
    public String getId() {
        return getKey().getKey();
    }

    /**
     * @return the recipe ingredients
     */
    public Map<RecipeIngredient, Integer> getIngredients() {
        return ingredients;
    }

    /**
     * @return the amount of the crafting result
     */
    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        if (getGroup().isEmpty()) {
            return "[result=" + result.getStringKey() + " x " + amount + "; " + toIngredientString() + "]";
        } else {
            return "[result=" + result.getStringKey() + " x " + amount + "; " + toIngredientString() + ";group=" + getGroup() + "]";
        }
    }

    @Override
    public String toIngredientString() {
        List<String> l = new ArrayList<>();
        ingredients.forEach((in, i) -> l.add(in.getSerialized() + ":" + i));
        return StringUtil.toString(l);
    }

    @Override
    public boolean equals(Object obj) {
        return getKey().equals(obj);
    }

}
