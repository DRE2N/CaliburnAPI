/*
 * Copyright (C) 2015-2019 Daniel Saukel.
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
import de.erethon.caliburn.mob.ExMob;
import de.erethon.caliburn.mob.VanillaMob;
import static de.erethon.caliburn.mob.VanillaMob.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class Category<T extends Categorizable> extends Categorizable {

    public static final Category<ExItem> ACACIA_LOGS = new Category<>("acacia_logs", ACACIA_WOOD, ACACIA_LOG, STRIPPED_ACACIA_LOG);
    public static final Category<ExItem> ANVIL = new Category<>("anvil", VanillaItem.ANVIL, CHIPPED_ANVIL, DAMAGED_ANVIL);
    public static final Category<ExItem> ARROWS = new Category<>("arrows", VanillaItem.ARROW, VanillaItem.TIPPED_ARROW, VanillaItem.SPECTRAL_ARROW);
    public static final Category<ExItem> BANNERS = new Category<>("banners", WHITE_BANNER, ORANGE_BANNER, MAGENTA_BANNER, LIGHT_BLUE_BANNER, YELLOW_BANNER, LIME_BANNER,
            PINK_BANNER, GRAY_BANNER, LIGHT_GRAY_BANNER, CYAN_BANNER, PURPLE_BANNER, BLUE_BANNER, BROWN_BANNER, GREEN_BANNER, RED_BANNER, BLACK_BANNER,
            WHITE_WALL_BANNER, ORANGE_WALL_BANNER, MAGENTA_WALL_BANNER, LIGHT_BLUE_WALL_BANNER, YELLOW_WALL_BANNER, LIME_WALL_BANNER, PINK_WALL_BANNER, GRAY_WALL_BANNER,
            LIGHT_GRAY_WALL_BANNER, CYAN_WALL_BANNER, PURPLE_WALL_BANNER, BLUE_WALL_BANNER, BROWN_WALL_BANNER, GREEN_WALL_BANNER, RED_WALL_BANNER, BLACK_WALL_BANNER);
    public static final Category<ExItem> BEDS = new Category<>("beds", WHITE_BED, ORANGE_BED, MAGENTA_BED, LIGHT_BLUE_BED, YELLOW_BED, LIME_BED, PINK_BED, GRAY_BED,
            LIGHT_GRAY_BED, CYAN_BED, PURPLE_BED, BLUE_BED, BROWN_BED, GREEN_BED, RED_BED, BLACK_BED);
    public static final Category<ExItem> BEEHIVES = new Category<>("beehives", VanillaItem.BEEHIVE, BEE_NEST);
    public static final Category<ExItem> BIRCH_LOGS = new Category<>("birch_logs", BIRCH_WOOD, BIRCH_LOG, STRIPPED_BIRCH_LOG);
    public static final Category<ExItem> BOATS = new Category<>("boats", OAK_BOAT, SPRUCE_BOAT, BIRCH_BOAT, JUNGLE_BOAT, ACACIA_BOAT, DARK_OAK_BOAT);
    public static final Category<ExItem> CARPETS = new Category<>("carpets", WHITE_CARPET, ORANGE_CARPET, MAGENTA_CARPET, LIGHT_BLUE_CARPET, YELLOW_CARPET, LIME_CARPET,
            PINK_CARPET, GRAY_CARPET, LIGHT_GRAY_CARPET, CYAN_CARPET, PURPLE_CARPET, BLUE_CARPET, BROWN_CARPET, GREEN_CARPET, RED_CARPET, BLACK_CARPET);
    public static final Category<ExItem> COALS = new Category<>("coals", COAL, CHARCOAL);
    public static final Category<ExItem> CORAL_PLANTS = new Category<>("coral_plants", TUBE_CORAL, BRAIN_CORAL, BUBBLE_CORAL, FIRE_CORAL, HORN_CORAL);
    public static final Category<ExItem> CORAL_BLOCKS = new Category<>("coral_blocks", TUBE_CORAL_BLOCK, BRAIN_CORAL_BLOCK, BUBBLE_CORAL_BLOCK, FIRE_CORAL_BLOCK, HORN_CORAL_BLOCK);
    public static final Category<ExItem> CROPS = new Category<>("crops", BEETROOTS, CARROTS, POTATOES, WHEAT, MELON, PUMPKIN);
    public static final Category<ExItem> DARK_OAK_LOGS = new Category<>("dark_oak_logs", DARK_OAK_WOOD, DARK_OAK_LOG, STRIPPED_DARK_OAK_LOG);
    public static final Category<ExItem> DEAD_CORAL_BLOCKS = new Category<>("dead_coral_blocks", DEAD_TUBE_CORAL_BLOCK, DEAD_BRAIN_CORAL_BLOCK, DEAD_BUBBLE_CORAL_BLOCK,
            DEAD_FIRE_CORAL_BLOCK, DEAD_HORN_CORAL_BLOCK);
    public static final Category<ExItem> DRAGON_IMMUNE = new Category<>("dragon_immune", BARRIER, BEDROCK, END_PORTAL, END_PORTAL_FRAME, END_GATEWAY, COMMAND_BLOCK,
            REPEATING_COMMAND_BLOCK, CHAIN_COMMAND_BLOCK, STRUCTURE_BLOCK, JIGSAW, MOVING_PISTON, OBSIDIAN, END_STONE, IRON_BARS);
    public static final Category<ExItem> FISHES = new Category<>("fishes", VanillaItem.COD, VanillaItem.COOKED_COD, VanillaItem.SALMON, VanillaItem.COOKED_SALMON,
            VanillaItem.PUFFERFISH, VanillaItem.TROPICAL_FISH);
    public static final Category<ExItem> FLOWER_POTS = new Category<>("flower_pots", FLOWER_POT, POTTED_POPPY, POTTED_DANDELION, POTTED_OAK_SAPLING, POTTED_SPRUCE_SAPLING,
            POTTED_BIRCH_SAPLING, POTTED_JUNGLE_SAPLING, POTTED_RED_MUSHROOM, POTTED_BROWN_MUSHROOM, POTTED_CACTUS, POTTED_DEAD_BUSH, POTTED_FERN, POTTED_ACACIA_SAPLING,
            POTTED_DARK_OAK_SAPLING, POTTED_BLUE_ORCHID, POTTED_ALLIUM, POTTED_AZURE_BLUET, POTTED_RED_TULIP, POTTED_ORANGE_TULIP, POTTED_WHITE_TULIP, POTTED_PINK_TULIP,
            POTTED_OXEYE_DAISY, POTTED_CORNFLOWER, POTTED_LILY_OF_THE_VALLEY, POTTED_WITHER_ROSE, POTTED_BAMBOO);
    public static final Category<ExItem> ICE = new Category<>("ice", VanillaItem.ICE, FROSTED_ICE, PACKED_ICE, BLUE_ICE);
    public static final Category<ExItem> IMPERMEABLE = new Category<>("impermeable", GLASS, WHITE_STAINED_GLASS, ORANGE_STAINED_GLASS, MAGENTA_STAINED_GLASS, LIGHT_BLUE_STAINED_GLASS,
            YELLOW_STAINED_GLASS, LIME_STAINED_GLASS, PINK_STAINED_GLASS, GRAY_STAINED_GLASS, LIGHT_GRAY_STAINED_GLASS, CYAN_STAINED_GLASS, PURPLE_STAINED_GLASS,
            BLUE_STAINED_GLASS, BROWN_STAINED_GLASS, GREEN_STAINED_GLASS, RED_STAINED_GLASS, BLACK_STAINED_GLASS);
    public static final Category<ExItem> JUNGLE_LOGS = new Category<>("jungle_logs", JUNGLE_WOOD, JUNGLE_LOG, STRIPPED_JUNGLE_LOG);
    public static final Category<ExItem> LAVA = new Category<>("lava", VanillaItem.LAVA, FLOWING_LAVA);
    public static final Category<ExItem> LEAVES = new Category<>("leaves", JUNGLE_LEAVES, OAK_LEAVES, SPRUCE_LEAVES, DARK_OAK_LEAVES, ACACIA_LEAVES, BIRCH_LEAVES);
    public static final Category<ExItem> LECTERN_BOOKS = new Category<>("lectern_books", WRITTEN_BOOK, WRITABLE_BOOK);
    public static final Category<ExItem> LIVE_CORAL_BLOCKS = new Category<>("live_coral_blocks", TUBE_CORAL_BLOCK, BRAIN_CORAL_BLOCK, BUBBLE_CORAL_BLOCK, FIRE_CORAL_BLOCK,
            HORN_CORAL_BLOCK);
    public static final Category<ExItem> MUSIC_DISCS = new Category<>("music_discs", MUSIC_DISC_13, MUSIC_DISC_CAT, MUSIC_DISC_BLOCKS, MUSIC_DISC_CHIRP, MUSIC_DISC_FAR,
            MUSIC_DISC_MALL, MUSIC_DISC_MELLOHI, MUSIC_DISC_STAL, MUSIC_DISC_STRAD, MUSIC_DISC_WARD, MUSIC_DISC_11, MUSIC_DISC_WAIT);
    public static final Category<ExItem> OAK_LOGS = new Category<>("oak_logs", OAK_WOOD, OAK_LOG, STRIPPED_OAK_LOG);
    public static final Category<ExItem> PORTALS = new Category<>("portals", NETHER_PORTAL, END_PORTAL, END_GATEWAY);
    public static final Category<ExItem> RAILS = new Category<>("rails", RAIL, POWERED_RAIL, DETECTOR_RAIL, ACTIVATOR_RAIL);
    public static final Category<ExItem> TALL_FLOWERS = new Category<>("tall_flowers", SUNFLOWER, LILAC, PEONY, ROSE_BUSH);
    public static final Category<ExItem> SAND = new Category<>("sand", VanillaItem.SAND, RED_SAND);
    public static final Category<ExItem> SAPLINGS = new Category<>("saplings", OAK_SAPLING, SPRUCE_SAPLING, BIRCH_SAPLING, JUNGLE_SAPLING, ACACIA_SAPLING, DARK_OAK_SAPLING);
    public static final Category<ExItem> SHULKER_BOXES = new Category<>("shulker_boxes", SHULKER_BOX, WHITE_SHULKER_BOX, ORANGE_SHULKER_BOX, MAGENTA_SHULKER_BOX,
            LIGHT_BLUE_SHULKER_BOX, YELLOW_SHULKER_BOX, LIME_SHULKER_BOX, PINK_SHULKER_BOX, GRAY_SHULKER_BOX, LIGHT_GRAY_SHULKER_BOX, CYAN_SHULKER_BOX,
            PURPLE_SHULKER_BOX, BLUE_SHULKER_BOX, BROWN_SHULKER_BOX, GREEN_SHULKER_BOX, RED_SHULKER_BOX, BLACK_SHULKER_BOX);
    public static final Category<ExItem> SLABS = new Category<>("slabs", STONE_SLAB, SMOOTH_STONE_SLAB, STONE_BRICK_SLAB, SANDSTONE_SLAB, ACACIA_SLAB, BIRCH_SLAB,
            DARK_OAK_SLAB, JUNGLE_SLAB, OAK_SLAB, SPRUCE_SLAB, PURPUR_SLAB, QUARTZ_SLAB, RED_SANDSTONE_SLAB, BRICK_SLAB, COBBLESTONE_SLAB, NETHER_BRICK_SLAB,
            PETRIFIED_OAK_SLAB, PRISMARINE_SLAB, PRISMARINE_BRICK_SLAB, DARK_PRISMARINE_SLAB, POLISHED_GRANITE_SLAB, SMOOTH_RED_SANDSTONE_SLAB,
            MOSSY_STONE_BRICK_SLAB, POLISHED_DIORITE_SLAB, MOSSY_COBBLESTONE_SLAB, END_STONE_BRICK_SLAB, SMOOTH_SANDSTONE_SLAB, SMOOTH_QUARTZ_SLAB, GRANITE_SLAB,
            ANDESITE_SLAB, RED_NETHER_BRICK_SLAB, POLISHED_ANDESITE_SLAB, DIORITE_SLAB);
    public static final Category<ExItem> SMALL_FLOWERS = new Category<>("small_flowers", DANDELION, POPPY, BLUE_ORCHID, ALLIUM, AZURE_BLUET, RED_TULIP, ORANGE_TULIP,
            WHITE_TULIP, PINK_TULIP, OXEYE_DAISY, CORNFLOWER, LILY_OF_THE_VALLEY, WITHER_ROSE);
    public static final Category<ExItem> SPRUCE_LOGS = new Category<>("spruce_logs", SPRUCE_WOOD, SPRUCE_LOG, STRIPPED_SPRUCE_LOG);
    public static final Category<ExItem> STAIRS = new Category<>("stairs", OAK_STAIRS, COBBLESTONE_STAIRS, SPRUCE_STAIRS, SANDSTONE_STAIRS, ACACIA_STAIRS, JUNGLE_STAIRS,
            BIRCH_STAIRS, DARK_OAK_STAIRS, NETHER_BRICK_STAIRS, STONE_BRICK_STAIRS, BRICK_STAIRS, PURPUR_STAIRS, QUARTZ_STAIRS, RED_SANDSTONE_STAIRS,
            PRISMARINE_BRICK_STAIRS, PRISMARINE_STAIRS, DARK_PRISMARINE_STAIRS, POLISHED_GRANITE_STAIRS, SMOOTH_RED_SANDSTONE_STAIRS, MOSSY_STONE_BRICK_STAIRS,
            POLISHED_DIORITE_STAIRS, MOSSY_COBBLESTONE_STAIRS, END_STONE_BRICK_STAIRS, STONE_STAIRS, SMOOTH_SANDSTONE_STAIRS, SMOOTH_QUARTZ_STAIRS, GRANITE_STAIRS,
            ANDESITE_STAIRS, RED_NETHER_BRICK_STAIRS, POLISHED_ANDESITE_STAIRS, DIORITE_STAIRS);
    public static final Category<ExItem> STANDING_SIGNS = new Category<>("standing_signs", OAK_SIGN, SPRUCE_SIGN, BIRCH_SIGN, ACACIA_SIGN, JUNGLE_SIGN, DARK_OAK_SIGN, SIGN_POST);
    public static final Category<ExItem> STONE_BRICKS = new Category<>("stone_bricks", VanillaItem.STONE_BRICKS, MOSSY_STONE_BRICKS, CRACKED_STONE_BRICKS, CHISELED_STONE_BRICKS);
    public static final Category<ExItem> VALID_SPAWN = new Category<>("valid_spawn", GRASS_BLOCK, PODZOL);
    public static final Category<ExItem> WALL_SIGNS = new Category<>("wall_signs", OAK_WALL_SIGN, SPRUCE_WALL_SIGN, BIRCH_WALL_SIGN, ACACIA_WALL_SIGN, JUNGLE_WALL_SIGN,
            DARK_OAK_WALL_SIGN);
    public static final Category<ExItem> WALL_CORALS = new Category<>("wall_corals", TUBE_CORAL_WALL_FAN, BRAIN_CORAL_WALL_FAN, BUBBLE_CORAL_WALL_FAN, FIRE_CORAL_WALL_FAN,
            HORN_CORAL_WALL_FAN);
    public static final Category<ExItem> WALLS = new Category<>("walls", COBBLESTONE_WALL, MOSSY_COBBLESTONE_WALL, BRICK_WALL, PRISMARINE_WALL, RED_SANDSTONE_WALL,
            MOSSY_STONE_BRICK_WALL, GRANITE_WALL, STONE_BRICK_WALL, NETHER_BRICK_WALL, ANDESITE_WALL, RED_NETHER_BRICK_WALL, SANDSTONE_WALL, END_STONE_BRICK_WALL,
            DIORITE_WALL);
    public static final Category<ExItem> WATER = new Category<>("water", VanillaItem.WATER, FLOWING_WATER);
    public static final Category<ExItem> WITHER_IMMUNE = new Category<>("wither_immune", BARRIER, BEDROCK, END_PORTAL, END_PORTAL_FRAME, END_GATEWAY, COMMAND_BLOCK,
            REPEATING_COMMAND_BLOCK, CHAIN_COMMAND_BLOCK, STRUCTURE_BLOCK, JIGSAW, MOVING_PISTON, OBSIDIAN, END_STONE, IRON_BARS);
    public static final Category<ExItem> WOODEN_BUTTONS = new Category<>("wooden_butons", OAK_BUTTON, SPRUCE_BUTTON, BIRCH_BUTTON, JUNGLE_BUTTON, ACACIA_BUTTON, DARK_OAK_BUTTON);
    public static final Category<ExItem> WOODEN_DOORS = new Category<>("wooden_doors", OAK_DOOR, SPRUCE_DOOR, BIRCH_DOOR, JUNGLE_DOOR, ACACIA_DOOR, DARK_OAK_DOOR, WOODEN_DOOR);
    public static final Category<ExItem> WOODEN_FENCES = new Category<>("wooden_fences", OAK_FENCE, ACACIA_FENCE, DARK_OAK_FENCE, SPRUCE_FENCE, BIRCH_FENCE, JUNGLE_FENCE);
    public static final Category<ExItem> WOODEN_PRESSURE_PLATES = new Category<>("wooden_pressure_plates", OAK_PRESSURE_PLATE, SPRUCE_PRESSURE_PLATE, BIRCH_PRESSURE_PLATE,
            JUNGLE_PRESSURE_PLATE, ACACIA_PRESSURE_PLATE, DARK_OAK_PRESSURE_PLATE);
    public static final Category<ExItem> WOODEN_SLABS = new Category<>("wooden_slabs", OAK_SLAB, SPRUCE_SLAB, BIRCH_SLAB, JUNGLE_SLAB, ACACIA_SLAB, DARK_OAK_SLAB);
    public static final Category<ExItem> WOODEN_STAIRS = new Category<>("wooden_stairs", OAK_STAIRS, SPRUCE_STAIRS, BIRCH_STAIRS, JUNGLE_STAIRS, ACACIA_STAIRS, DARK_OAK_STAIRS);
    public static final Category<ExItem> WOODEN_TRAPDOORS = new Category<>("wooden_trapdoors", ACACIA_TRAPDOOR, BIRCH_TRAPDOOR, DARK_OAK_TRAPDOOR, JUNGLE_TRAPDOOR,
            OAK_TRAPDOOR, SPRUCE_TRAPDOOR);
    public static final Category<ExItem> WOOL = new Category<>("wool", WHITE_WOOL, ORANGE_WOOL, MAGENTA_WOOL, LIGHT_BLUE_WOOL, YELLOW_WOOL, LIME_WOOL, PINK_WOOL,
            GRAY_WOOL, LIGHT_GRAY_WOOL, CYAN_WOOL, PURPLE_WOOL, BLUE_WOOL, BROWN_WOOL, GREEN_WOOL, RED_WOOL, BLACK_WOOL);

    // In Minecraft "arrows"
    public static final Category<ExMob> ARROW_MOBS = new Category<>("arrow_mobs", VanillaMob.ARROW, VanillaMob.TIPPED_ARROW, VanillaMob.SPECTRAL_ARROW);
    public static final Category<ExMob> BEEHIVE_INHABITORS = new Category<>("beehive_inhabitors", BEE);
    public static final Category<ExMob> RAIDERS = new Category<>("raiders", EVOKER, ILLUSIONER, PILLAGER, RAVAGER, VINDICATOR, WITCH);
    public static final Category<ExMob> SKELETONS = new Category<>("skeletons", SKELETON, STRAY, WITHER_SKELETON);

    // Removed in 1.15
    public static final Category<ExItem> DIRT_LIKE = new Category<>("dirt_like", DIRT, GRASS_BLOCK, PODZOL, COARSE_DIRT, MYCELIUM);

    // Parents
    public static final Category<ExItem> BAMBOO_PLANTABLE_ON = new Category<>("bamboo_plantable_on", SAND, DIRT_LIKE, BAMBOO, BAMBOO_SAPLING, GRAVEL);
    public static final Category<ExItem> BEE_GROWABLES = new Category<>("bee_growables", CROPS, SWEET_BERRY_BUSH);
    public static final Category<ExItem> BUTTONS = new Category<>("buttons", WOODEN_BUTTONS, STONE_BUTTON);
    public static final Category<ExItem> CORALS = new Category<>("corals", CORAL_PLANTS, TUBE_CORAL_FAN, BRAIN_CORAL_FAN, BUBBLE_CORAL_FAN, FIRE_CORAL_FAN, HORN_CORAL_FAN);
    public static final Category<ExItem> DOORS = new Category<>("doors", WOODEN_DOORS, IRON_DOOR);
    public static final Category<ExItem> ENDERMAN_HOLDABLE = new Category<>("enderman_holdable", SMALL_FLOWERS, GRASS_BLOCK, DIRT, COARSE_DIRT, PODZOL, VanillaItem.SAND, RED_SAND,
            GRAVEL, BROWN_MUSHROOM, RED_MUSHROOM, VanillaItem.TNT, CACTUS, CLAY, PUMPKIN, CARVED_PUMPKIN, MELON, MYCELIUM, NETHERRACK);
    public static final Category<ExItem> FENCES = new Category<>("fences", WOODEN_FENCES, NETHER_BRICK_FENCE);
    public static final Category<ExItem> FLOWERS = new Category<>("flowers", SMALL_FLOWERS, TALL_FLOWERS);
    public static final Category<ExItem> LOGS = new Category<>("logs", OAK_LOGS, SPRUCE_LOGS, BIRCH_LOGS, JUNGLE_LOGS, ACACIA_LOGS, DARK_OAK_LOGS);
    public static final Category<ExItem> PLANKS = new Category<>("planks", OAK_PLANKS, SPRUCE_PLANKS, BIRCH_PLANKS, JUNGLE_PLANKS, ACACIA_PLANKS, DARK_OAK_PLANKS);
    public static final Category<ExItem> SIGNS = new Category<>("signs", STANDING_SIGNS, WALL_SIGNS);
    public static final Category<ExItem> TRAPDOORS = new Category<>("trapdoors", WOODEN_TRAPDOORS, IRON_TRAPDOOR);
    public static final Category<ExItem> UNDERWATER_BONEMEALS = new Category<>("underwater_bonemeals", CORALS, WALL_CORALS);

    // Caliburn
    public static final Category<ExItem> CHESTS = new Category<>("chests", CHEST, TRAPPED_CHEST, ENDER_CHEST, SHULKER_BOXES);

    private String id;
    private Set<T> elements = new HashSet<>();

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
    public Set<T> getElements() {
        return elements;
    }

    /**
     * @param t an object of the category type
     * @return if this category contains the item
     */
    public boolean contains(T t) {
        if (t == null) {
            return false;
        }
        return elements.contains(t);
    }

    /**
     * @param material a material
     * @return if this category contains an item that wraps the material
     */
    public boolean containsMaterial(Material material) {
        if (material == null) {
            return false;
        }
        try {
            return contains((T) VanillaItem.get(material));
        } catch (ClassCastException exception) {
            return false;
        }
    }

    /**
     * @param block a block
     * @return if this category contains an item that wraps the material of this item
     */
    public boolean containsBlock(Block block) {
        if (block == null) {
            return false;
        }
        return containsMaterial(block.getType());
    }

    /**
     * @param item an item stack
     * @return if this category contains an item that wraps the material of this item
     */
    public boolean containsItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return containsMaterial(item.getType());
        try {
            return contains((T) CaliburnAPI.getInstance().getExItem(item));
        } catch (ClassCastException exception) {
            return false;
        }
    }

}
