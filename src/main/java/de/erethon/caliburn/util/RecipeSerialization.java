package de.erethon.caliburn.util;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.ExItem;
import de.erethon.caliburn.recipe.CustomRecipe;
import de.erethon.caliburn.recipe.CustomShapedRecipe;
import de.erethon.caliburn.recipe.CustomShapelessRecipe;
import de.erethon.caliburn.recipe.ingredient.ItemIngredient;
import de.erethon.caliburn.recipe.ingredient.MaterialIngredient;
import de.erethon.caliburn.recipe.ingredient.RecipeIngredient;
import de.erethon.caliburn.recipe.result.RecipeResult;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Fyreum
 */
public class RecipeSerialization {

    private final CaliburnAPI api;

    public RecipeSerialization(final CaliburnAPI api) {
        this.api = api;
    }

    /*
    CONCEPT of Serialization

    VANILLA:

     - shaped
     id: {type=shaped;result=diamond_pickaxe;amount=1;shape=ddd, s , s ;ingredients=d:diamond,s:stick;group=tool;}
     - shapeless
     id: {type=shapeless;result=diamond_pickaxe;amount=1;ingredients=diamond:3,stick:2;group=tool;}

    CUSTOM:

     - shaped
     id: {type=shaped;result=rainbow_pickaxe;amount=1;shape=rrr, c , c ;ingredients=c:rainbow_ore,c:candy_cane;group=rainbow;}
     - shapeless
     id: {type=shapeless;result=rainbow_pickaxe;amount=1;ingredients=rainbow_ore:3,candy_cane:2;group=rainbow;}
     */
    /**
     * Returns the serialized recipe as string.
     *
     * @param recipe the recipe to serialize
     * @return the serialized recipe string
     */
    public String serialize(CustomRecipe recipe) {
        StringBuilder sb = new StringBuilder();

        if (recipe instanceof CustomShapedRecipe) {
            CustomShapedRecipe shapedRecipe = (CustomShapedRecipe) recipe;

            sb.append("type=shaped").append(";");
            sb.append("result=").append(shapedRecipe.getRecipeResult().getStringKey()).append(";");
            sb.append("amount=").append(shapedRecipe.getAmount()).append(";");
            sb.append("shape=").append(RecipeUtil.toShapeString(shapedRecipe.getShape())).append(";");
            sb.append("ingredients=").append(toString(shapedRecipe.getIngredients())).append(";");
            if (!shapedRecipe.getGroup().isEmpty()) {
                sb.append("group=").append(shapedRecipe.getGroup()).append(";");
            }
        } else {
            CustomShapelessRecipe shapelessRecipe = (CustomShapelessRecipe) recipe;

            sb.append("type=shapeless").append(";");
            sb.append("result=").append(shapelessRecipe.getRecipeResult().getStringKey()).append(";");
            sb.append("amount=").append(shapelessRecipe.getAmount()).append(";");
            sb.append("ingredients=").append(toIngredientString(shapelessRecipe.getIngredients())).append(";");
            if (!shapelessRecipe.getGroup().isEmpty()) {
                sb.append("group=").append(shapelessRecipe.getGroup()).append(";");
            }
        }
        return "{" + sb.toString() + "}";
    }

    /**
     * Returns the list of serialized recipes as strings.
     *
     * @param recipes the recipes to serialize
     * @return the list of serialized recipe strings
     */
    public List<String> serializeRecipes(Collection<CustomRecipe> recipes) {
        List<String> serialized = new ArrayList<>();
        for (CustomRecipe recipe : recipes) {
            serialized.add(serialize(recipe));
        }
        return serialized;
    }

