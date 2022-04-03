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
package de.erethon.caliburn.category;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.ExItem;
import de.erethon.caliburn.item.VanillaItem;
import de.erethon.caliburn.mob.ExMob;
import de.erethon.caliburn.mob.VanillaMob;
import de.erethon.bedrock.chat.MessageUtil;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

/**
 * This class groups together certain values that can be subsumed under a superterm.
 * <p>
 * It is similar to Bukkit's {@link org.bukkit.Tag} class.
 *
 * @param <T> the type that can be subsumed under this category
 * @author Daniel Saukel
 */
public class Category<T extends Categorizable> extends Categorizable {

    public static final Category<ExItem> NEEDS_DIAMOND_TOOL = new Category<>("needs_diamond_tool", VanillaItem.OBSIDIAN, VanillaItem.CRYING_OBSIDIAN, VanillaItem.NETHERITE_BLOCK,
            VanillaItem.RESPAWN_ANCHOR, VanillaItem.ANCIENT_DEBRIS);
    public static final Category<ExItem> BASE_STONE_OVERWORLD = new Category<>("base_stone_overworld", VanillaItem.STONE, VanillaItem.GRANITE, VanillaItem.DIORITE, VanillaItem.ANDESITE,
            VanillaItem.TUFF, VanillaItem.DEEPSLATE);
    public static final Category<ExItem> CARPETS = new Category<>("carpets", VanillaItem.WHITE_CARPET, VanillaItem.ORANGE_CARPET, VanillaItem.MAGENTA_CARPET, VanillaItem.LIGHT_BLUE_CARPET,
            VanillaItem.YELLOW_CARPET, VanillaItem.LIME_CARPET, VanillaItem.PINK_CARPET, VanillaItem.GRAY_CARPET, VanillaItem.LIGHT_GRAY_CARPET, VanillaItem.CYAN_CARPET, VanillaItem.PURPLE_CARPET,
            VanillaItem.BLUE_CARPET, VanillaItem.BROWN_CARPET, VanillaItem.GREEN_CARPET, VanillaItem.RED_CARPET, VanillaItem.BLACK_CARPET);
    public static final Category<ExItem> TALL_FLOWERS = new Category<>("tall_flowers", VanillaItem.SUNFLOWER, VanillaItem.LILAC, VanillaItem.PEONY, VanillaItem.ROSE_BUSH);
    public static final Category<ExItem> FISHES = new Category<>("fishes", VanillaItem.COD, VanillaItem.COOKED_COD, VanillaItem.SALMON, VanillaItem.COOKED_SALMON, VanillaItem.PUFFERFISH,
            VanillaItem.TROPICAL_FISH);
    public static final Category<ExItem> GOLD_ORES = new Category<>("gold_ores", VanillaItem.GOLD_ORE, VanillaItem.NETHER_GOLD_ORE, VanillaItem.DEEPSLATE_GOLD_ORE);
    public static final Category<ExItem> DIAMOND_ORES = new Category<>("diamond_ores", VanillaItem.DIAMOND_ORE, VanillaItem.DEEPSLATE_DIAMOND_ORE);
    public static final Category<ExItem> GOATS_SPAWNABLE_ON = new Category<>("goats_spawnable_on", VanillaItem.STONE, VanillaItem.SNOW, VanillaItem.POWDER_SNOW, VanillaItem.SNOW_BLOCK,
            VanillaItem.PACKED_ICE, VanillaItem.GRAVEL);
    public static final Category<ExItem> POLAR_BEARS_SPAWNABLE_ON_IN_FROZEN_OCEAN = new Category<>("polar_bears_spawnable_on_in_frozen_ocean", VanillaItem.ICE);
    public static final Category<ExItem> CAVE_VINES = new Category<>("cave_vines", VanillaItem.CAVE_VINES_PLANT, VanillaItem.CAVE_VINES);
    public static final Category<ExItem> FIRE = new Category<>("fire", VanillaItem.FIRE, VanillaItem.SOUL_FIRE);
    public static final Category<ExItem> CORAL_BLOCKS = new Category<>("coral_blocks", VanillaItem.TUBE_CORAL_BLOCK, VanillaItem.BRAIN_CORAL_BLOCK, VanillaItem.BUBBLE_CORAL_BLOCK,
            VanillaItem.FIRE_CORAL_BLOCK, VanillaItem.HORN_CORAL_BLOCK);
    public static final Category<ExItem> CORAL_PLANTS = new Category<>("coral_plants", VanillaItem.TUBE_CORAL, VanillaItem.BRAIN_CORAL, VanillaItem.BUBBLE_CORAL, VanillaItem.FIRE_CORAL,
            VanillaItem.HORN_CORAL);
    public static final Category<ExItem> COALS = new Category<>("coals", VanillaItem.COAL, VanillaItem.CHARCOAL);
    public static final Category<ExItem> BEACON_BASE_BLOCKS = new Category<>("beacon_base_blocks", VanillaItem.NETHERITE_BLOCK, VanillaItem.EMERALD_BLOCK, VanillaItem.DIAMOND_BLOCK,
            VanillaItem.GOLD_BLOCK, VanillaItem.IRON_BLOCK);
    public static final Category<ExItem> SPRUCE_LOGS = new Category<>("spruce_logs", VanillaItem.SPRUCE_LOG, VanillaItem.SPRUCE_WOOD, VanillaItem.STRIPPED_SPRUCE_LOG, VanillaItem.STRIPPED_SPRUCE_WOOD);
    public static final Category<ExItem> LECTERN_BOOKS = new Category<>("lectern_books", VanillaItem.WRITTEN_BOOK, VanillaItem.WRITABLE_BOOK);
    public static final Category<ExItem> WITHER_IMMUNE = new Category<>("wither_immune", VanillaItem.BARRIER, VanillaItem.BEDROCK, VanillaItem.END_PORTAL, VanillaItem.END_PORTAL_FRAME,
            VanillaItem.END_GATEWAY, VanillaItem.COMMAND_BLOCK, VanillaItem.REPEATING_COMMAND_BLOCK, VanillaItem.CHAIN_COMMAND_BLOCK, VanillaItem.STRUCTURE_BLOCK, VanillaItem.JIGSAW,
            VanillaItem.MOVING_PISTON);
    public static final Category<ExItem> STRIDER_WARM_BLOCKS = new Category<>("strider_warm_blocks", VanillaItem.LAVA);
    public static final Category<ExItem> FENCE_GATES = new Category<>("fence_gates", VanillaItem.ACACIA_FENCE_GATE, VanillaItem.BIRCH_FENCE_GATE, VanillaItem.DARK_OAK_FENCE_GATE,
            VanillaItem.JUNGLE_FENCE_GATE, VanillaItem.OAK_FENCE_GATE, VanillaItem.SPRUCE_FENCE_GATE, VanillaItem.CRIMSON_FENCE_GATE, VanillaItem.WARPED_FENCE_GATE);
    public static final Category<ExItem> PIGLIN_REPELLENTS = new Category<>("piglin_repellents", VanillaItem.SOUL_FIRE, VanillaItem.SOUL_TORCH, VanillaItem.SOUL_LANTERN, VanillaItem.SOUL_WALL_TORCH,
            VanillaItem.SOUL_CAMPFIRE);
    public static final Category<ExItem> PORTALS = new Category<>("portals", VanillaItem.NETHER_PORTAL, VanillaItem.END_PORTAL, VanillaItem.END_GATEWAY);
    public static final Category<ExItem> MOOSHROOMS_SPAWNABLE_ON = new Category<>("mooshrooms_spawnable_on", VanillaItem.MYCELIUM);
    public static final Category<ExItem> SMALL_DRIPLEAF_PLACEABLE = new Category<>("small_dripleaf_placeable", VanillaItem.CLAY, VanillaItem.MOSS_BLOCK);
    public static final Category<ExItem> BANNERS = new Category<>("banners", VanillaItem.WHITE_BANNER, VanillaItem.ORANGE_BANNER, VanillaItem.MAGENTA_BANNER, VanillaItem.LIGHT_BLUE_BANNER,
            VanillaItem.YELLOW_BANNER, VanillaItem.LIME_BANNER, VanillaItem.PINK_BANNER, VanillaItem.GRAY_BANNER, VanillaItem.LIGHT_GRAY_BANNER, VanillaItem.CYAN_BANNER, VanillaItem.PURPLE_BANNER,
            VanillaItem.BLUE_BANNER, VanillaItem.BROWN_BANNER, VanillaItem.GREEN_BANNER, VanillaItem.RED_BANNER, VanillaItem.BLACK_BANNER, VanillaItem.WHITE_WALL_BANNER, VanillaItem.ORANGE_WALL_BANNER,
            VanillaItem.MAGENTA_WALL_BANNER, VanillaItem.LIGHT_BLUE_WALL_BANNER, VanillaItem.YELLOW_WALL_BANNER, VanillaItem.LIME_WALL_BANNER, VanillaItem.PINK_WALL_BANNER,
            VanillaItem.GRAY_WALL_BANNER, VanillaItem.LIGHT_GRAY_WALL_BANNER, VanillaItem.CYAN_WALL_BANNER, VanillaItem.PURPLE_WALL_BANNER, VanillaItem.BLUE_WALL_BANNER, VanillaItem.BROWN_WALL_BANNER,
            VanillaItem.GREEN_WALL_BANNER, VanillaItem.RED_WALL_BANNER, VanillaItem.BLACK_WALL_BANNER);
    public static final Category<ExItem> STONE_CRAFTING_MATERIALS = new Category<>("stone_crafting_materials", VanillaItem.COBBLESTONE, VanillaItem.BLACKSTONE, VanillaItem.COBBLED_DEEPSLATE);
    public static final Category<ExItem> STONE_PRESSURE_PLATES = new Category<>("stone_pressure_plates", VanillaItem.STONE_PRESSURE_PLATE, VanillaItem.POLISHED_BLACKSTONE_PRESSURE_PLATE);
    public static final Category<ExItem> RABBITS_SPAWNABLE_ON = new Category<>("rabbits_spawnable_on", VanillaItem.GRASS_BLOCK, VanillaItem.SNOW, VanillaItem.SNOW_BLOCK, VanillaItem.SAND);
    public static final Category<ExItem> BEDS = new Category<>("beds", VanillaItem.RED_BED, VanillaItem.BLACK_BED, VanillaItem.BLUE_BED, VanillaItem.BROWN_BED, VanillaItem.CYAN_BED,
            VanillaItem.GRAY_BED, VanillaItem.GREEN_BED, VanillaItem.LIGHT_BLUE_BED, VanillaItem.LIGHT_GRAY_BED, VanillaItem.LIME_BED, VanillaItem.MAGENTA_BED, VanillaItem.ORANGE_BED,
            VanillaItem.PINK_BED, VanillaItem.PURPLE_BED, VanillaItem.WHITE_BED, VanillaItem.YELLOW_BED);
    public static final Category<ExItem> SAPLINGS = new Category<>("saplings", VanillaItem.OAK_SAPLING, VanillaItem.SPRUCE_SAPLING, VanillaItem.BIRCH_SAPLING, VanillaItem.JUNGLE_SAPLING,
            VanillaItem.ACACIA_SAPLING, VanillaItem.DARK_OAK_SAPLING, VanillaItem.AZALEA, VanillaItem.FLOWERING_AZALEA);
    public static final Category<ExItem> HOE = new Category<>("hoe", VanillaItem.NETHER_WART_BLOCK, VanillaItem.WARPED_WART_BLOCK, VanillaItem.HAY_BLOCK, VanillaItem.DRIED_KELP_BLOCK,
            VanillaItem.TARGET, VanillaItem.SHROOMLIGHT, VanillaItem.SPONGE, VanillaItem.WET_SPONGE, VanillaItem.JUNGLE_LEAVES, VanillaItem.OAK_LEAVES, VanillaItem.SPRUCE_LEAVES,
            VanillaItem.DARK_OAK_LEAVES, VanillaItem.ACACIA_LEAVES, VanillaItem.BIRCH_LEAVES, VanillaItem.AZALEA_LEAVES, VanillaItem.FLOWERING_AZALEA_LEAVES, VanillaItem.SCULK_SENSOR,
            VanillaItem.MOSS_BLOCK, VanillaItem.MOSS_CARPET);
    public static final Category<ExItem> ACACIA_LOGS = new Category<>("acacia_logs", VanillaItem.ACACIA_LOG, VanillaItem.ACACIA_WOOD, VanillaItem.STRIPPED_ACACIA_LOG, VanillaItem.STRIPPED_ACACIA_WOOD);
    public static final Category<ExItem> ANVIL = new Category<>("anvil", VanillaItem.ANVIL, VanillaItem.CHIPPED_ANVIL, VanillaItem.DAMAGED_ANVIL);
    public static final Category<ExItem> WITHER_SUMMON_BASE_BLOCKS = new Category<>("wither_summon_base_blocks", VanillaItem.SOUL_SAND, VanillaItem.SOUL_SOIL);
    public static final Category<ExItem> CROPS = new Category<>("crops", VanillaItem.BEETROOTS, VanillaItem.CARROTS, VanillaItem.POTATOES, VanillaItem.WHEAT, VanillaItem.MELON_STEM,
            VanillaItem.PUMPKIN_STEM);
    public static final Category<ExItem> BASE_STONE_NETHER = new Category<>("base_stone_nether", VanillaItem.NETHERRACK, VanillaItem.BASALT, VanillaItem.BLACKSTONE);
    public static final Category<ExItem> LAPIS_ORES = new Category<>("lapis_ores", VanillaItem.LAPIS_ORE, VanillaItem.DEEPSLATE_LAPIS_ORE);
    public static final Category<ExItem> NON_FLAMMABLE_WOOD = new Category<>("non_flammable_wood", VanillaItem.WARPED_STEM, VanillaItem.STRIPPED_WARPED_STEM, VanillaItem.WARPED_HYPHAE,
            VanillaItem.STRIPPED_WARPED_HYPHAE, VanillaItem.CRIMSON_STEM, VanillaItem.STRIPPED_CRIMSON_STEM, VanillaItem.CRIMSON_HYPHAE, VanillaItem.STRIPPED_CRIMSON_HYPHAE, VanillaItem.CRIMSON_PLANKS,
            VanillaItem.WARPED_PLANKS, VanillaItem.CRIMSON_SLAB, VanillaItem.WARPED_SLAB, VanillaItem.CRIMSON_PRESSURE_PLATE, VanillaItem.WARPED_PRESSURE_PLATE, VanillaItem.CRIMSON_FENCE,
            VanillaItem.WARPED_FENCE, VanillaItem.CRIMSON_TRAPDOOR, VanillaItem.WARPED_TRAPDOOR, VanillaItem.CRIMSON_FENCE_GATE, VanillaItem.WARPED_FENCE_GATE, VanillaItem.CRIMSON_STAIRS,
            VanillaItem.WARPED_STAIRS, VanillaItem.CRIMSON_BUTTON, VanillaItem.WARPED_BUTTON, VanillaItem.CRIMSON_DOOR, VanillaItem.WARPED_DOOR, VanillaItem.CRIMSON_SIGN, VanillaItem.WARPED_SIGN,
            VanillaItem.CRIMSON_WALL_SIGN, VanillaItem.WARPED_WALL_SIGN);
    public static final Category<ExItem> WALLS = new Category<>("walls", VanillaItem.COBBLESTONE_WALL, VanillaItem.MOSSY_COBBLESTONE_WALL, VanillaItem.BRICK_WALL, VanillaItem.PRISMARINE_WALL,
            VanillaItem.RED_SANDSTONE_WALL, VanillaItem.MOSSY_STONE_BRICK_WALL, VanillaItem.GRANITE_WALL, VanillaItem.STONE_BRICK_WALL, VanillaItem.NETHER_BRICK_WALL, VanillaItem.ANDESITE_WALL,
            VanillaItem.RED_NETHER_BRICK_WALL, VanillaItem.SANDSTONE_WALL, VanillaItem.END_STONE_BRICK_WALL, VanillaItem.DIORITE_WALL, VanillaItem.BLACKSTONE_WALL,
            VanillaItem.POLISHED_BLACKSTONE_BRICK_WALL, VanillaItem.POLISHED_BLACKSTONE_WALL, VanillaItem.COBBLED_DEEPSLATE_WALL, VanillaItem.POLISHED_DEEPSLATE_WALL, VanillaItem.DEEPSLATE_TILE_WALL,
            VanillaItem.DEEPSLATE_BRICK_WALL);
    public static final Category<ExItem> AXOLOTL_TEMPT_ITEMS = new Category<>("axolotl_tempt_items", VanillaItem.TROPICAL_FISH_BUCKET);
    public static final Category<ExItem> WOODEN_STAIRS = new Category<>("wooden_stairs", VanillaItem.OAK_STAIRS, VanillaItem.SPRUCE_STAIRS, VanillaItem.BIRCH_STAIRS, VanillaItem.JUNGLE_STAIRS,
            VanillaItem.ACACIA_STAIRS, VanillaItem.DARK_OAK_STAIRS, VanillaItem.CRIMSON_STAIRS, VanillaItem.WARPED_STAIRS);
    public static final Category<ExItem> RAILS = new Category<>("rails", VanillaItem.RAIL, VanillaItem.POWERED_RAIL, VanillaItem.DETECTOR_RAIL, VanillaItem.ACTIVATOR_RAIL);
    public static final Category<ExItem> WOLVES_SPAWNABLE_ON = new Category<>("wolves_spawnable_on", VanillaItem.GRASS_BLOCK, VanillaItem.SNOW, VanillaItem.SNOW_BLOCK);
    public static final Category<ExItem> WOODEN_PRESSURE_PLATES = new Category<>("wooden_pressure_plates", VanillaItem.OAK_PRESSURE_PLATE, VanillaItem.SPRUCE_PRESSURE_PLATE,
            VanillaItem.BIRCH_PRESSURE_PLATE, VanillaItem.JUNGLE_PRESSURE_PLATE, VanillaItem.ACACIA_PRESSURE_PLATE, VanillaItem.DARK_OAK_PRESSURE_PLATE, VanillaItem.CRIMSON_PRESSURE_PLATE,
            VanillaItem.WARPED_PRESSURE_PLATE);
    public static final Category<ExItem> WOODEN_FENCES = new Category<>("wooden_fences", VanillaItem.OAK_FENCE, VanillaItem.ACACIA_FENCE, VanillaItem.DARK_OAK_FENCE, VanillaItem.SPRUCE_FENCE,
            VanillaItem.BIRCH_FENCE, VanillaItem.JUNGLE_FENCE, VanillaItem.CRIMSON_FENCE, VanillaItem.WARPED_FENCE);
    public static final Category<ExItem> FLOWER_POTS = new Category<>("flower_pots", VanillaItem.FLOWER_POT, VanillaItem.POTTED_POPPY, VanillaItem.POTTED_BLUE_ORCHID, VanillaItem.POTTED_ALLIUM,
            VanillaItem.POTTED_AZURE_BLUET, VanillaItem.POTTED_RED_TULIP, VanillaItem.POTTED_ORANGE_TULIP, VanillaItem.POTTED_WHITE_TULIP, VanillaItem.POTTED_PINK_TULIP, VanillaItem.POTTED_OXEYE_DAISY,
            VanillaItem.POTTED_DANDELION, VanillaItem.POTTED_OAK_SAPLING, VanillaItem.POTTED_SPRUCE_SAPLING, VanillaItem.POTTED_BIRCH_SAPLING, VanillaItem.POTTED_JUNGLE_SAPLING,
            VanillaItem.POTTED_ACACIA_SAPLING, VanillaItem.POTTED_DARK_OAK_SAPLING, VanillaItem.POTTED_RED_MUSHROOM, VanillaItem.POTTED_BROWN_MUSHROOM, VanillaItem.POTTED_DEAD_BUSH,
            VanillaItem.POTTED_FERN, VanillaItem.POTTED_CACTUS, VanillaItem.POTTED_CORNFLOWER, VanillaItem.POTTED_LILY_OF_THE_VALLEY, VanillaItem.POTTED_WITHER_ROSE, VanillaItem.POTTED_BAMBOO,
            VanillaItem.POTTED_CRIMSON_FUNGUS, VanillaItem.POTTED_WARPED_FUNGUS, VanillaItem.POTTED_CRIMSON_ROOTS, VanillaItem.POTTED_WARPED_ROOTS, VanillaItem.POTTED_AZALEA_BUSH,
            VanillaItem.POTTED_FLOWERING_AZALEA_BUSH);
    public static final Category<ExItem> EMERALD_ORES = new Category<>("emerald_ores", VanillaItem.EMERALD_ORE, VanillaItem.DEEPSLATE_EMERALD_ORE);
    public static final Category<ExItem> COAL_ORES = new Category<>("coal_ores", VanillaItem.COAL_ORE, VanillaItem.DEEPSLATE_COAL_ORE);
    public static final Category<ExItem> INSIDE_STEP_SOUND_BLOCKS = new Category<>("inside_step_sound_blocks", VanillaItem.SNOW, VanillaItem.POWDER_SNOW);
    public static final Category<ExItem> SOUL_FIRE_BASE_BLOCKS = new Category<>("soul_fire_base_blocks", VanillaItem.SOUL_SAND, VanillaItem.SOUL_SOIL);
    public static final Category<ExItem> PLANKS = new Category<>("planks", VanillaItem.OAK_PLANKS, VanillaItem.SPRUCE_PLANKS, VanillaItem.BIRCH_PLANKS, VanillaItem.JUNGLE_PLANKS,
            VanillaItem.ACACIA_PLANKS, VanillaItem.DARK_OAK_PLANKS, VanillaItem.CRIMSON_PLANKS, VanillaItem.WARPED_PLANKS);
    public static final Category<ExItem> WALL_SIGNS = new Category<>("wall_signs", VanillaItem.OAK_WALL_SIGN, VanillaItem.SPRUCE_WALL_SIGN, VanillaItem.BIRCH_WALL_SIGN, VanillaItem.ACACIA_WALL_SIGN,
            VanillaItem.JUNGLE_WALL_SIGN, VanillaItem.DARK_OAK_WALL_SIGN, VanillaItem.CRIMSON_WALL_SIGN, VanillaItem.WARPED_WALL_SIGN);
    public static final Category<ExItem> VALID_SPAWN = new Category<>("valid_spawn", VanillaItem.GRASS_BLOCK, VanillaItem.PODZOL);
    public static final Category<ExItem> FOX_FOOD = new Category<>("fox_food", VanillaItem.SWEET_BERRIES, VanillaItem.GLOW_BERRIES);
    public static final Category<ExItem> WOOL = new Category<>("wool", VanillaItem.WHITE_WOOL, VanillaItem.ORANGE_WOOL, VanillaItem.MAGENTA_WOOL, VanillaItem.LIGHT_BLUE_WOOL, VanillaItem.YELLOW_WOOL,
            VanillaItem.LIME_WOOL, VanillaItem.PINK_WOOL, VanillaItem.GRAY_WOOL, VanillaItem.LIGHT_GRAY_WOOL, VanillaItem.CYAN_WOOL, VanillaItem.PURPLE_WOOL, VanillaItem.BLUE_WOOL,
            VanillaItem.BROWN_WOOL, VanillaItem.GREEN_WOOL, VanillaItem.RED_WOOL, VanillaItem.BLACK_WOOL);
    public static final Category<ExItem> SHOVEL = new Category<>("shovel", VanillaItem.CLAY, VanillaItem.DIRT, VanillaItem.COARSE_DIRT, VanillaItem.PODZOL, VanillaItem.FARMLAND,
            VanillaItem.GRASS_BLOCK, VanillaItem.GRAVEL, VanillaItem.MYCELIUM, VanillaItem.SAND, VanillaItem.RED_SAND, VanillaItem.SNOW_BLOCK, VanillaItem.SNOW, VanillaItem.SOUL_SAND,
            VanillaItem.DIRT_PATH, VanillaItem.WHITE_CONCRETE_POWDER, VanillaItem.ORANGE_CONCRETE_POWDER, VanillaItem.MAGENTA_CONCRETE_POWDER, VanillaItem.LIGHT_BLUE_CONCRETE_POWDER,
            VanillaItem.YELLOW_CONCRETE_POWDER, VanillaItem.LIME_CONCRETE_POWDER, VanillaItem.PINK_CONCRETE_POWDER, VanillaItem.GRAY_CONCRETE_POWDER, VanillaItem.LIGHT_GRAY_CONCRETE_POWDER,
            VanillaItem.CYAN_CONCRETE_POWDER, VanillaItem.PURPLE_CONCRETE_POWDER, VanillaItem.BLUE_CONCRETE_POWDER, VanillaItem.BROWN_CONCRETE_POWDER, VanillaItem.GREEN_CONCRETE_POWDER,
            VanillaItem.RED_CONCRETE_POWDER, VanillaItem.BLACK_CONCRETE_POWDER, VanillaItem.SOUL_SOIL, VanillaItem.ROOTED_DIRT);
    public static final Category<ExItem> ARROWS = new Category<>("arrows", VanillaItem.ARROW, VanillaItem.TIPPED_ARROW, VanillaItem.SPECTRAL_ARROW);
    public static final Category<ExItem> ICE = new Category<>("ice", VanillaItem.ICE, VanillaItem.PACKED_ICE, VanillaItem.BLUE_ICE, VanillaItem.FROSTED_ICE);
    public static final Category<ExItem> CREEPER_DROP_MUSIC_DISCS = new Category<>("creeper_drop_music_discs", VanillaItem.MUSIC_DISC_13, VanillaItem.MUSIC_DISC_CAT, VanillaItem.MUSIC_DISC_BLOCKS,
            VanillaItem.MUSIC_DISC_CHIRP, VanillaItem.MUSIC_DISC_FAR, VanillaItem.MUSIC_DISC_MALL, VanillaItem.MUSIC_DISC_MELLOHI, VanillaItem.MUSIC_DISC_STAL, VanillaItem.MUSIC_DISC_STRAD,
            VanillaItem.MUSIC_DISC_WARD, VanillaItem.MUSIC_DISC_11, VanillaItem.MUSIC_DISC_WAIT);
    public static final Category<ExItem> BEEHIVES = new Category<>("beehives", VanillaItem.BEE_NEST, VanillaItem.BEEHIVE);
    public static final Category<ExItem> DRAGON_IMMUNE = new Category<>("dragon_immune", VanillaItem.BARRIER, VanillaItem.BEDROCK, VanillaItem.END_PORTAL, VanillaItem.END_PORTAL_FRAME,
            VanillaItem.END_GATEWAY, VanillaItem.COMMAND_BLOCK, VanillaItem.REPEATING_COMMAND_BLOCK, VanillaItem.CHAIN_COMMAND_BLOCK, VanillaItem.STRUCTURE_BLOCK, VanillaItem.JIGSAW,
            VanillaItem.MOVING_PISTON, VanillaItem.OBSIDIAN, VanillaItem.CRYING_OBSIDIAN, VanillaItem.END_STONE, VanillaItem.IRON_BARS, VanillaItem.RESPAWN_ANCHOR);
    public static final Category<ExItem> DEEPSLATE_ORE_REPLACEABLES = new Category<>("deepslate_ore_replaceables", VanillaItem.DEEPSLATE, VanillaItem.TUFF);
    public static final Category<ExItem> LEAVES = new Category<>("leaves", VanillaItem.JUNGLE_LEAVES, VanillaItem.OAK_LEAVES, VanillaItem.SPRUCE_LEAVES, VanillaItem.DARK_OAK_LEAVES,
            VanillaItem.ACACIA_LEAVES, VanillaItem.BIRCH_LEAVES, VanillaItem.AZALEA_LEAVES, VanillaItem.FLOWERING_AZALEA_LEAVES);
    public static final Category<ExItem> STONE_TOOL_MATERIALS = new Category<>("stone_tool_materials", VanillaItem.COBBLESTONE, VanillaItem.BLACKSTONE, VanillaItem.COBBLED_DEEPSLATE);
    public static final Category<ExItem> GEODE_INVALID_BLOCKS = new Category<>("geode_invalid_blocks", VanillaItem.BEDROCK, VanillaItem.WATER, VanillaItem.LAVA, VanillaItem.ICE, VanillaItem.PACKED_ICE,
            VanillaItem.BLUE_ICE);
    public static final Category<ExItem> FOXES_SPAWNABLE_ON = new Category<>("foxes_spawnable_on", VanillaItem.GRASS_BLOCK, VanillaItem.SNOW, VanillaItem.SNOW_BLOCK, VanillaItem.PODZOL,
            VanillaItem.COARSE_DIRT);
    public static final Category<ExItem> PIGLIN_FOOD = new Category<>("piglin_food", VanillaItem.PORKCHOP, VanillaItem.COOKED_PORKCHOP);
    public static final Category<ExItem> NEEDS_IRON_TOOL = new Category<>("needs_iron_tool", VanillaItem.DIAMOND_BLOCK, VanillaItem.DIAMOND_ORE, VanillaItem.DEEPSLATE_DIAMOND_ORE,
            VanillaItem.EMERALD_ORE, VanillaItem.DEEPSLATE_EMERALD_ORE, VanillaItem.EMERALD_BLOCK, VanillaItem.GOLD_BLOCK, VanillaItem.RAW_GOLD_BLOCK, VanillaItem.GOLD_ORE,
            VanillaItem.DEEPSLATE_GOLD_ORE, VanillaItem.REDSTONE_ORE, VanillaItem.DEEPSLATE_REDSTONE_ORE);
    public static final Category<ExItem> CAMPFIRES = new Category<>("campfires", VanillaItem.CAMPFIRE, VanillaItem.SOUL_CAMPFIRE);
    public static final Category<ExItem> REDSTONE_ORES = new Category<>("redstone_ores", VanillaItem.REDSTONE_ORE, VanillaItem.DEEPSLATE_REDSTONE_ORE);
    public static final Category<ExItem> CRIMSON_STEMS = new Category<>("crimson_stems", VanillaItem.CRIMSON_STEM, VanillaItem.STRIPPED_CRIMSON_STEM, VanillaItem.CRIMSON_HYPHAE,
            VanillaItem.STRIPPED_CRIMSON_HYPHAE);
    public static final Category<ExItem> TERRACOTTA = new Category<>("terracotta", VanillaItem.TERRACOTTA, VanillaItem.WHITE_TERRACOTTA, VanillaItem.ORANGE_TERRACOTTA, VanillaItem.MAGENTA_TERRACOTTA,
            VanillaItem.LIGHT_BLUE_TERRACOTTA, VanillaItem.YELLOW_TERRACOTTA, VanillaItem.LIME_TERRACOTTA, VanillaItem.PINK_TERRACOTTA, VanillaItem.GRAY_TERRACOTTA, VanillaItem.LIGHT_GRAY_TERRACOTTA,
            VanillaItem.CYAN_TERRACOTTA, VanillaItem.PURPLE_TERRACOTTA, VanillaItem.BLUE_TERRACOTTA, VanillaItem.BROWN_TERRACOTTA, VanillaItem.GREEN_TERRACOTTA, VanillaItem.RED_TERRACOTTA,
            VanillaItem.BLACK_TERRACOTTA);
    public static final Category<ExItem> IRON_ORES = new Category<>("iron_ores", VanillaItem.IRON_ORE, VanillaItem.DEEPSLATE_IRON_ORE);
    public static final Category<ExItem> DIRT = new Category<>("dirt", VanillaItem.DIRT, VanillaItem.GRASS_BLOCK, VanillaItem.PODZOL, VanillaItem.COARSE_DIRT, VanillaItem.MYCELIUM,
            VanillaItem.ROOTED_DIRT, VanillaItem.MOSS_BLOCK);
    public static final Category<ExItem> WOODEN_SLABS = new Category<>("wooden_slabs", VanillaItem.OAK_SLAB, VanillaItem.SPRUCE_SLAB, VanillaItem.BIRCH_SLAB, VanillaItem.JUNGLE_SLAB,
            VanillaItem.ACACIA_SLAB, VanillaItem.DARK_OAK_SLAB, VanillaItem.CRIMSON_SLAB, VanillaItem.WARPED_SLAB);
    public static final Category<ExItem> BEACON_PAYMENT_ITEMS = new Category<>("beacon_payment_items", VanillaItem.NETHERITE_INGOT, VanillaItem.EMERALD, VanillaItem.DIAMOND, VanillaItem.GOLD_INGOT,
            VanillaItem.IRON_INGOT);
    public static final Category<ExItem> BOATS = new Category<>("boats", VanillaItem.OAK_BOAT, VanillaItem.SPRUCE_BOAT, VanillaItem.BIRCH_BOAT, VanillaItem.JUNGLE_BOAT, VanillaItem.ACACIA_BOAT,
            VanillaItem.DARK_OAK_BOAT);
    public static final Category<ExItem> SMALL_FLOWERS = new Category<>("small_flowers", VanillaItem.DANDELION, VanillaItem.POPPY, VanillaItem.BLUE_ORCHID, VanillaItem.ALLIUM, VanillaItem.AZURE_BLUET,
            VanillaItem.RED_TULIP, VanillaItem.ORANGE_TULIP, VanillaItem.WHITE_TULIP, VanillaItem.PINK_TULIP, VanillaItem.OXEYE_DAISY, VanillaItem.CORNFLOWER, VanillaItem.LILY_OF_THE_VALLEY,
            VanillaItem.WITHER_ROSE);
    public static final Category<ExItem> WATER = new Category<>("water", VanillaItem.WATER, VanillaItem.FLOWING_WATER);
    public static final Category<ExItem> DARK_OAK_LOGS = new Category<>("dark_oak_logs", VanillaItem.DARK_OAK_LOG, VanillaItem.DARK_OAK_WOOD, VanillaItem.STRIPPED_DARK_OAK_LOG,
            VanillaItem.STRIPPED_DARK_OAK_WOOD);
    public static final Category<ExItem> NYLIUM = new Category<>("nylium", VanillaItem.CRIMSON_NYLIUM, VanillaItem.WARPED_NYLIUM);
    public static final Category<ExItem> WOODEN_TRAPDOORS = new Category<>("wooden_trapdoors", VanillaItem.ACACIA_TRAPDOOR, VanillaItem.BIRCH_TRAPDOOR, VanillaItem.DARK_OAK_TRAPDOOR,
            VanillaItem.JUNGLE_TRAPDOOR, VanillaItem.OAK_TRAPDOOR, VanillaItem.SPRUCE_TRAPDOOR, VanillaItem.CRIMSON_TRAPDOOR, VanillaItem.WARPED_TRAPDOOR);
    public static final Category<ExItem> SAND = new Category<>("sand", VanillaItem.SAND, VanillaItem.RED_SAND);
    public static final Category<ExItem> IMPERMEABLE = new Category<>("impermeable", VanillaItem.GLASS, VanillaItem.WHITE_STAINED_GLASS, VanillaItem.ORANGE_STAINED_GLASS,
            VanillaItem.MAGENTA_STAINED_GLASS, VanillaItem.LIGHT_BLUE_STAINED_GLASS, VanillaItem.YELLOW_STAINED_GLASS, VanillaItem.LIME_STAINED_GLASS, VanillaItem.PINK_STAINED_GLASS,
            VanillaItem.GRAY_STAINED_GLASS, VanillaItem.LIGHT_GRAY_STAINED_GLASS, VanillaItem.CYAN_STAINED_GLASS, VanillaItem.PURPLE_STAINED_GLASS, VanillaItem.BLUE_STAINED_GLASS,
            VanillaItem.BROWN_STAINED_GLASS, VanillaItem.GREEN_STAINED_GLASS, VanillaItem.RED_STAINED_GLASS, VanillaItem.BLACK_STAINED_GLASS, VanillaItem.TINTED_GLASS);
    public static final Category<ExItem> SNOW = new Category<>("snow", VanillaItem.SNOW, VanillaItem.SNOW_BLOCK, VanillaItem.POWDER_SNOW);
    public static final Category<ExItem> OAK_LOGS = new Category<>("oak_logs", VanillaItem.OAK_LOG, VanillaItem.OAK_WOOD, VanillaItem.STRIPPED_OAK_LOG, VanillaItem.STRIPPED_OAK_WOOD);
    public static final Category<ExItem> FREEZE_IMMUNE_WEARABLES = new Category<>("freeze_immune_wearables", VanillaItem.LEATHER_BOOTS, VanillaItem.LEATHER_LEGGINGS, VanillaItem.LEATHER_CHESTPLATE,
            VanillaItem.LEATHER_HELMET, VanillaItem.LEATHER_HORSE_ARMOR);
    public static final Category<ExItem> CLUSTER_MAX_HARVESTABLES = new Category<>("cluster_max_harvestables", VanillaItem.DIAMOND_PICKAXE, VanillaItem.GOLDEN_PICKAXE, VanillaItem.IRON_PICKAXE,
            VanillaItem.NETHERITE_PICKAXE, VanillaItem.STONE_PICKAXE, VanillaItem.WOODEN_PICKAXE);
    public static final Category<ExItem> BIRCH_LOGS = new Category<>("birch_logs", VanillaItem.BIRCH_LOG, VanillaItem.BIRCH_WOOD, VanillaItem.STRIPPED_BIRCH_LOG, VanillaItem.STRIPPED_BIRCH_WOOD);
    public static final Category<ExItem> COPPER_ORES = new Category<>("copper_ores", VanillaItem.COPPER_ORE, VanillaItem.DEEPSLATE_COPPER_ORE);
    public static final Category<ExItem> CANDLE_CAKES = new Category<>("candle_cakes", VanillaItem.CANDLE_CAKE, VanillaItem.WHITE_CANDLE_CAKE, VanillaItem.ORANGE_CANDLE_CAKE,
            VanillaItem.MAGENTA_CANDLE_CAKE, VanillaItem.LIGHT_BLUE_CANDLE_CAKE, VanillaItem.YELLOW_CANDLE_CAKE, VanillaItem.LIME_CANDLE_CAKE, VanillaItem.PINK_CANDLE_CAKE,
            VanillaItem.GRAY_CANDLE_CAKE, VanillaItem.LIGHT_GRAY_CANDLE_CAKE, VanillaItem.CYAN_CANDLE_CAKE, VanillaItem.PURPLE_CANDLE_CAKE, VanillaItem.BLUE_CANDLE_CAKE, VanillaItem.BROWN_CANDLE_CAKE,
            VanillaItem.GREEN_CANDLE_CAKE, VanillaItem.RED_CANDLE_CAKE, VanillaItem.BLACK_CANDLE_CAKE);
    public static final Category<ExItem> WOODEN_BUTTONS = new Category<>("wooden_buttons", VanillaItem.OAK_BUTTON, VanillaItem.SPRUCE_BUTTON, VanillaItem.BIRCH_BUTTON, VanillaItem.JUNGLE_BUTTON,
            VanillaItem.ACACIA_BUTTON, VanillaItem.DARK_OAK_BUTTON, VanillaItem.CRIMSON_BUTTON, VanillaItem.WARPED_BUTTON);
    public static final Category<ExItem> CRYSTAL_SOUND_BLOCKS = new Category<>("crystal_sound_blocks", VanillaItem.AMETHYST_BLOCK, VanillaItem.BUDDING_AMETHYST);
    public static final Category<ExItem> WALL_CORALS = new Category<>("wall_corals", VanillaItem.TUBE_CORAL_WALL_FAN, VanillaItem.BRAIN_CORAL_WALL_FAN, VanillaItem.BUBBLE_CORAL_WALL_FAN,
            VanillaItem.FIRE_CORAL_WALL_FAN, VanillaItem.HORN_CORAL_WALL_FAN);
    public static final Category<ExItem> CANDLES = new Category<>("candles", VanillaItem.CANDLE, VanillaItem.WHITE_CANDLE, VanillaItem.ORANGE_CANDLE, VanillaItem.MAGENTA_CANDLE,
            VanillaItem.LIGHT_BLUE_CANDLE, VanillaItem.YELLOW_CANDLE, VanillaItem.LIME_CANDLE, VanillaItem.PINK_CANDLE, VanillaItem.GRAY_CANDLE, VanillaItem.LIGHT_GRAY_CANDLE, VanillaItem.CYAN_CANDLE,
            VanillaItem.PURPLE_CANDLE, VanillaItem.BLUE_CANDLE, VanillaItem.BROWN_CANDLE, VanillaItem.GREEN_CANDLE, VanillaItem.RED_CANDLE, VanillaItem.BLACK_CANDLE);
    public static final Category<ExItem> STONE_BRICKS = new Category<>("stone_bricks", VanillaItem.STONE_BRICKS, VanillaItem.MOSSY_STONE_BRICKS, VanillaItem.CRACKED_STONE_BRICKS,
            VanillaItem.CHISELED_STONE_BRICKS);
    public static final Category<ExItem> AXOLOTLS_SPAWNABLE_ON = new Category<>("axolotls_spawnable_on", VanillaItem.CLAY);
    public static final Category<ExItem> LAVA = new Category<>("lava", VanillaItem.LAVA, VanillaItem.FLOWING_LAVA);
    public static final Category<ExItem> SHULKER_BOXES = new Category<>("shulker_boxes", VanillaItem.SHULKER_BOX, VanillaItem.BLACK_SHULKER_BOX, VanillaItem.BLUE_SHULKER_BOX,
            VanillaItem.BROWN_SHULKER_BOX, VanillaItem.CYAN_SHULKER_BOX, VanillaItem.GRAY_SHULKER_BOX, VanillaItem.GREEN_SHULKER_BOX, VanillaItem.LIGHT_BLUE_SHULKER_BOX,
            VanillaItem.LIGHT_GRAY_SHULKER_BOX, VanillaItem.LIME_SHULKER_BOX, VanillaItem.MAGENTA_SHULKER_BOX, VanillaItem.ORANGE_SHULKER_BOX, VanillaItem.PINK_SHULKER_BOX,
            VanillaItem.PURPLE_SHULKER_BOX, VanillaItem.RED_SHULKER_BOX, VanillaItem.WHITE_SHULKER_BOX, VanillaItem.YELLOW_SHULKER_BOX);
    public static final Category<ExItem> CAULDRONS = new Category<>("cauldrons", VanillaItem.CAULDRON, VanillaItem.WATER_CAULDRON, VanillaItem.LAVA_CAULDRON, VanillaItem.POWDER_SNOW_CAULDRON);
    public static final Category<ExItem> JUNGLE_LOGS = new Category<>("jungle_logs", VanillaItem.JUNGLE_LOG, VanillaItem.JUNGLE_WOOD, VanillaItem.STRIPPED_JUNGLE_LOG, VanillaItem.STRIPPED_JUNGLE_WOOD);
    public static final Category<ExItem> NEEDS_STONE_TOOL = new Category<>("needs_stone_tool", VanillaItem.IRON_BLOCK, VanillaItem.RAW_IRON_BLOCK, VanillaItem.IRON_ORE, VanillaItem.DEEPSLATE_IRON_ORE,
            VanillaItem.LAPIS_BLOCK, VanillaItem.LAPIS_ORE, VanillaItem.DEEPSLATE_LAPIS_ORE, VanillaItem.COPPER_BLOCK, VanillaItem.RAW_COPPER_BLOCK, VanillaItem.COPPER_ORE,
            VanillaItem.DEEPSLATE_COPPER_ORE, VanillaItem.CUT_COPPER_SLAB, VanillaItem.CUT_COPPER_STAIRS, VanillaItem.CUT_COPPER, VanillaItem.WEATHERED_COPPER, VanillaItem.WEATHERED_CUT_COPPER_SLAB,
            VanillaItem.WEATHERED_CUT_COPPER_STAIRS, VanillaItem.WEATHERED_CUT_COPPER, VanillaItem.OXIDIZED_COPPER, VanillaItem.OXIDIZED_CUT_COPPER_SLAB, VanillaItem.OXIDIZED_CUT_COPPER_STAIRS,
            VanillaItem.OXIDIZED_CUT_COPPER, VanillaItem.EXPOSED_COPPER, VanillaItem.EXPOSED_CUT_COPPER_SLAB, VanillaItem.EXPOSED_CUT_COPPER_STAIRS, VanillaItem.EXPOSED_CUT_COPPER,
            VanillaItem.WAXED_COPPER_BLOCK, VanillaItem.WAXED_CUT_COPPER_SLAB, VanillaItem.WAXED_CUT_COPPER_STAIRS, VanillaItem.WAXED_CUT_COPPER, VanillaItem.WAXED_WEATHERED_COPPER,
            VanillaItem.WAXED_WEATHERED_CUT_COPPER_SLAB, VanillaItem.WAXED_WEATHERED_CUT_COPPER_STAIRS, VanillaItem.WAXED_WEATHERED_CUT_COPPER, VanillaItem.WAXED_EXPOSED_COPPER,
            VanillaItem.WAXED_EXPOSED_CUT_COPPER_SLAB, VanillaItem.WAXED_EXPOSED_CUT_COPPER_STAIRS, VanillaItem.WAXED_EXPOSED_CUT_COPPER, VanillaItem.WAXED_OXIDIZED_COPPER,
            VanillaItem.WAXED_OXIDIZED_CUT_COPPER_SLAB, VanillaItem.WAXED_OXIDIZED_CUT_COPPER_STAIRS, VanillaItem.WAXED_OXIDIZED_CUT_COPPER, VanillaItem.LIGHTNING_ROD);
    public static final Category<ExItem> REPLACEABLE_PLANTS = new Category<>("replaceable_plants", VanillaItem.GRASS, VanillaItem.FERN, VanillaItem.DEAD_BUSH, VanillaItem.VINE, VanillaItem.GLOW_LICHEN,
            VanillaItem.SUNFLOWER, VanillaItem.LILAC, VanillaItem.ROSE_BUSH, VanillaItem.PEONY, VanillaItem.TALL_GRASS, VanillaItem.LARGE_FERN, VanillaItem.HANGING_ROOTS);
    public static final Category<ExItem> IGNORED_BY_PIGLIN_BABIES = new Category<>("ignored_by_piglin_babies", VanillaItem.LEATHER);
    public static final Category<ExItem> SOUL_SPEED_BLOCKS = new Category<>("soul_speed_blocks", VanillaItem.SOUL_SAND, VanillaItem.SOUL_SOIL);
    public static final Category<ExItem> WART_BLOCKS = new Category<>("wart_blocks", VanillaItem.NETHER_WART_BLOCK, VanillaItem.WARPED_WART_BLOCK);
    public static final Category<ExItem> ANIMALS_SPAWNABLE_ON = new Category<>("animals_spawnable_on", VanillaItem.GRASS_BLOCK);
    public static final Category<ExItem> MUSHROOM_GROW_BLOCK = new Category<>("mushroom_grow_block", VanillaItem.MYCELIUM, VanillaItem.PODZOL, VanillaItem.CRIMSON_NYLIUM, VanillaItem.WARPED_NYLIUM);
    public static final Category<ExItem> CLIMBABLE = new Category<>("climbable", VanillaItem.LADDER, VanillaItem.VINE, VanillaItem.SCAFFOLDING, VanillaItem.WEEPING_VINES,
            VanillaItem.WEEPING_VINES_PLANT, VanillaItem.TWISTING_VINES, VanillaItem.TWISTING_VINES_PLANT, VanillaItem.CAVE_VINES, VanillaItem.CAVE_VINES_PLANT);
    public static final Category<ExItem> WOODEN_DOORS = new Category<>("wooden_doors", VanillaItem.OAK_DOOR, VanillaItem.SPRUCE_DOOR, VanillaItem.BIRCH_DOOR, VanillaItem.JUNGLE_DOOR,
            VanillaItem.ACACIA_DOOR, VanillaItem.DARK_OAK_DOOR, VanillaItem.CRIMSON_DOOR, VanillaItem.WARPED_DOOR);
    public static final Category<ExItem> HOGLIN_REPELLENTS = new Category<>("hoglin_repellents", VanillaItem.WARPED_FUNGUS, VanillaItem.POTTED_WARPED_FUNGUS, VanillaItem.NETHER_PORTAL,
            VanillaItem.RESPAWN_ANCHOR);
    public static final Category<ExItem> STONE_ORE_REPLACEABLES = new Category<>("stone_ore_replaceables", VanillaItem.STONE, VanillaItem.GRANITE, VanillaItem.DIORITE, VanillaItem.ANDESITE);
    public static final Category<ExItem> FEATURES_CANNOT_REPLACE = new Category<>("features_cannot_replace", VanillaItem.BEDROCK, VanillaItem.SPAWNER, VanillaItem.CHEST, VanillaItem.END_PORTAL_FRAME);
    public static final Category<ExItem> INFINIBURN_OVERWORLD = new Category<>("infiniburn_overworld", VanillaItem.NETHERRACK, VanillaItem.MAGMA_BLOCK);
    public static final Category<ExItem> STANDING_SIGNS = new Category<>("standing_signs", VanillaItem.OAK_SIGN, VanillaItem.SPRUCE_SIGN, VanillaItem.BIRCH_SIGN, VanillaItem.ACACIA_SIGN,
            VanillaItem.JUNGLE_SIGN, VanillaItem.DARK_OAK_SIGN, VanillaItem.CRIMSON_SIGN, VanillaItem.WARPED_SIGN);
    public static final Category<ExItem> WARPED_STEMS = new Category<>("warped_stems", VanillaItem.WARPED_STEM, VanillaItem.STRIPPED_WARPED_STEM, VanillaItem.WARPED_HYPHAE,
            VanillaItem.STRIPPED_WARPED_HYPHAE);
    public static final Category<ExItem> MUSIC_DISCS = new Category<>("music_discs", Category.CREEPER_DROP_MUSIC_DISCS, VanillaItem.MUSIC_DISC_PIGSTEP);
    public static final Category<ExItem> FLOWERS = new Category<>("flowers", Category.SMALL_FLOWERS, Category.TALL_FLOWERS, VanillaItem.FLOWERING_AZALEA_LEAVES, VanillaItem.FLOWERING_AZALEA);
    public static final Category<ExItem> SIGNS = new Category<>("signs", Category.STANDING_SIGNS, Category.WALL_SIGNS);
    public static final Category<ExItem> PRESSURE_PLATES = new Category<>("pressure_plates", VanillaItem.LIGHT_WEIGHTED_PRESSURE_PLATE, VanillaItem.HEAVY_WEIGHTED_PRESSURE_PLATE,
            Category.WOODEN_PRESSURE_PLATES, Category.STONE_PRESSURE_PLATES);
    public static final Category<ExItem> MOSS_REPLACEABLE = new Category<>("moss_replaceable", Category.BASE_STONE_OVERWORLD, Category.CAVE_VINES, Category.DIRT);
    public static final Category<ExItem> LOGS_THAT_BURN = new Category<>("logs_that_burn", Category.DARK_OAK_LOGS, Category.OAK_LOGS, Category.ACACIA_LOGS, Category.BIRCH_LOGS, Category.JUNGLE_LOGS,
            Category.SPRUCE_LOGS);
    public static final Category<ExItem> LOGS = new Category<>("logs", Category.LOGS_THAT_BURN, Category.CRIMSON_STEMS, Category.WARPED_STEMS);
    public static final Category<ExItem> AXE = new Category<>("axe", VanillaItem.NOTE_BLOCK, VanillaItem.ATTACHED_MELON_STEM, VanillaItem.ATTACHED_PUMPKIN_STEM, VanillaItem.AZALEA, VanillaItem.BAMBOO,
            VanillaItem.BARREL, VanillaItem.BEE_NEST, VanillaItem.BEEHIVE, VanillaItem.BEETROOTS, VanillaItem.BIG_DRIPLEAF_STEM, VanillaItem.BIG_DRIPLEAF, VanillaItem.BOOKSHELF,
            VanillaItem.BROWN_MUSHROOM_BLOCK, VanillaItem.BROWN_MUSHROOM, VanillaItem.CAMPFIRE, VanillaItem.CARROTS, VanillaItem.CARTOGRAPHY_TABLE, VanillaItem.CARVED_PUMPKIN,
            VanillaItem.CAVE_VINES_PLANT, VanillaItem.CAVE_VINES, VanillaItem.CHEST, VanillaItem.CHORUS_FLOWER, VanillaItem.CHORUS_PLANT, VanillaItem.COCOA, VanillaItem.COMPOSTER,
            VanillaItem.CRAFTING_TABLE, VanillaItem.CRIMSON_FUNGUS, VanillaItem.DAYLIGHT_DETECTOR, VanillaItem.DEAD_BUSH, VanillaItem.FERN, VanillaItem.FLETCHING_TABLE, VanillaItem.GLOW_LICHEN,
            VanillaItem.GRASS, VanillaItem.HANGING_ROOTS, VanillaItem.JACK_O_LANTERN, VanillaItem.JUKEBOX, VanillaItem.LADDER, VanillaItem.LARGE_FERN, VanillaItem.LECTERN, VanillaItem.LILY_PAD,
            VanillaItem.LOOM, VanillaItem.MELON_STEM, VanillaItem.MELON, VanillaItem.MUSHROOM_STEM, VanillaItem.NETHER_WART, VanillaItem.POTATOES, VanillaItem.PUMPKIN_STEM, VanillaItem.PUMPKIN,
            VanillaItem.RED_MUSHROOM_BLOCK, VanillaItem.RED_MUSHROOM, VanillaItem.SCAFFOLDING, VanillaItem.SMALL_DRIPLEAF, VanillaItem.SMITHING_TABLE, VanillaItem.SOUL_CAMPFIRE,
            VanillaItem.SPORE_BLOSSOM, VanillaItem.SUGAR_CANE, VanillaItem.SWEET_BERRY_BUSH, VanillaItem.TALL_GRASS, VanillaItem.TRAPPED_CHEST, VanillaItem.TWISTING_VINES_PLANT,
            VanillaItem.TWISTING_VINES, VanillaItem.VINE, VanillaItem.WARPED_FUNGUS, VanillaItem.WEEPING_VINES_PLANT, VanillaItem.WEEPING_VINES, VanillaItem.WHEAT, Category.BANNERS,
            Category.FENCE_GATES, Category.LOGS, Category.PLANKS, Category.SAPLINGS, Category.SIGNS, Category.WOODEN_BUTTONS, Category.WOODEN_DOORS, Category.WOODEN_FENCES,
            Category.WOODEN_PRESSURE_PLATES, Category.WOODEN_SLABS, Category.WOODEN_STAIRS, Category.WOODEN_TRAPDOORS);
    public static final Category<ExItem> TRAPDOORS = new Category<>("trapdoors", Category.WOODEN_TRAPDOORS, VanillaItem.IRON_TRAPDOOR);
    public static final Category<ExItem> BAMBOO_PLANTABLE_ON = new Category<>("bamboo_plantable_on", Category.SAND, Category.DIRT, VanillaItem.BAMBOO, VanillaItem.BAMBOO_SAPLING, VanillaItem.GRAVEL);
    public static final Category<ExItem> CORALS = new Category<>("corals", Category.CORAL_PLANTS, VanillaItem.TUBE_CORAL_FAN, VanillaItem.BRAIN_CORAL_FAN, VanillaItem.BUBBLE_CORAL_FAN,
            VanillaItem.FIRE_CORAL_FAN, VanillaItem.HORN_CORAL_FAN);
    public static final Category<ExItem> LAVA_POOL_STONE_CANNOT_REPLACE = new Category<>("lava_pool_stone_cannot_replace", Category.FEATURES_CANNOT_REPLACE, Category.LEAVES, Category.LOGS);
    public static final Category<ExItem> DOORS = new Category<>("doors", Category.WOODEN_DOORS, VanillaItem.IRON_DOOR);
    public static final Category<ExItem> WALL_POST_OVERRIDE = new Category<>("wall_post_override", VanillaItem.TORCH, VanillaItem.SOUL_TORCH, VanillaItem.REDSTONE_TORCH, VanillaItem.TRIPWIRE,
            Category.SIGNS, Category.BANNERS, Category.PRESSURE_PLATES);
    public static final Category<ExItem> LUSH_GROUND_REPLACEABLE = new Category<>("lush_ground_replaceable", Category.MOSS_REPLACEABLE, VanillaItem.CLAY, VanillaItem.GRAVEL, VanillaItem.SAND);
    public static final Category<ExItem> DRIPSTONE_REPLACEABLE_BLOCKS = new Category<>("dripstone_replaceable_blocks", Category.BASE_STONE_OVERWORLD, VanillaItem.DIRT);
    public static final Category<ExItem> PIGLIN_LOVED = new Category<>("piglin_loved", Category.GOLD_ORES, VanillaItem.GOLD_BLOCK, VanillaItem.GILDED_BLACKSTONE,
            VanillaItem.LIGHT_WEIGHTED_PRESSURE_PLATE, VanillaItem.GOLD_INGOT, VanillaItem.BELL, VanillaItem.CLOCK, VanillaItem.GOLDEN_CARROT, VanillaItem.GLISTERING_MELON_SLICE,
            VanillaItem.GOLDEN_APPLE, VanillaItem.ENCHANTED_GOLDEN_APPLE, VanillaItem.GOLDEN_HELMET, VanillaItem.GOLDEN_CHESTPLATE, VanillaItem.GOLDEN_LEGGINGS, VanillaItem.GOLDEN_BOOTS,
            VanillaItem.GOLDEN_HORSE_ARMOR, VanillaItem.GOLDEN_SWORD, VanillaItem.GOLDEN_PICKAXE, VanillaItem.GOLDEN_SHOVEL, VanillaItem.GOLDEN_AXE, VanillaItem.GOLDEN_HOE, VanillaItem.RAW_GOLD,
            VanillaItem.RAW_GOLD_BLOCK);
    public static final Category<ExItem> AZALEA_ROOT_REPLACEABLE = new Category<>("azalea_root_replaceable", Category.LUSH_GROUND_REPLACEABLE, Category.TERRACOTTA, VanillaItem.RED_SAND);
    public static final Category<ExItem> BEE_GROWABLES = new Category<>("bee_growables", Category.CROPS, VanillaItem.SWEET_BERRY_BUSH, VanillaItem.CAVE_VINES, VanillaItem.CAVE_VINES_PLANT);
    public static final Category<ExItem> ENDERMAN_HOLDABLE = new Category<>("enderman_holdable", Category.SMALL_FLOWERS, Category.DIRT, VanillaItem.SAND, VanillaItem.RED_SAND, VanillaItem.GRAVEL,
            VanillaItem.BROWN_MUSHROOM, VanillaItem.RED_MUSHROOM, VanillaItem.TNT, VanillaItem.CACTUS, VanillaItem.CLAY, VanillaItem.PUMPKIN, VanillaItem.CARVED_PUMPKIN, VanillaItem.MELON,
            VanillaItem.CRIMSON_FUNGUS, VanillaItem.CRIMSON_NYLIUM, VanillaItem.CRIMSON_ROOTS, VanillaItem.WARPED_FUNGUS, VanillaItem.WARPED_NYLIUM, VanillaItem.WARPED_ROOTS);
    public static final Category<ExItem> INFINIBURN_END = new Category<>("infiniburn_end", Category.INFINIBURN_OVERWORLD, VanillaItem.BEDROCK);
    public static final Category<ExItem> AZALEA_GROWS_ON = new Category<>("azalea_grows_on", Category.DIRT, Category.SAND, Category.TERRACOTTA, VanillaItem.SNOW_BLOCK, VanillaItem.POWDER_SNOW);
    public static final Category<ExItem> GUARDED_BY_PIGLINS = new Category<>("guarded_by_piglins", VanillaItem.GOLD_BLOCK, VanillaItem.BARREL, VanillaItem.CHEST, VanillaItem.ENDER_CHEST,
            VanillaItem.GILDED_BLACKSTONE, VanillaItem.TRAPPED_CHEST, VanillaItem.RAW_GOLD_BLOCK, Category.SHULKER_BOXES, Category.GOLD_ORES);
    public static final Category<ExItem> STAIRS = new Category<>("stairs", Category.WOODEN_STAIRS, VanillaItem.COBBLESTONE_STAIRS, VanillaItem.SANDSTONE_STAIRS, VanillaItem.NETHER_BRICK_STAIRS,
            VanillaItem.STONE_BRICK_STAIRS, VanillaItem.BRICK_STAIRS, VanillaItem.PURPUR_STAIRS, VanillaItem.QUARTZ_STAIRS, VanillaItem.RED_SANDSTONE_STAIRS, VanillaItem.PRISMARINE_BRICK_STAIRS,
            VanillaItem.PRISMARINE_STAIRS, VanillaItem.DARK_PRISMARINE_STAIRS, VanillaItem.POLISHED_GRANITE_STAIRS, VanillaItem.SMOOTH_RED_SANDSTONE_STAIRS, VanillaItem.MOSSY_STONE_BRICK_STAIRS,
            VanillaItem.POLISHED_DIORITE_STAIRS, VanillaItem.MOSSY_COBBLESTONE_STAIRS, VanillaItem.END_STONE_BRICK_STAIRS, VanillaItem.STONE_STAIRS, VanillaItem.SMOOTH_SANDSTONE_STAIRS,
            VanillaItem.SMOOTH_QUARTZ_STAIRS, VanillaItem.GRANITE_STAIRS, VanillaItem.ANDESITE_STAIRS, VanillaItem.RED_NETHER_BRICK_STAIRS, VanillaItem.POLISHED_ANDESITE_STAIRS,
            VanillaItem.DIORITE_STAIRS, VanillaItem.BLACKSTONE_STAIRS, VanillaItem.POLISHED_BLACKSTONE_BRICK_STAIRS, VanillaItem.POLISHED_BLACKSTONE_STAIRS, VanillaItem.COBBLED_DEEPSLATE_STAIRS,
            VanillaItem.POLISHED_DEEPSLATE_STAIRS, VanillaItem.DEEPSLATE_TILE_STAIRS, VanillaItem.DEEPSLATE_BRICK_STAIRS, VanillaItem.OXIDIZED_CUT_COPPER_STAIRS,
            VanillaItem.WEATHERED_CUT_COPPER_STAIRS, VanillaItem.EXPOSED_CUT_COPPER_STAIRS, VanillaItem.CUT_COPPER_STAIRS, VanillaItem.WAXED_WEATHERED_CUT_COPPER_STAIRS,
            VanillaItem.WAXED_EXPOSED_CUT_COPPER_STAIRS, VanillaItem.WAXED_CUT_COPPER_STAIRS, VanillaItem.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
    public static final Category<ExItem> OCCLUDES_VIBRATION_SIGNALS = new Category<>("occludes_vibration_signals", Category.WOOL);
    public static final Category<ExItem> INFINIBURN_NETHER = new Category<>("infiniburn_nether", Category.INFINIBURN_OVERWORLD);
    public static final Category<ExItem> FENCES = new Category<>("fences", Category.WOODEN_FENCES, VanillaItem.NETHER_BRICK_FENCE);
    public static final Category<ExItem> UNSTABLE_BOTTOM_CENTER = new Category<>("unstable_bottom_center", Category.FENCE_GATES);
    public static final Category<ExItem> PICKAXE = new Category<>("pickaxe", VanillaItem.STONE, VanillaItem.GRANITE, VanillaItem.POLISHED_GRANITE, VanillaItem.DIORITE, VanillaItem.POLISHED_DIORITE,
            VanillaItem.ANDESITE, VanillaItem.POLISHED_ANDESITE, VanillaItem.COBBLESTONE, VanillaItem.GOLD_ORE, VanillaItem.DEEPSLATE_GOLD_ORE, VanillaItem.IRON_ORE, VanillaItem.DEEPSLATE_IRON_ORE,
            VanillaItem.COAL_ORE, VanillaItem.DEEPSLATE_COAL_ORE, VanillaItem.NETHER_GOLD_ORE, VanillaItem.LAPIS_ORE, VanillaItem.DEEPSLATE_LAPIS_ORE, VanillaItem.LAPIS_BLOCK, VanillaItem.DISPENSER,
            VanillaItem.SANDSTONE, VanillaItem.CHISELED_SANDSTONE, VanillaItem.CUT_SANDSTONE, VanillaItem.GOLD_BLOCK, VanillaItem.IRON_BLOCK, VanillaItem.BRICKS, VanillaItem.MOSSY_COBBLESTONE,
            VanillaItem.OBSIDIAN, VanillaItem.SPAWNER, VanillaItem.DIAMOND_ORE, VanillaItem.DEEPSLATE_DIAMOND_ORE, VanillaItem.DIAMOND_BLOCK, VanillaItem.FURNACE, VanillaItem.COBBLESTONE_STAIRS,
            VanillaItem.STONE_PRESSURE_PLATE, VanillaItem.IRON_DOOR, VanillaItem.REDSTONE_ORE, VanillaItem.DEEPSLATE_REDSTONE_ORE, VanillaItem.NETHERRACK, VanillaItem.BASALT,
            VanillaItem.POLISHED_BASALT, VanillaItem.STONE_BRICKS, VanillaItem.MOSSY_STONE_BRICKS, VanillaItem.CRACKED_STONE_BRICKS, VanillaItem.CHISELED_STONE_BRICKS, VanillaItem.IRON_BARS,
            VanillaItem.CHAIN, VanillaItem.BRICK_STAIRS, VanillaItem.STONE_BRICK_STAIRS, VanillaItem.NETHER_BRICKS, VanillaItem.NETHER_BRICK_FENCE, VanillaItem.NETHER_BRICK_STAIRS,
            VanillaItem.ENCHANTING_TABLE, VanillaItem.BREWING_STAND, VanillaItem.END_STONE, VanillaItem.SANDSTONE_STAIRS, VanillaItem.EMERALD_ORE, VanillaItem.DEEPSLATE_EMERALD_ORE,
            VanillaItem.ENDER_CHEST, VanillaItem.EMERALD_BLOCK, VanillaItem.LIGHT_WEIGHTED_PRESSURE_PLATE, VanillaItem.HEAVY_WEIGHTED_PRESSURE_PLATE, VanillaItem.REDSTONE_BLOCK,
            VanillaItem.NETHER_QUARTZ_ORE, VanillaItem.HOPPER, VanillaItem.QUARTZ_BLOCK, VanillaItem.CHISELED_QUARTZ_BLOCK, VanillaItem.QUARTZ_PILLAR, VanillaItem.QUARTZ_STAIRS, VanillaItem.DROPPER,
            VanillaItem.WHITE_TERRACOTTA, VanillaItem.ORANGE_TERRACOTTA, VanillaItem.MAGENTA_TERRACOTTA, VanillaItem.LIGHT_BLUE_TERRACOTTA, VanillaItem.YELLOW_TERRACOTTA, VanillaItem.LIME_TERRACOTTA,
            VanillaItem.PINK_TERRACOTTA, VanillaItem.GRAY_TERRACOTTA, VanillaItem.LIGHT_GRAY_TERRACOTTA, VanillaItem.CYAN_TERRACOTTA, VanillaItem.PURPLE_TERRACOTTA, VanillaItem.BLUE_TERRACOTTA,
            VanillaItem.BROWN_TERRACOTTA, VanillaItem.GREEN_TERRACOTTA, VanillaItem.RED_TERRACOTTA, VanillaItem.BLACK_TERRACOTTA, VanillaItem.IRON_TRAPDOOR, VanillaItem.PRISMARINE,
            VanillaItem.PRISMARINE_BRICKS, VanillaItem.DARK_PRISMARINE, VanillaItem.PRISMARINE_STAIRS, VanillaItem.PRISMARINE_BRICK_STAIRS, VanillaItem.DARK_PRISMARINE_STAIRS,
            VanillaItem.PRISMARINE_SLAB, VanillaItem.PRISMARINE_BRICK_SLAB, VanillaItem.DARK_PRISMARINE_SLAB, VanillaItem.TERRACOTTA, VanillaItem.COAL_BLOCK, VanillaItem.RED_SANDSTONE,
            VanillaItem.CHISELED_RED_SANDSTONE, VanillaItem.CUT_RED_SANDSTONE, VanillaItem.RED_SANDSTONE_STAIRS, VanillaItem.STONE_SLAB, VanillaItem.SMOOTH_STONE_SLAB, VanillaItem.SANDSTONE_SLAB,
            VanillaItem.CUT_SANDSTONE_SLAB, VanillaItem.PETRIFIED_OAK_SLAB, VanillaItem.COBBLESTONE_SLAB, VanillaItem.BRICK_SLAB, VanillaItem.STONE_BRICK_SLAB, VanillaItem.NETHER_BRICK_SLAB,
            VanillaItem.QUARTZ_SLAB, VanillaItem.RED_SANDSTONE_SLAB, VanillaItem.CUT_RED_SANDSTONE_SLAB, VanillaItem.PURPUR_SLAB, VanillaItem.SMOOTH_STONE, VanillaItem.SMOOTH_SANDSTONE,
            VanillaItem.SMOOTH_QUARTZ, VanillaItem.SMOOTH_RED_SANDSTONE, VanillaItem.PURPUR_BLOCK, VanillaItem.PURPUR_PILLAR, VanillaItem.PURPUR_STAIRS, VanillaItem.END_STONE_BRICKS,
            VanillaItem.MAGMA_BLOCK, VanillaItem.RED_NETHER_BRICKS, VanillaItem.BONE_BLOCK, VanillaItem.OBSERVER, VanillaItem.WHITE_GLAZED_TERRACOTTA, VanillaItem.ORANGE_GLAZED_TERRACOTTA,
            VanillaItem.MAGENTA_GLAZED_TERRACOTTA, VanillaItem.LIGHT_BLUE_GLAZED_TERRACOTTA, VanillaItem.YELLOW_GLAZED_TERRACOTTA, VanillaItem.LIME_GLAZED_TERRACOTTA,
            VanillaItem.PINK_GLAZED_TERRACOTTA, VanillaItem.GRAY_GLAZED_TERRACOTTA, VanillaItem.LIGHT_GRAY_GLAZED_TERRACOTTA, VanillaItem.CYAN_GLAZED_TERRACOTTA, VanillaItem.PURPLE_GLAZED_TERRACOTTA,
            VanillaItem.BLUE_GLAZED_TERRACOTTA, VanillaItem.BROWN_GLAZED_TERRACOTTA, VanillaItem.GREEN_GLAZED_TERRACOTTA, VanillaItem.RED_GLAZED_TERRACOTTA, VanillaItem.BLACK_GLAZED_TERRACOTTA,
            VanillaItem.WHITE_CONCRETE, VanillaItem.ORANGE_CONCRETE, VanillaItem.MAGENTA_CONCRETE, VanillaItem.LIGHT_BLUE_CONCRETE, VanillaItem.YELLOW_CONCRETE, VanillaItem.LIME_CONCRETE,
            VanillaItem.PINK_CONCRETE, VanillaItem.GRAY_CONCRETE, VanillaItem.LIGHT_GRAY_CONCRETE, VanillaItem.CYAN_CONCRETE, VanillaItem.PURPLE_CONCRETE, VanillaItem.BLUE_CONCRETE,
            VanillaItem.BROWN_CONCRETE, VanillaItem.GREEN_CONCRETE, VanillaItem.RED_CONCRETE, VanillaItem.BLACK_CONCRETE, VanillaItem.DEAD_TUBE_CORAL_BLOCK, VanillaItem.DEAD_BRAIN_CORAL_BLOCK,
            VanillaItem.DEAD_BUBBLE_CORAL_BLOCK, VanillaItem.DEAD_FIRE_CORAL_BLOCK, VanillaItem.DEAD_HORN_CORAL_BLOCK, VanillaItem.TUBE_CORAL_BLOCK, VanillaItem.BRAIN_CORAL_BLOCK,
            VanillaItem.BUBBLE_CORAL_BLOCK, VanillaItem.FIRE_CORAL_BLOCK, VanillaItem.HORN_CORAL_BLOCK, VanillaItem.DEAD_TUBE_CORAL, VanillaItem.DEAD_BRAIN_CORAL, VanillaItem.DEAD_BUBBLE_CORAL,
            VanillaItem.DEAD_FIRE_CORAL, VanillaItem.DEAD_HORN_CORAL, VanillaItem.DEAD_TUBE_CORAL_FAN, VanillaItem.DEAD_BRAIN_CORAL_FAN, VanillaItem.DEAD_BUBBLE_CORAL_FAN,
            VanillaItem.DEAD_FIRE_CORAL_FAN, VanillaItem.DEAD_HORN_CORAL_FAN, VanillaItem.DEAD_TUBE_CORAL_WALL_FAN, VanillaItem.DEAD_BRAIN_CORAL_WALL_FAN, VanillaItem.DEAD_BUBBLE_CORAL_WALL_FAN,
            VanillaItem.DEAD_FIRE_CORAL_WALL_FAN, VanillaItem.DEAD_HORN_CORAL_WALL_FAN, VanillaItem.POLISHED_GRANITE_STAIRS, VanillaItem.SMOOTH_RED_SANDSTONE_STAIRS,
            VanillaItem.MOSSY_STONE_BRICK_STAIRS, VanillaItem.POLISHED_DIORITE_STAIRS, VanillaItem.MOSSY_COBBLESTONE_STAIRS, VanillaItem.END_STONE_BRICK_STAIRS, VanillaItem.STONE_STAIRS,
            VanillaItem.SMOOTH_SANDSTONE_STAIRS, VanillaItem.SMOOTH_QUARTZ_STAIRS, VanillaItem.GRANITE_STAIRS, VanillaItem.ANDESITE_STAIRS, VanillaItem.RED_NETHER_BRICK_STAIRS,
            VanillaItem.POLISHED_ANDESITE_STAIRS, VanillaItem.DIORITE_STAIRS, VanillaItem.POLISHED_GRANITE_SLAB, VanillaItem.SMOOTH_RED_SANDSTONE_SLAB, VanillaItem.MOSSY_STONE_BRICK_SLAB,
            VanillaItem.POLISHED_DIORITE_SLAB, VanillaItem.MOSSY_COBBLESTONE_SLAB, VanillaItem.END_STONE_BRICK_SLAB, VanillaItem.SMOOTH_SANDSTONE_SLAB, VanillaItem.SMOOTH_QUARTZ_SLAB,
            VanillaItem.GRANITE_SLAB, VanillaItem.ANDESITE_SLAB, VanillaItem.RED_NETHER_BRICK_SLAB, VanillaItem.POLISHED_ANDESITE_SLAB, VanillaItem.DIORITE_SLAB, VanillaItem.SMOKER,
            VanillaItem.BLAST_FURNACE, VanillaItem.GRINDSTONE, VanillaItem.STONECUTTER, VanillaItem.BELL, VanillaItem.LANTERN, VanillaItem.SOUL_LANTERN, VanillaItem.WARPED_NYLIUM,
            VanillaItem.CRIMSON_NYLIUM, VanillaItem.NETHERITE_BLOCK, VanillaItem.ANCIENT_DEBRIS, VanillaItem.CRYING_OBSIDIAN, VanillaItem.RESPAWN_ANCHOR, VanillaItem.LODESTONE, VanillaItem.BLACKSTONE,
            VanillaItem.BLACKSTONE_STAIRS, VanillaItem.BLACKSTONE_SLAB, VanillaItem.POLISHED_BLACKSTONE, VanillaItem.POLISHED_BLACKSTONE_BRICKS, VanillaItem.CRACKED_POLISHED_BLACKSTONE_BRICKS,
            VanillaItem.CHISELED_POLISHED_BLACKSTONE, VanillaItem.POLISHED_BLACKSTONE_BRICK_SLAB, VanillaItem.POLISHED_BLACKSTONE_BRICK_STAIRS, VanillaItem.GILDED_BLACKSTONE,
            VanillaItem.POLISHED_BLACKSTONE_STAIRS, VanillaItem.POLISHED_BLACKSTONE_SLAB, VanillaItem.POLISHED_BLACKSTONE_PRESSURE_PLATE, VanillaItem.CHISELED_NETHER_BRICKS,
            VanillaItem.CRACKED_NETHER_BRICKS, VanillaItem.QUARTZ_BRICKS, VanillaItem.TUFF, VanillaItem.CALCITE, VanillaItem.OXIDIZED_COPPER, VanillaItem.WEATHERED_COPPER, VanillaItem.EXPOSED_COPPER,
            VanillaItem.COPPER_BLOCK, VanillaItem.COPPER_ORE, VanillaItem.DEEPSLATE_COPPER_ORE, VanillaItem.OXIDIZED_CUT_COPPER, VanillaItem.WEATHERED_CUT_COPPER, VanillaItem.EXPOSED_CUT_COPPER,
            VanillaItem.CUT_COPPER, VanillaItem.OXIDIZED_CUT_COPPER_STAIRS, VanillaItem.WEATHERED_CUT_COPPER_STAIRS, VanillaItem.EXPOSED_CUT_COPPER_STAIRS, VanillaItem.CUT_COPPER_STAIRS,
            VanillaItem.OXIDIZED_CUT_COPPER_SLAB, VanillaItem.WEATHERED_CUT_COPPER_SLAB, VanillaItem.EXPOSED_CUT_COPPER_SLAB, VanillaItem.CUT_COPPER_SLAB, VanillaItem.WAXED_COPPER_BLOCK,
            VanillaItem.WAXED_WEATHERED_COPPER, VanillaItem.WAXED_EXPOSED_COPPER, VanillaItem.WAXED_OXIDIZED_COPPER, VanillaItem.WAXED_OXIDIZED_CUT_COPPER, VanillaItem.WAXED_WEATHERED_CUT_COPPER,
            VanillaItem.WAXED_EXPOSED_CUT_COPPER, VanillaItem.WAXED_CUT_COPPER, VanillaItem.WAXED_OXIDIZED_CUT_COPPER_STAIRS, VanillaItem.WAXED_WEATHERED_CUT_COPPER_STAIRS,
            VanillaItem.WAXED_EXPOSED_CUT_COPPER_STAIRS, VanillaItem.WAXED_CUT_COPPER_STAIRS, VanillaItem.WAXED_OXIDIZED_CUT_COPPER_SLAB, VanillaItem.WAXED_WEATHERED_CUT_COPPER_SLAB,
            VanillaItem.WAXED_EXPOSED_CUT_COPPER_SLAB, VanillaItem.WAXED_CUT_COPPER_SLAB, VanillaItem.LIGHTNING_ROD, VanillaItem.POINTED_DRIPSTONE, VanillaItem.DRIPSTONE_BLOCK, VanillaItem.DEEPSLATE,
            VanillaItem.COBBLED_DEEPSLATE, VanillaItem.COBBLED_DEEPSLATE_STAIRS, VanillaItem.COBBLED_DEEPSLATE_SLAB, VanillaItem.POLISHED_DEEPSLATE, VanillaItem.POLISHED_DEEPSLATE_STAIRS,
            VanillaItem.POLISHED_DEEPSLATE_SLAB, VanillaItem.DEEPSLATE_TILES, VanillaItem.DEEPSLATE_TILE_STAIRS, VanillaItem.DEEPSLATE_TILE_SLAB, VanillaItem.DEEPSLATE_BRICKS,
            VanillaItem.DEEPSLATE_BRICK_STAIRS, VanillaItem.DEEPSLATE_BRICK_SLAB, VanillaItem.CHISELED_DEEPSLATE, VanillaItem.CRACKED_DEEPSLATE_BRICKS, VanillaItem.CRACKED_DEEPSLATE_TILES,
            VanillaItem.SMOOTH_BASALT, VanillaItem.RAW_IRON_BLOCK, VanillaItem.RAW_COPPER_BLOCK, VanillaItem.RAW_GOLD_BLOCK, VanillaItem.ICE, VanillaItem.PACKED_ICE, VanillaItem.BLUE_ICE,
            VanillaItem.STONE_BUTTON, VanillaItem.PISTON, VanillaItem.STICKY_PISTON, VanillaItem.PISTON_HEAD, VanillaItem.AMETHYST_CLUSTER, VanillaItem.SMALL_AMETHYST_BUD,
            VanillaItem.MEDIUM_AMETHYST_BUD, VanillaItem.LARGE_AMETHYST_BUD, VanillaItem.AMETHYST_BLOCK, VanillaItem.BUDDING_AMETHYST, VanillaItem.INFESTED_COBBLESTONE,
            VanillaItem.INFESTED_CHISELED_STONE_BRICKS, VanillaItem.INFESTED_CRACKED_STONE_BRICKS, VanillaItem.INFESTED_DEEPSLATE, VanillaItem.INFESTED_STONE, VanillaItem.INFESTED_MOSSY_STONE_BRICKS,
            VanillaItem.INFESTED_STONE_BRICKS, Category.WALLS, Category.SHULKER_BOXES, Category.ANVIL, Category.CAULDRONS, Category.RAILS, VanillaItem.CONDUIT);
    public static final Category<ExItem> UNDERWATER_BONEMEALS = new Category<>("underwater_bonemeals", VanillaItem.SEAGRASS, Category.CORALS, Category.WALL_CORALS);
    public static final Category<ExItem> BIG_DRIPLEAF_PLACEABLE = new Category<>("big_dripleaf_placeable", Category.SMALL_DRIPLEAF_PLACEABLE, Category.DIRT, VanillaItem.FARMLAND);
    public static final Category<ExItem> PARROTS_SPAWNABLE_ON = new Category<>("parrots_spawnable_on", VanillaItem.GRASS_BLOCK, VanillaItem.AIR, Category.LEAVES, Category.LOGS);
    public static final Category<ExItem> BUTTONS = new Category<>("buttons", Category.WOODEN_BUTTONS, VanillaItem.STONE_BUTTON, VanillaItem.POLISHED_BLACKSTONE_BUTTON);
    public static final Category<ExItem> PREVENT_MOB_SPAWNING_INSIDE = new Category<>("prevent_mob_spawning_inside", Category.RAILS);
    public static final Category<ExItem> SLABS = new Category<>("slabs", Category.WOODEN_SLABS, VanillaItem.STONE_SLAB, VanillaItem.SMOOTH_STONE_SLAB, VanillaItem.STONE_BRICK_SLAB,
            VanillaItem.SANDSTONE_SLAB, VanillaItem.PURPUR_SLAB, VanillaItem.QUARTZ_SLAB, VanillaItem.RED_SANDSTONE_SLAB, VanillaItem.BRICK_SLAB, VanillaItem.COBBLESTONE_SLAB,
            VanillaItem.NETHER_BRICK_SLAB, VanillaItem.PETRIFIED_OAK_SLAB, VanillaItem.PRISMARINE_SLAB, VanillaItem.PRISMARINE_BRICK_SLAB, VanillaItem.DARK_PRISMARINE_SLAB,
            VanillaItem.POLISHED_GRANITE_SLAB, VanillaItem.SMOOTH_RED_SANDSTONE_SLAB, VanillaItem.MOSSY_STONE_BRICK_SLAB, VanillaItem.POLISHED_DIORITE_SLAB, VanillaItem.MOSSY_COBBLESTONE_SLAB,
            VanillaItem.END_STONE_BRICK_SLAB, VanillaItem.SMOOTH_SANDSTONE_SLAB, VanillaItem.SMOOTH_QUARTZ_SLAB, VanillaItem.GRANITE_SLAB, VanillaItem.ANDESITE_SLAB, VanillaItem.RED_NETHER_BRICK_SLAB,
            VanillaItem.POLISHED_ANDESITE_SLAB, VanillaItem.DIORITE_SLAB, VanillaItem.CUT_SANDSTONE_SLAB, VanillaItem.CUT_RED_SANDSTONE_SLAB, VanillaItem.BLACKSTONE_SLAB,
            VanillaItem.POLISHED_BLACKSTONE_BRICK_SLAB, VanillaItem.POLISHED_BLACKSTONE_SLAB, VanillaItem.COBBLED_DEEPSLATE_SLAB, VanillaItem.POLISHED_DEEPSLATE_SLAB, VanillaItem.DEEPSLATE_TILE_SLAB,
            VanillaItem.DEEPSLATE_BRICK_SLAB, VanillaItem.WAXED_WEATHERED_CUT_COPPER_SLAB, VanillaItem.WAXED_EXPOSED_CUT_COPPER_SLAB, VanillaItem.WAXED_CUT_COPPER_SLAB,
            VanillaItem.OXIDIZED_CUT_COPPER_SLAB, VanillaItem.WEATHERED_CUT_COPPER_SLAB, VanillaItem.EXPOSED_CUT_COPPER_SLAB, VanillaItem.CUT_COPPER_SLAB, VanillaItem.WAXED_OXIDIZED_CUT_COPPER_SLAB);

    public static final Category<ExMob> FREEZE_IMMUNE_ENTITY_TYPES = new Category<>("freeze_immune_entity_types", VanillaMob.STRAY, VanillaMob.POLAR_BEAR, VanillaMob.SNOW_GOLEM, VanillaMob.WITHER);
    public static final Category<ExMob> SKELETONS = new Category<>("skeletons", VanillaMob.SKELETON, VanillaMob.STRAY, VanillaMob.WITHER_SKELETON);
    public static final Category<ExMob> AXOLOTL_HUNT_TARGETS = new Category<>("axolotl_hunt_targets", VanillaMob.TROPICAL_FISH, VanillaMob.PUFFERFISH, VanillaMob.SALMON, VanillaMob.COD,
            VanillaMob.SQUID, VanillaMob.GLOW_SQUID);
    public static final Category<ExMob> POWDER_SNOW_WALKABLE_MOBS = new Category<>("powder_snow_walkable_mobs", VanillaMob.RABBIT, VanillaMob.ENDERMITE, VanillaMob.SILVERFISH, VanillaMob.FOX);
    // Vanilla tag is called "arrows"
    public static final Category<ExMob> ARROW_MOBS = new Category<>("arrow_mobs", VanillaMob.ARROW, VanillaMob.SPECTRAL_ARROW);
    public static final Category<ExMob> BEEHIVE_INHABITORS = new Category<>("beehive_inhabitors", VanillaMob.BEE);
    public static final Category<ExMob> AXOLOTL_ALWAYS_HOSTILES = new Category<>("axolotl_always_hostiles", VanillaMob.DROWNED, VanillaMob.GUARDIAN, VanillaMob.ELDER_GUARDIAN);
    public static final Category<ExMob> FREEZE_HURTS_EXTRA_TYPES = new Category<>("freeze_hurts_extra_types", VanillaMob.STRIDER, VanillaMob.BLAZE, VanillaMob.MAGMA_CUBE);
    public static final Category<ExMob> RAIDERS = new Category<>("raiders", VanillaMob.EVOKER, VanillaMob.PILLAGER, VanillaMob.RAVAGER, VanillaMob.VINDICATOR, VanillaMob.ILLUSIONER, VanillaMob.WITCH);
    public static final Category<ExMob> IMPACT_PROJECTILES = new Category<>("impact_projectiles", Category.ARROW_MOBS, VanillaMob.SNOWBALL, VanillaMob.FIREBALL, VanillaMob.SMALL_FIREBALL,
            VanillaMob.EGG, VanillaMob.TRIDENT, VanillaMob.DRAGON_FIREBALL, VanillaMob.WITHER_SKULL);

    // Caliburn
    public static final Category<ExItem> CHESTS = new Category<>("chests", VanillaItem.CHEST, VanillaItem.TRAPPED_CHEST, VanillaItem.ENDER_CHEST, Category.SHULKER_BOXES);

    static {
        if (System.getProperty("XLDevMode") != null) {
            String msg = "&c[ERROR] One or more categories contain an erroneous element:";
            StringBuilder sb = new StringBuilder(msg);
            for (Field constant : Category.class.getFields()) {
                try {
                    Object category = constant.get(null);
                    if (category instanceof Category) {
                        confirmCorrect(((Category) category), sb);
                    }
                } catch (IllegalArgumentException | IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }
            if (sb.length() > msg.length()) {
                MessageUtil.log(sb.toString());
            }
        }
    }

    private static void confirmCorrect(Category category, StringBuilder sb) {
        boolean correct = true, first = true, item = true;
        for (Object element : category.getElements()) {
            if (element != null && first) {
                item = element instanceof ExItem;
                first = false;
            }

            if (element == null || !(element instanceof Category) && ((item && !(element instanceof ExItem)) || (item && element instanceof ExMob))) {
                if (correct) {
                    if (sb.length() > 62) {
                        sb.append(";");
                    }
                    sb.append(" ").append(category.getId()).append(": ");
                    correct = false;
                } else {
                    sb.append(",");
                }
                sb.append(element);
            }
        }
    }

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
     * Returns the objects that belong to this category.
     *
     * @return the objects that belong to this category
     */
    public Set<T> getElements() {
        return elements;
    }

    /**
     * Returns if this category contains the given value.
     *
     * @param t an object of the category type
     * @return if this category contains the given value
     */
    public boolean contains(T t) {
        if (t == null) {
            return false;
        }
        return elements.contains(t);
    }

    /**
     * Returns if this category ontains the {@link VanillaItem} that wraps the given material.
     *
     * @param material the material
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
     * Returns if this category contains a {@link VanillaItem} that wraps the material of this block.
     *
     * @param block a block
     * @return if this category contains an {@link VanillaItem} that wraps the material of this block
     */
    public boolean containsBlock(Block block) {
        if (block == null) {
            return false;
        }
        return containsMaterial(block.getType());
    }

    /**
     * Returns if this category contains the {@link ExItem} of the given ItemStack.
     *
     * @param item an item stack
     * @return if this category contains the {@link ExItem} of the given ItemStack
     */
    public boolean containsItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        try {
            return contains((T) api.getExItem(item));
        } catch (ClassCastException exception) {
            return false;
        }
    }

}
