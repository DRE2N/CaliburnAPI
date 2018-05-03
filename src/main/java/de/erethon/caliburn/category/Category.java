/*
 * Copyright (C) 2015-2018 Daniel Saukel.
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
package de.erethon.caliburn.category;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.ExItem;
import de.erethon.caliburn.item.VanillaItem;
import static de.erethon.caliburn.item.VanillaItem.*;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class Category<T extends Categorizable> extends Categorizable {

    public static final Category ACACIA_LOGS = new Category("acacia_logs", /*ACACIA_BARK, */ ACACIA_LOG/*, STRIPPED_ACACIA_LOG*/);
    public static final Category ANVIL = new Category("anvil", VanillaItem.ANVIL, CHIPPED_ANVIL, DAMAGED_ANVIL);
    public static final Category BANNERS = new Category("banners", WHITE_BANNER, ORANGE_BANNER, MAGENTA_BANNER, LIGHT_BLUE_BANNER, YELLOW_BANNER, LIME_BANNER,
            PINK_BANNER, GRAY_BANNER, LIGHT_GRAY_BANNER, CYAN_BANNER, PURPLE_BANNER, BLUE_BANNER, BROWN_BANNER, GREEN_BANNER, RED_BANNER, BLACK_BANNER,
            WHITE_WALL_BANNER, ORANGE_WALL_BANNER, MAGENTA_WALL_BANNER, LIGHT_BLUE_WALL_BANNER, YELLOW_WALL_BANNER, LIME_WALL_BANNER, PINK_WALL_BANNER, GRAY_WALL_BANNER,
            LIGHT_GRAY_WALL_BANNER, CYAN_WALL_BANNER, PURPLE_WALL_BANNER, BLUE_WALL_BANNER, BROWN_WALL_BANNER, GREEN_WALL_BANNER, RED_WALL_BANNER, BLACK_WALL_BANNER);
    public static final Category BIRCH_LOGS = new Category("birch_logs", /*BIRCH_BARK, */ BIRCH_LOG/*, STRIPPED_BIRCH_LOG*/);
    public static final Category BOATS = new Category("boats", ACACIA_BOAT);
    public static final Category CARPETS = new Category("carpets", WHITE_CARPET, ORANGE_CARPET, MAGENTA_CARPET, LIGHT_BLUE_CARPET, YELLOW_CARPET, LIME_CARPET,
            PINK_CARPET, GRAY_CARPET, LIGHT_GRAY_CARPET, CYAN_CARPET, PURPLE_CARPET, BLUE_CARPET, BROWN_CARPET, GREEN_CARPET, RED_CARPET, BLACK_CARPET);
    public static final Category CORAL = new Category("coral"/*, DEAD_CORAL, BLUE_CORAL, PINK_CORAL, PURPLE_CORAL, RED_CORAL, YELLOW_CORAL*/);
    public static final Category CORAL_PLANTS = new Category("coral_plants"/*, DEAD_CORAL_PLANT, BLUE_CORAL_PLANT, PINK_CORAL_PLANT, PURPLE_CORAL_PLANT, RED_CORAL_PLANT, YELLOW_CORAL_PLANT*/);
    public static final Category DARK_OAK_LOGS = new Category("dark_oak_logs", /*DARK_OAK_BARK, */ DARK_OAK_LOG/*, STRIPPED_DARK_OAK_LOG*/);
    public static final Category ENDERMAN_HOLDABLE = new Category("enderman_holdable", GRASS_BLOCK, DIRT, COARSE_DIRT, PODZOL, VanillaItem.SAND, RED_SAND, GRAVEL, DANDELION,
            POPPY, BLUE_ORCHID, ALLIUM, AZURE_BLUET, RED_TULIP, ORANGE_TULIP, WHITE_TULIP, PINK_TULIP, OXEYE_DAISY, BROWN_MUSHROOM, RED_MUSHROOM, TNT, CACTUS,
            CLAY, PUMPKIN, /*CARVED_PUMPKIN, */ MELON_BLOCK, MYCELIUM, NETHERRACK);
    public static final Category FLOWER_POTS = new Category("flower_pots", FLOWER_POT/*, POTTED_POPPY, POTTED_BLUE_ORCHID, POTTED_ALLIUM, POTTED_AZURE_BLUET,
            POTTED_RED_TULIP, POTTED_ORANGE_TULIP, POTTED_WHITE_TULIP, POTTED_PINK_TULIP, POTTED_OXEYE_DAISY, POTTED_DANDELION, POTTED_OAK_SAPLING,
            POTTED_SPRUCE_SAPLING, POTTED_BIRCH_SAPLING, POTTED_JUNGLE_SAPLING, POTTED_ACACIA_SAPLING, POTTED_DARK_OAK_SAPLING, POTTED_RED_MUSHROOM,
            POTTED_BROWN_MUSHROOM, POTTED_DEAD_BUSH, POTTED_FERN, POTTED_CACTUS*/);
    public static final Category JUNGLE_LOGS = new Category("jungle_logs", /*JUNGLE_BARK, */ JUNGLE_LOG/*, STRIPPED_JUNGLE_LOG*/);
    public static final Category OAK_LOGS = new Category("oak_logs", /*OAK_BARK, */ OAK_LOG/*, STRIPPED_OAK_LOG*/);
    public static final Category PLANKS = new Category("planks", OAK_PLANKS, SPRUCE_PLANKS, BIRCH_PLANKS, JUNGLE_PLANKS, ACACIA_PLANKS, DARK_OAK_PLANKS);
    public static final Category RAILS = new Category("rails", RAIL, POWERED_RAIL, DETECTOR_RAIL, ACTIVATOR_RAIL);
    public static final Category SAND = new Category("sand", VanillaItem.SAND, RED_SAND);
    public static final Category SAPLINGS = new Category("saplings", OAK_SAPLING, SPRUCE_SAPLING, BIRCH_SAPLING, JUNGLE_SAPLING, ACACIA_SAPLING, DARK_OAK_SAPLING);
    public static final Category SLABS = new Category("slabs", STONE_SLAB, STONE_BRICK_SLAB, SANDSTONE_SLAB, ACACIA_SLAB, BIRCH_SLAB, DARK_OAK_SLAB, JUNGLE_SLAB,
            OAK_SLAB, SPRUCE_SLAB, PURPUR_SLAB, QUARTZ_SLAB, RED_SANDSTONE_SLAB, BRICK_SLAB, COBBLESTONE_SLAB, NETHER_BRICK_SLAB, PETRIFIED_OAK_SLAB/*,
            PRISMARINE_SLAB, PRISMARINE_BRICKS_SLAB, DARK_PRISMARINE_SLAB*/);
    public static final Category SPRUCE_LOGS = new Category("spruce_logs", /*SPRUCE_BARK, */ SPRUCE_LOG/*, STRIPPED_SPRUCE_LOG*/);
    public static final Category STAIRS = new Category("stairs", STONE_BRICK_STAIRS, SANDSTONE_STAIRS, ACACIA_STAIRS, BIRCH_STAIRS, DARK_OAK_STAIRS,
            JUNGLE_STAIRS, OAK_STAIRS, SPRUCE_STAIRS, PURPUR_STAIRS, QUARTZ_STAIRS, RED_SANDSTONE_STAIRS, BRICK_STAIRS, COBBLESTONE_STAIRS, NETHER_BRICK_STAIRS/*,
            PRISMARINE_STAIRS, PRISMARINE_BRICKS_STAIRS, DARK_PRISMARINE_STAIRS*/);
    public static final Category STONE_BRICKS = new Category("stone_bricks", VanillaItem.STONE_BRICKS, MOSSY_STONE_BRICKS, CRACKED_STONE_BRICKS, CHISELED_STONE_BRICKS);
    public static final Category WOODEN_BUTTONS = new Category("wooden_butons", OAK_BUTTON/*, SPRUCE_BUTTON, BIRCH_BUTTON, JUNGLE_BUTTON, ACACIA_BUTTON, DARK_OAK_BUTTON*/);
    public static final Category WOODEN_DOORS = new Category("wooden_doors", OAK_DOOR, SPRUCE_DOOR, BIRCH_DOOR, JUNGLE_DOOR, ACACIA_DOOR, DARK_OAK_DOOR);
    public static final Category WOODEN_PRESSURE_PLATES = new Category("wooden_pressure_plates", OAK_PRESSURE_PLATE/*, SPRUCE_PRESSURE_PLATE, BIRCH_PRESSURE_PLATE,
            JUNGLE_PRESSURE_PLATE, ACACIA_PRESSURE_PLATE, DARK_OAK_PRESSURE_PLATE*/);
    public static final Category WOODEN_SLABS = new Category("wooden_slabs", OAK_SLAB, SPRUCE_SLAB, BIRCH_SLAB, JUNGLE_SLAB, ACACIA_SLAB, DARK_OAK_SLAB);
    public static final Category WOODEN_STAIRS = new Category("wooden_stairs", OAK_STAIRS, SPRUCE_STAIRS, BIRCH_STAIRS, JUNGLE_STAIRS, ACACIA_STAIRS, DARK_OAK_STAIRS);
    public static final Category WOOL = new Category("wool", WHITE_WOOL, ORANGE_WOOL, MAGENTA_WOOL, LIGHT_BLUE_WOOL, YELLOW_WOOL, LIME_WOOL, PINK_WOOL,
            GRAY_WOOL, LIGHT_GRAY_WOOL, CYAN_WOOL, PURPLE_WOOL, BLUE_WOOL, BROWN_WOOL, GREEN_WOOL, RED_WOOL, BLACK_WOOL);

    // Parents
    public static final Category BUTTONS = new Category("buttons", WOODEN_BUTTONS, STONE_BUTTON);
    public static final Category LOGS = new Category("logs", OAK_LOGS, SPRUCE_LOGS, BIRCH_LOGS, JUNGLE_LOGS, ACACIA_LOGS, DARK_OAK_LOGS);
    public static final Category DOORS = new Category("doors", WOODEN_DOORS, IRON_DOOR);

    // Caliburn
    public static final Category BEDS = new Category("beds", WHITE_BED, ORANGE_BED, MAGENTA_BED, LIGHT_BLUE_BED, YELLOW_BED, LIME_BED, PINK_BED, GRAY_BED,
            LIGHT_GRAY_BED, CYAN_BED, PURPLE_BED, BLUE_BED, BROWN_BED, GREEN_BED, RED_BED, BLACK_BED);
    public static final Category SIGNS = new Category("signs", SIGN, WALL_SIGN, SIGN_POST);

    private String id;
    private List<T> elements = new ArrayList<>();

    public Category(String id) {
        this.id = id;
    }

    public Category(CaliburnAPI api, String id, List<String> elements) {
        this(id);
        elements.forEach(e -> this.elements.add((T) api.getExObject(e)));
    }

    private Category(String id, Categorizable... elements) {
        this.id = id;
        for (Categorizable element : elements) {
            if (element instanceof Category) {
                this.elements.addAll(((Category) element).elements);
            } else {
                this.elements.add((T) element);
            }
        }
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * @return the objects that belong to this category
     */
    public List<T> getElements() {
        return elements;
    }

    /**
     * @param item
     * an item
     * @return
     * if this category contains the item
     */
    public boolean contains(ExItem item) {
        if (item == null) {
            return false;
        }
        return elements.contains(item);
    }

    /**
     * @param material
     * a material
     * @return
     * if this category contains an item that wraps the material
     */
    public boolean containsMaterial(Material material) {
        if (material == null) {
            return false;
        }
        return contains(VanillaItem.get(material));
    }

    /**
     * @param block
     * a block
     * @return
     * if this category contains an item that wraps the material of this item
     */
    public boolean containsBlock(Block block) {
        if (block == null) {
            return false;
        }
        return containsMaterial(block.getType());
    }

    /**
     * @param item
     * an item stack
     * @return
     * if this category contains an item that wraps the material of this item
     */
    public boolean containsItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return containsMaterial(item.getType());
    }

}