    /**
     * Returns the deserialized {@link CustomRecipe} if success,
     * <br>
     * else an Exception with detailed error message will be thrown.
     *
     * @param recipeKey the key to register the recipe with
     * @param serialized the serialized recipe string
     * @throws IllegalArgumentException if the string doesn't have the right format or lacks of information
     */
    public CustomRecipe deserialize(NamespacedKey recipeKey, String serialized) throws IllegalArgumentException {
        if (serialized.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find any value");
        }
        try {
            char[] allChars = Validate.braceFormat(serialized.toCharArray());

            SecuredStringBuilder typeBuilder = new SecuredStringBuilder(Key.TYPE.getStringKey());
            SecuredStringBuilder resultBuilder = new SecuredStringBuilder(Key.RESULT.getStringKey());
            SecuredStringBuilder amountBuilder = new SecuredStringBuilder(Key.AMOUNT.getStringKey());
            SecuredStringBuilder shapeBuilder = new SecuredStringBuilder(Key.SHAPE.getStringKey());
            SecuredStringBuilder ingredientsBuilder = new SecuredStringBuilder(Key.INGREDIENTS.getStringKey());
            SecuredStringBuilder groupBuilder = new SecuredStringBuilder(Key.GROUP.getStringKey());

            StringBuilder keyBuilder = new StringBuilder();

            Key key = null;

            for (int i = 1; i < allChars.length - 1; i++) {
                char current = allChars[i];

                if (key == null) {
                    if (Util.handleChar(Validate.noSpace(current, i), '=', i, keyBuilder)) {
                        String keyName = keyBuilder.toString();
                        keyBuilder = new StringBuilder();

                        key = Validate.notNull(Key.getByKey(keyName), "Couldn't identify key '" + keyName + "'");
                    }
                } else {
                    switch (key) {
                        case TYPE:
                            if (Util.handleChar(current, i, typeBuilder)) {
                                key = null;
                            }
                            break;
                        case RESULT:
                            if (Util.handleChar(current, i, resultBuilder)) {
                                key = null;
                            }
                            break;
                        case AMOUNT:
                            if (Util.handleChar(current, i, amountBuilder)) {
                                key = null;
                            }
                            break;
                        case SHAPE:
                            if (Util.handleChar(current, i, shapeBuilder, ':')) {
                                key = null;
                            }
                            break;
                        case INGREDIENTS:
                            if (Util.handleChar(current, i, ingredientsBuilder, ':')) {
                                key = null;
                            }
                            break;
                        case GROUP:
                            if (Util.handleChar(current, i, groupBuilder)) {
                                key = null;
                            }
                            break;
                    }
                }
            }
            boolean shaped = isShaped(Validate.builderValue(typeBuilder, "Missing statement 'type=TYPE'"));

            int amount = 1;

            if (!amountBuilder.isEmpty()) {
                amount = Util.parseInt(amountBuilder.toString(), 0, 1, "Cannot set an lower amount than '1'");
            }

            String group = groupBuilder.isEmpty() ? "" : groupBuilder.toString();

            String ig = Validate.builderValue(ingredientsBuilder);

            if (shaped) {
                String shapeString = Validate.builderValue(shapeBuilder, "You need to set an shape for shaped recipes");
                Validate.check(shapeString.length() == 11, "Wrong shape format, found '" + shapeString + "' but require: '___:___:___'");

                String[] shape = Validate.length(shapeString.split(":"), 3, "Wrong shape format, found '" + shapeString + "' but require: '___:___:___'");

                // CHAR1:INGREDIENT1,CHAR2:INGREDIENT2;
                Map<Character, RecipeIngredient> ingredients = new HashMap<>();

                String[] split = ig.split(",");
                for (String igString : split) {
                    // CHAR:INGREDIENT
                    String[] s = Validate.length(igString.split(":"), 2, "Wrong Ingredient format, found '" + igString + "' but require: 'CHAR:INGREDIENT'");

                    char c = Validate.length(s[0], 1, "").charAt(0);

                    ingredients.put(c, buildIngredient(s[1]));
                }

                return new CustomShapedRecipe(recipeKey, buildResult(resultBuilder.toString(), amount), shape, ingredients, group);
            } else {
                Validate.check(shapeBuilder.isEmpty(), "You cannot set an shape for shapeless recipes (Make sure that type=TYPE is the first statement)");

                // INGREDIENT1:AMOUNT2,INGREDIENT1:AMOUNT2;
                Map<RecipeIngredient, Integer> ingredients = new HashMap<>();

                String[] split = ig.split(",");
                for (String igString : split) {
                    // INGREDIENT:AMOUNT
                    String[] s = Validate.length(igString.split(":"), 2, "Wrong Ingredient format, found '" + igString + "' but require: 'INGREDIENT:AMOUNT'");

                    RecipeIngredient ingredient = buildIngredient(s[0]);
                    int a = Util.parseInt(s[1], 0, 1, "Ingredient amount has to be between 1 and 9");

                    ingredients.put(ingredient, a);
                }

                return new CustomShapelessRecipe(recipeKey, buildResult(resultBuilder.toString(), amount), ingredients, group);
            }
        } catch (IllegalArgumentException | ValidationException e) {
            throw new IllegalArgumentException("An error happened during deserialization of '" + serialized + "' | " + e.getMessage());
        }
    }

