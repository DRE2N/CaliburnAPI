/*
 * Copyright (C) 2015-2021 Daniel Saukel.
 *
 * This library is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNULesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.erethon.caliburn;

import de.erethon.caliburn.category.Categorizable;
import de.erethon.caliburn.category.Category;
import de.erethon.caliburn.category.IdentifierType;
import de.erethon.caliburn.item.CustomItem;
import de.erethon.caliburn.item.ExItem;
import de.erethon.caliburn.item.VanillaItem;
import de.erethon.caliburn.listener.ItemListener;
import de.erethon.caliburn.listener.MobListener;
import de.erethon.caliburn.loottable.LootTable;
import de.erethon.caliburn.mob.CustomMob;
import de.erethon.caliburn.mob.ExMob;
import de.erethon.caliburn.mob.VanillaMob;
import de.erethon.caliburn.recipe.CustomRecipe;
import de.erethon.caliburn.util.ExSerialization;
import de.erethon.caliburn.util.RecipeSerialization;
import de.erethon.caliburn.util.SimpleSerialization;
import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.compatibility.Version;
import de.erethon.commons.config.RawConfiguration;
import de.erethon.commons.misc.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * The main class of the API. It contains methods for initialization and most important getter methods.
 *
 * @author Daniel Saukel, Fyreum
 */
public class CaliburnAPI {

    private static CaliburnAPI instance;

    private final boolean isAtLeast1_14 = Version.isAtLeast(Version.MC1_14);

    public static final String META_ID_KEY = "caliburnID";

    private final String identifierPrefix;
    private final File dataFolder;
    private File ciDir;
    private File rDir;
    private File rFile;
    private RawConfiguration recipeConfig;

    private final SimpleSerialization simpleSerialization = new SimpleSerialization(this);
    private final ExSerialization exSerialization = new ExSerialization(this);
    private final RecipeSerialization recipeSerialization = new RecipeSerialization(this);

    private final List<Category<ExItem>> itemCategories = new ArrayList<>();
    private final List<ExItem> items = new ArrayList<>();
    private final List<Category<ExMob>> mobCategories = new ArrayList<>();
    private final List<ExMob> mobs = new ArrayList<>();
    private final List<LootTable> lootTables = new ArrayList<>();
    private final List<CustomRecipe> recipes = new CopyOnWriteArrayList<>();

    /**
     * Initializes the singleton instance with the default identifier prefix.
     *
     * @param plugin the plugin
     */
    public CaliburnAPI(Plugin plugin) {
        this(plugin, ChatColor.GRAY.toString());
    }

    /**
     * Initializes the singleton instance.
     *
     * @param plugin           the plugin
     * @param identifierPrefix the prefix that is put before identifiers in some contexts, e.g. a color code before the first lore line.
     */
    public CaliburnAPI(Plugin plugin, String identifierPrefix) {
        instance = this;

        this.identifierPrefix = identifierPrefix;
        dataFolder = new File(plugin.getDataFolder().getParentFile(), "Caliburn");

        items.addAll(VanillaItem.getLoaded());
        mobs.addAll(VanillaMob.getLoaded());

        Bukkit.getPluginManager().registerEvents(new MobListener(this), plugin);
        ItemListener il = new ItemListener(this);
        Bukkit.getPluginManager().registerEvents(il, plugin);
        if (CompatibilityHandler.getInstance().isSpigot()) {
            Bukkit.getPluginManager().registerEvents(il.new Spigot(), plugin);
        }

        ConfigurationSerialization.registerClass(CustomItem.class);
        ConfigurationSerialization.registerClass(CustomMob.class);
        ConfigurationSerialization.registerClass(LootTable.class);
    }

    /**
     * Returns the loaded instance of CaliburnAPI.
     *
     * @return the loaded instance of CaliburnAPI
     */
    public static CaliburnAPI getInstance() {
        return instance;
    }

    /**
     * Reloads all content.
     */
    public void reload() {
        items.clear();
        items.addAll(VanillaItem.getLoaded());
        mobs.clear();
        mobs.addAll(VanillaMob.getLoaded());
        itemCategories.clear();
        mobCategories.clear();
        lootTables.clear();
        loadDataFiles();
        finishInitialization();
    }

