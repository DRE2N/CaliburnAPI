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
package de.erethon.caliburn.item;

import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.compatibility.Version;
import static de.erethon.commons.compatibility.Version.*;
import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a vanilla item.
 * As of 1.13, a Material enum value is considered one vanilla item.
 * Before 1.13, each data value of the same material is considered an item if it is not a damage value.
 *
 * @author Daniel Saukel
 */
public class VanillaItem extends ExItem {

    // BLOCKS
    public static final VanillaItem AIR = new VanillaItem(MC1_8, "AIR", "AIR", 0);
    public static final VanillaItem STONE = new VanillaItem(MC1_8, "STONE", "STONE", 1);
    public static final VanillaItem GRANITE = new VanillaItem(MC1_8, "STONE", "GRANITE", 1, (short) 1);
    public static final VanillaItem POLISHED_GRANITE = new VanillaItem(MC1_8, "STONE", "POLISHED_GRANITE", 1, (short) 2);
    public static final VanillaItem DIORITE = new VanillaItem(MC1_8, "STONE", "DIORITE", 1, (short) 3);
    public static final VanillaItem POLISHED_DIORITE = new VanillaItem(MC1_8, "STONE", "POLISHED_DIORITE", 1, (short) 4);
    public static final VanillaItem ANDESITE = new VanillaItem(MC1_8, "STONE", "ANDESITE", 1, (short) 5);
    public static final VanillaItem POLISHED_ANDESITE = new VanillaItem(MC1_8, "STONE", "POLISHED_ANDESITE", 1, (short) 6);
    public static final VanillaItem GRASS_BLOCK = new VanillaItem(MC1_8, "GRASS", "GRASS_BLOCK", 2);
    public static final VanillaItem DIRT = new VanillaItem(MC1_8, "DIRT", "DIRT", 3);
    public static final VanillaItem COARSE_DIRT = new VanillaItem(MC1_8, "DIRT", "COARSE_DIRT", 3, (short) 1);
    public static final VanillaItem PODZOL = new VanillaItem(MC1_8, "DIRT", "PODZOL", 3, (short) 2);
    public static final VanillaItem COBBLESTONE = new VanillaItem(MC1_8, "COBBLESTONE", "COBBLESTONE", 4);
    public static final VanillaItem OAK_PLANKS = new VanillaItem(MC1_8, "WOOD", "OAK_PLANKS", 5);
    public static final VanillaItem SPRUCE_PLANKS = new VanillaItem(MC1_8, "WOOD", "SPRUCE_PLANKS", 5, (short) 1);
    public static final VanillaItem BIRCH_PLANKS = new VanillaItem(MC1_8, "WOOD", "BIRCH_PLANKS", 5, (short) 2);
    public static final VanillaItem JUNGLE_PLANKS = new VanillaItem(MC1_8, "WOOD", "JUNGLE_PLANKS", 5, (short) 3);
    public static final VanillaItem ACACIA_PLANKS = new VanillaItem(MC1_8, "WOOD", "ACACIA_PLANKS", 5, (short) 4);
    public static final VanillaItem DARK_OAK_PLANKS = new VanillaItem(MC1_8, "WOOD", "DARK_OAK_PLANKS", 5, (short) 5);
    public static final VanillaItem OAK_SAPLING = new VanillaItem(MC1_8, "SAPLING", "OAK_SAPLING", 6);
    public static final VanillaItem SPRUCE_SAPLING = new VanillaItem(MC1_8, "SAPLING", "SPRUCE_SAPLING", 6, (short) 1);
    public static final VanillaItem BIRCH_SAPLING = new VanillaItem(MC1_8, "SAPLING", "BIRCH_SAPLING", 6, (short) 2);
    public static final VanillaItem JUNGLE_SAPLING = new VanillaItem(MC1_8, "SAPLING", "JUNGLE_SAPLING", 6, (short) 3);
    public static final VanillaItem ACACIA_SAPLING = new VanillaItem(MC1_8, "SAPLING", "ACACIA_SAPLING", 6, (short) 4);
    public static final VanillaItem DARK_OAK_SAPLING = new VanillaItem(MC1_8, "SAPLING", "DARK_OAK_SAPLING", 6, (short) 5);
    public static final VanillaItem BEDROCK = new VanillaItem(MC1_8, "BEDROCK", "BEDROCK", 7);
    public static final VanillaItem FLOWING_WATER = new VanillaItem(MC1_8, "WATER", "FLOWING_WATER", 8);
    public static final VanillaItem WATER = new VanillaItem(MC1_8, "STATIONARY_WATER", "WATER", 9);
    public static final VanillaItem FLOWING_LAVA = new VanillaItem(MC1_8, "LAVA", "FLOWING_LAVA", 10);
    public static final VanillaItem LAVA = new VanillaItem(MC1_8, "STATIONARY_LAVA", "LAVA", 11);
    public static final VanillaItem SAND = new VanillaItem(MC1_8, "SAND", "SAND", 12);
    public static final VanillaItem RED_SAND = new VanillaItem(MC1_8, "SAND", "RED_SAND", 12, (short) 1);
    public static final VanillaItem GRAVEL = new VanillaItem(MC1_8, "GRAVEL", "GRAVEL", 13);
    public static final VanillaItem GOLD_ORE = new VanillaItem(MC1_8, "GOLD_ORE", "GOLD_ORE", 14);
    public static final VanillaItem IRON_ORE = new VanillaItem(MC1_8, "IRON_ORE", "IRON_ORE", 15);
    public static final VanillaItem COAL_ORE = new VanillaItem(MC1_8, "COAL_ORE", "COAL_ORE", 16);
    public static final VanillaItem OAK_LOG = new VanillaItem(MC1_8, "LOG", "OAK_LOG", 17);
    public static final VanillaItem SPRUCE_LOG = new VanillaItem(MC1_8, "LOG", "SPRUCE_LOG", 17, (short) 1);
    public static final VanillaItem BIRCH_LOG = new VanillaItem(MC1_8, "LOG", "BIRCH_LOG", 17, (short) 2);
    public static final VanillaItem JUNGLE_LOG = new VanillaItem(MC1_8, "LOG", "JUNGLE_LOG", 17, (short) 3);
    public static final VanillaItem OAK_LEAVES = new VanillaItem(MC1_8, "LEAVES", "OAK_LEAVES", 18);
    public static final VanillaItem SPRUCE_LEAVES = new VanillaItem(MC1_8, "LEAVES", "SPRUCE_LEAVES", 18, (short) 1);
    public static final VanillaItem BIRCH_LEAVES = new VanillaItem(MC1_8, "LEAVES", "BIRCH_LEAVES", 18, (short) 2);
    public static final VanillaItem JUNGLE_LEAVES = new VanillaItem(MC1_8, "LEAVES", "JUNGLE_LEAVES", 18, (short) 3);
    public static final VanillaItem SPONGE = new VanillaItem(MC1_8, "SPONGE", "SPONGE", 19);
    public static final VanillaItem WET_SPONGE = new VanillaItem(MC1_8, "SPONGE", "WET_SPONGE", 19, (short) 1);
    public static final VanillaItem GLASS = new VanillaItem(MC1_8, "GLASS", "GLASS", 20);
    public static final VanillaItem LAPIS_ORE = new VanillaItem(MC1_8, "LAPIS_ORE", "LAPIS_ORE", 21);
    public static final VanillaItem LAPIS_BLOCK = new VanillaItem(MC1_8, "LAPIS_BLOCK", "LAPIS_BLOCK", 22);
    public static final VanillaItem DISPENSER = new VanillaItem(MC1_8, "DISPENSER", "DISPENSER", 23);
    public static final VanillaItem SANDSTONE = new VanillaItem(MC1_8, "SANDSTONE", "SANDSTONE", 24);
    public static final VanillaItem CHISELED_SANDSTONE = new VanillaItem(MC1_8, "SANDSTONE", "CHISELED_SANDSTONE", 24, (short) 1);
    public static final VanillaItem CUT_SANDSTONE = new VanillaItem(MC1_8, "SANDSTONE", "CUT_SANDSTONE", 24, (short) 2);
    // public static final VanillaItem SMOOTH_SANDSTONE = new VanillaItem(MC1_13, "SMOOTH_SANDSTONE");
    public static final VanillaItem NOTE_BLOCK = new VanillaItem(MC1_8, "NOTE_BLOCK", "NOTE_BLOCK", 25);
    public static final VanillaItem POWERED_RAIL = new VanillaItem(MC1_8, "POWERED_RAIL", "POWERED_RAIL", 27);
    public static final VanillaItem DETECTOR_RAIL = new VanillaItem(MC1_8, "DETECTOR_RAIL", "DETECTOR_RAIL", 28);
    public static final VanillaItem STICKY_PISTON = new VanillaItem(MC1_8, "PISTON_STICKY_BASE", "STICKY_PISTON", 29);
    public static final VanillaItem COBWEB = new VanillaItem(MC1_8, "WEB", "COBWEB", 30);
    public static final VanillaItem UNUSED_DEAD_BUSH = new VanillaItem(MC1_8, MC1_12_2, "LONG_GRASS", "DEAD_BUSH", 31);
    public static final VanillaItem GRASS = new VanillaItem(MC1_8, "LONG_GRASS", "GRASS", 31, (short) 1);
    public static final VanillaItem FERN = new VanillaItem(MC1_8, "LONG_GRASS", "FERN", 31, (short) 2);
    public static final VanillaItem DEAD_BUSH = new VanillaItem(MC1_8, "DEAD_BUSH", "DEAD_BUSH", 32);
    public static final VanillaItem PISTON = new VanillaItem(MC1_8, "PISTON_BASE", "PISTON", 33);
    public static final VanillaItem PISTON_HEAD = new VanillaItem(MC1_8, "PISTON_EXTENSION", "PISTON_HEAD", 34);
    public static final VanillaItem WHITE_WOOL = new VanillaItem(MC1_8, "WOOL", "WHITE_WOOL", 35);
    public static final VanillaItem ORANGE_WOOL = new VanillaItem(MC1_8, "WOOL", "ORANGE_WOOL", 35, (short) 1);
    public static final VanillaItem MAGENTA_WOOL = new VanillaItem(MC1_8, "WOOL", "MAGENTA_WOOL", 35, (short) 2);
    public static final VanillaItem LIGHT_BLUE_WOOL = new VanillaItem(MC1_8, "WOOL", "LIGHT_BLUE_WOOL", 35, (short) 3);
    public static final VanillaItem YELLOW_WOOL = new VanillaItem(MC1_8, "WOOL", "YELLOW_WOOL", 35, (short) 4);
    public static final VanillaItem LIME_WOOL = new VanillaItem(MC1_8, "WOOL", "LIME_WOOL", 35, (short) 5);
    public static final VanillaItem PINK_WOOL = new VanillaItem(MC1_8, "WOOL", "PINK_WOOL", 35, (short) 6);
    public static final VanillaItem GRAY_WOOL = new VanillaItem(MC1_8, "WOOL", "GRAY_WOOL", 35, (short) 7);
    public static final VanillaItem LIGHT_GRAY_WOOL = new VanillaItem(MC1_8, "WOOL", "LIGHT_GRAY_WOOL", 35, (short) 8);
    public static final VanillaItem CYAN_WOOL = new VanillaItem(MC1_8, "WOOL", "CYAN_WOOL", 35, (short) 9);
    public static final VanillaItem PURPLE_WOOL = new VanillaItem(MC1_8, "WOOL", "PURPLE_WOOL", 35, (short) 10);
    public static final VanillaItem BLUE_WOOL = new VanillaItem(MC1_8, "WOOL", "BLUE_WOOL", 35, (short) 11);
    public static final VanillaItem BROWN_WOOL = new VanillaItem(MC1_8, "WOOL", "BROWN_WOOL", 35, (short) 12);
    public static final VanillaItem GREEN_WOOL = new VanillaItem(MC1_8, "WOOL", "GREEN_WOOL", 35, (short) 13);
    public static final VanillaItem RED_WOOL = new VanillaItem(MC1_8, "WOOL", "RED_WOOL", 35, (short) 14);
    public static final VanillaItem BLACK_WOOL = new VanillaItem(MC1_8, "WOOL", "BLACK_WOOL", 35, (short) 15);
    public static final VanillaItem MOVING_PISTON = new VanillaItem(MC1_8, "PISTON_MOVING_PIECE", "MOVING_PISTON", 36);
    public static final VanillaItem DANDELION = new VanillaItem(MC1_8, "YELLOW_FLOWER", "DANDELION", 37);
    public static final VanillaItem POPPY = new VanillaItem(MC1_8, "RED_ROSE", "POPPY", 38);
    public static final VanillaItem BLUE_ORCHID = new VanillaItem(MC1_8, "RED_ROSE", "BLUE_ORCHID", 38, (short) 1);
    public static final VanillaItem ALLIUM = new VanillaItem(MC1_8, "RED_ROSE", "ALLIUM", 38, (short) 2);
    public static final VanillaItem AZURE_BLUET = new VanillaItem(MC1_8, "RED_ROSE", "AZURE_BLUET", 38, (short) 3);
    public static final VanillaItem RED_TULIP = new VanillaItem(MC1_8, "RED_ROSE", "RED_TULIP", 38, (short) 4);
    public static final VanillaItem ORANGE_TULIP = new VanillaItem(MC1_8, "RED_ROSE", "ORANGE_TULIP", 38, (short) 5);
    public static final VanillaItem WHITE_TULIP = new VanillaItem(MC1_8, "RED_ROSE", "WHITE_TULIP", 38, (short) 6);
    public static final VanillaItem PINK_TULIP = new VanillaItem(MC1_8, "RED_ROSE", "PINK_TULIP", 38, (short) 7);
    public static final VanillaItem OXEYE_DAISY = new VanillaItem(MC1_8, "RED_ROSE", "OXEYE_DAISY", 38, (short) 8);
    public static final VanillaItem BROWN_MUSHROOM = new VanillaItem(MC1_8, "BROWN_MUSHROOM", "BROWN_MUSHROOM", 39);
    public static final VanillaItem RED_MUSHROOM = new VanillaItem(MC1_8, "RED_MUSHROOM", "RED_MUSHROOM", 40);
    public static final VanillaItem GOLD_BLOCK = new VanillaItem(MC1_8, "GOLD_BLOCK", "GOLD_BLOCK", 41);
    public static final VanillaItem AIIRON_BLOCKR = new VanillaItem(MC1_8, "IRON_BLOCK", "IRON_BLOCK", 42);
    public static final VanillaItem STONE_SLAB = new VanillaItem(MC1_8, "STEP", "STONE_SLAB", 44);
    public static final VanillaItem SANDSTONE_SLAB = new VanillaItem(MC1_8, "STEP", "SANDSTONE_SLAB", 44, (short) 1);
    public static final VanillaItem PETRIFIED_OAK_SLAB = new VanillaItem(MC1_8, "STEP", "PETRIFIED_OAK_SLAB", 44, (short) 2);
    public static final VanillaItem COBBLESTONE_SLAB = new VanillaItem(MC1_8, "STEP", "COBBLESTONE_SLAB", 44, (short) 3);
    public static final VanillaItem BRICK_SLAB = new VanillaItem(MC1_8, "STEP", "BRICK_SLAB", 44, (short) 4);
    public static final VanillaItem STONE_BRICK_SLAB = new VanillaItem(MC1_8, "STEP", "STONE_BRICK_SLAB", 44, (short) 5);
    public static final VanillaItem NETHER_BRICK_SLAB = new VanillaItem(MC1_8, "STEP", "NETHER_BRICK_SLAB", 44, (short) 6);
    public static final VanillaItem QUARTZ_SLAB = new VanillaItem(MC1_8, "STEP", "QUARTZ_SLAB", 44, (short) 7);
    // PRISMARINE SLABS
    // SMOOTH BLOCKS
    public static final VanillaItem BRICKS = new VanillaItem(MC1_8, "BRICK", "BRICKS", 45);
    public static final VanillaItem TNT = new VanillaItem(MC1_8, "TNT", "TNT", 46);
    public static final VanillaItem BOOKSHELF = new VanillaItem(MC1_8, "BOOKSHELF", "BOOKSHELF", 47);
    public static final VanillaItem MOSSY_COBBLESTONE = new VanillaItem(MC1_8, "MOSSY_COBBLESTONE", "MOSSY_COBBLESTONE", 48);
    public static final VanillaItem OBSIDIAN = new VanillaItem(MC1_8, "OBSIDIAN", "OBSIDIAN", 49);
    public static final VanillaItem TORCH = new VanillaItem(MC1_8, "TORCH", "TORCH", 50);
    public static final VanillaItem FIRE = new VanillaItem(MC1_8, "FIRE", "FIRE", 51);
    public static final VanillaItem MOB_SPAWNER = new VanillaItem(MC1_8, "MOB_SPAWNER", "MOB_SPAWNER", 52);
    public static final VanillaItem OAK_STAIRS = new VanillaItem(MC1_8, "WOOD_STAIRS", "OAK_STAIRS", 53);
    public static final VanillaItem CHEST = new VanillaItem(MC1_8, "CHEST", "CHEST", 54);
    public static final VanillaItem REDSTONE_WIRE = new VanillaItem(MC1_8, "REDSTONE_WIRE", "REDSTONE_WIRE", 55);
    public static final VanillaItem DIAMOND_ORE = new VanillaItem(MC1_8, "DIAMOND_ORE", "DIAMOND_ORE", 56);
    public static final VanillaItem DIAMOND_BLOCK = new VanillaItem(MC1_8, "DIAMOND_BLOCK", "DIAMOND_BLOCK", 57);
    public static final VanillaItem CRAFTING_TABLE = new VanillaItem(MC1_8, "WORKBENCH", "CRAFTING_TABLE", 58);
    public static final VanillaItem LADDER = new VanillaItem(MC1_8, "LADDER", "LADDER", 65);
    public static final VanillaItem RAIL = new VanillaItem(MC1_8, "RAILS", "RAIL", 66);
    public static final VanillaItem COBBLESTONE_STAIRS = new VanillaItem(MC1_8, "COBBLESTONE_STAIRS", "COBBLESTONE_STAIRS", 67);
    public static final VanillaItem WALL_SIGN = new VanillaItem(MC1_8, "WALL_SIGN", "WALL_SIGN", 68);
    public static final VanillaItem LEVER = new VanillaItem(MC1_8, "LEVER", "LEVER", 69);
    public static final VanillaItem STONE_PRESSURE_PLATE = new VanillaItem(MC1_8, "STONE_PLATE", "STONE_PRESSURE_PLATE", 70);
    public static final VanillaItem FARMLAND = new VanillaItem(MC1_8, "SOIL", "FARMLAND", 60);
    public static final VanillaItem FURNACE = new VanillaItem(MC1_8, "FURNACE", "FURNACE", 61);
    public static final VanillaItem OAK_PRESSURE_PLATE = new VanillaItem(MC1_8, "WOOD_PLATE", "OAK_PRESSURE_PLATE", 72);
    public static final VanillaItem REDSTONE_ORE = new VanillaItem(MC1_8, "REDSTONE_ORE", "REDSTONE_ORE", 73);
    public static final VanillaItem REDSTONE_TORCH = new VanillaItem(MC1_8, "REDSTONE_TORCH_ON", "REDSTONE_TORCH", 76);
    // public static final VanillaItem REDSTONE_WALL_TORCH = new VanillaItem(MC1_13, "REDSTONE_TORCH_ON", "REDSTONE_WALL_TORCH", 76);
    public static final VanillaItem STONE_BUTTON = new VanillaItem(MC1_8, "STONE_BUTTON", "STONE_BUTTON", 77);
    public static final VanillaItem SNOW = new VanillaItem(MC1_8, "SNOW", "SNOW", 78);
    public static final VanillaItem ICE = new VanillaItem(MC1_8, "ICE", "ICE", 79);
    public static final VanillaItem SNOW_BLOCK = new VanillaItem(MC1_8, "SNOW_BLOCK", "SNOW_BLOCK", 80);
    public static final VanillaItem CACTUS = new VanillaItem(MC1_8, "CACTUS", "CACTUS", 81);
    public static final VanillaItem CLAY = new VanillaItem(MC1_8, "CLAY", "CLAY", 82);
    public static final VanillaItem JUKEBOX = new VanillaItem(MC1_8, "JUKEBOX", "JUKEBOX", 84);
    public static final VanillaItem OAK_FENCE = new VanillaItem(MC1_8, "FENCE", "OAK_FENCE", 85);
    public static final VanillaItem PUMPKIN = new VanillaItem(MC1_8, "PUMPKIN", "PUMPKIN", 86);
    public static final VanillaItem NETHERRACK = new VanillaItem(MC1_8, "NETHERRACK", "NETHERRACK", 87);
    public static final VanillaItem SOUL_SAND = new VanillaItem(MC1_8, "SOUL_SAND", "SOUL_SAND", 88);
    public static final VanillaItem GLOWSTONE = new VanillaItem(MC1_8, "GLOWSTONE", "GLOWSTONE", 89);
    public static final VanillaItem PORTAL = new VanillaItem(MC1_8, "PORTAL", "PORTAL", 90);
    public static final VanillaItem JACK_O_LANTERN = new VanillaItem(MC1_8, "JACK_O_LANTERN", "JACK_O_LANTERN", 91);
    public static final VanillaItem WHITE_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "WHITE_STAINED_GLASS", 95);
    public static final VanillaItem ORANGE_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "ORANGE_STAINED_GLASS", 95, (short) 1);
    public static final VanillaItem MAGENTA_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "MAGENTA_STAINED_GLASS", 95, (short) 2);
    public static final VanillaItem LIGHT_BLUE_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "LIGHT_BLUE_STAINED_GLASS", 95, (short) 3);
    public static final VanillaItem YELLOW_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "YELLOW_STAINED_GLASS", 95, (short) 4);
    public static final VanillaItem LIME_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "LIME_STAINED_GLASS", 95, (short) 5);
    public static final VanillaItem PINK_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "PINK_STAINED_GLASS", 95, (short) 6);
    public static final VanillaItem GRAY_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "GRAY_STAINED_GLASS", 95, (short) 7);
    public static final VanillaItem LIGHT_GRAY_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "LIGHT_GRAY_STAINED_GLASS", 95, (short) 8);
    public static final VanillaItem CYAN_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "CYAN_STAINED_GLASS", 95, (short) 9);
    public static final VanillaItem PURPLE_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "PURPLE_STAINED_GLASS", 95, (short) 10);
    public static final VanillaItem BLUE_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "BLUE_STAINED_GLASS", 95, (short) 11);
    public static final VanillaItem BROWN_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "BROWN_STAINED_GLASS", 95, (short) 12);
    public static final VanillaItem GREEN_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "GREEN_STAINED_GLASS", 95, (short) 13);
    public static final VanillaItem RED_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "RED_STAINED_GLASS", 95, (short) 14);
    public static final VanillaItem BLACK_STAINED_GLASS = new VanillaItem(MC1_8, "STAINED_GLASS", "BLACK_STAINED_GLASS", 95, (short) 15);
    public static final VanillaItem OAK_TRAPDOOR = new VanillaItem(MC1_8, "TRAP_DOOR", "OAK_TRAPDOOR", 96);
    public static final VanillaItem INFESTED_STONE = new VanillaItem(MC1_8, "MONSTER_EGG", "INFESTED_STONE", 97);
    public static final VanillaItem INFESTED_COBBLESTONE = new VanillaItem(MC1_8, "MONSTER_EGG", "INFESTED_COBBLESTONE", 97, (short) 1);
    public static final VanillaItem INFESTED_STONE_BRICKS = new VanillaItem(MC1_8, "MONSTER_EGG", "INFESTED_STONE_BRICKS", 97, (short) 2);
    public static final VanillaItem INFESTED_MOSSY_STONE_BRICKS = new VanillaItem(MC1_8, "MONSTER_EGG", "INFESTED_MOSSY_STONE_BRICKS", 97, (short) 3);
    public static final VanillaItem INFESTED_CRACKED_STONE_BRICKS = new VanillaItem(MC1_8, "MONSTER_EGG", "INFESTED_CRACKED_STONE_BRICKS", 97, (short) 4);
    public static final VanillaItem INFESTED_CHISELED_STONE_BRICKS = new VanillaItem(MC1_8, "MONSTER_EGG", "INFESTED_CHISELED_STONE_BRICKS", 97, (short) 5);
    public static final VanillaItem STONE_BRICKS = new VanillaItem(MC1_8, "SMOOTH_BRICK", "STONE_BRICKS", 98);
    public static final VanillaItem MOSSY_STONE_BRICKS = new VanillaItem(MC1_8, "SMOOTH_BRICK", "MOSSY_STONE_BRICKS", 98, (short) 1);
    public static final VanillaItem CRACKED_STONE_BRICKS = new VanillaItem(MC1_8, "SMOOTH_BRICK", "CRACKED_STONE_BRICKS", 98, (short) 2);
    public static final VanillaItem CHISELED_STONE_BRICKS = new VanillaItem(MC1_8, "SMOOTH_BRICK", "CHISELED_STONE_BRICKS", 98, (short) 3);
    public static final VanillaItem BROWN_MUSHROOM_BLOCK = new VanillaItem(MC1_8, "HUGE_MUSHROOM_1", "BROWN_MUSHROOM_BLOCK", 99);
    // public static final VanillaItem MUSHROOM_STEM = new VanillaItem(MC1_13, "MUSHROOM_STEM");
    public static final VanillaItem RED_MUSHROOM_BLOCK = new VanillaItem(MC1_8, "HUGE_MUSHROOM_2", "RED_MUSHROOM_BLOCK", 100);
    public static final VanillaItem IRON_BARS = new VanillaItem(MC1_8, "IRON_FENCE", "IRON_BARS", 101);
    public static final VanillaItem GLASS_PANE = new VanillaItem(MC1_8, "THIN_GLASS", "GLASS_PANE", 102);
    public static final VanillaItem MELON_BLOCK = new VanillaItem(MC1_8, "MELON_BLOCK", "MELON_BLOCK", 103);
    public static final VanillaItem PUMPKIN_STEM = new VanillaItem(MC1_8, "PUMPKIN_STEM", "PUMPKIN_STEM", 104);
    public static final VanillaItem MELON_STEM = new VanillaItem(MC1_8, "MELON_STEM", "MELON_STEM", 105);
    // 1.13: Attached stems
    public static final VanillaItem VINE = new VanillaItem(MC1_8, "VINE", "VINE", 106);
    public static final VanillaItem OAK_FENCE_GATE = new VanillaItem(MC1_8, "FENCE_GATE", "OAK_FENCE_GATE", 107);
    public static final VanillaItem BRICK_STAIRS = new VanillaItem(MC1_8, "BRICK_STAIRS", "BRICK_STAIRS", 108);
    public static final VanillaItem STONE_BRICK_STAIRS = new VanillaItem(MC1_8, "SMOOTH_STAIRS", "STONE_BRICK_STAIRS", 109);
    public static final VanillaItem MYCELIUM = new VanillaItem(MC1_8, "MYCEL", "MYCELIUM", 110);
    public static final VanillaItem LILY_PAD = new VanillaItem(MC1_8, "WATER_LILY", "LILY_PAD", 111);
    public static final VanillaItem NETHER_BRICKS = new VanillaItem(MC1_8, "NETHER_BRICK", "NETHER_BRICKS", 112);
    public static final VanillaItem NETHER_BRICK_FENCE = new VanillaItem(MC1_8, "NETHER_FENCE", "NETHER_BRICK_FENCE", 113);
    public static final VanillaItem NETHER_BRICK_STAIRS = new VanillaItem(MC1_8, "NETHER_BRICK_STAIRS", "NETHER_BRICK_STAIRS", 114);
    public static final VanillaItem ENCHANTING_TABLE = new VanillaItem(MC1_8, "ENCHANTMENT_TABLE", "ENCHANTING_TABLE", 116);
    public static final VanillaItem END_PORTAL = new VanillaItem(MC1_8, "ENDER_PORTAL", "END_PORTAL", 119);
    public static final VanillaItem END_PORTAL_FRAME = new VanillaItem(MC1_8, "ENDER_PORTAL_FRAME", "END_PORTAL_FRAME", 120);
    public static final VanillaItem END_STONE = new VanillaItem(MC1_8, "ENDER_STONE", "END_STONE", 121);
    public static final VanillaItem DRAGON_EGG = new VanillaItem(MC1_8, "DRAGON_EGG", "DRAGON_EGG", 122);
    public static final VanillaItem REDSTONE_LAMP = new VanillaItem(MC1_8, "REDSTONE_LAMP_ON", "REDSTONE_LAMP", 124);
    public static final VanillaItem OAK_SLAB = new VanillaItem(MC1_8, "WOOD_STEP", "OAK_SLAB", 126);
    public static final VanillaItem SPRUCE_SLAB = new VanillaItem(MC1_8, "WOOD_STEP", "SPRUCE_SLAB", 126, (short) 1);
    public static final VanillaItem BIRCH_SLAB = new VanillaItem(MC1_8, "WOOD_STEP", "BIRCH_SLAB", 126, (short) 2);
    public static final VanillaItem JUNGLE_SLAB = new VanillaItem(MC1_8, "WOOD_STEP", "JUNGLE_SLAB", 126, (short) 3);
    public static final VanillaItem ACACIA_SLAB = new VanillaItem(MC1_8, "WOOD_STEP", "ACACIA_SLAB", 126, (short) 4);
    public static final VanillaItem DARK_OAK_SLAB = new VanillaItem(MC1_8, "WOOD_STEP", "DARK_OAK_SLAB", 126, (short) 5);
    public static final VanillaItem COCOA = new VanillaItem(MC1_8, "COCOA", "COCOA", 127);
    public static final VanillaItem SANDSTONE_STAIRS = new VanillaItem(MC1_8, "SANDSTONE_STAIRS", "SANDSTONE_STAIRS", 128);
    public static final VanillaItem EMERALD_ORE = new VanillaItem(MC1_8, "EMERALD_ORE", "EMERALD_ORE", 129);
    public static final VanillaItem ENDER_CHEST = new VanillaItem(MC1_8, "ENDER_CHEST", "ENDER_CHEST", 130);
    public static final VanillaItem TRIPWIRE_HOOK = new VanillaItem(MC1_8, "TRIPWIRE_HOOK", "TRIPWIRE_HOOK", 131);
    public static final VanillaItem TRIPWIRE = new VanillaItem(MC1_8, "TRIPWIRE", "TRIPWIRE", 132);
    public static final VanillaItem EMERALD_BLOCK = new VanillaItem(MC1_8, "EMERALD_BLOCK", "EMERALD_BLOCK", 133);
    public static final VanillaItem SPRUCE_STAIRS = new VanillaItem(MC1_8, "SPRUCE_WOOD_STAIRS", "SPRUCE_STAIRS", 134);
    public static final VanillaItem BIRCH_STAIRS = new VanillaItem(MC1_8, "BIRCH_WOOD_STAIRS", "BIRCH_STAIRS", 135);
    public static final VanillaItem JUNGLE_STAIRS = new VanillaItem(MC1_8, "JUNGLE_WOOD_STAIRS", "JUNGLE_STAIRS", 136);
    public static final VanillaItem COMMAND_BLOCK = new VanillaItem(MC1_8, "COMMAND", "COMMAND_BLOCK", 137);
    public static final VanillaItem BEACON = new VanillaItem(MC1_8, "BEACON", "BEACON", 138);
    public static final VanillaItem COBBLESTONE_WALL = new VanillaItem(MC1_8, "COBBLE_WALL", "COBBLESTONE_WALL", 139);
    public static final VanillaItem MOSSY_COBBLESTONE_WALL = new VanillaItem(MC1_8, "COBBLE_WALL", "MOSSY_COBBLESTONE_WALL", 139, (short) 1);
    public static final VanillaItem CARROTS = new VanillaItem(MC1_8, "CARROT", "CARROTS", 141);
    public static final VanillaItem POTATOES = new VanillaItem(MC1_8, "POTATO", "POTATOES", 142);
    public static final VanillaItem OAK_BUTTON = new VanillaItem(MC1_8, "WOOD_BUTTON", "OAK_BUTTON", 143);
    // public static final VanillaItem SPRUCE_BUTTON = new VanillaItem(MC1_13, "SPRUCE_BUTTON");
    // public static final VanillaItem BIRCH_BUTTON = new VanillaItem(MC1_13, "BIRCH_BUTTON");
    // public static final VanillaItem JUNGLE_BUTTON = new VanillaItem(MC1_13, "JUNGLE_BUTTON");
    // public static final VanillaItem ACACIA_BUTTON = new VanillaItem(MC1_13, "ACACIA_BUTTON");
    // public static final VanillaItem DARK_OAK_BUTTON = new VanillaItem(MC1_13, "DARK_OAK_BUTTON");
    public static final VanillaItem ANVIL = new VanillaItem(MC1_8, "ANVIL", "ANVIL", 145);
    public static final VanillaItem CHIPPED_ANVIL = new VanillaItem(MC1_8, "ANVIL", "CHIPPED_ANVIL", 145, (short) 1);
    public static final VanillaItem DAMAGED_ANVIL = new VanillaItem(MC1_8, "ANVIL", "DAMAGED_ANVIL", 145, (short) 2);
    public static final VanillaItem TRAPPED_CHEST = new VanillaItem(MC1_8, "TRAPPED_CHEST", "TRAPPED_CHEST", 146);
    public static final VanillaItem LIGHT_WEIGHTED_PRESSURE_PLATE = new VanillaItem(MC1_8, "GOLD_PLATE", "LIGHT_WEIGHTED_PRESSURE_PLATE", 147);
    public static final VanillaItem HEAVY_WEIGHTED_PRESSURE_PLATE = new VanillaItem(MC1_8, "IRON_PLATE", "HEAVY_WEIGHTED_PRESSURE_PLATE", 148);
    public static final VanillaItem DAYLIGHT_DETECTOR = new VanillaItem(MC1_8, "DAYLIGHT_DETECTOR", "DAYLIGHT_DETECTOR", 151);
    public static final VanillaItem REDSTONE_BLOCK = new VanillaItem(MC1_8, "REDSTONE_BLOCK", "REDSTONE_BLOCK", 152);
    public static final VanillaItem QUARTZ_ORE = new VanillaItem(MC1_8, "QUARTZ_ORE", "QUARTZ_ORE", 153);
    public static final VanillaItem HOPPER = new VanillaItem(MC1_8, "HOPPER", "HOPPER", 154);
    public static final VanillaItem QUARTZ_BLOCK = new VanillaItem(MC1_8, "QUARTZ_BLOCK", "QUARTZ_BLOCK", 155);
    public static final VanillaItem CHISELED_QUARTZ_BLOCK = new VanillaItem(MC1_8, "QUARTZ_BLOCK", "CHISELED_QUARTZ_BLOCK", 155, (short) 1);
    public static final VanillaItem QUARTZ_PILLAR = new VanillaItem(MC1_8, "QUARTZ_BLOCK", "QUARTZ_PILLAR", 155, (short) 2);
    public static final VanillaItem QUARTZ_STAIRS = new VanillaItem(MC1_8, "QUARTZ_STAIRS", "QUARTZ_STAIRS", 156);
    public static final VanillaItem ACTIVATOR_RAIL = new VanillaItem(MC1_8, "ACTIVATOR_RAIL", "ACTIVATOR_RAIL", 157);
    public static final VanillaItem DROPPER = new VanillaItem(MC1_8, "DROPPER", "DROPPER", 158);
    public static final VanillaItem WHITE_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "WHITE_TERRACOTTA", 159);
    public static final VanillaItem ORANGE_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "ORANGE_TERRACOTTA", 159, (short) 1);
    public static final VanillaItem MAGENTA_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "MAGENTA_TERRACOTTA", 159, (short) 2);
    public static final VanillaItem LIGHT_BLUE_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "LIGHT_BLUE_TERRACOTTA", 159, (short) 3);
    public static final VanillaItem YELLOW_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "YELLOW_TERRACOTTA", 159, (short) 4);
    public static final VanillaItem LIME_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "LIME_TERRACOTTA", 159, (short) 5);
    public static final VanillaItem PINK_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "PINK_TERRACOTTA", 159, (short) 6);
    public static final VanillaItem GRAY_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "GRAY_TERRACOTTA", 159, (short) 7);
    public static final VanillaItem LIGHT_GRAY_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "LIGHT_GRAY_TERRACOTTA", 159, (short) 8);
    public static final VanillaItem CYAN_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "CYAN_TERRACOTTA", 159, (short) 9);
    public static final VanillaItem PURPLE_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "PURPLE_TERRACOTTA", 159, (short) 10);
    public static final VanillaItem BLUE_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "BLUE_TERRACOTTA", 159, (short) 11);
    public static final VanillaItem BROWN_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "BROWN_TERRACOTTA", 159, (short) 12);
    public static final VanillaItem GREEN_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "GREEN_TERRACOTTA", 159, (short) 13);
    public static final VanillaItem RED_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "RED_TERRACOTTA", 159, (short) 14);
    public static final VanillaItem BLACK_TERRACOTTA = new VanillaItem(MC1_8, "STAINED_CLAY", "BLACK_TERRACOTTA", 159, (short) 15);
    public static final VanillaItem WHITE_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "WHITE_STAINED_GLASS_PANE", 160);
    public static final VanillaItem ORANGE_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "ORANGE_STAINED_GLASS_PANE", 160, (short) 1);
    public static final VanillaItem MAGENTA_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "MAGENTA_STAINED_GLASS_PANE", 160, (short) 2);
    public static final VanillaItem LIGHT_BLUE_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "LIGHT_BLUE_STAINED_GLASS_PANE", 160, (short) 3);
    public static final VanillaItem YELLOW_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "YELLOW_STAINED_GLASS_PANE", 160, (short) 4);
    public static final VanillaItem LIME_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "LIME_STAINED_GLASS_PANE", 160, (short) 5);
    public static final VanillaItem PINK_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "PINK_STAINED_GLASS_PANE", 160, (short) 6);
    public static final VanillaItem GRAY_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "GRAY_STAINED_GLASS_PANE", 160, (short) 7);
    public static final VanillaItem LIGHT_GRAY_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "LIGHT_GRAY_STAINED_GLASS_PANE", 160, (short) 8);
    public static final VanillaItem CYAN_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "CYAN_STAINED_GLASS_PANE", 160, (short) 9);
    public static final VanillaItem PURPLE_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "PURPLE_STAINED_GLASS_PANE", 160, (short) 10);
    public static final VanillaItem BLUE_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "BLUE_STAINED_GLASS_PANE", 160, (short) 11);
    public static final VanillaItem BROWN_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "BROWN_STAINED_GLASS_PANE", 160, (short) 12);
    public static final VanillaItem GREEN_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "GREEN_STAINED_GLASS_PANE", 160, (short) 13);
    public static final VanillaItem RED_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "RED_STAINED_GLASS_PANE", 160, (short) 14);
    public static final VanillaItem BLACK_STAINED_GLASS_PANE = new VanillaItem(MC1_8, "STAINED_GLASS_PANE", "BLACK_STAINED_GLASS_PANE", 160, (short) 15);
    public static final VanillaItem ACACIA_LEAVES = new VanillaItem(MC1_8, "LEAVES_2", "ACACIA_LEAVES", 161);
    public static final VanillaItem DARK_OAK_LEAVES = new VanillaItem(MC1_8, "LEAVES_2", "DARK_OAK_LEAVES", 161, (short) 1);
    public static final VanillaItem ACACIA_LOG = new VanillaItem(MC1_8, "LOG_2", "ACACIA_LOG", 162);
    public static final VanillaItem DARK_OAK_LOG = new VanillaItem(MC1_8, "LOG_2", "DARK_OAK_LOG", 162, (short) 1);
    // public static final VanillaItem ACACIA_BARK = new VanillaItem(MC1_13, "ACACIA_BARK");
    // public static final VanillaItem DARK_OAK_BARK = new VanillaItem(MC1_13, "DARK_OAK_BARK");
    public static final VanillaItem ACACIA_STAIRS = new VanillaItem(MC1_8, "ACACIA_STAIRS", "ACACIA_STAIRS", 163);
    public static final VanillaItem DARK_OAK_STAIRS = new VanillaItem(MC1_8, "DARK_OAK_STAIRS", "DARK_OAK_STAIRS", 164);
    public static final VanillaItem SLIME_BLOCK = new VanillaItem(MC1_8, "SLIME_BLOCK", "SLIME_BLOCK", 165);
    public static final VanillaItem BARRIER = new VanillaItem(MC1_8, "BARRIER", "BARRIER", 166);
    public static final VanillaItem IRON_TRAPDOOR = new VanillaItem(MC1_8, "IRON_TRAPDOOR", "IRON_TRAPDOOR", 167);
    public static final VanillaItem PRISMARINE = new VanillaItem(MC1_8, "PRISMARINE", "PRISMARINE", 168);
    public static final VanillaItem PRISMARINE_BRICKS = new VanillaItem(MC1_8, "PRISMARINE", "PRISMARINE_BRICKS", 168, (short) 1);
    public static final VanillaItem DARK_PRISMARINE = new VanillaItem(MC1_8, "PRISMARINE", "DARK_PRISMARINE", 168, (short) 2);
    public static final VanillaItem SEA_LANTERN = new VanillaItem(MC1_8, "SEA_LANTERN", "SEA_LANTERN", 169);
    public static final VanillaItem HAY_BLOCK = new VanillaItem(MC1_8, "HAY_BLOCK", "HAY_BLOCK", 170);
    public static final VanillaItem WHITE_CARPET = new VanillaItem(MC1_8, "CARPET", "WHITE_CARPET", 171);
    public static final VanillaItem ORANGE_CARPET = new VanillaItem(MC1_8, "CARPET", "ORANGE_CARPET", 171, (short) 1);
    public static final VanillaItem MAGENTA_CARPET = new VanillaItem(MC1_8, "CARPET", "MAGENTA_CARPET", 171, (short) 2);
    public static final VanillaItem LIGHT_BLUE_CARPET = new VanillaItem(MC1_8, "CARPET", "LIGHT_BLUE_CARPET", 171, (short) 3);
    public static final VanillaItem YELLOW_CARPET = new VanillaItem(MC1_8, "CARPET", "YELLOW_CARPET", 171, (short) 4);
    public static final VanillaItem LIME_CARPET = new VanillaItem(MC1_8, "CARPET", "LIME_CARPET", 171, (short) 5);
    public static final VanillaItem PINK_CARPET = new VanillaItem(MC1_8, "CARPET", "PINK_CARPET", 171, (short) 6);
    public static final VanillaItem GRAY_CARPET = new VanillaItem(MC1_8, "CARPET", "GRAY_CARPET", 171, (short) 7);
    public static final VanillaItem LIGHT_GRAY_CARPET = new VanillaItem(MC1_8, "CARPET", "LIGHT_GRAY_CARPET", 171, (short) 8);
    public static final VanillaItem CYAN_CARPET = new VanillaItem(MC1_8, "CARPET", "CYAN_CARPET", 171, (short) 9);
    public static final VanillaItem PURPLE_CARPET = new VanillaItem(MC1_8, "CARPET", "PURPLE_CARPET", 171, (short) 10);
    public static final VanillaItem BLUE_CARPET = new VanillaItem(MC1_8, "CARPET", "BLUE_CARPET", 171, (short) 11);
    public static final VanillaItem BROWN_CARPET = new VanillaItem(MC1_8, "CARPET", "BROWN_CARPET", 171, (short) 12);
    public static final VanillaItem GREEN_CARPET = new VanillaItem(MC1_8, "CARPET", "GREEN_CARPET", 171, (short) 13);
    public static final VanillaItem RED_CARPET = new VanillaItem(MC1_8, "CARPET", "RED_CARPET", 171, (short) 14);
    public static final VanillaItem BLACK_CARPET = new VanillaItem(MC1_8, "CARPET", "BLACK_CARPET", 171, (short) 15);
    public static final VanillaItem TERRACOTTA = new VanillaItem(MC1_8, "HARD_CLAY", "TERRACOTTA", 172);
    public static final VanillaItem COAL_BLOCK = new VanillaItem(MC1_8, "COAL_BLOCK", "COAL_BLOCK", 173);
    public static final VanillaItem PACKED_ICE = new VanillaItem(MC1_8, "PACKED_ICE", "PACKED_ICE", 174);
    public static final VanillaItem SUNFLOWER = new VanillaItem(MC1_8, "DOUBLE_PLANT", "SUNFLOWER", 175);
    public static final VanillaItem LILAC = new VanillaItem(MC1_8, "DOUBLE_PLANT", "LILAC", 175, (short) 1);
    public static final VanillaItem TALL_GRASS = new VanillaItem(MC1_8, "DOUBLE_PLANT", "TALL_GRASS", 175, (short) 2);
    public static final VanillaItem LARGE_FERN = new VanillaItem(MC1_8, "DOUBLE_PLANT", "LARGE_FERN", 175, (short) 3);
    public static final VanillaItem ROSE_BUSH = new VanillaItem(MC1_8, "DOUBLE_PLANT", "ROSE_BUSH", 175, (short) 4);
    public static final VanillaItem PEONY = new VanillaItem(MC1_8, "DOUBLE_PLANT", "PEONY", 175, (short) 5);
    public static final VanillaItem WHITE_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "WHITE_WALL_BANNER", 177);
    public static final VanillaItem ORANGE_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "ORANGE_WALL_BANNER", 177, (short) 1);
    public static final VanillaItem MAGENTA_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "MAGENTA_WALL_BANNER", 177, (short) 2);
    public static final VanillaItem LIGHT_BLUE_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "LIGHT_BLUE_WALL_BANNER", 177, (short) 3);
    public static final VanillaItem YELLOW_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "YELLOW_WALL_BANNER", 177, (short) 4);
    public static final VanillaItem LIME_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "LIME_WALL_BANNER", 177, (short) 5);
    public static final VanillaItem PINK_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "PINK_WALL_BANNER", 177, (short) 6);
    public static final VanillaItem GRAY_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "GRAY_WALL_BANNER", 177, (short) 7);
    public static final VanillaItem LIGHT_GRAY_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "LIGHT_GRAY_WALL_BANNER", 177, (short) 8);
    public static final VanillaItem CYAN_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "CYAN_WALL_BANNER", 177, (short) 9);
    public static final VanillaItem PURPLE_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "PURPLE_WALL_BANNER", 177, (short) 10);
    public static final VanillaItem BLUE_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "BLUE_WALL_BANNER", 177, (short) 11);
    public static final VanillaItem BROWN_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "BROWN_WALL_BANNER", 177, (short) 12);
    public static final VanillaItem GREEN_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "GREEN_WALL_BANNER", 177, (short) 13);
    public static final VanillaItem RED_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "RED_WALL_BANNER", 177, (short) 14);
    public static final VanillaItem BLACK_WALL_BANNER = new VanillaItem(MC1_8, "WALL_BANNER", "BLACK_WALL_BANNER", 177, (short) 15);
    public static final VanillaItem RED_SANDSTONE = new VanillaItem(MC1_8, "RED_SANDSTONE", "RED_SANDSTONE", 179);
    public static final VanillaItem CHISELED_RED_SANDSTONE = new VanillaItem(MC1_8, "RED_SANDSTONE", "CHISELED_RED_SANDSTONE", 179, (short) 1);
    public static final VanillaItem CUT_RED_SANDSTONE = new VanillaItem(MC1_8, "RED_SANDSTONE", "CUT_RED_SANDSTONE", 179, (short) 2);
    public static final VanillaItem RED_SANDSTONE_STAIRS = new VanillaItem(MC1_8, "RED_SANDSTONE_STAIRS", "RED_SANDSTONE_STAIRS", 180);
    public static final VanillaItem RED_SANDSTONE_SLAB = new VanillaItem(MC1_8, "STONE_SLAB2", "RED_SANDSTONE_SLAB", 182);
    // 1.13: SMOOTH
    public static final VanillaItem SPRUCE_FENCE_GATE = new VanillaItem(MC1_8, "SPRUCE_FENCE_GATE", "SPRUCE_FENCE_GATE", 183);
    public static final VanillaItem BIRCH_FENCE_GATE = new VanillaItem(MC1_8, "BIRCH_FENCE_GATE", "BIRCH_FENCE_GATE", 184);
    public static final VanillaItem JUNGLE_FENCE_GATE = new VanillaItem(MC1_8, "JUNGLE_FENCE_GATE", "JUNGLE_FENCE_GATE", 185);
    public static final VanillaItem DARK_OAK_FENCE_GATE = new VanillaItem(MC1_8, "DARK_OAK_FENCE_GATE", "DARK_OAK_FENCE_GATE", 186);
    public static final VanillaItem ACACIA_FENCE_GATE = new VanillaItem(MC1_8, "ACACIA_FENCE_GATE", "ACACIA_FENCE_GATE", 187);
    public static final VanillaItem SPRUCE_FENCE = new VanillaItem(MC1_8, "SPRUCE_FENCE", "SPRUCE_FENCE", 188);
    public static final VanillaItem BIRCH_FENCE = new VanillaItem(MC1_8, "BIRCH_FENCE", "BIRCH_FENCE", 189);
    public static final VanillaItem JUNGLE_FENCE = new VanillaItem(MC1_8, "JUNGLE_FENCE", "JUNGLE_FENCE", 190);
    public static final VanillaItem DARK_OAK_FENCE = new VanillaItem(MC1_8, "DARK_OAK_FENCE", "DARK_OAK_FENCE", 191);
    public static final VanillaItem ACACIA_FENCE = new VanillaItem(MC1_8, "ACACIA_FENCE", "ACACIA_FENCE", 192);
    public static final VanillaItem END_ROD = new VanillaItem(MC1_9, "END_ROD", "END_ROD", 198);
    public static final VanillaItem CHORUS_PLANT = new VanillaItem(MC1_9, "CHORUS_PLANT", "CHORUS_PLANT", 199);
    public static final VanillaItem CHORUS_FLOWER = new VanillaItem(MC1_9, "CHORUS_FLOWER", "CHORUS_FLOWER", 200);
    public static final VanillaItem PURPUR_BLOCK = new VanillaItem(MC1_9, "PURPUR_BLOCK", "PURPUR_BLOCK", 201);
    public static final VanillaItem PURPUR_PILLAR = new VanillaItem(MC1_9, "PURPUR_PILLAR", "PURPUR_PILLAR", 202);
    public static final VanillaItem PURPUR_STAIRS = new VanillaItem(MC1_9, "PURPUR_STAIRS", "PURPUR_STAIRS", 203);
    public static final VanillaItem PURPUR_DOUBLE_SLAB = new VanillaItem(MC1_9, "PURPUR_DOUBLE_SLAB", "PURPUR_DOUBLE_SLAB", 204);
    public static final VanillaItem PURPUR_SLAB = new VanillaItem(MC1_9, "PURPUR_SLAB", "PURPUR_SLAB", 205);
    public static final VanillaItem END_BRICKS = new VanillaItem(MC1_9, "END_BRICKS", "END_BRICKS", 206);
    public static final VanillaItem BEETROOT_BLOCK = new VanillaItem(MC1_9, "BEETROOT_BLOCK", "BEETROOT_BLOCK", 207);
    public static final VanillaItem GRASS_PATH = new VanillaItem(MC1_9, "GRASS_PATH", "GRASS_PATH", 208);
    public static final VanillaItem END_GATEWAY = new VanillaItem(MC1_9, "END_GATEWAY", "END_GATEWAY", 209);
    public static final VanillaItem REPEATING_COMMAND_BLOCK = new VanillaItem(MC1_9, "COMMAND_REPEATING", "REPEATING_COMMAND_BLOCK", 210);
    public static final VanillaItem CHAIN_COMMAND_BLOCK = new VanillaItem(MC1_9, "COMMAND_CHAIN", "CHAIN_COMMAND_BLOCK", 211);
    public static final VanillaItem FROSTED_ICE = new VanillaItem(MC1_9, "FROSTED_ICE", "FROSTED_ICE", 212);
    public static final VanillaItem MAGMA_BLOCK = new VanillaItem(MC1_10, "MAGMA", "MAGMA_BLOCK", 213);
    public static final VanillaItem NETHER_WART_BLOCK = new VanillaItem(MC1_10, "NETHER_WART_BLOCK", "NETHER_WART_BLOCK", 214);
    public static final VanillaItem RED_NETHER_BRICK = new VanillaItem(MC1_10, "RED_NETHER_BRICK", "RED_NETHER_BRICK", 215);
    public static final VanillaItem BONE_BLOCK = new VanillaItem(MC1_10, "BONE_BLOCK", "BONE_BLOCK", 216);
    public static final VanillaItem STRUCTURE_VOID = new VanillaItem(MC1_10, "STRUCTURE_VOID", "STRUCTURE_VOID", 217);
    public static final VanillaItem OBSERVER = new VanillaItem(MC1_11, "OBSERVER", "OBSERVER", 218);
    public static final VanillaItem WHITE_SHULKER_BOX = new VanillaItem(MC1_11, "WHITE_SHULKER_BOX", "WHITE_SHULKER_BOX", 219);
    public static final VanillaItem ORANGE_SHULKER_BOX = new VanillaItem(MC1_11, "ORANGE_SHULKER_BOX", "ORANGE_SHULKER_BOX", 220);
    public static final VanillaItem MAGENTA_SHULKER_BOX = new VanillaItem(MC1_11, "MAGENTA_SHULKER_BOX", "MAGENTA_SHULKER_BOX", 221);
    public static final VanillaItem LIGHT_BLUE_SHULKER_BOX = new VanillaItem(MC1_11, "LIGHT_BLUE_SHULKER_BOX", "LIGHT_BLUE_SHULKER_BOX", 222);
    public static final VanillaItem YELLOW_SHULKER_BOX = new VanillaItem(MC1_11, "YELLOW_SHULKER_BOX", "YELLOW_SHULKER_BOX", 223);
    public static final VanillaItem LIME_SHULKER_BOX = new VanillaItem(MC1_11, "LIME_SHULKER_BOX", "LIME_SHULKER_BOX", 224);
    public static final VanillaItem PINK_SHULKER_BOX = new VanillaItem(MC1_11, "PINK_SHULKER_BOX", "PINK_SHULKER_BOX", 225);
    public static final VanillaItem GRAY_SHULKER_BOX = new VanillaItem(MC1_11, "GRAY_SHULKER_BOX", "GRAY_SHULKER_BOX", 226);
    public static final VanillaItem SILVER_SHULKER_BOX = new VanillaItem(MC1_11, "SILVER_SHULKER_BOX", "SILVER_SHULKER_BOX", 227);
    public static final VanillaItem CYAN_SHULKER_BOX = new VanillaItem(MC1_11, "CYAN_SHULKER_BOX", "CYAN_SHULKER_BOX", 228);
    public static final VanillaItem PURPLE_SHULKER_BOX = new VanillaItem(MC1_11, "PURPLE_SHULKER_BOX", "PURPLE_SHULKER_BOX", 229);
    public static final VanillaItem BLUE_SHULKER_BOX = new VanillaItem(MC1_11, "BLUE_SHULKER_BOX", "BLUE_SHULKER_BOX", 230);
    public static final VanillaItem BROWN_SHULKER_BOX = new VanillaItem(MC1_11, "BROWN_SHULKER_BOX", "BROWN_SHULKER_BOX", 231);
    public static final VanillaItem GREEN_SHULKER_BOX = new VanillaItem(MC1_11, "GREEN_SHULKER_BOX", "GREEN_SHULKER_BOX", 232);
    public static final VanillaItem RED_SHULKER_BOX = new VanillaItem(MC1_11, "RED_SHULKER_BOX", "RED_SHULKER_BOX", 233);
    public static final VanillaItem BLACK_SHULKER_BOX = new VanillaItem(MC1_11, "BLACK_SHULKER_BOX", "BLACK_SHULKER_BOX", 234);
    public static final VanillaItem WHITE_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "WHITE_GLAZED_TERRACOTTA", "WHITE_GLAZED_TERRACOTTA", 235);
    public static final VanillaItem ORANGE_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "ORANGE_GLAZED_TERRACOTTA", "ORANGE_GLAZED_TERRACOTTA", 236);
    public static final VanillaItem MAGENTA_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "MAGENTA_GLAZED_TERRACOTTA", "MAGENTA_GLAZED_TERRACOTTA", 237);
    public static final VanillaItem LIGHT_BLUE_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "LIGHT_BLUE_GLAZED_TERRACOTTA", "LIGHT_BLUE_GLAZED_TERRACOTTA", 238);
    public static final VanillaItem YELLOW_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "YELLOW_GLAZED_TERRACOTTA", "YELLOW_GLAZED_TERRACOTTA", 239);
    public static final VanillaItem LIME_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "LIME_GLAZED_TERRACOTTA", "LIME_GLAZED_TERRACOTTA", 240);
    public static final VanillaItem PINK_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "PINK_GLAZED_TERRACOTTA", "PINK_GLAZED_TERRACOTTA", 241);
    public static final VanillaItem GRAY_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "GRAY_GLAZED_TERRACOTTA", "GRAY_GLAZED_TERRACOTTA", 242);
    public static final VanillaItem SILVER_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "SILVER_GLAZED_TERRACOTTA", "SILVER_GLAZED_TERRACOTTA", 243);
    public static final VanillaItem CYAN_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "CYAN_GLAZED_TERRACOTTA", "CYAN_GLAZED_TERRACOTTA", 244);
    public static final VanillaItem PURPLE_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "PURPLE_GLAZED_TERRACOTTA", "PURPLE_GLAZED_TERRACOTTA", 245);
    public static final VanillaItem BLUE_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "BLUE_GLAZED_TERRACOTTA", "BLUE_GLAZED_TERRACOTTA", 246);
    public static final VanillaItem BROWN_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "BROWN_GLAZED_TERRACOTTA", "BROWN_GLAZED_TERRACOTTA", 247);
    public static final VanillaItem GREEN_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "GREEN_GLAZED_TERRACOTTA", "GREEN_GLAZED_TERRACOTTA", 248);
    public static final VanillaItem RED_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "RED_GLAZED_TERRACOTTA", "RED_GLAZED_TERRACOTTA", 249);
    public static final VanillaItem BLACK_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "BLACK_GLAZED_TERRACOTTA", "BLACK_GLAZED_TERRACOTTA", 250);
    public static final VanillaItem WHITE_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "WHITE_CONCRETE", 251);
    public static final VanillaItem ORANGE_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "ORANGE_CONCRETE", 251, (short) 1);
    public static final VanillaItem MAGENTA_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "MAGENTA_CONCRETE", 251, (short) 2);
    public static final VanillaItem LIGHT_BLUE_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "LIGHT_BLUE_CONCRETE", 251, (short) 3);
    public static final VanillaItem YELLOW_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "YELLOW_CONCRETE", 251, (short) 4);
    public static final VanillaItem LIME_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "LIME_CONCRETE", 251, (short) 5);
    public static final VanillaItem PINK_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "PINK_CONCRETE", 251, (short) 6);
    public static final VanillaItem GRAY_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "GRAY_CONCRETE", 251, (short) 7);
    public static final VanillaItem LIGHT_GRAY_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "LIGHT_GRAY_CONCRETE", 251, (short) 8);
    public static final VanillaItem CYAN_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "CYAN_CONCRETE", 251, (short) 9);
    public static final VanillaItem PURPLE_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "PURPLE_CONCRETE", 251, (short) 10);
    public static final VanillaItem BLUE_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "BLUE_CONCRETE", 251, (short) 11);
    public static final VanillaItem BROWN_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "BROWN_CONCRETE", 251, (short) 12);
    public static final VanillaItem GREEN_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "GREEN_CONCRETE", 251, (short) 13);
    public static final VanillaItem RED_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "RED_CONCRETE", 251, (short) 14);
    public static final VanillaItem BLACK_CONCRETE = new VanillaItem(MC1_12, "CONCRETE", "BLACK_CONCRETE", 251, (short) 15);
    public static final VanillaItem WHITE_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "WHITE_CONCRETE_POWDER", 252);
    public static final VanillaItem ORANGE_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "ORANGE_CONCRETE_POWDER", 252, (short) 1);
    public static final VanillaItem MAGENTA_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "MAGENTA_CONCRETE_POWDER", 252, (short) 2);
    public static final VanillaItem LIGHT_BLUE_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "LIGHT_BLUE_CONCRETE_POWDER", 252, (short) 3);
    public static final VanillaItem YELLOW_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "YELLOW_CONCRETE_POWDER", 252, (short) 4);
    public static final VanillaItem LIME_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "LIME_CONCRETE_POWDER", 252, (short) 5);
    public static final VanillaItem PINK_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "PINK_CONCRETE_POWDER", 252, (short) 6);
    public static final VanillaItem GRAY_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "GRAY_CONCRETE_POWDER", 252, (short) 7);
    public static final VanillaItem LIGHT_GRAY_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "LIGHT_GRAY_CONCRETE_POWDER", 252, (short) 8);
    public static final VanillaItem CYAN_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "CYAN_CONCRETE_POWDER", 252, (short) 9);
    public static final VanillaItem PURPLE_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "PURPLE_CONCRETE_POWDER", 252, (short) 10);
    public static final VanillaItem BLUE_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "BLUE_CONCRETE_POWDER", 252, (short) 11);
    public static final VanillaItem BROWN_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "BROWN_CONCRETE_POWDER", 252, (short) 12);
    public static final VanillaItem GREEN_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "GREEN_CONCRETE_POWDER", 252, (short) 13);
    public static final VanillaItem RED_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "RED_CONCRETE_POWDER", 252, (short) 14);
    public static final VanillaItem BLACK_CONCRETE_POWDER = new VanillaItem(MC1_12, "CONCRETE_POWDER", "BLACK_CONCRETE_POWDER", 252, (short) 15);
    public static final VanillaItem STRUCTURE_BLOCK = new VanillaItem(MC1_9, "STRUCTURE_BLOCK", "STRUCTURE_BLOCK", 255);

    // IITEMS
    public static final VanillaItem IRON_SHOVEL = new VanillaItem(MC1_8, "IRON_SPADE", "IRON_SHOVEL", 256);
    public static final VanillaItem IRON_PICKAXE = new VanillaItem(MC1_8, "IRON_PICKAXE", "IRON_PICKAXE", 257);
    public static final VanillaItem IRON_AXE = new VanillaItem(MC1_8, "IRON_AXE", "IRON_AXE", 258);
    public static final VanillaItem FLINT_AND_STEEL = new VanillaItem(MC1_8, "FLINT_AND_STEEL", "FLINT_AND_STEEL", 259);
    public static final VanillaItem APPLE = new VanillaItem(MC1_8, "APPLE", "APPLE", 260);
    public static final VanillaItem BOW = new VanillaItem(MC1_8, "BOW", "BOW", 261);
    public static final VanillaItem ARROW = new VanillaItem(MC1_8, "ARROW", "ARROW", 262);
    public static final VanillaItem COAL = new VanillaItem(MC1_8, "COAL", "COAL", 263);
    public static final VanillaItem CHARCOAL = new VanillaItem(MC1_8, "COAL", "CHARCOAL", 263, (short) 1);
    public static final VanillaItem DIAMOND = new VanillaItem(MC1_8, "DIAMOND", "DIAMOND", 264);
    public static final VanillaItem IRON_INGOT = new VanillaItem(MC1_8, "IRON_INGOT", "IRON_INGOT", 265);
    public static final VanillaItem GOLD_INGOT = new VanillaItem(MC1_8, "GOLD_INGOT", "GOLD_INGOT", 266);
    public static final VanillaItem IRON_SWORD = new VanillaItem(MC1_8, "IRON_SWORD", "IRON_SWORD", 267);
    public static final VanillaItem WOODEN_SWORD = new VanillaItem(MC1_8, "WOOD_SWORD", "WOODEN_SWORD", 268);
    public static final VanillaItem WOODEN_SHOVEL = new VanillaItem(MC1_8, "WOOD_SPADE", "WOODEN_SHOVEL", 269);
    public static final VanillaItem WOODEN_PICKAXE = new VanillaItem(MC1_8, "WOOD_PICKAXE", "WOODEN_PICKAXE", 270);
    public static final VanillaItem WOODEN_AXE = new VanillaItem(MC1_8, "WOOD_AXE", "WOODEN_AXE", 271);
    public static final VanillaItem STONE_SWORD = new VanillaItem(MC1_8, "STONE_SWORD", "STONE_SWORD", 272);
    public static final VanillaItem STONE_SHOVEL = new VanillaItem(MC1_8, "STONE_SPADE", "STONE_SHOVEL", 273);
    public static final VanillaItem STONE_PICKAXE = new VanillaItem(MC1_8, "STONE_PICKAXE", "STONE_PICKAXE", 274);
    public static final VanillaItem STONE_AXE = new VanillaItem(MC1_8, "STONE_AXE", "STONE_AXE", 275);
    public static final VanillaItem DIAMOND_SWORD = new VanillaItem(MC1_8, "DIAMOND_SWORD", "DIAMOND_SWORD", 276);
    public static final VanillaItem DIAMOND_SHOVEL = new VanillaItem(MC1_8, "DIAMOND_SPADE", "DIAMOND_SHOVEL", 277);
    public static final VanillaItem DIAMOND_PICKAXE = new VanillaItem(MC1_8, "DIAMOND_PICKAXE", "DIAMOND_PICKAXE", 278);
    public static final VanillaItem DIAMOND_AXE = new VanillaItem(MC1_8, "DIAMOND_AXE", "DIAMOND_AXE", 279);
    public static final VanillaItem STICK = new VanillaItem(MC1_8, "STICK", "STICK", 280);
    public static final VanillaItem BOWL = new VanillaItem(MC1_8, "BOWL", "BOWL", 281);
    public static final VanillaItem MUSHROOM_STEW = new VanillaItem(MC1_8, "MUSHROOM_SOUP", "MUSHROOM_STEW", 282);
    public static final VanillaItem GOLDEN_SWORD = new VanillaItem(MC1_8, "GOLD_SWORD", "GOLDEN_SWORD", 283);
    public static final VanillaItem GOLDEN_SHOVEL = new VanillaItem(MC1_8, "GOLD_SPADE", "GOLDEN_SHOVEL", 284);
    public static final VanillaItem GOLDEN_PICKAXE = new VanillaItem(MC1_8, "GOLD_PICKAXE", "GOLDEN_PICKAXE", 285);
    public static final VanillaItem GOLDEN_AXE = new VanillaItem(MC1_8, "GOLD_AXE", "GOLDEN_AXE", 286);
    public static final VanillaItem STRING = new VanillaItem(MC1_8, "STRING", "STRING", 287);
    public static final VanillaItem FEATHER = new VanillaItem(MC1_8, "FEATHER", "FEATHER", 288);
    public static final VanillaItem GUNPOWDER = new VanillaItem(MC1_8, "SULPHUR", "GUNPOWDER", 289);
    public static final VanillaItem WOODEN_HOE = new VanillaItem(MC1_8, "WOOD_HOE", "WOODEN_HOE", 290);
    public static final VanillaItem STONE_HOE = new VanillaItem(MC1_8, "STONE_HOE", "STONE_HOE", 291);
    public static final VanillaItem IRON_HOE = new VanillaItem(MC1_8, "IRON_HOE", "IRON_HOE", 292);
    public static final VanillaItem DIAMOND_HOE = new VanillaItem(MC1_8, "DIAMOND_HOE", "DIAMOND_HOE", 293);
    public static final VanillaItem GOLDEN_HOE = new VanillaItem(MC1_8, "GOLD_HOE", "GOLDEN_HOE", 294);
    public static final VanillaItem WHEAT_SEEDS = new VanillaItem(MC1_8, "SEEDS", "WHEAT_SEEDS", 295);
    public static final VanillaItem WHEAT = new VanillaItem(MC1_8, "WHEAT", "WHEAT", 296);
    public static final VanillaItem BREAD = new VanillaItem(MC1_8, "BREAD", "BREAD", 297);
    public static final VanillaItem LEATHER_HELMET = new VanillaItem(MC1_8, "LEATHER_HELMET", "LEATHER_HELMET", 298);
    public static final VanillaItem LEATHER_CHESTPLATE = new VanillaItem(MC1_8, "LEATHER_CHESTPLATE", "LEATHER_CHESTPLATE", 299);
    public static final VanillaItem LEATHER_LEGGINGS = new VanillaItem(MC1_8, "LEATHER_LEGGINGS", "LEATHER_LEGGINGS", 300);
    public static final VanillaItem LEATHER_BOOTS = new VanillaItem(MC1_8, "LEATHER_BOOTS", "LEATHER_BOOTS", 301);
    public static final VanillaItem CHAINMAIL_HELMET = new VanillaItem(MC1_8, "CHAINMAIL_HELMET", "CHAINMAIL_HELMET", 302);
    public static final VanillaItem CHAINMAIL_CHESTPLATE = new VanillaItem(MC1_8, "CHAINMAIL_CHESTPLATE", "CHAINMAIL_CHESTPLATE", 303);
    public static final VanillaItem CHAINMAIL_LEGGINGS = new VanillaItem(MC1_8, "CHAINMAIL_LEGGINGS", "CHAINMAIL_LEGGINGS", 304);
    public static final VanillaItem CHAINMAIL_BOOTS = new VanillaItem(MC1_8, "CHAINMAIL_BOOTS", "CHAINMAIL_BOOTS", 305);
    public static final VanillaItem IRON_HELMET = new VanillaItem(MC1_8, "IRON_HELMET", "IRON_HELMET", 306);
    public static final VanillaItem IRON_CHESTPLATE = new VanillaItem(MC1_8, "IRON_CHESTPLATE", "IRON_CHESTPLATE", 307);
    public static final VanillaItem IRON_LEGGINGS = new VanillaItem(MC1_8, "IRON_LEGGINGS", "IRON_LEGGINGS", 308);
    public static final VanillaItem IRON_BOOTS = new VanillaItem(MC1_8, "IRON_BOOTS", "IRON_BOOTS", 309);
    public static final VanillaItem DIAMOND_HELMET = new VanillaItem(MC1_8, "DIAMOND_HELMET", "DIAMOND_HELMET", 310);
    public static final VanillaItem DIAMOND_CHESTPLATE = new VanillaItem(MC1_8, "DIAMOND_CHESTPLATE", "DIAMOND_CHESTPLATE", 311);
    public static final VanillaItem DIAMOND_LEGGINGS = new VanillaItem(MC1_8, "DIAMOND_LEGGINGS", "DIAMOND_LEGGINGS", 312);
    public static final VanillaItem DIAMOND_BOOTS = new VanillaItem(MC1_8, "DIAMOND_BOOTS", "DIAMOND_BOOTS", 313);
    public static final VanillaItem GOLDEN_HELMET = new VanillaItem(MC1_8, "GOLD_HELMET", "GOLDEN_HELMET", 314);
    public static final VanillaItem GOLDEN_CHESTPLATE = new VanillaItem(MC1_8, "GOLD_CHESTPLATE", "GOLDEN_CHESTPLATE", 315);
    public static final VanillaItem GOLDEN_LEGGINGS = new VanillaItem(MC1_8, "GOLD_LEGGINGS", "GOLDEN_LEGGINGS", 316);
    public static final VanillaItem GOLDEN_BOOTS = new VanillaItem(MC1_8, "GOLD_BOOTS", "GOLDEN_BOOTS", 317);
    public static final VanillaItem FLINT = new VanillaItem(MC1_8, "FLINT", "FLINT", 318);
    public static final VanillaItem PORKCHOP = new VanillaItem(MC1_8, "PORK", "PORKCHOP", 319);
    public static final VanillaItem COOKED_PORKCHOP = new VanillaItem(MC1_8, "GRILLED_PORK", "COOKED_PORKCHOP", 320);
    public static final VanillaItem PAINTING = new VanillaItem(MC1_8, "PAINTING", "PAINTING", 321);
    public static final VanillaItem GOLDEN_APPLE = new VanillaItem(MC1_8, "GOLDEN_APPLE", "GOLDEN_APPLE", 322);
    public static final VanillaItem ENCHANTED_GOLDEN_APPLE = new VanillaItem(MC1_8, "GOLDEN_APPLE", "ENCHANTED_GOLDEN_APPLE", 322, (short) 1);
    public static final VanillaItem SIGN = new VanillaItem(MC1_8, "SIGN", "SIGN", 323);
    public static final VanillaItem OAK_DOOR = new VanillaItem(MC1_8, "WOOD_DOOR", "OAK_DOOR", 324);
    public static final VanillaItem BUCKET = new VanillaItem(MC1_8, "BUCKET", "BUCKET", 325);
    public static final VanillaItem WATER_BUCKET = new VanillaItem(MC1_8, "WATER_BUCKET", "WATER_BUCKET", 326);
    public static final VanillaItem LAVA_BUCKET = new VanillaItem(MC1_8, "LAVA_BUCKET", "LAVA_BUCKET", 327);
    public static final VanillaItem MINECART = new VanillaItem(MC1_8, "MINECART", "MINECART", 328);
    public static final VanillaItem SADDLE = new VanillaItem(MC1_8, "SADDLE", "SADDLE", 329);
    public static final VanillaItem IRON_DOOR = new VanillaItem(MC1_8, "IRON_DOOR", "IRON_DOOR", 330);
    public static final VanillaItem REDSTONE = new VanillaItem(MC1_8, "REDSTONE", "REDSTONE", 331);
    public static final VanillaItem SNOWBALL = new VanillaItem(MC1_8, "SNOW_BALL", "SNOWBALL", 332);
    public static final VanillaItem OAK_BOAT = new VanillaItem(MC1_8, "BOAT", "OAK_BOAT", 333);
    public static final VanillaItem LEATHER = new VanillaItem(MC1_8, "LEATHER", "LEATHER", 334);
    public static final VanillaItem MILK_BUCKET = new VanillaItem(MC1_8, "MILK_BUCKET", "MILK_BUCKET", 335);
    public static final VanillaItem BRICK = new VanillaItem(MC1_8, "CLAY_BRICK", "BRICK", 336);
    public static final VanillaItem CLAY_BALL = new VanillaItem(MC1_8, "CLAY_BALL", "CLAY_BALL", 337);
    public static final VanillaItem SUGAR_CANE = new VanillaItem(MC1_8, "SUGAR_CANE", "SUGAR_CANE", 338);
    public static final VanillaItem PAPER = new VanillaItem(MC1_8, "PAPER", "PAPER", 339);
    public static final VanillaItem BOOK = new VanillaItem(MC1_8, "BOOK", "BOOK", 340);
    public static final VanillaItem SLIME_BALL = new VanillaItem(MC1_8, "SLIME_BALL", "SLIME_BALL", 341);
    public static final VanillaItem CHEST_MINECART = new VanillaItem(MC1_8, "STORAGE_MINECART", "CHEST_MINECART", 342);
    public static final VanillaItem FURNACE_MINECART = new VanillaItem(MC1_8, "POWERED_MINECART", "FURNACE_MINECART", 343);
    public static final VanillaItem EGG = new VanillaItem(MC1_8, "EGG", "EGG", 344);
    public static final VanillaItem COMPASS = new VanillaItem(MC1_8, "COMPASS", "COMPASS", 345);
    public static final VanillaItem FISHING_ROD = new VanillaItem(MC1_8, "FISHING_ROD", "FISHING_ROD", 346);
    public static final VanillaItem CLOCK = new VanillaItem(MC1_8, "WATCH", "CLOCK", 347);
    public static final VanillaItem GLOWSTONE_DUST = new VanillaItem(MC1_8, "GLOWSTONE_DUST", "GLOWSTONE_DUST", 348);
    public static final VanillaItem COD = new VanillaItem(MC1_8, "RAW_FISH", "COD", 349);
    public static final VanillaItem SALMON = new VanillaItem(MC1_8, "RAW_FISH", "SALMON", 349, (short) 1);
    public static final VanillaItem CLOWNFISH = new VanillaItem(MC1_8, "RAW_FISH", "CLOWNFISH", 349, (short) 2);
    public static final VanillaItem PUFFERFISH = new VanillaItem(MC1_8, "RAW_FISH", "PUFFERFISH", 349, (short) 3);
    public static final VanillaItem COOKED_COD = new VanillaItem(MC1_8, "COOKED_FISH", "COOKED_COD", 350);
    public static final VanillaItem COOKED_SALMON = new VanillaItem(MC1_8, "COOKED_FISH", "COOKED_SALMON", 350, (short) 1);
    public static final VanillaItem INK_SAC = new VanillaItem(MC1_8, "INK_SACK", "INK_SAC", 351);
    public static final VanillaItem ROSE_RED = new VanillaItem(MC1_8, "INK_SACK", "ROSE_RED", 351, (short) 1);
    public static final VanillaItem CACTUS_GREEN = new VanillaItem(MC1_8, "INK_SACK", "CACTUS_GREEN", 351, (short) 2);
    public static final VanillaItem COCOA_BEANS = new VanillaItem(MC1_8, "INK_SACK", "COCOA_BEANS", 351, (short) 3);
    public static final VanillaItem LAPIS_LAZULI = new VanillaItem(MC1_8, "INK_SACK", "LAPIS_LAZULI", 351, (short) 4);
    public static final VanillaItem PURPLE_DYE = new VanillaItem(MC1_8, "INK_SACK", "PURPLE_DYE", 351, (short) 5);
    public static final VanillaItem CYAN_DYE = new VanillaItem(MC1_8, "INK_SACK", "CYAN_DYE", 351, (short) 6);
    public static final VanillaItem LIGHT_GRAY_DYE = new VanillaItem(MC1_8, "INK_SACK", "LIGHT_GRAY_DYE", 351, (short) 7);
    public static final VanillaItem GRAY_DYE = new VanillaItem(MC1_8, "INK_SACK", "GRAY_DYE", 351, (short) 8);
    public static final VanillaItem PINK_DYE = new VanillaItem(MC1_8, "INK_SACK", "PINK_DYE", 351, (short) 9);
    public static final VanillaItem LIME_DYE = new VanillaItem(MC1_8, "INK_SACK", "LIME_DYE", 351, (short) 10);
    public static final VanillaItem DANDELION_YELLOW = new VanillaItem(MC1_8, "INK_SACK", "DANDELION_YELLOW", 351, (short) 11);
    public static final VanillaItem LIGHT_BLUE_DYE = new VanillaItem(MC1_8, "INK_SACK", "LIGHT_BLUE_DYE", 351, (short) 12);
    public static final VanillaItem MAGENTA_DYE = new VanillaItem(MC1_8, "INK_SACK", "MAGENTA_DYE", 351, (short) 13);
    public static final VanillaItem ORANGE_DYE = new VanillaItem(MC1_8, "INK_SACK", "ORANGE_DYE", 351, (short) 14);
    public static final VanillaItem BONE_MEAL = new VanillaItem(MC1_8, "INK_SACK", "BONE_MEAL", 351, (short) 15);
    public static final VanillaItem BONE = new VanillaItem(MC1_8, "BONE", "BONE", 352);
    public static final VanillaItem SUGAR = new VanillaItem(MC1_8, "SUGAR", "SUGAR", 353);
    public static final VanillaItem CAKE = new VanillaItem(MC1_8, "CAKE", "CAKE", 354);
    public static final VanillaItem WHITE_BED = new VanillaItem(MC1_8, "BED", "WHITE_BED", 355);
    public static final VanillaItem ORANGE_BED = new VanillaItem(MC1_12, "BED", "ORANGE_BED", 355, (short) 1);
    public static final VanillaItem MAGENTA_BED = new VanillaItem(MC1_12, "BED", "MAGENTA_BED", 355, (short) 2);
    public static final VanillaItem LIGHT_BLUE_BED = new VanillaItem(MC1_12, "BED", "LIGHT_BLUE_BED", 355, (short) 3);
    public static final VanillaItem YELLOW_BED = new VanillaItem(MC1_12, "BED", "YELLOW_BED", 355, (short) 4);
    public static final VanillaItem LIME_BED = new VanillaItem(MC1_12, "BED", "LIME_BED", 355, (short) 5);
    public static final VanillaItem PINK_BED = new VanillaItem(MC1_12, "BED", "PINK_BED", 355, (short) 6);
    public static final VanillaItem GRAY_BED = new VanillaItem(MC1_12, "BED", "GRAY_BED", 355, (short) 7);
    public static final VanillaItem LIGHT_GRAY_BED = new VanillaItem(MC1_12, "BED", "LIGHT_GRAY_BED", 355, (short) 8);
    public static final VanillaItem CYAN_BED = new VanillaItem(MC1_12, "BED", "CYAN_BED", 355, (short) 9);
    public static final VanillaItem PURPLE_BED = new VanillaItem(MC1_12, "BED", "PURPLE_BED", 355, (short) 10);
    public static final VanillaItem BLUE_BED = new VanillaItem(MC1_12, "BED", "BLUE_BED", 355, (short) 11);
    public static final VanillaItem BROWN_BED = new VanillaItem(MC1_12, "BED", "BROWN_BED", 355, (short) 12);
    public static final VanillaItem GREEN_BED = new VanillaItem(MC1_12, "BED", "GREEN_BED", 355, (short) 13);
    public static final VanillaItem RED_BED = new VanillaItem(MC1_12, "BED", "RED_BED", 355, (short) 14);
    public static final VanillaItem BLACK_BED = new VanillaItem(MC1_12, "BED", "BLACK_BED", 355, (short) 15);
    public static final VanillaItem REPEATER = new VanillaItem(MC1_8, "DIODE", "REPEATER", 356);
    public static final VanillaItem COOKIE = new VanillaItem(MC1_8, "COOKIE", "COOKIE", 357);
    public static final VanillaItem FILLED_MAP = new VanillaItem(MC1_8, "MAP", "FILLED_MAP", 358);
    public static final VanillaItem SHEARS = new VanillaItem(MC1_8, "SHEARS", "SHEARS", 359);
    public static final VanillaItem MELON = new VanillaItem(MC1_8, "MELON", "MELON", 360);
    public static final VanillaItem PUMPKIN_SEEDS = new VanillaItem(MC1_8, "PUMPKIN_SEEDS", "PUMPKIN_SEEDS", 361);
    public static final VanillaItem MELON_SEEDS = new VanillaItem(MC1_8, "MELON_SEEDS", "MELON_SEEDS", 362);
    public static final VanillaItem BEEF = new VanillaItem(MC1_8, "RAW_BEEF", "BEEF", 363);
    public static final VanillaItem COOKED_BEEF = new VanillaItem(MC1_8, "COOKED_BEEF", "COOKED_BEEF", 364);
    public static final VanillaItem CHICKEN = new VanillaItem(MC1_8, "RAW_CHICKEN", "CHICKEN", 365);
    public static final VanillaItem COOKED_CHICKEN = new VanillaItem(MC1_8, "COOKED_CHICKEN", "COOKED_CHICKEN", 366);
    public static final VanillaItem ROTTEN_FLESH = new VanillaItem(MC1_8, "ROTTEN_FLESH", "ROTTEN_FLESH", 367);
    public static final VanillaItem ENDER_PEARL = new VanillaItem(MC1_8, "ENDER_PEARL", "ENDER_PEARL", 368);
    public static final VanillaItem BLAZE_ROD = new VanillaItem(MC1_8, "BLAZE_ROD", "BLAZE_ROD", 369);
    public static final VanillaItem GHAST_TEAR = new VanillaItem(MC1_8, "GHAST_TEAR", "GHAST_TEAR", 370);
    public static final VanillaItem GOLD_NUGGET = new VanillaItem(MC1_8, "GOLD_NUGGET", "GOLD_NUGGET", 371);
    public static final VanillaItem NETHER_WART = new VanillaItem(MC1_8, "NETHER_STALK", "NETHER_WART", 372);
    public static final VanillaItem POTION = new VanillaItem(MC1_8, "POTION", "POTION", 373);
    public static final VanillaItem GLASS_BOTTLE = new VanillaItem(MC1_8, "GLASS_BOTTLE", "GLASS_BOTTLE", 374);
    public static final VanillaItem SPIDER_EYE = new VanillaItem(MC1_8, "SPIDER_EYE", "SPIDER_EYE", 375);
    public static final VanillaItem FERMENTED_SPIDER_EYE = new VanillaItem(MC1_8, "FERMENTED_SPIDER_EYE", "FERMENTED_SPIDER_EYE", 376);
    public static final VanillaItem BLAZE_POWDER = new VanillaItem(MC1_8, "BLAZE_POWDER", "BLAZE_POWDER", 377);
    public static final VanillaItem MAGMA_CREAM = new VanillaItem(MC1_8, "MAGMA_CREAM", "MAGMA_CREAM", 378);
    public static final VanillaItem BREWING_STAND = new VanillaItem(MC1_8, "BREWING_STAND_ITEM", "BREWING_STAND", 379);
    public static final VanillaItem CAULDRON = new VanillaItem(MC1_8, "CAULDRON_ITEM", "CAULDRON", 380);
    public static final VanillaItem ENDER_EYE = new VanillaItem(MC1_8, "EYE_OF_ENDER", "ENDER_EYE", 381);
    public static final VanillaItem SPECKLED_MELON = new VanillaItem(MC1_8, "SPECKLED_MELON", "SPECKLED_MELON", 382);
    public static final VanillaItem EXPERIENCE_BOTTLE = new VanillaItem(MC1_8, "EXP_BOTTLE", "EXPERIENCE_BOTTLE", 384);
    public static final VanillaItem FIRE_CHARGE = new VanillaItem(MC1_8, "FIREBALL", "FIRE_CHARGE", 385);
    public static final VanillaItem WRITABLE_BOOK = new VanillaItem(MC1_8, "BOOK_AND_QUILL", "WRITABLE_BOOK", 386);
    public static final VanillaItem WRITTEN_BOOK = new VanillaItem(MC1_8, "WRITTEN_BOOK", "RITTEN_BOOK", 387);
    public static final VanillaItem EMERALD = new VanillaItem(MC1_8, "EMERALD", "EMERALD", 388);
    public static final VanillaItem ITEM_FRAME = new VanillaItem(MC1_8, "ITEM_FRAME", "ITEM_FRAME", 389);
    public static final VanillaItem FLOWER_POT = new VanillaItem(MC1_8, "FLOWER_POT_ITEM", "FLOWER_POT", 390);
    // 1.13: Filled flower pots
    public static final VanillaItem CARROT = new VanillaItem(MC1_8, "CARROT_ITEM", "CARROT", 391);
    public static final VanillaItem POTATO = new VanillaItem(MC1_8, "POTATO_ITEM", "POTATO", 392);
    public static final VanillaItem BAKED_POTATO = new VanillaItem(MC1_8, "BAKED_POTATO", "BAKED_POTATO", 393);
    public static final VanillaItem POISONOUS_POTATO = new VanillaItem(MC1_8, "POISONOUS_POTATO", "POISONOUS_POTATO", 394);
    public static final VanillaItem MAP = new VanillaItem(MC1_8, "EMPTY_MAP", "MAP", 395);
    public static final VanillaItem GOLDEN_CARROT = new VanillaItem(MC1_8, "GOLDEN_CARROT", "GOLDEN_CARROT", 396);
    public static final VanillaItem SKELETON_SKULL = new VanillaItem(MC1_8, "SKULL_ITEM", "SKELETON_SKULL", 397);
    public static final VanillaItem WITHER_SKELETON_SKULL = new VanillaItem(MC1_8, "SKULL_ITEM", "WITHER_SKELETON_SKULL", 397, (short) 1);
    public static final VanillaItem ZOMBIE_HEAD = new VanillaItem(MC1_8, "SKULL_ITEM", "ZOMBIE_HEAD", 397, (short) 2);
    public static final VanillaItem PLAYER_HEAD = new VanillaItem(MC1_8, "SKULL_ITEM", "PLAYER_HEAD", 397, (short) 3);
    public static final VanillaItem CREEPER_HEAD = new VanillaItem(MC1_8, "SKULL_ITEM", "CREEPER_HEAD", 397, (short) 4);
    public static final VanillaItem DRAGON_HEAD = new VanillaItem(MC1_9, "SKULL_ITEM", "DRAGON_HEAD", 397, (short) 5);
    public static final VanillaItem CARROT_ON_A_STICK = new VanillaItem(MC1_8, "CARROT_STICK", "CARROT_ON_A_STICK", 398);
    public static final VanillaItem NETHER_STAR = new VanillaItem(MC1_8, "NETHER_STAR", "NETHER_STAR", 399);
    public static final VanillaItem PUMPKIN_PIE = new VanillaItem(MC1_8, "PUMPKIN_PIE", "PUMPKIN_PIE", 400);
    public static final VanillaItem FIREWORK_ROCKET = new VanillaItem(MC1_8, "FIREWORK", "FIREWORK_ROCKET", 401);
    public static final VanillaItem FIREWORK_STAR = new VanillaItem(MC1_8, "FIREWORK_CHARGE", "FIREWORK_STAR", 402);
    public static final VanillaItem ENCHANTED_BOOK = new VanillaItem(MC1_8, "ENCHANTED_BOOK", "ENCHANTED_BOOK", 403);
    public static final VanillaItem COMPARATOR = new VanillaItem(MC1_8, "REDSTONE_COMPARATOR", "COMPARATOR", 404);
    public static final VanillaItem NETHER_BRICK = new VanillaItem(MC1_8, "NETHER_BRICK_ITEM", "NETHER_BRICK", 405);
    public static final VanillaItem QUARTZ = new VanillaItem(MC1_8, "QUARTZ", "QUARTZ", 406);
    public static final VanillaItem TNT_MINECART = new VanillaItem(MC1_8, "EXPLOSIVE_MINECART", "TNT_MINECART", 407);
    public static final VanillaItem HOPPER_MINECART = new VanillaItem(MC1_8, "HOPPER_MINECART", "HOPPER_MINECART", 408);
    public static final VanillaItem PRISMARINE_SHARD = new VanillaItem(MC1_8, "PRISMARINE_SHARD", "PRISMARINE_SHARD", 409);
    public static final VanillaItem PRISMARINE_CRYSTALS = new VanillaItem(MC1_8, "PRISMARINE_CRYSTALS", "PRISMARINE_CRYSTALS", 410);
    public static final VanillaItem RABBIT = new VanillaItem(MC1_8, "RABBIT", "RABBIT", 411);
    public static final VanillaItem COOKED_RABBIT = new VanillaItem(MC1_8, "COOKED_RABBIT", "COOKED_RABBIT", 412);
    public static final VanillaItem RABBIT_STEW = new VanillaItem(MC1_8, "RABBIT_STEW", "RABBIT_STEW", 413);
    public static final VanillaItem RABBIT_FOOT = new VanillaItem(MC1_8, "RABBIT_FOOT", "RABBIT_FOOT", 414);
    public static final VanillaItem RABBIT_HIDE = new VanillaItem(MC1_8, "RABBIT_HIDE", "RABBIT_HIDE", 415);
    public static final VanillaItem ARMOR_STAND = new VanillaItem(MC1_8, "ARMOR_STAND", "ARMOR_STAND", 416);
    public static final VanillaItem IRON_HORSE_ARMOR = new VanillaItem(MC1_8, "IRON_BARDING", "IRON_HORSE_ARMOR", 417);
    public static final VanillaItem GOLDEN_HORSE_ARMOR = new VanillaItem(MC1_8, "GOLD_BARDING", "GOLDEN_HORSE_ARMOR", 418);
    public static final VanillaItem DIAMOND_HORSE_ARMOR = new VanillaItem(MC1_8, "DIAMOND_BARDING", "DIAMOND_HORSE_ARMOR", 419);
    public static final VanillaItem LEAD = new VanillaItem(MC1_8, "LEASH", "LEAD", 420);
    public static final VanillaItem NAME_TAG = new VanillaItem(MC1_8, "NAME_TAG", "NAME_TAG", 421);
    public static final VanillaItem COMMAND_BLOCK_MINECART = new VanillaItem(MC1_8, "COMMAND_MINECART", "COMMAND_BLOCK_MINECART", 422);
    public static final VanillaItem MUTTON = new VanillaItem(MC1_8, "MUTTON", "MUTTON", 423);
    public static final VanillaItem COOKED_MUTTON = new VanillaItem(MC1_8, "COOKED_MUTTON", "COOKED_MUTTON", 424);
    public static final VanillaItem WHITE_BANNER = new VanillaItem(MC1_8, "BANNER", "WHITE_BANNER", 425);
    public static final VanillaItem ORANGE_BANNER = new VanillaItem(MC1_8, "BANNER", "ORANGE_BANNER", 425, (short) 1);
    public static final VanillaItem MAGENTA_BANNER = new VanillaItem(MC1_8, "BANNER", "MAGENTA_BANNER", 425, (short) 2);
    public static final VanillaItem LIGHT_BLUE_BANNER = new VanillaItem(MC1_8, "BANNER", "LIGHT_BLUE_BANNER", 425, (short) 3);
    public static final VanillaItem YELLOW_BANNER = new VanillaItem(MC1_8, "BANNER", "YELLOW_BANNER", 425, (short) 4);
    public static final VanillaItem LIME_BANNER = new VanillaItem(MC1_8, "BANNER", "LIME_BANNER", 425, (short) 5);
    public static final VanillaItem PINK_BANNER = new VanillaItem(MC1_8, "BANNER", "PINK_BANNER", 425, (short) 6);
    public static final VanillaItem GRAY_BANNER = new VanillaItem(MC1_8, "BANNER", "GRAY_BANNER", 425, (short) 7);
    public static final VanillaItem LIGHT_GRAY_BANNER = new VanillaItem(MC1_8, "BANNER", "LIGHT_GRAY_BANNER", 425, (short) 8);
    public static final VanillaItem CYAN_BANNER = new VanillaItem(MC1_8, "BANNER", "CYAN_BANNER", 425, (short) 9);
    public static final VanillaItem PURPLE_BANNER = new VanillaItem(MC1_8, "BANNER", "PURPLE_BANNER", 425, (short) 10);
    public static final VanillaItem BLUE_BANNER = new VanillaItem(MC1_8, "BANNER", "BLUE_BANNER", 425, (short) 11);
    public static final VanillaItem BROWN_BANNER = new VanillaItem(MC1_8, "BANNER", "BROWN_BANNER", 425, (short) 12);
    public static final VanillaItem GREEN_BANNER = new VanillaItem(MC1_8, "BANNER", "GREEN_BANNER", 425, (short) 13);
    public static final VanillaItem RED_BANNER = new VanillaItem(MC1_8, "BANNER", "RED_BANNER", 425, (short) 14);
    public static final VanillaItem BLACK_BANNER = new VanillaItem(MC1_8, "BANNER", "BLACK_BANNER", 425, (short) 15);
    public static final VanillaItem END_CRYSTAL = new VanillaItem(MC1_9, "END_CRYSTAL", "END_CRYSTAL", 426);
    public static final VanillaItem SPRUCE_DOOR = new VanillaItem(MC1_8, "SPRUCE_DOOR_ITEM", "SPRUCE_DOOR", 427);
    public static final VanillaItem BIRCH_DOOR = new VanillaItem(MC1_8, "BIRCH_DOOR_ITEM", "BIRCH_DOOR", 428);
    public static final VanillaItem JUNGLE_DOOR = new VanillaItem(MC1_8, "JUNGLE_DOOR_ITEM", "JUNGLE_DOOR", 429);
    public static final VanillaItem ACACIA_DOOR = new VanillaItem(MC1_8, "ACACIA_DOOR_ITEM", "ACACIA_DOOR", 430);
    public static final VanillaItem DARK_OAK_DOOR = new VanillaItem(MC1_8, "DARK_OAK_DOOR_ITEM", "DARK_OAK_DOOR", 431);
    public static final VanillaItem CHORUS_FRUIT = new VanillaItem(MC1_9, "CHORUS_FRUIT", "CHORUS_FRUIT", 432);
    public static final VanillaItem CHORUS_FRUIT_POPPED = new VanillaItem(MC1_9, "CHORUS_FRUIT_POPPED", "CHORUS_FRUIT_POPPED", 433);
    public static final VanillaItem BEETROOT = new VanillaItem(MC1_9, "BEETROOT", "BEETROOT", 434);
    public static final VanillaItem BEETROOT_SEEDS = new VanillaItem(MC1_9, "BEETROOT_SEEDS", "BEETROOT_SEEDS", 435);
    public static final VanillaItem BEETROOT_SOUP = new VanillaItem(MC1_9, "BEETROOT_SOUP", "BEETROOT_SOUP", 436);
    public static final VanillaItem DRAGON_BREATH = new VanillaItem(MC1_9, "DRAGONS_BREATH", "DRAGON_BREATH", 437);
    public static final VanillaItem SPLASH_POTION = new VanillaItem(MC1_9, "SPLASH_POTION", "SPLASH_POTION", 438);
    public static final VanillaItem SPECTRAL_ARROW = new VanillaItem(MC1_9, "SPECTRAL_ARROW", "SPECTRAL_ARROW", 439);
    public static final VanillaItem TIPPED_ARROW = new VanillaItem(MC1_9, "TIPPED_ARROW", "TIPPED_ARROW", 440);
    public static final VanillaItem LINGERING_POTION = new VanillaItem(MC1_9, "LINGERING_POTION", "LINGERING_POTION", 441);
    public static final VanillaItem SHIELD = new VanillaItem(MC1_9, "SHIELD", "SHIELD", 442);
    public static final VanillaItem ELYTRA = new VanillaItem(MC1_9, "ELYTRA", "ELYTRA", 443);
    public static final VanillaItem SPRUCE_BOAT = new VanillaItem(MC1_9, "BOAT_SPRUCE", "SPRUCE_BOAT", 444);
    public static final VanillaItem BIRCH_BOAT = new VanillaItem(MC1_9, "BOAT_BIRCH", "BIRCH_BOAT", 445);
    public static final VanillaItem JUNGLE_BOAT = new VanillaItem(MC1_9, "BOAT_JUNGLE", "JUNGLE_BOAT", 446);
    public static final VanillaItem ACACIA_BOAT = new VanillaItem(MC1_9, "BOAT_ACACIA", "ACACIA_BOAT", 447);
    public static final VanillaItem DARK_OAK_BOAT = new VanillaItem(MC1_9, "BOAT_DARK_OAK", "DARK_OAK_BOAT", 448);
    public static final VanillaItem TOTEM_OF_UNDYING = new VanillaItem(MC1_11, "TOTEM", "TOTEM_OF_UNDYING", 449);
    public static final VanillaItem SHULKER_SHELL = new VanillaItem(MC1_11, "SHULKER_SHELL", "SHULKER_SHELL", 450);
    public static final VanillaItem IRON_NUGGET = new VanillaItem(MC1_11_1, "IRON_NUGGET", "IRON_NUGGET", 452);
    public static final VanillaItem KNOWLEDGE_BOOK = new VanillaItem(MC1_12, "KNOWLEDGE_BOOK", "KNOWLEDGE_BOOK", 453);
    public static final VanillaItem MUSIC_DISC_13 = new VanillaItem(MC1_8, "GOLD_RECORD", "MUSIC_DISC_13", 2256);
    public static final VanillaItem MUSIC_DISC_CAT = new VanillaItem(MC1_8, "GREEN_RECORD", "MUSIC_DISC_CAT", 2257);
    public static final VanillaItem MUSIC_DISC_BLOCKS = new VanillaItem(MC1_8, "RECORD_3", "MUSIC_DISC_BLOCKS", 2258);
    public static final VanillaItem MUSIC_DISC_CHIRP = new VanillaItem(MC1_8, "RECORD_4", "MUSIC_DISC_CHIRP", 2259);
    public static final VanillaItem MUSIC_DISC_FAR = new VanillaItem(MC1_8, "RECORD_5", "MUSIC_DISC_FAR", 2260);
    public static final VanillaItem MUSIC_DISC_MALL = new VanillaItem(MC1_8, "RECORD_6", "MUSIC_DISC_MALL", 2261);
    public static final VanillaItem MUSIC_DISC_MELLOHI = new VanillaItem(MC1_8, "RECORD_7", "MUSIC_DISC_MELLOHI", 2262);
    public static final VanillaItem MUSIC_DISC_STAL = new VanillaItem(MC1_8, "RECORD_8", "MUSIC_DISC_STAL", 2263);
    public static final VanillaItem MUSIC_DISC_STRAD = new VanillaItem(MC1_8, "RECORD_9", "MUSIC_DISC_STRAD", 2264);
    public static final VanillaItem MUSIC_DISC_WARD = new VanillaItem(MC1_8, "RECORD_10", "MUSIC_DISC_WARD", 2265);
    public static final VanillaItem MUSIC_DISC_11 = new VanillaItem(MC1_8, "RECORD_11", "MUSIC_DISC_11", 2266);
    public static final VanillaItem MUSIC_DISC_WAIT = new VanillaItem(MC1_8, "RECORD_12", "MUSIC_DISC_WAIT", 2267);
    // NEW SPAWN EGGS

    // OLD / MERGED BLOCKS
    public static final OldBlockItem WHITE_BED_BLOCK = new OldBlockItem(MC1_8, "BED_BLOCK", WHITE_BED, 26);
    public static final OldBlockItem ORANGE_BED_BLOCK = new OldBlockItem(MC1_8, "BED_BLOCK", ORANGE_BED, 26, (short) 1);
    public static final OldBlockItem DOUBLE_STONE_SLAB = new OldBlockItem(MC1_8, "DOUBLE_STEP", STONE_SLAB, 43);
    public static final OldBlockItem DOUBLE_SANDSTONE_SLAB = new OldBlockItem(MC1_8, "DOUBLE_STEP", SANDSTONE_SLAB, 43, (short) 1);
    public static final OldBlockItem DOUBLE_PETRIFIED_OAK_SLAB = new OldBlockItem(MC1_8, "DOUBLE_STEP", PETRIFIED_OAK_SLAB, 43, (short) 2);
    public static final OldBlockItem DOUBLE_COBBLESTONE_SLAB = new OldBlockItem(MC1_8, "DOUBLE_STEP", COBBLESTONE_SLAB, 43, (short) 3);
    public static final OldBlockItem DOUBLE_BRICK_SLAB = new OldBlockItem(MC1_8, "DOUBLE_STEP", BRICK_SLAB, 43, (short) 4);
    public static final OldBlockItem DOUBLE_STONE_BRICK_SLAB = new OldBlockItem(MC1_8, "DOUBLE_STEP", STONE_BRICK_SLAB, 43, (short) 5);
    public static final OldBlockItem DOUBLE_NETHER_BRICK_SLAB = new OldBlockItem(MC1_8, "DOUBLE_STEP", NETHER_BRICK_SLAB, 43, (short) 6);
    public static final OldBlockItem DOUBLE_QUARTZ_SLAB = new OldBlockItem(MC1_8, "DOUBLE_STEP", QUARTZ_SLAB, 43, (short) 7);
    public static final OldBlockItem CROPS = new OldBlockItem(MC1_8, "CROPS", WHEAT, 59);
    public static final OldBlockItem BURNING_FURNACE = new OldBlockItem(MC1_8, "BURNING_FURNACE", FURNACE, 62);
    public static final OldBlockItem SIGN_POST = new OldBlockItem(MC1_8, "SIGN_POST", SIGN, 63);
    public static final OldBlockItem WOODEN_DOOR = new OldBlockItem(MC1_8, "WOODEN_DOOR", OAK_DOOR, 64);
    public static final OldBlockItem IRON_DOOR_BLOCK = new OldBlockItem(MC1_8, "IRON_DOOR_BLOCK", IRON_DOOR, 71);
    public static final OldBlockItem GLOWING_REDSTONE_ORE = new OldBlockItem(MC1_8, "GLOWING_REDSTONE_ORE", REDSTONE_ORE, 74);
    public static final OldBlockItem REDSTONE_TORCH_OFF = new OldBlockItem(MC1_8, "REDSTONE_TORCH_OFF", REDSTONE_TORCH, 75);
    public static final OldBlockItem SUGAR_CANE_BLOCK = new OldBlockItem(MC1_8, "SUGAR_CANE_BLOCK", SUGAR_CANE, 83);
    public static final OldBlockItem CAKE_BLOCK = new OldBlockItem(MC1_8, "CAKE_BLOCK", CAKE, 92);
    public static final OldBlockItem DIODE_BLOCK_OFF = new OldBlockItem(MC1_8, "DIODE_BLOCK_OFF", REPEATER, 93);
    public static final OldBlockItem DIODE_BLOCK_ON = new OldBlockItem(MC1_8, "DIODE_BLOCK_ON", REPEATER, 94);
    public static final OldBlockItem NETHER_WARTS = new OldBlockItem(MC1_8, "NETHER_WARTS", NETHER_WART, 115);
    public static final OldBlockItem BREWING_STAND_BLOCK = new OldBlockItem(MC1_8, "BREWING_STAND", BREWING_STAND, 117);
    public static final OldBlockItem CAULDRON_BLOCK = new OldBlockItem(MC1_8, "CAULDRON", CAULDRON, 118);
    public static final OldBlockItem REDSTONE_LAMP_OFF = new OldBlockItem(MC1_8, "REDSTONE_LAMP_OFF", REDSTONE_LAMP, 123);
    public static final OldBlockItem DOUBLE_OAK_SLAB = new OldBlockItem(MC1_8, "WOOD_DOUBLE_STEP", OAK_SLAB, 125);
    public static final OldBlockItem DOUBLE_SPRUCE_SLAB = new OldBlockItem(MC1_8, "WOOD_DOUBLE_STEP", SPRUCE_SLAB, 125, (short) 1);
    public static final OldBlockItem DOUBLE_BIRCH_SLAB = new OldBlockItem(MC1_8, "WOOD_DOUBLE_STEP", BIRCH_SLAB, 125, (short) 2);
    public static final OldBlockItem DOUBLE_JUNGLE_SLAB = new OldBlockItem(MC1_8, "WOOD_DOUBLE_STEP", JUNGLE_SLAB, 125, (short) 3);
    public static final OldBlockItem DOUBLE_ACACIA_SLAB = new OldBlockItem(MC1_8, "WOOD_DOUBLE_STEP", ACACIA_SLAB, 125, (short) 4);
    public static final OldBlockItem DOUBLE_DARK_OAK_SLAB = new OldBlockItem(MC1_8, "WOOD_DOUBLE_STEP", DARK_OAK_SLAB, 125, (short) 5);
    public static final OldBlockItem FLOWER_POT_BLOCK = new OldBlockItem(MC1_8, "FLOWER_POT", FLOWER_POT, 140);
    public static final OldBlockItem SKULL_BLOCK = new OldBlockItem(MC1_8, "SKULL", SKELETON_SKULL, 144);
    public static final OldBlockItem WITHER_SKELETON_SKULL_BLOCK = new OldBlockItem(MC1_8, "SKULL", WITHER_SKELETON_SKULL, 144, (short) 1);
    public static final OldBlockItem ZOMBIE_HEAD_BLOCK = new OldBlockItem(MC1_8, "SKULL", ZOMBIE_HEAD, 144, (short) 2);
    public static final OldBlockItem PLAYER_HEAD_BLOCK = new OldBlockItem(MC1_8, "SKULL", PLAYER_HEAD, 144, (short) 3);
    public static final OldBlockItem CREEPER_HEAD_BLOCK = new OldBlockItem(MC1_8, "SKULL", CREEPER_HEAD, 144, (short) 4);
    public static final OldBlockItem DRAGON_HEAD_BLOCK = new OldBlockItem(MC1_9, "SKULL", DRAGON_HEAD, 144, (short) 5);
    public static final OldBlockItem REDSTONE_COMPARATOR_OFF = new OldBlockItem(MC1_8, "REDSTONE_COMPARATOR_OFF", COMPARATOR, 149);
    public static final OldBlockItem REDSTONE_COMPARATOR_ON = new OldBlockItem(MC1_8, "REDSTONE_COMPARATOR_ON", COMPARATOR, 150);
    public static final OldBlockItem WHITE_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", WHITE_BANNER, 176);
    public static final OldBlockItem ORANGE_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", ORANGE_BANNER, 176, (short) 1);
    public static final OldBlockItem MAGENTA_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", MAGENTA_BANNER, 176, (short) 2);
    public static final OldBlockItem LIGHT_STANDING_BLUE_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", LIGHT_BLUE_BANNER, 176, (short) 3);
    public static final OldBlockItem YELLOW_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", YELLOW_BANNER, 176, (short) 4);
    public static final OldBlockItem LIME_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", LIME_BANNER, 176, (short) 5);
    public static final OldBlockItem PINK_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", PINK_BANNER, 176, (short) 6);
    public static final OldBlockItem GRAY_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", GRAY_BANNER, 176, (short) 7);
    public static final OldBlockItem LIGHT_STANDING_GRAY_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", LIGHT_GRAY_BANNER, 176, (short) 8);
    public static final OldBlockItem CYAN_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", CYAN_BANNER, 176, (short) 9);
    public static final OldBlockItem PURPLE_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", PURPLE_BANNER, 176, (short) 10);
    public static final OldBlockItem BLUE_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", BLUE_BANNER, 176, (short) 11);
    public static final OldBlockItem BROWN_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", BROWN_BANNER, 176, (short) 12);
    public static final OldBlockItem GREEN_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", GREEN_BANNER, 176, (short) 13);
    public static final OldBlockItem RED_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", RED_BANNER, 176, (short) 14);
    public static final OldBlockItem BLACK_STANDING_BANNER = new OldBlockItem(MC1_8, "STANDING_BANNER", BLACK_BANNER, 176, (short) 15);
    public static final OldBlockItem DAYLIGHT_DETECTOR_INVERTED = new OldBlockItem(MC1_8, "DAYLIGHT_DETECTOR_INVERTED", DAYLIGHT_DETECTOR, 178);
    public static final OldBlockItem DOUBLE_RED_SANDSTONE_SLAB = new OldBlockItem(MC1_8, "DOUBLE_STONE_SLAB2", RED_SANDSTONE_SLAB, 181);
    public static final OldBlockItem SPRUCE_DOOR_BLOCK = new OldBlockItem(MC1_8, "SPRUCE_DOOR", SPRUCE_DOOR, 193);
    public static final OldBlockItem BIRCH_DOOR_BLOCK = new OldBlockItem(MC1_8, "BIRCH_DOOR", BIRCH_DOOR, 194);
    public static final OldBlockItem JUNGLE_DOOR_BLOCK = new OldBlockItem(MC1_8, "JUNGLE_DOOR", JUNGLE_DOOR, 195);
    public static final OldBlockItem ACACIA_DOOR_BLOCK = new OldBlockItem(MC1_8, "ACACIA_DOOR", ACACIA_DOOR, 196);
    public static final OldBlockItem DARK_OAK_DOOR_BLOCK = new OldBlockItem(MC1_8, "DARK_OAK_DOOR", DARK_OAK_DOOR, 197);

    // REMOVED ITEMS
    public static final VanillaItem MONSTER_EGG = new VanillaItem(MC1_8, MC1_12_2, "MONSTER_EGG", "BAT_SPAWN_EGG", 383);

    private static Collection<VanillaItem> values = new ArrayList<>();
    private static Collection<VanillaItem> loaded = new ArrayList<>();

    static {
        values.add(AIR);
        values.add(STONE);
        values.add(GRANITE);
        values.add(POLISHED_GRANITE);
        values.add(DIORITE);
        values.add(POLISHED_DIORITE);
        values.add(ANDESITE);
        values.add(POLISHED_ANDESITE);
        values.add(GRASS_BLOCK);
        values.add(DIRT);
        values.add(COARSE_DIRT);
        values.add(PODZOL);
        values.add(COBBLESTONE);
        values.add(OAK_PLANKS);
        values.add(SPRUCE_PLANKS);
        values.add(BIRCH_PLANKS);
        values.add(JUNGLE_PLANKS);
        values.add(ACACIA_PLANKS);
        values.add(DARK_OAK_PLANKS);
        values.add(OAK_SAPLING);
        values.add(SPRUCE_SAPLING);
        values.add(BIRCH_SAPLING);
        values.add(JUNGLE_SAPLING);
        values.add(ACACIA_SAPLING);
        values.add(DARK_OAK_SAPLING);
        values.add(BEDROCK);
        values.add(FLOWING_WATER);
        values.add(WATER);
        values.add(FLOWING_LAVA);
        values.add(LAVA);
        values.add(SAND);
        values.add(RED_SAND);
        values.add(GRAVEL);
        values.add(GOLD_ORE);
        values.add(IRON_ORE);
        values.add(COAL_ORE);
        values.add(OAK_LOG);
        values.add(SPRUCE_LOG);
        values.add(BIRCH_LOG);
        values.add(JUNGLE_LOG);
        values.add(OAK_LEAVES);
        values.add(SPRUCE_LEAVES);
        values.add(BIRCH_LEAVES);
        values.add(JUNGLE_LEAVES);
        values.add(SPONGE);
        values.add(WET_SPONGE);
        values.add(GLASS);
        values.add(LAPIS_ORE);
        values.add(LAPIS_BLOCK);
        values.add(DISPENSER);
        values.add(SANDSTONE);
        values.add(CHISELED_SANDSTONE);
        values.add(CUT_SANDSTONE);
        values.add(NOTE_BLOCK);
        values.add(POWERED_RAIL);
        values.add(DETECTOR_RAIL);
        values.add(STICKY_PISTON);
        values.add(COBWEB);
        values.add(UNUSED_DEAD_BUSH);
        values.add(GRASS);
        values.add(FERN);
        values.add(DEAD_BUSH);
        values.add(PISTON);
        values.add(PISTON_HEAD);
        values.add(WHITE_WOOL);
        values.add(ORANGE_WOOL);
        values.add(MAGENTA_WOOL);
        values.add(LIGHT_BLUE_WOOL);
        values.add(YELLOW_WOOL);
        values.add(LIME_WOOL);
        values.add(PINK_WOOL);
        values.add(GRAY_WOOL);
        values.add(LIGHT_GRAY_WOOL);
        values.add(CYAN_WOOL);
        values.add(PURPLE_WOOL);
        values.add(BLUE_WOOL);
        values.add(BROWN_WOOL);
        values.add(GREEN_WOOL);
        values.add(RED_WOOL);
        values.add(BLACK_WOOL);
        values.add(MOVING_PISTON);
        values.add(DANDELION);
        values.add(POPPY);
        values.add(BLUE_ORCHID);
        values.add(ALLIUM);
        values.add(AZURE_BLUET);
        values.add(RED_TULIP);
        values.add(ORANGE_TULIP);
        values.add(WHITE_TULIP);
        values.add(PINK_TULIP);
        values.add(OXEYE_DAISY);
        values.add(BROWN_MUSHROOM);
        values.add(RED_MUSHROOM);
        values.add(GOLD_BLOCK);
        values.add(AIIRON_BLOCKR);
        values.add(STONE_SLAB);
        values.add(SANDSTONE_SLAB);
        values.add(PETRIFIED_OAK_SLAB);
        values.add(COBBLESTONE_SLAB);
        values.add(BRICK_SLAB);
        values.add(STONE_BRICK_SLAB);
        values.add(NETHER_BRICK_SLAB);
        values.add(QUARTZ_SLAB);
        values.add(BRICKS);
        values.add(TNT);
        values.add(BOOKSHELF);
        values.add(MOSSY_COBBLESTONE);
        values.add(OBSIDIAN);
        values.add(TORCH);
        values.add(FIRE);
        values.add(MOB_SPAWNER);
        values.add(OAK_STAIRS);
        values.add(CHEST);
        values.add(REDSTONE_WIRE);
        values.add(DIAMOND_ORE);
        values.add(DIAMOND_BLOCK);
        values.add(CRAFTING_TABLE);
        values.add(LADDER);
        values.add(RAIL);
        values.add(COBBLESTONE_STAIRS);
        values.add(WALL_SIGN);
        values.add(LEVER);
        values.add(STONE_PRESSURE_PLATE);
        values.add(FARMLAND);
        values.add(FURNACE);
        values.add(OAK_PRESSURE_PLATE);
        values.add(REDSTONE_ORE);
        values.add(REDSTONE_TORCH);
        values.add(STONE_BUTTON);
        values.add(SNOW);
        values.add(ICE);
        values.add(SNOW_BLOCK);
        values.add(CACTUS);
        values.add(CLAY);
        values.add(JUKEBOX);
        values.add(OAK_FENCE);
        values.add(PUMPKIN);
        values.add(NETHERRACK);
        values.add(SOUL_SAND);
        values.add(GLOWSTONE);
        values.add(PORTAL);
        values.add(JACK_O_LANTERN);
        values.add(WHITE_STAINED_GLASS);
        values.add(ORANGE_STAINED_GLASS);
        values.add(MAGENTA_STAINED_GLASS);
        values.add(LIGHT_BLUE_STAINED_GLASS);
        values.add(YELLOW_STAINED_GLASS);
        values.add(LIME_STAINED_GLASS);
        values.add(PINK_STAINED_GLASS);
        values.add(GRAY_STAINED_GLASS);
        values.add(LIGHT_GRAY_STAINED_GLASS);
        values.add(CYAN_STAINED_GLASS);
        values.add(PURPLE_STAINED_GLASS);
        values.add(BLUE_STAINED_GLASS);
        values.add(BROWN_STAINED_GLASS);
        values.add(GREEN_STAINED_GLASS);
        values.add(RED_STAINED_GLASS);
        values.add(BLACK_STAINED_GLASS);
        values.add(OAK_TRAPDOOR);
        values.add(INFESTED_STONE);
        values.add(INFESTED_COBBLESTONE);
        values.add(INFESTED_STONE_BRICKS);
        values.add(INFESTED_MOSSY_STONE_BRICKS);
        values.add(INFESTED_CRACKED_STONE_BRICKS);
        values.add(INFESTED_CHISELED_STONE_BRICKS);
        values.add(STONE_BRICKS);
        values.add(MOSSY_STONE_BRICKS);
        values.add(CRACKED_STONE_BRICKS);
        values.add(CHISELED_STONE_BRICKS);
        values.add(BROWN_MUSHROOM_BLOCK);
        values.add(RED_MUSHROOM_BLOCK);
        values.add(IRON_BARS);
        values.add(GLASS_PANE);
        values.add(MELON_BLOCK);
        values.add(PUMPKIN_STEM);
        values.add(MELON_STEM);
        values.add(VINE);
        values.add(OAK_FENCE_GATE);
        values.add(BRICK_STAIRS);
        values.add(STONE_BRICK_STAIRS);
        values.add(MYCELIUM);
        values.add(LILY_PAD);
        values.add(NETHER_BRICKS);
        values.add(NETHER_BRICK_FENCE);
        values.add(NETHER_BRICK_STAIRS);
        values.add(ENCHANTING_TABLE);
        values.add(END_PORTAL);
        values.add(END_PORTAL_FRAME);
        values.add(END_STONE);
        values.add(DRAGON_EGG);
        values.add(REDSTONE_LAMP);
        values.add(OAK_SLAB);
        values.add(SPRUCE_SLAB);
        values.add(BIRCH_SLAB);
        values.add(JUNGLE_SLAB);
        values.add(ACACIA_SLAB);
        values.add(DARK_OAK_SLAB);
        values.add(COCOA);
        values.add(SANDSTONE_STAIRS);
        values.add(EMERALD_ORE);
        values.add(ENDER_CHEST);
        values.add(TRIPWIRE_HOOK);
        values.add(TRIPWIRE);
        values.add(EMERALD_BLOCK);
        values.add(SPRUCE_STAIRS);
        values.add(BIRCH_STAIRS);
        values.add(JUNGLE_STAIRS);
        values.add(COMMAND_BLOCK);
        values.add(BEACON);
        values.add(COBBLESTONE_WALL);
        values.add(MOSSY_COBBLESTONE_WALL);
        values.add(CARROTS);
        values.add(POTATOES);
        values.add(OAK_BUTTON);
        values.add(ANVIL);
        values.add(CHIPPED_ANVIL);
        values.add(DAMAGED_ANVIL);
        values.add(TRAPPED_CHEST);
        values.add(LIGHT_WEIGHTED_PRESSURE_PLATE);
        values.add(HEAVY_WEIGHTED_PRESSURE_PLATE);
        values.add(DAYLIGHT_DETECTOR);
        values.add(REDSTONE_BLOCK);
        values.add(QUARTZ_ORE);
        values.add(HOPPER);
        values.add(QUARTZ_BLOCK);
        values.add(CHISELED_QUARTZ_BLOCK);
        values.add(QUARTZ_PILLAR);
        values.add(QUARTZ_STAIRS);
        values.add(ACTIVATOR_RAIL);
        values.add(DROPPER);
        values.add(WHITE_TERRACOTTA);
        values.add(ORANGE_TERRACOTTA);
        values.add(MAGENTA_TERRACOTTA);
        values.add(LIGHT_BLUE_TERRACOTTA);
        values.add(YELLOW_TERRACOTTA);
        values.add(LIME_TERRACOTTA);
        values.add(PINK_TERRACOTTA);
        values.add(GRAY_TERRACOTTA);
        values.add(LIGHT_GRAY_TERRACOTTA);
        values.add(CYAN_TERRACOTTA);
        values.add(PURPLE_TERRACOTTA);
        values.add(BLUE_TERRACOTTA);
        values.add(BROWN_TERRACOTTA);
        values.add(GREEN_TERRACOTTA);
        values.add(RED_TERRACOTTA);
        values.add(BLACK_TERRACOTTA);
        values.add(WHITE_STAINED_GLASS_PANE);
        values.add(ORANGE_STAINED_GLASS_PANE);
        values.add(MAGENTA_STAINED_GLASS_PANE);
        values.add(LIGHT_BLUE_STAINED_GLASS_PANE);
        values.add(YELLOW_STAINED_GLASS_PANE);
        values.add(LIME_STAINED_GLASS_PANE);
        values.add(PINK_STAINED_GLASS_PANE);
        values.add(GRAY_STAINED_GLASS_PANE);
        values.add(LIGHT_GRAY_STAINED_GLASS_PANE);
        values.add(CYAN_STAINED_GLASS_PANE);
        values.add(PURPLE_STAINED_GLASS_PANE);
        values.add(BLUE_STAINED_GLASS_PANE);
        values.add(BROWN_STAINED_GLASS_PANE);
        values.add(GREEN_STAINED_GLASS_PANE);
        values.add(RED_STAINED_GLASS_PANE);
        values.add(BLACK_STAINED_GLASS_PANE);
        values.add(ACACIA_LEAVES);
        values.add(DARK_OAK_LEAVES);
        values.add(ACACIA_LOG);
        values.add(DARK_OAK_LOG);
        values.add(ACACIA_STAIRS);
        values.add(DARK_OAK_STAIRS);
        values.add(SLIME_BLOCK);
        values.add(BARRIER);
        values.add(IRON_TRAPDOOR);
        values.add(PRISMARINE);
        values.add(PRISMARINE_BRICKS);
        values.add(DARK_PRISMARINE);
        values.add(SEA_LANTERN);
        values.add(HAY_BLOCK);
        values.add(WHITE_CARPET);
        values.add(ORANGE_CARPET);
        values.add(MAGENTA_CARPET);
        values.add(LIGHT_BLUE_CARPET);
        values.add(YELLOW_CARPET);
        values.add(LIME_CARPET);
        values.add(PINK_CARPET);
        values.add(GRAY_CARPET);
        values.add(LIGHT_GRAY_CARPET);
        values.add(CYAN_CARPET);
        values.add(PURPLE_CARPET);
        values.add(BLUE_CARPET);
        values.add(BROWN_CARPET);
        values.add(GREEN_CARPET);
        values.add(RED_CARPET);
        values.add(BLACK_CARPET);
        values.add(TERRACOTTA);
        values.add(COAL_BLOCK);
        values.add(PACKED_ICE);
        values.add(SUNFLOWER);
        values.add(LILAC);
        values.add(TALL_GRASS);
        values.add(LARGE_FERN);
        values.add(ROSE_BUSH);
        values.add(PEONY);
        values.add(WHITE_WALL_BANNER);
        values.add(ORANGE_WALL_BANNER);
        values.add(MAGENTA_WALL_BANNER);
        values.add(LIGHT_BLUE_WALL_BANNER);
        values.add(YELLOW_WALL_BANNER);
        values.add(LIME_WALL_BANNER);
        values.add(PINK_WALL_BANNER);
        values.add(GRAY_WALL_BANNER);
        values.add(LIGHT_GRAY_WALL_BANNER);
        values.add(CYAN_WALL_BANNER);
        values.add(PURPLE_WALL_BANNER);
        values.add(BLUE_WALL_BANNER);
        values.add(BROWN_WALL_BANNER);
        values.add(GREEN_WALL_BANNER);
        values.add(RED_WALL_BANNER);
        values.add(BLACK_WALL_BANNER);
        values.add(RED_SANDSTONE);
        values.add(CHISELED_RED_SANDSTONE);
        values.add(CUT_RED_SANDSTONE);
        values.add(RED_SANDSTONE_STAIRS);
        values.add(RED_SANDSTONE_SLAB);
        values.add(SPRUCE_FENCE_GATE);
        values.add(BIRCH_FENCE_GATE);
        values.add(JUNGLE_FENCE_GATE);
        values.add(DARK_OAK_FENCE_GATE);
        values.add(ACACIA_FENCE_GATE);
        values.add(SPRUCE_FENCE);
        values.add(BIRCH_FENCE);
        values.add(JUNGLE_FENCE);
        values.add(DARK_OAK_FENCE);
        values.add(ACACIA_FENCE);
        values.add(END_ROD);
        values.add(CHORUS_PLANT);
        values.add(CHORUS_FLOWER);
        values.add(PURPUR_BLOCK);
        values.add(PURPUR_PILLAR);
        values.add(PURPUR_STAIRS);
        values.add(PURPUR_DOUBLE_SLAB);
        values.add(PURPUR_SLAB);
        values.add(END_BRICKS);
        values.add(BEETROOT_BLOCK);
        values.add(GRASS_PATH);
        values.add(END_GATEWAY);
        values.add(REPEATING_COMMAND_BLOCK);
        values.add(CHAIN_COMMAND_BLOCK);
        values.add(FROSTED_ICE);
        values.add(MAGMA_BLOCK);
        values.add(NETHER_WART_BLOCK);
        values.add(RED_NETHER_BRICK);
        values.add(BONE_BLOCK);
        values.add(STRUCTURE_VOID);
        values.add(OBSERVER);
        values.add(WHITE_SHULKER_BOX);
        values.add(ORANGE_SHULKER_BOX);
        values.add(MAGENTA_SHULKER_BOX);
        values.add(LIGHT_BLUE_SHULKER_BOX);
        values.add(YELLOW_SHULKER_BOX);
        values.add(LIME_SHULKER_BOX);
        values.add(PINK_SHULKER_BOX);
        values.add(GRAY_SHULKER_BOX);
        values.add(SILVER_SHULKER_BOX);
        values.add(CYAN_SHULKER_BOX);
        values.add(PURPLE_SHULKER_BOX);
        values.add(BLUE_SHULKER_BOX);
        values.add(BROWN_SHULKER_BOX);
        values.add(GREEN_SHULKER_BOX);
        values.add(RED_SHULKER_BOX);
        values.add(BLACK_SHULKER_BOX);
        values.add(WHITE_GLAZED_TERRACOTTA);
        values.add(ORANGE_GLAZED_TERRACOTTA);
        values.add(MAGENTA_GLAZED_TERRACOTTA);
        values.add(LIGHT_BLUE_GLAZED_TERRACOTTA);
        values.add(YELLOW_GLAZED_TERRACOTTA);
        values.add(LIME_GLAZED_TERRACOTTA);
        values.add(PINK_GLAZED_TERRACOTTA);
        values.add(GRAY_GLAZED_TERRACOTTA);
        values.add(SILVER_GLAZED_TERRACOTTA);
        values.add(CYAN_GLAZED_TERRACOTTA);
        values.add(PURPLE_GLAZED_TERRACOTTA);
        values.add(BLUE_GLAZED_TERRACOTTA);
        values.add(BROWN_GLAZED_TERRACOTTA);
        values.add(GREEN_GLAZED_TERRACOTTA);
        values.add(RED_GLAZED_TERRACOTTA);
        values.add(BLACK_GLAZED_TERRACOTTA);
        values.add(WHITE_CONCRETE);
        values.add(ORANGE_CONCRETE);
        values.add(MAGENTA_CONCRETE);
        values.add(LIGHT_BLUE_CONCRETE);
        values.add(YELLOW_CONCRETE);
        values.add(LIME_CONCRETE);
        values.add(PINK_CONCRETE);
        values.add(GRAY_CONCRETE);
        values.add(LIGHT_GRAY_CONCRETE);
        values.add(CYAN_CONCRETE);
        values.add(PURPLE_CONCRETE);
        values.add(BLUE_CONCRETE);
        values.add(BROWN_CONCRETE);
        values.add(GREEN_CONCRETE);
        values.add(RED_CONCRETE);
        values.add(BLACK_CONCRETE);
        values.add(WHITE_CONCRETE_POWDER);
        values.add(ORANGE_CONCRETE_POWDER);
        values.add(MAGENTA_CONCRETE_POWDER);
        values.add(LIGHT_BLUE_CONCRETE_POWDER);
        values.add(YELLOW_CONCRETE_POWDER);
        values.add(LIME_CONCRETE_POWDER);
        values.add(PINK_CONCRETE_POWDER);
        values.add(GRAY_CONCRETE_POWDER);
        values.add(LIGHT_GRAY_CONCRETE_POWDER);
        values.add(CYAN_CONCRETE_POWDER);
        values.add(PURPLE_CONCRETE_POWDER);
        values.add(BLUE_CONCRETE_POWDER);
        values.add(BROWN_CONCRETE_POWDER);
        values.add(GREEN_CONCRETE_POWDER);
        values.add(RED_CONCRETE_POWDER);
        values.add(BLACK_CONCRETE_POWDER);
        values.add(STRUCTURE_BLOCK);
        values.add(IRON_SHOVEL);
        values.add(IRON_PICKAXE);
        values.add(IRON_AXE);
        values.add(FLINT_AND_STEEL);
        values.add(APPLE);
        values.add(BOW);
        values.add(ARROW);
        values.add(COAL);
        values.add(CHARCOAL);
        values.add(DIAMOND);
        values.add(IRON_INGOT);
        values.add(GOLD_INGOT);
        values.add(IRON_SWORD);
        values.add(WOODEN_SWORD);
        values.add(WOODEN_SHOVEL);
        values.add(WOODEN_PICKAXE);
        values.add(WOODEN_AXE);
        values.add(STONE_SWORD);
        values.add(STONE_SHOVEL);
        values.add(STONE_PICKAXE);
        values.add(STONE_AXE);
        values.add(DIAMOND_SWORD);
        values.add(DIAMOND_SHOVEL);
        values.add(DIAMOND_PICKAXE);
        values.add(DIAMOND_AXE);
        values.add(STICK);
        values.add(BOWL);
        values.add(MUSHROOM_STEW);
        values.add(GOLDEN_SWORD);
        values.add(GOLDEN_SHOVEL);
        values.add(GOLDEN_PICKAXE);
        values.add(GOLDEN_AXE);
        values.add(STRING);
        values.add(FEATHER);
        values.add(GUNPOWDER);
        values.add(WOODEN_HOE);
        values.add(STONE_HOE);
        values.add(IRON_HOE);
        values.add(DIAMOND_HOE);
        values.add(GOLDEN_HOE);
        values.add(WHEAT_SEEDS);
        values.add(WHEAT);
        values.add(BREAD);
        values.add(LEATHER_HELMET);
        values.add(LEATHER_CHESTPLATE);
        values.add(LEATHER_LEGGINGS);
        values.add(LEATHER_BOOTS);
        values.add(CHAINMAIL_HELMET);
        values.add(CHAINMAIL_CHESTPLATE);
        values.add(CHAINMAIL_LEGGINGS);
        values.add(CHAINMAIL_BOOTS);
        values.add(IRON_HELMET);
        values.add(IRON_CHESTPLATE);
        values.add(IRON_LEGGINGS);
        values.add(IRON_BOOTS);
        values.add(DIAMOND_HELMET);
        values.add(DIAMOND_CHESTPLATE);
        values.add(DIAMOND_LEGGINGS);
        values.add(DIAMOND_BOOTS);
        values.add(GOLDEN_HELMET);
        values.add(GOLDEN_CHESTPLATE);
        values.add(GOLDEN_LEGGINGS);
        values.add(GOLDEN_BOOTS);
        values.add(FLINT);
        values.add(PORKCHOP);
        values.add(COOKED_PORKCHOP);
        values.add(PAINTING);
        values.add(GOLDEN_APPLE);
        values.add(ENCHANTED_GOLDEN_APPLE);
        values.add(SIGN);
        values.add(OAK_DOOR);
        values.add(BUCKET);
        values.add(WATER_BUCKET);
        values.add(LAVA_BUCKET);
        values.add(MINECART);
        values.add(SADDLE);
        values.add(IRON_DOOR);
        values.add(REDSTONE);
        values.add(SNOWBALL);
        values.add(OAK_BOAT);
        values.add(LEATHER);
        values.add(MILK_BUCKET);
        values.add(BRICK);
        values.add(CLAY_BALL);
        values.add(SUGAR_CANE);
        values.add(PAPER);
        values.add(BOOK);
        values.add(SLIME_BALL);
        values.add(CHEST_MINECART);
        values.add(FURNACE_MINECART);
        values.add(EGG);
        values.add(COMPASS);
        values.add(FISHING_ROD);
        values.add(CLOCK);
        values.add(GLOWSTONE_DUST);
        values.add(COD);
        values.add(SALMON);
        values.add(CLOWNFISH);
        values.add(PUFFERFISH);
        values.add(COOKED_COD);
        values.add(COOKED_SALMON);
        values.add(INK_SAC);
        values.add(ROSE_RED);
        values.add(CACTUS_GREEN);
        values.add(COCOA_BEANS);
        values.add(LAPIS_LAZULI);
        values.add(PURPLE_DYE);
        values.add(CYAN_DYE);
        values.add(LIGHT_GRAY_DYE);
        values.add(GRAY_DYE);
        values.add(PINK_DYE);
        values.add(LIME_DYE);
        values.add(DANDELION_YELLOW);
        values.add(LIGHT_BLUE_DYE);
        values.add(MAGENTA_DYE);
        values.add(ORANGE_DYE);
        values.add(BONE_MEAL);
        values.add(BONE);
        values.add(SUGAR);
        values.add(CAKE);
        values.add(WHITE_BED);
        values.add(ORANGE_BED);
        values.add(MAGENTA_BED);
        values.add(LIGHT_BLUE_BED);
        values.add(YELLOW_BED);
        values.add(LIME_BED);
        values.add(PINK_BED);
        values.add(GRAY_BED);
        values.add(LIGHT_GRAY_BED);
        values.add(CYAN_BED);
        values.add(PURPLE_BED);
        values.add(BLUE_BED);
        values.add(BROWN_BED);
        values.add(GREEN_BED);
        values.add(RED_BED);
        values.add(BLACK_BED);
        values.add(REPEATER);
        values.add(COOKIE);
        values.add(FILLED_MAP);
        values.add(SHEARS);
        values.add(MELON);
        values.add(PUMPKIN_SEEDS);
        values.add(MELON_SEEDS);
        values.add(BEEF);
        values.add(COOKED_BEEF);
        values.add(CHICKEN);
        values.add(COOKED_CHICKEN);
        values.add(ROTTEN_FLESH);
        values.add(ENDER_PEARL);
        values.add(BLAZE_ROD);
        values.add(GHAST_TEAR);
        values.add(GOLD_NUGGET);
        values.add(NETHER_WART);
        values.add(POTION);
        values.add(GLASS_BOTTLE);
        values.add(SPIDER_EYE);
        values.add(FERMENTED_SPIDER_EYE);
        values.add(BLAZE_POWDER);
        values.add(MAGMA_CREAM);
        values.add(BREWING_STAND);
        values.add(CAULDRON);
        values.add(ENDER_EYE);
        values.add(SPECKLED_MELON);
        values.add(EXPERIENCE_BOTTLE);
        values.add(FIRE_CHARGE);
        values.add(WRITABLE_BOOK);
        values.add(WRITTEN_BOOK);
        values.add(EMERALD);
        values.add(ITEM_FRAME);
        values.add(FLOWER_POT);
        values.add(CARROT);
        values.add(POTATO);
        values.add(BAKED_POTATO);
        values.add(POISONOUS_POTATO);
        values.add(MAP);
        values.add(GOLDEN_CARROT);
        values.add(SKELETON_SKULL);
        values.add(WITHER_SKELETON_SKULL);
        values.add(ZOMBIE_HEAD);
        values.add(PLAYER_HEAD);
        values.add(CREEPER_HEAD);
        values.add(DRAGON_HEAD);
        values.add(CARROT_ON_A_STICK);
        values.add(NETHER_STAR);
        values.add(PUMPKIN_PIE);
        values.add(FIREWORK_ROCKET);
        values.add(FIREWORK_STAR);
        values.add(ENCHANTED_BOOK);
        values.add(COMPARATOR);
        values.add(NETHER_BRICK);
        values.add(QUARTZ);
        values.add(TNT_MINECART);
        values.add(HOPPER_MINECART);
        values.add(PRISMARINE_SHARD);
        values.add(PRISMARINE_CRYSTALS);
        values.add(RABBIT);
        values.add(COOKED_RABBIT);
        values.add(RABBIT_STEW);
        values.add(RABBIT_FOOT);
        values.add(RABBIT_HIDE);
        values.add(ARMOR_STAND);
        values.add(IRON_HORSE_ARMOR);
        values.add(GOLDEN_HORSE_ARMOR);
        values.add(DIAMOND_HORSE_ARMOR);
        values.add(LEAD);
        values.add(NAME_TAG);
        values.add(COMMAND_BLOCK_MINECART);
        values.add(MUTTON);
        values.add(COOKED_MUTTON);
        values.add(WHITE_BANNER);
        values.add(ORANGE_BANNER);
        values.add(MAGENTA_BANNER);
        values.add(LIGHT_BLUE_BANNER);
        values.add(YELLOW_BANNER);
        values.add(LIME_BANNER);
        values.add(PINK_BANNER);
        values.add(GRAY_BANNER);
        values.add(LIGHT_GRAY_BANNER);
        values.add(CYAN_BANNER);
        values.add(PURPLE_BANNER);
        values.add(BLUE_BANNER);
        values.add(BROWN_BANNER);
        values.add(GREEN_BANNER);
        values.add(RED_BANNER);
        values.add(BLACK_BANNER);
        values.add(END_CRYSTAL);
        values.add(SPRUCE_DOOR);
        values.add(BIRCH_DOOR);
        values.add(JUNGLE_DOOR);
        values.add(ACACIA_DOOR);
        values.add(DARK_OAK_DOOR);
        values.add(CHORUS_FRUIT);
        values.add(CHORUS_FRUIT_POPPED);
        values.add(BEETROOT);
        values.add(BEETROOT_SEEDS);
        values.add(BEETROOT_SOUP);
        values.add(DRAGON_BREATH);
        values.add(SPLASH_POTION);
        values.add(SPECTRAL_ARROW);
        values.add(TIPPED_ARROW);
        values.add(LINGERING_POTION);
        values.add(SHIELD);
        values.add(ELYTRA);
        values.add(SPRUCE_BOAT);
        values.add(BIRCH_BOAT);
        values.add(JUNGLE_BOAT);
        values.add(ACACIA_BOAT);
        values.add(DARK_OAK_BOAT);
        values.add(TOTEM_OF_UNDYING);
        values.add(SHULKER_SHELL);
        values.add(IRON_NUGGET);
        values.add(KNOWLEDGE_BOOK);
        values.add(MUSIC_DISC_13);
        values.add(MUSIC_DISC_CAT);
        values.add(MUSIC_DISC_BLOCKS);
        values.add(MUSIC_DISC_CHIRP);
        values.add(MUSIC_DISC_FAR);
        values.add(MUSIC_DISC_MALL);
        values.add(MUSIC_DISC_MELLOHI);
        values.add(MUSIC_DISC_STAL);
        values.add(MUSIC_DISC_STRAD);
        values.add(MUSIC_DISC_WARD);
        values.add(MUSIC_DISC_11);
        values.add(MUSIC_DISC_WAIT);
        values.add(WHITE_BED_BLOCK);
        values.add(ORANGE_BED_BLOCK);
        values.add(DOUBLE_STONE_SLAB);
        values.add(DOUBLE_SANDSTONE_SLAB);
        values.add(DOUBLE_PETRIFIED_OAK_SLAB);
        values.add(DOUBLE_COBBLESTONE_SLAB);
        values.add(DOUBLE_BRICK_SLAB);
        values.add(DOUBLE_STONE_BRICK_SLAB);
        values.add(DOUBLE_NETHER_BRICK_SLAB);
        values.add(DOUBLE_QUARTZ_SLAB);
        values.add(CROPS);
        values.add(BURNING_FURNACE);
        values.add(SIGN_POST);
        values.add(WOODEN_DOOR);
        values.add(IRON_DOOR_BLOCK);
        values.add(GLOWING_REDSTONE_ORE);
        values.add(REDSTONE_TORCH_OFF);
        values.add(SUGAR_CANE_BLOCK);
        values.add(CAKE_BLOCK);
        values.add(DIODE_BLOCK_OFF);
        values.add(DIODE_BLOCK_ON);
        values.add(NETHER_WARTS);
        values.add(BREWING_STAND_BLOCK);
        values.add(CAULDRON_BLOCK);
        values.add(REDSTONE_LAMP_OFF);
        values.add(DOUBLE_OAK_SLAB);
        values.add(DOUBLE_SPRUCE_SLAB);
        values.add(DOUBLE_BIRCH_SLAB);
        values.add(DOUBLE_JUNGLE_SLAB);
        values.add(DOUBLE_ACACIA_SLAB);
        values.add(DOUBLE_DARK_OAK_SLAB);
        values.add(FLOWER_POT_BLOCK);
        values.add(SKULL_BLOCK);
        values.add(WITHER_SKELETON_SKULL_BLOCK);
        values.add(ZOMBIE_HEAD_BLOCK);
        values.add(PLAYER_HEAD_BLOCK);
        values.add(CREEPER_HEAD_BLOCK);
        values.add(DRAGON_HEAD_BLOCK);
        values.add(REDSTONE_COMPARATOR_OFF);
        values.add(REDSTONE_COMPARATOR_ON);
        values.add(WHITE_STANDING_BANNER);
        values.add(ORANGE_STANDING_BANNER);
        values.add(MAGENTA_STANDING_BANNER);
        values.add(LIGHT_STANDING_BLUE_BANNER);
        values.add(YELLOW_STANDING_BANNER);
        values.add(LIME_STANDING_BANNER);
        values.add(PINK_STANDING_BANNER);
        values.add(GRAY_STANDING_BANNER);
        values.add(LIGHT_STANDING_GRAY_BANNER);
        values.add(CYAN_STANDING_BANNER);
        values.add(PURPLE_STANDING_BANNER);
        values.add(BLUE_STANDING_BANNER);
        values.add(BROWN_STANDING_BANNER);
        values.add(GREEN_STANDING_BANNER);
        values.add(RED_STANDING_BANNER);
        values.add(BLACK_STANDING_BANNER);
        values.add(DAYLIGHT_DETECTOR_INVERTED);
        values.add(DOUBLE_RED_SANDSTONE_SLAB);
        values.add(SPRUCE_DOOR_BLOCK);
        values.add(BIRCH_DOOR_BLOCK);
        values.add(JUNGLE_DOOR_BLOCK);
        values.add(ACACIA_DOOR_BLOCK);
        values.add(DARK_OAK_DOOR_BLOCK);
        values.add(MONSTER_EGG);

        for (VanillaItem vi : values) {
            if (vi.isAvailable()) {
                loaded.add(vi);
            }
        }
    }

    /**
     * @return
     * all vanilla items that are known
     */
    public static Collection<VanillaItem> values() {
        return values;
    }

    /**
     * @return
     * all vanilla items that exist in this runtime environment
     */
    public static Collection<VanillaItem> getLoaded() {
        return loaded;
    }

    private Version firstVersion;
    private Version lastVersion;
    private String oldName;
    private String newName;
    private int numeric;
    private short data;

    protected VanillaItem(String oldName, String newName, int numeric) {
        this(MC1_8, oldName, newName, numeric);
    }

    protected VanillaItem(String oldName, String newName, int numeric, short data) {
        this(MC1_8, oldName, newName, numeric, data);
    }

    protected VanillaItem(Version firstVersion, String oldName, String newName, int numeric) {
        this(firstVersion, oldName, newName, numeric, (short) 0);
    }

    protected VanillaItem(Version firstVersion, String oldName, String newName, int numeric, short data) {
        this(firstVersion, null, oldName, newName, numeric, data);
    }

    protected VanillaItem(Version firstVersion, String newName) {
        this(firstVersion, null, newName);
    }

    protected VanillaItem(Version firstVersion, Version lastVersion, String newName) {
        this(firstVersion, lastVersion, new String(), newName, -1, (short) 0);
    }

    protected VanillaItem(Version firstVersion, Version lastVersion, String oldName, String newName, int numeric) {
        this(firstVersion, lastVersion, oldName, newName, numeric, (short) 0);
    }

    protected VanillaItem(Version firstVersion, Version lastVersion, String oldName, String newName, int numeric, short data) {
        this.firstVersion = firstVersion;
        this.lastVersion = lastVersion;
        this.oldName = oldName;
        this.newName = newName;
        this.numeric = numeric;
        this.data = data;
        id = newName.toLowerCase();
    }

    /**
     * @return
     * the first supported version where this item existed
     */
    public Version getFirstVersion() {
        return firstVersion;
    }

    /**
     * @return
     * the last version where this item existed;
     * null if the item still exists
     */
    public Version getLastVersion() {
        return lastVersion;
    }

    /**
     * The old name might be ambiguous.
     *
     * @return
     * the Material enum name used before Minecraft 1.13;
     * null if the item didn't exist before 1.13
     */
    public String getOldName() {
        return oldName;
    }

    /**
     * The new name refers to one VanillaItem exclusively.
     *
     * @return
     * the Material enum name used since Minecraft 1.13;
     * equals the internal String ID but upper case
     */
    public String getNewName() {
        return newName;
    }

    /**
     * @return
     * the numeric ID used before Minecraft 1.13;
     * -1 if the item didn't exist before 1.13
     */
    public int getNumericId() {
        return numeric;
    }

    /**
     * @return
     * the numeric data value used before Minecraft 1.13;
     * 0 if the item didn't exist before 1.13;
     * 0 if the item doesn't need data
     */
    public short getData() {
        return data;
    }

    /**
     * @return
     * the internal ID.
     * The enum name in 1.13+;
     * the numeric ID and the data value if it isn't 0 separated with a ":" before 1.13
     */
    public String getInternalId() {
        if (CompatibilityHandler.getInstance().getVersion().useNewMaterials()) {
            return newName;
        } else {
            return getOldNameAndData();
        }
    }

    /**
     * @return
     * the numeric ID + the data value if it exists, separated by a ":"
     */
    public String getNumericIdAndData() {
        if (data != 0) {
            return numeric + ":" + data;
        } else {
            return String.valueOf(numeric);
        }
    }

    /**
     * @return
     * the old name + the data value if it exists, separated by a ":"
     */
    public String getOldNameAndData() {
        if (data != 0) {
            return oldName + ":" + data;
        } else {
            return oldName;
        }
    }

    @Override
    public String getName() {
        return getNewName();
    }

    /**
     * @return
     * if the represented mob is available in the current version
     */
    public boolean isAvailable() {
        Version version = CompatibilityHandler.getInstance().getVersion();
        return Version.andHigher(firstVersion).contains(version) && (lastVersion == null || Version.andHigher(version).contains(lastVersion));
    }

    @Override
    public ExItem idMatch(String id) {
        if (id.equals(newName) || id.equals(getNumericIdAndData()) || id.equals(getOldNameAndData())) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public ExItem idMatch2nd(String id) {
        if (id.equalsIgnoreCase(newName) || id.equalsIgnoreCase(getNumericIdAndData()) || id.equalsIgnoreCase(getOldNameAndData())
                || id.equalsIgnoreCase(oldName) || id.equalsIgnoreCase(String.valueOf(numeric))) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public Material getMaterial() {
        if (material == null) {
            if (!isAvailable()) {
                material = Material.AIR;
            }
            material = Material.valueOf(CompatibilityHandler.getInstance().getVersion().useNewMaterials() ? newName : oldName);
        }
        return material;
    }

    @Override
    public ItemStack toItemStack(int amount) {
        if (!isAvailable()) {
            return new ItemStack(Material.AIR);
        }
        if (CompatibilityHandler.getInstance().getVersion().useNewMaterials()) {
            return new ItemStack(Material.valueOf(newName), amount);
        } else {
            return new ItemStack(Material.valueOf(oldName), amount, data);
        }
    }

}