    /**
     * Returns a list of deserialized {@link CustomRecipe}s.
     * <br>
     * Thrown Exceptions will be caught and logged via the {@link Logger} class.
     *
     * @param plugin the plugin to create the {@link NamespacedKey}s with
     * @param serializedRecipes the serialized recipes map
     */
    public List<CustomRecipe> deserializeRecipes(Plugin plugin, Map<String, Object> serializedRecipes) {
        List<CustomRecipe> recipes = new ArrayList<>();
        serializedRecipes.forEach((key, value) -> {
            try {
                NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
                if (!(value instanceof String)) {
                    throw new IllegalArgumentException("The object stored at '" + key + "' is no string");
                }
                CustomRecipe recipe = deserialize(namespacedKey, (String) value);
                recipes.add(recipe);
            } catch (IllegalArgumentException e) {
                plugin.getLogger().info(ChatColor.RED + e.getMessage());
            }
        });
        return recipes;
    }

    private RecipeResult buildResult(String s, int a) {
        ExItem ex = api.getExItem(s);
        if (ex != null) {
            return new RecipeResult(ex, a);
        } else {
            Material m = Validate.notNull(Material.getMaterial(s.toUpperCase()), "Couldn't find matching item for '" + s + "'");
            return new RecipeResult(m, a);
        }
    }

    private RecipeIngredient buildIngredient(String s) {
        ExItem ex = api.getExItem(s);
        if (ex != null) {
            return new ItemIngredient(ex);
        } else {
            Material m = Validate.notNull(Material.getMaterial(s.toUpperCase()), "Couldn't identify ingredient '" + s + "'");
            return new MaterialIngredient(m);
        }
    }

    private boolean isShaped(String s) {
        if (s.equalsIgnoreCase("shaped")) {
            return true;
        } else if (s.equalsIgnoreCase("shapeless")) {
            return false;
        } else {
            throw new IllegalArgumentException("Cannot identify recipe type '" + s + "'");
        }
    }

    private String toString(Map<Character, RecipeIngredient> ingredients) {
        Map<String, String> m = new HashMap<>();
        ingredients.forEach((c, i) -> m.put(String.valueOf(c), i.getSerialized()));
        return StringUtil.toString(m);
    }

    public static String toIngredientString(Map<RecipeIngredient, Integer> ingredients) {
        List<String> l = new ArrayList<>();
        ingredients.forEach((in, i) -> l.add(in.getSerialized() + ":" + i));
        return StringUtil.toString(l);
    }

    public enum Key {

        TYPE,
        RESULT,
        AMOUNT,
        SHAPE,
        INGREDIENTS,
        GROUP;

        public String getStringKey() {
            return name().toLowerCase();
        }

        public static Key getByKey(String key) {
            for (Key value : values()) {
                if (value.name().equalsIgnoreCase(key)) {
                    return value;
                }
            }
            return null;
        }
    }
}
