package de.erethon.caliburn.recipe;

import de.erethon.caliburn.recipe.ingredient.RecipeIngredient;
import de.erethon.caliburn.recipe.result.RecipeResult;
import de.erethon.caliburn.util.RecipeUtil;
import de.erethon.caliburn.util.StringUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Fyreum
 */
public class CustomShapedRecipe extends ShapedRecipe implements CustomRecipe {

    private final RecipeResult result;
    private final int amount;
    private final Map<Character, RecipeIngredient> ingredients;

    public CustomShapedRecipe(NamespacedKey key, RecipeResult result, String[] shape, Map<Character, RecipeIngredient> ingredients) {
        this(key, result, shape, ingredients, "");
    }

    public CustomShapedRecipe(NamespacedKey key, RecipeResult result, String[] shape, Map<Character, RecipeIngredient> ingredients, String group) {
        super(key, result.getItemStack());
        this.result = result;
        this.amount = result.getItemStack().getAmount();
        this.shape(shape);
        this.ingredients = ingredients;
        ingredients.forEach(this::setIngredient);
        this.setGroup(group);
    }

    /**
     * Sets the ingredient
     *
     * @param key the key to set
     * @param ingredient the ingredient to set
     */
    public void setIngredient(char key, RecipeIngredient ingredient) {
        this.setIngredient(key, ingredient.getRecipeChoice());
    }

    /**
     * @return the crafting result
     */
    @Override
    public RecipeResult getRecipeResult() {
        return result;
    }

    /**
     * @return the recipe ingredients
     */
    public Map<Character, RecipeIngredient> getIngredients() {
        return ingredients;
    }

    /**
     * @return the recipe id
     */
    @Override
    public String getId() {
        return getKey().getKey();
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
            return "[result=" + result.getStringKey() + " x " + amount + ";shape=" + RecipeUtil.toShapeString(getShape()) + ";" + "ingredients=" + toIngredientString() + "]";
        } else {
            return "[result=" + result.getStringKey() + " x " + amount + ";shape=" + RecipeUtil.toShapeString(getShape()) + ";" + "ingredients=" + toIngredientString() + ";group=" + getGroup() + "]";
        }
    }

    @Override
    public String toIngredientString() {
        Map<String, String> m = new HashMap<>();
        ingredients.forEach((c, i) -> m.put(String.valueOf(c), i.getSerialized()));
        return StringUtil.toString(m);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CustomShapedRecipe)) {
            return false;
        }
        CustomShapedRecipe other = (CustomShapedRecipe) obj;
        return getKey().equals(other.getKey());
    }
}