    public void reload(Plugin plugin) {
        this.reload();
        plugin.getLogger().info("Reloading recipes...");
        removeRecipes();
        loadRecipes(plugin);
        addLoadedRecipes();
        plugin.getLogger().info("Successfully loaded " + recipes.size() + " recipes");
    }

    /**
     * Loads the data files.
     */
    public void loadDataFiles() {
        getDataFolder().mkdir();
        File icFile = new File(getDataFolder(), "ItemCategories.yml");
        if (!icFile.exists()) {
            try {
                icFile.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        RawConfiguration icConfig = RawConfiguration.loadConfiguration(icFile);
        for (Object entry : icConfig.getArgs().entrySet()) {
            itemCategories.add(new Category<>(this, ((Map.Entry) entry).getKey().toString(), (List<String>) ((Map.Entry) entry).getValue()));
        }

        File mcFile = new File(getDataFolder(), "MobCategories.yml");
        if (!mcFile.exists()) {
            try {
                mcFile.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        RawConfiguration mcConfig = RawConfiguration.loadConfiguration(mcFile);
        for (Object entry : mcConfig.getArgs().entrySet()) {
            mobCategories.add(new Category<>(this, ((Map.Entry) entry).getKey().toString(), (List<String>) ((Map.Entry) entry).getValue()));
        }

        File custom = new File(getDataFolder() + "/custom/mobs");
        custom.mkdirs();
        for (File file : FileUtil.getFilesForFolder(custom)) {
            RawConfiguration config = RawConfiguration.loadConfiguration(file);
            CustomMob mob = null;
            try {
                mob = CustomMob.deserialize(config.getArgs());
            } catch (Exception exception) {
                MessageUtil.log("[Caliburn] The custom mob file \"" + file.getName() + "\"is invalid:");
                exception.printStackTrace();
                continue;
            }
            String id = file.getName().substring(0, file.getName().length() - 4);
            mob.register(id);
        }
        File vmFile = new File(getDataFolder() + "/vanilla/mobs");
        vmFile.mkdirs();
        for (VanillaMob mob : VanillaMob.getLoaded()) {
            File file = new File(vmFile, mob.getId() + ".yml");
            RawConfiguration config;
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                config = RawConfiguration.loadConfiguration(file);
                config.createSection("categoryDamageModifiers");
                config.createSection("itemDamageModifiers");
                try {
                    config.save(file);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } else {
                config = RawConfiguration.loadConfiguration(file);
            }
            mob.setRaw(config.getArgs());
        }

        ciDir = new File(getDataFolder() + "/custom/items");
        ciDir.mkdirs();
        for (File file : FileUtil.getFilesForFolder(ciDir)) {
            RawConfiguration config = RawConfiguration.loadConfiguration(file);
            CustomItem item = null;
            try {
                item = CustomItem.deserialize(config.getArgs());
            } catch (Exception exception) {
                MessageUtil.log("[Caliburn] The custom item file \"" + file.getName() + "\"is invalid:");
                exception.printStackTrace();
                continue;
            }
            String id = file.getName().substring(0, file.getName().length() - 4);
            item.register(id);
        }
        File viFile = new File(getDataFolder() + "/vanilla/items");
        if (!viFile.exists()) {
            viFile.mkdirs();
        }
        for (VanillaItem item : VanillaItem.getLoaded()) {
            File file = new File(viFile, item.getId() + ".yml");
            RawConfiguration config;
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                config = RawConfiguration.loadConfiguration(file);
                config.createSection("categoryDamageModifiers");
                config.createSection("mobDamageModifiers");
                try {
                    config.save(file);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } else {
                config = RawConfiguration.loadConfiguration(file);
            }
            item.setRaw(config.getArgs());
        }

        File ltDir = new File(getDataFolder() + "/custom/loottables");
        ltDir.mkdirs();
        FileUtil.getFilesForFolder(ltDir).forEach(f -> lootTables.add(
                LootTable.deserialize(YamlConfiguration.loadConfiguration(f).getValues(false)).name(f.getName().replace(".yml", ""))
        ));
        rDir = new File(getDataFolder() + "/custom/recipes");
        rDir.mkdirs();
        rFile = new File(rDir, "recipes.yml");
        FileUtil.createIfNotExisting(rFile);
        recipeConfig = RawConfiguration.loadConfiguration(rFile);
    }

    /**
     * Deserializes all recipes from the recipes.yml file and adds them to the cache.
     *
     * @param plugin the plugin to create the {@link NamespacedKey}s with
     */
    public void loadRecipes(Plugin plugin) {
        List<CustomRecipe> recipes = recipeSerialization.deserializeRecipes(plugin, recipeConfig.getValues(false));
        this.recipes.addAll(recipes);
    }

    /**
     * Supposed to be called after all items, mobs and categories are loaded. Makes items and mobs load their damage modifiers.
     */
    public void finishInitialization() {
        items.forEach(i -> i.load(this));
        mobs.forEach(m -> m.load(this));
    }

    /**
     * Registers all loaded recipes on the server.
     */
    public void addLoadedRecipes() {
        for (CustomRecipe recipe : recipes) {
            addRecipe(recipe);
        }
    }

    /**
     * Registers the recipe on the server and adds it to the cache.
     *
     * @param recipe the recipe to add
     */
    public boolean addRecipe(CustomRecipe recipe) {
        removeRecipe(recipe);
        recipes.add(recipe);
        return Bukkit.addRecipe(recipe);
    }

    /**
     * Unregisters the recipe on the server removes it from the cache.
     *
     * @param recipe the recipe to remove
     */
    public void removeRecipe(CustomRecipe recipe) {
        recipes.removeIf(stored -> stored.getId().equalsIgnoreCase(recipe.getId()));

        Iterator<Recipe> iterator = Bukkit.recipeIterator();

        while (iterator.hasNext()) {
            Recipe next = iterator.next();
            if (next instanceof ShapedRecipe) {
                ShapedRecipe other = (ShapedRecipe) next;

                if (other.getKey().getKey().equalsIgnoreCase(recipe.getId())) {
                    iterator.remove();
                }
            } else if (next instanceof ShapelessRecipe) {
                ShapelessRecipe other = (ShapelessRecipe) next;

                if (other.getKey().getKey().equalsIgnoreCase(recipe.getId())) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Unregister all loaded recipes and clears the recipe cache
     */
    public void removeRecipes() {
        Iterator<Recipe> iterator = Bukkit.recipeIterator();

        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe instanceof ShapedRecipe) {
                ShapedRecipe shaped = (ShapedRecipe) recipe;

                for (CustomRecipe customRecipe : recipes) {
                    if (shaped.getKey().getKey().equalsIgnoreCase(customRecipe.getId())) {
                        iterator.remove();
                    }
                }
            } else if (recipe instanceof ShapelessRecipe) {
                ShapelessRecipe shapeless = (ShapelessRecipe) recipe;

                for (CustomRecipe customRecipe : recipes) {
                    if (shapeless.getKey().getKey().equalsIgnoreCase(customRecipe.getId())) {
                        iterator.remove();
                    }
                }
            }
        }
        recipes.clear();
    }

    /**
     * Deletes the recipe from the recipes configuration
     *
     * @param recipe the recipe to delete
     */
    public void deleteRecipe(CustomRecipe recipe) {
        String id = recipe.getId();

        for (String configKey : recipeConfig.getKeys(false)) {
            if (configKey.equalsIgnoreCase(id)) {
                recipeConfig.set(configKey, null);
                saveRecipeConfig();
            }
        }
    }

    /**
     * Save the recipe configuration into the recipes file
     */
    public void saveRecipeConfig() {
        try {
            recipeConfig.save(getRecipesFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getter

    /**
     * Returns the folder where the data is stored.
     *
     * @return the folder where the data is stored
     */
    public File getDataFolder() {
        return dataFolder;
    }

    /**
     * Returns the prefix of a custom item identifier lore line.
     *
     * @return the prefix of a custom item identifier lore line
     */
    public String getIdentifierPrefix() {
        return identifierPrefix;
    }

    /**
     * Returns the loaded instance of the SimpleSerialization format class.
     *
     * @return the loaded instance of SimpleSerialization
     */
    public SimpleSerialization getSimpleSerialization() {
        return simpleSerialization;
    }

    /**
     * Returns the loaded instance of the ExSerialization format class.
     *
     * @return the loaded instance of ExSerialization
     */
    public ExSerialization getExSerialization() {
        return exSerialization;
    }

    /**
     * Returns the {@link ExItem} or {@link ExMob} this the given ID represents.
     *
     * @param id the identifier String
     * @return the {@link ExItem} or {@link ExMob} this the given ID represents
     */
    public Categorizable getExObject(String id) {
        ExItem item = getExItem(id);
        if (item != null) {
            return getExItem(id);
        }
        ExMob mob = getExMob(id);
        if (mob != null) {
            return mob;
        }
        return null;
    }

    /* Items */
    /**
     * Returns all registered items.
     *
     * @return all registered items
     */
    public List<ExItem> getExItems() {
        return items;
    }

    /**
     * Returns all registered custom items.
     *
     * @return all registered custom items
     */
    public List<CustomItem> getCustomItems() {
        List<CustomItem> customItems = new ArrayList<>();
        for (ExItem item : items) {
            if (item instanceof CustomItem) {
                customItems.add((CustomItem) item);
            }
        }
        return customItems;
    }

    /**
     * Returns the ID of the {@link ExItem} that the given ItemStack is an instance of. If there is no {@link CustomItem} registered, the {@link VanillaItem} of
     * the ItemStack's material is used.
     *
     * @param item the ItemStack
     * @return the ID of the {@link ExItem} that the given Entity is an instance of. If there is no {@link CustomItem} registered, the {@link VanillaItem} of
     *         the material's type is used
     */
    public ExItem getExItem(ItemStack item) {
        for (IdentifierType idType : IdentifierType.ITEM_PRIORITY) {
            String id = getExItemId(item, idType);
            ExItem exItem = getExItem(id);
            if (exItem != null) {
                return exItem;
            }
        }
        return null;
    }

    /**
     * Returns the item that has the given ID.
     *
     * @param id a CustomItem or VanillaItem ID
     * @return the item that has the given ID
     */
    public ExItem getExItem(Object id) {
        if (id instanceof String) {
            // This only returns something if the ID exclusively refers to the item
            for (ExItem item : items) {
                ExItem idMatch = (ExItem) item.idMatch((String) id);
                if (idMatch != null) {
                    return idMatch;
                }
            }
            // This also allows ambiguous matches
            for (ExItem item : items) {
                ExItem idMatch = item.idMatch2nd((String) id);
                if (idMatch != null) {
                    return idMatch;
                }
            }

        } else if (id instanceof Integer) {
            for (VanillaItem item : VanillaItem.getLoaded()) {
                if (item.getNumericId() == (int) id) {
                    return item;
                }
            }
        }

        return null;
    }

    /**
     * Returns the ID of the {@link ExItem} that the given ItemStack is an instance of. If there is no {@link CustomItem} registered, the {@link VanillaItem} of
     * the stack's material is used.
     *
     * @param item   the ItemStack
     * @param idType the ID storage method
     * @return the ID of the {@link ExItem} that the given ItemStack is an instance of. If there is no {@link CustomItem} registered, the {@link VanillaItem} of
     *         the stack's material is used
     */
    public String getExItemId(ItemStack item, IdentifierType idType) {
        if (item == null) {
            return null;
        }
        switch (idType) {
            case DISPLAY_NAME:
                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    return item.getItemMeta().getDisplayName().replace(identifierPrefix, "");
                } else {
                    return null;
                }
            case LORE:
                if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
                    return item.getItemMeta().getLore().get(0).replace(identifierPrefix, "");
                } else {
                    return null;
                }
            case PERSISTENT_DATA_CONTAINER:
                if (isAtLeast1_14 && item.hasItemMeta()) {
                    return item.getItemMeta().getPersistentDataContainer().getOrDefault(new NamespacedKey("caliburn", "id"), PersistentDataType.STRING, null);
                } else {
                    return null;
                }
            case VANILLA:
                return item.getType().toString();
            default:
                return null;
        }
    }

    /**
     * Returns if the given collection contains any items that are subsumable under one of the given items.
     *
     * @param collection a Collection of items to check
     * @param items      the possible parent items
     * @return if the given collection contains any items that are subsumable under one of the given items
     */
    public boolean itemCollectionContainsSubsumables(Collection<ExItem> collection, ExItem... items) {
        for (ExItem entry : collection) {
            if (entry == null) {
                continue;
            }
            for (ExItem item : items) {
                if (item == null) {
                    continue;
                }
                if (entry.isSubsumableUnder(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns if the given collection contains any items that are subsumable under one of the given items.
     *
     * @param collection a Collection of item stacks to check
     * @param items      the possible parent items
     * @return if the given collection contains any items that are subsumable under one of the given items
     */
    public boolean stackCollectionContainsSubsumables(Collection<ItemStack> collection, ExItem... items) {
        for (ItemStack entry : collection) {
            if (entry == null) {
                continue;
            }
            for (ExItem item : items) {
                if (item == null) {
                    continue;
                }
                if (getExItem(entry).isSubsumableUnder(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Item categories */
    /**
     * Returns the registered ExItem categories.
     *
     * @return the registered ExItem categories
     */
    public List<Category<ExItem>> getItemCategories() {
        return itemCategories;
    }

    /**
     * Returns the ExItem Category that has the given ID.
     *
     * @param id the ID
     * @return the ExItem Category that has the given ID
     */
    public Category<ExItem> getItemCategory(String id) {
        for (Category<ExItem> itemCategory : itemCategories) {
            if (itemCategory.getId().equals(id)) {
                return itemCategory;
            }
        }
        return null;
    }

    /* Mobs */
    /**
     * Returns all registered mobs.
     *
     * @return all registered mobs
     */
    public List<ExMob> getExMobs() {
        return mobs;
    }

    /**
     * Returns all registered custom mobs
     *
     * @return all registered custom mobs
     */
    public List<CustomMob> getCustomMobs() {
        List<CustomMob> customMobs = new ArrayList<>();
        for (ExMob mob : mobs) {
            if (mob instanceof CustomMob) {
                customMobs.add((CustomMob) mob);
            }
        }
        return customMobs;
    }

    /**
     * Returns the mob that has the given ID.
     *
     * @param id a CustomMob or VanillaMob ID
     * @return the mob that has the given ID
     */
    public ExMob getExMob(Object id) {
        if (id instanceof String) {
            for (ExMob mob : mobs) {
                ExMob idMatch = (ExMob) mob.idMatch((String) id);
                if (idMatch != null) {
                    return idMatch;
                }
            }

        } else if (id instanceof Integer) {
            for (VanillaMob mob : VanillaMob.getLoaded()) {
                if (mob.getNumericId() == (int) id) {
                    return mob;
                }
            }
        }

        return null;
    }

    /**
     * Returns the ID of the {@link ExMob} that the given Entity is an instance of. If there is no {@link CustomMob} registered, the {@link VanillaMob} of
     * the entity's type is used.
     *
     * @param entity the Entity
     * @return the ID of the {@link ExMob} that the given Entity is an instance of. If there is no {@link CustomMob} registered, the {@link VanillaMob} of
     *         the entity's type is used
     */
    public ExMob getExMob(Entity entity) {
        for (IdentifierType idType : IdentifierType.MOB_PRIORITY) {
            String id = getExMobId(entity, idType);
            ExMob exMob = getExMob(id);
            if (exMob != null) {
                return exMob;
            }
        }
        return null;
    }

    /**
     * Returns the ID of the {@link ExMob} that the given Entity is an instance of.If there is no such {@link CustomMob} registered, the {@link VanillaMob} of
     * the entity's type is used.
     *
     * @param entity the Entity
     * @param idType the ID storage method
     * @return the ID of the {@link ExMob} that the given Entity is an instance of. If there is no such {@link CustomMob} registered, the {@link VanillaMob} of
     *         the entity's type is used
     */
    public String getExMobId(Entity entity, IdentifierType idType) {
        if (entity == null) {
            return null;
        }
        switch (idType) {
            case DISPLAY_NAME:
                return entity.getCustomName();
            case METADATA:
                List<MetadataValue> values = entity.getMetadata(META_ID_KEY);
                if (!values.isEmpty()) {
                    return values.get(0).asString();
                }
                return null;
            case PERSISTENT_DATA_CONTAINER:
                if (isAtLeast1_14) {
                    return entity.getPersistentDataContainer().getOrDefault(new NamespacedKey("caliburn", "id"), PersistentDataType.STRING, null);
                } else {
                    return null;
                }
            case VANILLA:
                return entity.getType().toString();
            default:
                return null;
        }
    }

    /**
     * Returns if the given collection contains any mobs that are subsumable under one of the given mobs.
     *
     * @param collection a Collection of mobs to check
     * @param mobs       the possible parent mobs
     * @return if the given collection contains any mobs that are subsumable under one of the given mobs
     */
    public boolean mobCollectionContainsSubsumables(Collection<ExMob> collection, ExMob... mobs) {
        for (ExMob entry : collection) {
            if (entry == null) {
                continue;
            }
            for (ExMob mob : mobs) {
                if (mob == null) {
                    continue;
                }
                if (entry.isSubsumableUnder(mob)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns if the given collection contains any mobs that are subsumable under one of the given mobs.
     *
     * @param collection a Collection of entities to check
     * @param mobs       the possible parent mobs
     * @return if the given collection contains any mobs that are subsumable under one of the given mobs
     */
    public boolean entityCollectionContainsSubsumables(Collection<Entity> collection, ExMob... mobs) {
        for (Entity entry : collection) {
            if (entry == null) {
                continue;
            }
            for (ExMob mob : mobs) {
                if (mob == null) {
                    continue;
                }
                if (getExMob(entry).isSubsumableUnder(mob)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Mob categories */
    /**
     * Returns the registered ExMob categories.
     *
     * @return the registered ExMob categories
     */
    public List<Category<ExMob>> getMobCategories() {
        return mobCategories;
    }

    /**
     * Returns the ExMob Category that has the given ID.
     *
     * @param id the ID
     * @return the ExMob Category that has the given ID
     */
    public Category<ExMob> getMobCategory(String id) {
        for (Category<ExMob> mobCategory : mobCategories) {
            if (mobCategory.getId().equals(id)) {
                return mobCategory;
            }
        }
        return null;
    }

    /* Loot tables */
    /**
     * Returns the registered loot tables.
     *
     * @return the registered loot tables
     */
    public List<LootTable> getLootTables() {
        return lootTables;
    }

    /**
     * Returns the loot table that has the given name.
     * <p>
     * LootTable names are not case-sensitive.
     *
     * @param name the name String
     * @return the loot table that has the given name
     */
    public LootTable getLootTable(String name) {
        for (LootTable lootTable : lootTables) {
            if (lootTable.getName().equalsIgnoreCase(name)) {
                return lootTable;
            }
        }

        return null;
    }

    /* Serialization */
    /**
     * Universal deserialization method to deserialize a Bukkit ItemStack.
     *
     * @param config a ConfigurationSection
     * @param path   the path in the config where the item to deserialize is found
     * @return the deserialized ItemStack
     */
    public ItemStack deserializeStack(ConfigurationSection config, String path) {
        return deserializeStack(config.get(path));
    }

    /**
     * Universal deserialization method to deserialize a Bukkit ItemStack.
     *
     * @param object ItemStack, ExItem or {@link de.erethon.caliburn.util.SimpleSerialization} String
     * @return the deserialized ItemStack
     */
    public ItemStack deserializeStack(Object object) {
        if (object instanceof ItemStack) {
            return (ItemStack) object;
        } else if (object instanceof String) {
            return simpleSerialization.deserialize((String) object);
        } else if (object instanceof ExItem) {
            return exSerialization.deserialize(((ExItem) object).getRaw());
        } else {
            return null;
        }
    }

    /**
     * Universal deserialization method to deserialize lists of Bukkit ItemStacks.
     *
     * @param config a ConfigurationSection
     * @param path   the path in the config where the item to deserialize is found
     * @return the deserialized list of ItemStacks
     */
    public List<ItemStack> deserializeStackList(ConfigurationSection config, String path) {
        List<ItemStack> deserialized = new ArrayList<>();
        List<?> list = config.getList(path);
        if (list == null) {
            return deserialized;
        }
        list.forEach(e -> {
            ItemStack itemStack = deserializeStack(e);
            if (itemStack != null) {
                deserialized.add(itemStack);
            }
        });
        return deserialized;
    }

    /**
     * Universal deserialization method to deserialize an ExItem.
     *
     * @param config a ConfigurationSection
     * @param path   the path in the config where the item to deserialize is found
     * @return the deserialized ExItem
     */
    public ExItem deserializeExItem(ConfigurationSection config, String path) {
        Object obj = config.get(path);
        if (obj instanceof String) {
            return getExItem((String) obj);
        } else if (config.get(path) instanceof ExItem) {
            return (ExItem) obj;
        }
        return null;
    }

    /**
     * Universal deserialization method to deserialize lists of ExItems.
     *
     * @param config a ConfigurationSection
     * @param path   the path in the config where the item to deserialize is found
     * @return the deserialized list of ItemStacks
     */
    public List<ExItem> deserializeExItemList(ConfigurationSection config, String path) {
        List<ExItem> deserialized = new ArrayList<>();
        List<?> list = config.getList(path);
        if (list == null) {
            return deserialized;
        }
        for (Object obj : list) {
            if (obj instanceof String || obj instanceof Integer) {
                ExItem exItem = getExItem(obj);
                if (exItem != null) {
                    deserialized.add(exItem);
                }
            } else if (obj instanceof ExItem) {
                deserialized.add((ExItem) obj);
            }
        }
        return deserialized;
    }

    /**
     * Returns the custom items folder where all item files are stored at.
     *
     * @return the custom items folder
     */
    public File getCustomItemsFile() {
        return ciDir;
    }

    /**
     * Returns the recipe folder where all recipe files are stored at.
     *
     * @return the recipe folder
     */
    public File getRecipeFolder() {
        return rDir;
    }

    /**
     * Returns the file where all recipes are stored at.
     *
     * @return the recipes file
     */
    public File getRecipesFile() {
        return rFile;
    }

    public RawConfiguration getRecipeConfig() {
        return recipeConfig;
    }

    public RecipeSerialization getRecipeSerialization() {
        return recipeSerialization;
    }

    /* recipe */

    /**
     * Returns a list of all loaded recipes.
     *
     * @return the list of loaded recipes
     */
    public List<CustomRecipe> getRecipes() {
        return recipes;
    }

    /**
     * Returns the matching recipe for this id, or null if not found.
     *
     * @param id the id
     * @return the matching recipe if found, or else null
     */
    public CustomRecipe getRecipe(String id) {
        for (CustomRecipe recipe : recipes) {
            if (recipe.getKey().getKey().equalsIgnoreCase(id)) {
                return recipe;
            }
        }
        return null;
    }

    /**
     * Returns a list of all recipes with the given ItemStack as result.
     *
     * @param result the result to get recipes for
     * @return the list of all recipes with matching item as result
     */
    public List<CustomRecipe> getRecipes(ItemStack result) {
        List<CustomRecipe> list = new ArrayList<>();
        for (CustomRecipe recipe : recipes) {
            if (recipe.getRecipeResult().getItemStack().isSimilar(result)) {
                list.add(recipe);
            }
        }
        return list;
    }

    /**
     * Returns the serialized recipe as string.
     *
     * @param recipe the recipe to serialize
     * @return the serialized recipe string
     */
    public String serializeRecipe(CustomRecipe recipe) {
        return recipeSerialization.serialize(recipe);
    }

    /**
     * Returns the list of serialized recipes as strings.
     *
     * @param recipes the recipes to serialize
     * @return the list of serialized recipe strings
     */
    public List<String> serializeRecipes(Collection<CustomRecipe> recipes) {
        return recipeSerialization.serializeRecipes(recipes);
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
    public CustomRecipe deserializeRecipe(NamespacedKey recipeKey, String serialized) throws IllegalArgumentException {
        return recipeSerialization.deserialize(recipeKey, serialized);
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
        return recipeSerialization.deserializeRecipes(plugin, serializedRecipes);
    }

}
