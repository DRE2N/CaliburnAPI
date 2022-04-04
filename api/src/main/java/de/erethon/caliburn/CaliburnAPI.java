/*
 * Copyright (C) 2015-2022 Daniel Saukel.
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
import de.erethon.caliburn.loottable.LootTable;
import de.erethon.caliburn.mob.CustomMob;
import de.erethon.caliburn.mob.ExMob;
import de.erethon.caliburn.mob.VanillaMob;
import de.erethon.caliburn.util.ExSerialization;
import de.erethon.caliburn.util.SimpleSerialization;
import de.erethon.bedrock.chat.MessageUtil;
import de.erethon.bedrock.compatibility.Version;
import de.erethon.bedrock.misc.FileUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

/**
 * The main class of the API. It contains methods for initialization and most important getter methods.
 *
 * @author Daniel Saukel
 */
public class CaliburnAPI {

    private static CaliburnAPI instance;

    private boolean isAtLeast1_14 = Version.isAtLeast(Version.MC1_14);

    public static final String META_ID_KEY = "caliburnID";

    private String identifierPrefix;
    private File dataFolder;

    private SimpleSerialization simpleSerialization = new SimpleSerialization(this);
    private ExSerialization exSerialization = new ExSerialization(this);

    private List<Category<ExItem>> itemCategories = new ArrayList<>();
    private List<ExItem> items = new ArrayList<>();
    private List<Category<ExMob>> mobCategories = new ArrayList<>();
    private List<ExMob> mobs = new ArrayList<>();
    private List<LootTable> lootTables = new ArrayList<>();

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
        FileConfiguration icConfig = YamlConfiguration.loadConfiguration(icFile);
        for (Object entry : icConfig.getValues(false).entrySet()) {
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
        FileConfiguration mcConfig = YamlConfiguration.loadConfiguration(mcFile);
        for (Object entry : mcConfig.getValues(false).entrySet()) {
            mobCategories.add(new Category<>(this, ((Map.Entry) entry).getKey().toString(), (List<String>) ((Map.Entry) entry).getValue()));
        }

        File custom = new File(getDataFolder() + "/custom/mobs");
        custom.mkdirs();
        for (File file : FileUtil.getFilesForFolder(custom)) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            CustomMob mob = null;
            try {
                mob = CustomMob.deserialize(config.getValues(false));
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
            YamlConfiguration config;
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                config = YamlConfiguration.loadConfiguration(file);
                config.createSection("categoryDamageModifiers");
                config.createSection("itemDamageModifiers");
                try {
                    config.save(file);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } else {
                config = YamlConfiguration.loadConfiguration(file);
            }
            mob.setRaw(config.getValues(false));
        }

        File ciFile = new File(getDataFolder() + "/custom/items");
        ciFile.mkdirs();
        for (File file : FileUtil.getFilesForFolder(ciFile)) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            CustomItem item = null;
            try {
                item = CustomItem.deserialize(config.getValues(false));
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
            FileConfiguration config;
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                config = YamlConfiguration.loadConfiguration(file);
                config.createSection("categoryDamageModifiers");
                config.createSection("mobDamageModifiers");
                try {
                    config.save(file);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } else {
                config = YamlConfiguration.loadConfiguration(file);
            }
            item.setRaw(config.getValues(false));
        }

        File ltDir = new File(getDataFolder() + "/custom/loottables");
        ltDir.mkdirs();
        FileUtil.getFilesForFolder(ltDir).forEach(f -> lootTables.add(
                LootTable.deserialize(YamlConfiguration.loadConfiguration(f).getValues(false)).name(f.getName().replace(".yml", "")))
        );
    }

    /**
     * Supposed to be called after all items, mobs and categories are loaded. Makes items and mobs load their damage modifiers.
     */
    public void finishInitialization() {
        items.forEach(i -> i.load(this));
        mobs.forEach(m -> m.load(this));
    }

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
            String id = id = getExItemId(item, idType);
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

}
