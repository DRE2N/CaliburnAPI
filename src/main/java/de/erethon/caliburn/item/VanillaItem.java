/*
 * Copyright (C) 2015-2020 Daniel Saukel.
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

import de.erethon.caliburn.category.IdentifierType;
import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.compatibility.Version;
import static de.erethon.commons.compatibility.Version.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a vanilla item. As of 1.13, a Material enum value is considered one vanilla item. Before 1.13, each data value of the same material is considered
 * an item if it is not a damage value.
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
    public static final VanillaItem WATER = new VanillaItem(MC1_8, "STATIONARY_WATER", "WATER", 9);
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
    public static final VanillaItem IRON_BLOCK = new VanillaItem(MC1_8, "IRON_BLOCK", "IRON_BLOCK", 42);
    public static final VanillaItem STONE_SLAB = new VanillaItem(MC1_8, "STEP", "STONE_SLAB", 44);
    public static final VanillaItem SANDSTONE_SLAB = new VanillaItem(MC1_8, "STEP", "SANDSTONE_SLAB", 44, (short) 1);
    public static final VanillaItem PETRIFIED_OAK_SLAB = new VanillaItem(MC1_8, "STEP", "PETRIFIED_OAK_SLAB", 44, (short) 2);
    public static final VanillaItem COBBLESTONE_SLAB = new VanillaItem(MC1_8, "STEP", "COBBLESTONE_SLAB", 44, (short) 3);
    public static final VanillaItem BRICK_SLAB = new VanillaItem(MC1_8, "STEP", "BRICK_SLAB", 44, (short) 4);
    public static final VanillaItem STONE_BRICK_SLAB = new VanillaItem(MC1_8, "STEP", "STONE_BRICK_SLAB", 44, (short) 5);
    public static final VanillaItem NETHER_BRICK_SLAB = new VanillaItem(MC1_8, "STEP", "NETHER_BRICK_SLAB", 44, (short) 6);
    public static final VanillaItem QUARTZ_SLAB = new VanillaItem(MC1_8, "STEP", "QUARTZ_SLAB", 44, (short) 7);
    public static final VanillaItem BRICKS = new VanillaItem(MC1_8, "BRICK", "BRICKS", 45);
    public static final VanillaItem TNT = new VanillaItem(MC1_8, "TNT", "TNT", 46);
    public static final VanillaItem BOOKSHELF = new VanillaItem(MC1_8, "BOOKSHELF", "BOOKSHELF", 47);
    public static final VanillaItem MOSSY_COBBLESTONE = new VanillaItem(MC1_8, "MOSSY_COBBLESTONE", "MOSSY_COBBLESTONE", 48);
    public static final VanillaItem OBSIDIAN = new VanillaItem(MC1_8, "OBSIDIAN", "OBSIDIAN", 49);
    public static final VanillaItem TORCH = new VanillaItem(MC1_8, "TORCH", "TORCH", 50);
    public static final VanillaItem FIRE = new VanillaItem(MC1_8, "FIRE", "FIRE", 51);
    public static final VanillaItem SPAWNER = new VanillaItem(MC1_8, "MOB_SPAWNER", "SPAWNER", 52);
    public static final VanillaItem OAK_STAIRS = new VanillaItem(MC1_8, "WOOD_STAIRS", "OAK_STAIRS", 53);
    public static final VanillaItem CHEST = new VanillaItem(MC1_8, "CHEST", "CHEST", 54);
    public static final VanillaItem REDSTONE_WIRE = new VanillaItem(MC1_8, "REDSTONE_WIRE", "REDSTONE_WIRE", 55);
    public static final VanillaItem DIAMOND_ORE = new VanillaItem(MC1_8, "DIAMOND_ORE", "DIAMOND_ORE", 56);
    public static final VanillaItem DIAMOND_BLOCK = new VanillaItem(MC1_8, "DIAMOND_BLOCK", "DIAMOND_BLOCK", 57);
    public static final VanillaItem CRAFTING_TABLE = new VanillaItem(MC1_8, "WORKBENCH", "CRAFTING_TABLE", 58);
    public static final VanillaItem FARMLAND = new VanillaItem(MC1_8, "SOIL", "FARMLAND", 60);
    public static final VanillaItem FURNACE = new VanillaItem(MC1_8, "FURNACE", "FURNACE", 61);
    public static final VanillaItem LADDER = new VanillaItem(MC1_8, "LADDER", "LADDER", 65);
    public static final VanillaItem RAIL = new VanillaItem(MC1_8, "RAILS", "RAIL", 66);
    public static final VanillaItem COBBLESTONE_STAIRS = new VanillaItem(MC1_8, "COBBLESTONE_STAIRS", "COBBLESTONE_STAIRS", 67);
    public static final VanillaItem OAK_WALL_SIGN = new VanillaItem(MC1_8, "WALL_SIGN", "WALL_SIGN", "OAK_WALL_SIGN", 68);
    public static final VanillaItem LEVER = new VanillaItem(MC1_8, "LEVER", "LEVER", 69);
    public static final VanillaItem STONE_PRESSURE_PLATE = new VanillaItem(MC1_8, "STONE_PLATE", "STONE_PRESSURE_PLATE", 70);
    public static final VanillaItem OAK_PRESSURE_PLATE = new VanillaItem(MC1_8, "WOOD_PLATE", "OAK_PRESSURE_PLATE", 72);
    public static final VanillaItem REDSTONE_ORE = new VanillaItem(MC1_8, "REDSTONE_ORE", "REDSTONE_ORE", 73);
    public static final VanillaItem REDSTONE_TORCH = new VanillaItem(MC1_8, "REDSTONE_TORCH_ON", "REDSTONE_TORCH", 76);
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
    public static final VanillaItem NETHER_PORTAL = new VanillaItem(MC1_8, "PORTAL", "NETHER_PORTAL", 90);
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
    public static final VanillaItem INFESTED_STONE = new VanillaItem(MC1_8, "MONSTER_EGGS", "INFESTED_STONE", 97);
    public static final VanillaItem INFESTED_COBBLESTONE = new VanillaItem(MC1_8, "MONSTER_EGGS", "INFESTED_COBBLESTONE", 97, (short) 1);
    public static final VanillaItem INFESTED_STONE_BRICKS = new VanillaItem(MC1_8, "MONSTER_EGGS", "INFESTED_STONE_BRICKS", 97, (short) 2);
    public static final VanillaItem INFESTED_MOSSY_STONE_BRICKS = new VanillaItem(MC1_8, "MONSTER_EGGS", "INFESTED_MOSSY_STONE_BRICKS", 97, (short) 3);
    public static final VanillaItem INFESTED_CRACKED_STONE_BRICKS = new VanillaItem(MC1_8, "MONSTER_EGGS", "INFESTED_CRACKED_STONE_BRICKS", 97, (short) 4);
    public static final VanillaItem INFESTED_CHISELED_STONE_BRICKS = new VanillaItem(MC1_8, "MONSTER_EGGS", "INFESTED_CHISELED_STONE_BRICKS", 97, (short) 5);
    public static final VanillaItem STONE_BRICKS = new VanillaItem(MC1_8, "SMOOTH_BRICK", "STONE_BRICKS", 98);
    public static final VanillaItem MOSSY_STONE_BRICKS = new VanillaItem(MC1_8, "SMOOTH_BRICK", "MOSSY_STONE_BRICKS", 98, (short) 1);
    public static final VanillaItem CRACKED_STONE_BRICKS = new VanillaItem(MC1_8, "SMOOTH_BRICK", "CRACKED_STONE_BRICKS", 98, (short) 2);
    public static final VanillaItem CHISELED_STONE_BRICKS = new VanillaItem(MC1_8, "SMOOTH_BRICK", "CHISELED_STONE_BRICKS", 98, (short) 3);
    public static final VanillaItem BROWN_MUSHROOM_BLOCK = new VanillaItem(MC1_8, "HUGE_MUSHROOM_1", "BROWN_MUSHROOM_BLOCK", 99);
    public static final VanillaItem RED_MUSHROOM_BLOCK = new VanillaItem(MC1_8, "HUGE_MUSHROOM_2", "RED_MUSHROOM_BLOCK", 100);
    public static final VanillaItem IRON_BARS = new VanillaItem(MC1_8, "IRON_FENCE", "IRON_BARS", 101);
    public static final VanillaItem GLASS_PANE = new VanillaItem(MC1_8, "THIN_GLASS", "GLASS_PANE", 102);
    public static final VanillaItem MELON = new VanillaItem(MC1_8, "MELON_BLOCK", "MELON", 103);
    public static final VanillaItem PUMPKIN_STEM = new VanillaItem(MC1_8, "PUMPKIN_STEM", "PUMPKIN_STEM", 104);
    public static final VanillaItem MELON_STEM = new VanillaItem(MC1_8, "MELON_STEM", "MELON_STEM", 105);
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
    public static final VanillaItem ANVIL = new VanillaItem(MC1_8, "ANVIL", "ANVIL", 145);
    public static final VanillaItem CHIPPED_ANVIL = new VanillaItem(MC1_8, "ANVIL", "CHIPPED_ANVIL", 145, (short) 1);
    public static final VanillaItem DAMAGED_ANVIL = new VanillaItem(MC1_8, "ANVIL", "DAMAGED_ANVIL", 145, (short) 2);
    public static final VanillaItem TRAPPED_CHEST = new VanillaItem(MC1_8, "TRAPPED_CHEST", "TRAPPED_CHEST", 146);
    public static final VanillaItem LIGHT_WEIGHTED_PRESSURE_PLATE = new VanillaItem(MC1_8, "GOLD_PLATE", "LIGHT_WEIGHTED_PRESSURE_PLATE", 147);
    public static final VanillaItem HEAVY_WEIGHTED_PRESSURE_PLATE = new VanillaItem(MC1_8, "IRON_PLATE", "HEAVY_WEIGHTED_PRESSURE_PLATE", 148);
    public static final VanillaItem DAYLIGHT_DETECTOR = new VanillaItem(MC1_8, "DAYLIGHT_DETECTOR", "DAYLIGHT_DETECTOR", 151);
    public static final VanillaItem REDSTONE_BLOCK = new VanillaItem(MC1_8, "REDSTONE_BLOCK", "REDSTONE_BLOCK", 152);
    public static final VanillaItem NETHER_QUARTZ_ORE = new VanillaItem(MC1_8, "QUARTZ_ORE", "NETHER_QUARTZ_ORE", 153);
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
    public static final VanillaItem PURPUR_SLAB = new VanillaItem(MC1_9, "PURPUR_SLAB", "PURPUR_SLAB", 205);
    public static final VanillaItem END_STONE_BRICKS = new VanillaItem(MC1_9, "END_BRICKS", "END_STONE_BRICKS", 206);
    public static final VanillaItem BEETROOTS = new VanillaItem(MC1_9, "BEETROOT_BLOCK", "BEETROOTS", 207);
    public static final VanillaItem GRASS_PATH = new VanillaItem(MC1_9, "GRASS_PATH", "GRASS_PATH", 208);
    public static final VanillaItem END_GATEWAY = new VanillaItem(MC1_9, "END_GATEWAY", "END_GATEWAY", 209);
    public static final VanillaItem REPEATING_COMMAND_BLOCK = new VanillaItem(MC1_9, "COMMAND_REPEATING", "REPEATING_COMMAND_BLOCK", 210);
    public static final VanillaItem CHAIN_COMMAND_BLOCK = new VanillaItem(MC1_9, "COMMAND_CHAIN", "CHAIN_COMMAND_BLOCK", 211);
    public static final VanillaItem FROSTED_ICE = new VanillaItem(MC1_9, "FROSTED_ICE", "FROSTED_ICE", 212);
    public static final VanillaItem MAGMA_BLOCK = new VanillaItem(MC1_10, "MAGMA", "MAGMA_BLOCK", 213);
    public static final VanillaItem NETHER_WART_BLOCK = new VanillaItem(MC1_10, "NETHER_WART_BLOCK", "NETHER_WART_BLOCK", 214);
    public static final VanillaItem RED_NETHER_BRICKS = new VanillaItem(MC1_10, "RED_NETHER_BRICK", "RED_NETHER_BRICKS", 215);
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
    public static final VanillaItem LIGHT_GRAY_SHULKER_BOX = new VanillaItem(MC1_11, "SILVER_SHULKER_BOX", "LIGHT_GRAY_SHULKER_BOX", 227);
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
    public static final VanillaItem LIGHT_GRAY_GLAZED_TERRACOTTA = new VanillaItem(MC1_12, "SILVER_GLAZED_TERRACOTTA", "LIGHT_GRAY_GLAZED_TERRACOTTA", 243);
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

    // 1.13 BLOCKS
    public static final VanillaItem CAVE_AIR = new VanillaItem(MC1_13, "CAVE_AIR");
    public static final VanillaItem VOID_AIR = new VanillaItem(MC1_13, "VOID_AIR");
    public static final VanillaItem BLUE_ICE = new VanillaItem(MC1_13, "BLUE_ICE");
    public static final VanillaItem BUBBLE_COLUMN = new VanillaItem(MC1_13, "BUBBLE_COLUMN");
    public static final VanillaItem CONDUIT = new VanillaItem(MC1_13, "CONDUIT");
    public static final VanillaItem TUBE_CORAL = new VanillaItem(MC1_13, "TUBE_CORAL");
    public static final VanillaItem BRAIN_CORAL = new VanillaItem(MC1_13, "BRAIN_CORAL");
    public static final VanillaItem BUBBLE_CORAL = new VanillaItem(MC1_13, "BUBBLE_CORAL");
    public static final VanillaItem FIRE_CORAL = new VanillaItem(MC1_13, "FIRE_CORAL");
    public static final VanillaItem HORN_CORAL = new VanillaItem(MC1_13, "HORN_CORAL");
    public static final VanillaItem TUBE_CORAL_BLOCK = new VanillaItem(MC1_13, "TUBE_CORAL_BLOCK");
    public static final VanillaItem BRAIN_CORAL_BLOCK = new VanillaItem(MC1_13, "BRAIN_CORAL_BLOCK");
    public static final VanillaItem BUBBLE_CORAL_BLOCK = new VanillaItem(MC1_13, "BUBBLE_CORAL_BLOCK");
    public static final VanillaItem FIRE_CORAL_BLOCK = new VanillaItem(MC1_13, "FIRE_CORAL_BLOCK");
    public static final VanillaItem HORN_CORAL_BLOCK = new VanillaItem(MC1_13, "HORN_CORAL_BLOCK");
    public static final VanillaItem DEAD_TUBE_CORAL_BLOCK = new VanillaItem(MC1_13, "DEAD_TUBE_CORAL_BLOCK");
    public static final VanillaItem DEAD_BRAIN_CORAL_BLOCK = new VanillaItem(MC1_13, "DEAD_BRAIN_CORAL_BLOCK");
    public static final VanillaItem DEAD_BUBBLE_CORAL_BLOCK = new VanillaItem(MC1_13, "DEAD_BUBBLE_CORAL_BLOCK");
    public static final VanillaItem DEAD_FIRE_CORAL_BLOCK = new VanillaItem(MC1_13, "DEAD_FIRE_CORAL_BLOCK");
    public static final VanillaItem DEAD_HORN_CORAL_BLOCK = new VanillaItem(MC1_13, "DEAD_HORN_CORAL_BLOCK");
    public static final VanillaItem TUBE_CORAL_FAN = new VanillaItem(MC1_13, "TUBE_CORAL_FAN");
    public static final VanillaItem BRAIN_CORAL_FAN = new VanillaItem(MC1_13, "BRAIN_CORAL_FAN");
    public static final VanillaItem BUBBLE_CORAL_FAN = new VanillaItem(MC1_13, "BUBBLE_CORAL_FAN");
    public static final VanillaItem FIRE_CORAL_FAN = new VanillaItem(MC1_13, "FIRE_CORAL_FAN");
    public static final VanillaItem HORN_CORAL_FAN = new VanillaItem(MC1_13, "HORN_CORAL_FAN");
    public static final VanillaItem TUBE_CORAL_WALL_FAN = new VanillaItem(MC1_13, "TUBE_CORAL_WALL_FAN");
    public static final VanillaItem BRAIN_CORAL_WALL_FAN = new VanillaItem(MC1_13, "BRAIN_CORAL_WALL_FAN");
    public static final VanillaItem BUBBLE_CORAL_WALL_FAN = new VanillaItem(MC1_13, "BUBBLE_CORAL_WALL_FAN");
    public static final VanillaItem FIRE_CORAL_WALL_FAN = new VanillaItem(MC1_13, "FIRE_CORAL_WALL_FAN");
    public static final VanillaItem HORN_CORAL_WALL_FAN = new VanillaItem(MC1_13, "HORN_CORAL_WALL_FAN");
    public static final VanillaItem DEAD_TUBE_CORAL_FAN = new VanillaItem(MC1_13, "DEAD_TUBE_CORAL_FAN");
    public static final VanillaItem DEAD_BRAIN_CORAL_FAN = new VanillaItem(MC1_13, "DEAD_BRAIN_CORAL_FAN");
    public static final VanillaItem DEAD_BUBBLE_CORAL_FAN = new VanillaItem(MC1_13, "DEAD_BUBBLE_CORAL_FAN");
    public static final VanillaItem DEAD_FIRE_CORAL_FAN = new VanillaItem(MC1_13, "DEAD_FIRE_CORAL_FAN");
    public static final VanillaItem DEAD_HORN_CORAL_FAN = new VanillaItem(MC1_13, "DEAD_HORN_CORAL_FAN");
    public static final VanillaItem DEAD_TUBE_CORAL_WALL_FAN = new VanillaItem(MC1_13, "DEAD_TUBE_CORAL_WALL_FAN");
    public static final VanillaItem DEAD_BRAIN_CORAL_WALL_FAN = new VanillaItem(MC1_13, "DEAD_BRAIN_CORAL_WALL_FAN");
    public static final VanillaItem DEAD_BUBBLE_CORAL_WALL_FAN = new VanillaItem(MC1_13, "DEAD_BUBBLE_CORAL_WALL_FAN");
    public static final VanillaItem DEAD_FIRE_CORAL_WALL_FAN = new VanillaItem(MC1_13, "DEAD_FIRE_CORAL_WALL_FAN");
    public static final VanillaItem DEAD_HORN_CORAL_WALL_FAN = new VanillaItem(MC1_13, "DEAD_HORN_CORAL_WALL_FAN");
    public static final VanillaItem DRIED_KELP_BLOCK = new VanillaItem(MC1_13, "DRIED_KELP_BLOCK");
    public static final VanillaItem KELP_PLANT = new VanillaItem(MC1_13, "KELP_PLANT");
    public static final VanillaItem PRISMARINE_STAIRS = new VanillaItem(MC1_13, "PRISMARINE_STAIRS");
    public static final VanillaItem PRISMARINE_BRICK_STAIRS = new VanillaItem(MC1_13, "PRISMARINE_BRICK_STAIRS");
    public static final VanillaItem DARK_PRISMARINE_STAIRS = new VanillaItem(MC1_13, "DARK_PRISMARINE_STAIRS");
    public static final VanillaItem PRISMARINE_SLAB = new VanillaItem(MC1_13, "PRISMARINE_SLAB");
    public static final VanillaItem PRISMARINE_BRICK_SLAB = new VanillaItem(MC1_13, "PRISMARINE_BRICK_SLAB");
    public static final VanillaItem DARK_PRISMARINE_SLAB = new VanillaItem(MC1_13, "DARK_PRISMARINE_SLAB");
    public static final VanillaItem SEAGRASS = new VanillaItem(MC1_13, "SEAGRASS");
    public static final VanillaItem TALL_SEAGRASS = new VanillaItem(MC1_13, "TALL_SEAGRASS");
    public static final VanillaItem SEA_PICKLE = new VanillaItem(MC1_13, "SEA_PICKLE");
    public static final VanillaItem SHULKER_BOX = new VanillaItem(MC1_13, "SHULKER_BOX");
    public static final VanillaItem OAK_WOOD = new VanillaItem(MC1_13, "OAK_WOOD");
    public static final VanillaItem SPRUCE_WOOD = new VanillaItem(MC1_13, "SPRUCE_WOOD");
    public static final VanillaItem BIRCH_WOOD = new VanillaItem(MC1_13, "BIRCH_WOOD");
    public static final VanillaItem JUNGLE_WOOD = new VanillaItem(MC1_13, "JUNGLE_WOOD");
    public static final VanillaItem ACACIA_WOOD = new VanillaItem(MC1_13, "ACACIA_WOOD");
    public static final VanillaItem DARK_OAK_WOOD = new VanillaItem(MC1_13, "DARK_OAK_WOOD");
    public static final VanillaItem SPRUCE_BUTTON = new VanillaItem(MC1_13, "SPRUCE_BUTTON");
    public static final VanillaItem BIRCH_BUTTON = new VanillaItem(MC1_13, "BIRCH_BUTTON");
    public static final VanillaItem JUNGLE_BUTTON = new VanillaItem(MC1_13, "JUNGLE_BUTTON");
    public static final VanillaItem ACACIA_BUTTON = new VanillaItem(MC1_13, "ACACIA_BUTTON");
    public static final VanillaItem DARK_OAK_BUTTON = new VanillaItem(MC1_13, "DARK_OAK_BUTTON");
    public static final VanillaItem SPRUCE_PRESSURE_PLATE = new VanillaItem(MC1_13, "SPRUCE_PRESSURE_PLATE");
    public static final VanillaItem BIRCH_PRESSURE_PLATE = new VanillaItem(MC1_13, "BIRCH_PRESSURE_PLATE");
    public static final VanillaItem JUNGLE_PRESSURE_PLATE = new VanillaItem(MC1_13, "JUNGLE_PRESSURE_PLATE");
    public static final VanillaItem ACACIA_PRESSURE_PLATE = new VanillaItem(MC1_13, "ACACIA_PRESSURE_PLATE");
    public static final VanillaItem DARK_OAK_PRESSURE_PLATE = new VanillaItem(MC1_13, "DARK_OAK_PRESSURE_PLATE");
    public static final VanillaItem CARVED_PUMPKIN = new VanillaItem(MC1_13, "CARVED_PUMPKIN");
    public static final VanillaItem SPRUCE_TRAPDOOR = new VanillaItem(MC1_13, "SPRUCE_TRAPDOOR");
    public static final VanillaItem BIRCH_TRAPDOOR = new VanillaItem(MC1_13, "BIRCH_TRAPDOOR");
    public static final VanillaItem JUNGLE_TRAPDOOR = new VanillaItem(MC1_13, "JUNGLE_TRAPDOOR");
    public static final VanillaItem ACACIA_TRAPDOOR = new VanillaItem(MC1_13, "ACACIA_TRAPDOOR");
    public static final VanillaItem DARK_OAK_TRAPDOOR = new VanillaItem(MC1_13, "DARK_OAK_TRAPDOOR");
    public static final VanillaItem MUSHROOM_STEM = new VanillaItem(MC1_13, "MUSHROOM_STEM");
    public static final VanillaItem STRIPPED_OAK_LOG = new VanillaItem(MC1_13, "STRIPPED_OAK_LOG");
    public static final VanillaItem STRIPPED_SPRUCE_LOG = new VanillaItem(MC1_13, "STRIPPED_SPRUCE_LOG");
    public static final VanillaItem STRIPPED_BIRCH_LOG = new VanillaItem(MC1_13, "STRIPPED_BIRCH_LOG");
    public static final VanillaItem STRIPPED_JUNGLE_LOG = new VanillaItem(MC1_13, "STRIPPED_JUNGLE_LOG");
    public static final VanillaItem STRIPPED_ACACIA_LOG = new VanillaItem(MC1_13, "STRIPPED_ACACIA_LOG");
    public static final VanillaItem STRIPPED_DARK_OAK_LOG = new VanillaItem(MC1_13, "STRIPPED_DARK_OAK_LOG");
    public static final VanillaItem STRIPPED_OAK_WOOD = new VanillaItem(MC1_13, "STRIPPED_OAK_WOOD");
    public static final VanillaItem STRIPPED_SPRUCE_WOOD = new VanillaItem(MC1_13, "STRIPPED_SPRUCE_WOOD");
    public static final VanillaItem STRIPPED_BIRCH_WOOD = new VanillaItem(MC1_13, "STRIPPED_BIRCH_WOOD");
    public static final VanillaItem STRIPPED_JUNGLE_WOOD = new VanillaItem(MC1_13, "STRIPPED_JUNGLE_WOOD");
    public static final VanillaItem STRIPPED_ACACIA_WOOD = new VanillaItem(MC1_13, "STRIPPED_ACACIA_WOOD");
    public static final VanillaItem STRIPPED_DARK_OAK_WOOD = new VanillaItem(MC1_13, "STRIPPED_DARK_OAK_WOOD");
    public static final VanillaItem SMOOTH_SANDSTONE = new VanillaItem(MC1_13, "SMOOTH_SANDSTONE");
    public static final VanillaItem SMOOTH_RED_SANDSTONE = new VanillaItem(MC1_13, "SMOOTH_RED_SANDSTONE");
    public static final VanillaItem SMOOTH_QUARTZ = new VanillaItem(MC1_13, "SMOOTH_QUARTZ");
    public static final VanillaItem SMOOTH_STONE = new VanillaItem(MC1_13, "SMOOTH_STONE");
    public static final VanillaItem TURTLE_EGG = new VanillaItem(MC1_13, "TURTLE_EGG");
    public static final VanillaItem WALL_TORCH = new VanillaItem(MC1_13, "TORCH", "WALL_TORCH", 50);
    public static final VanillaItem REDSTONE_WALL_TORCH = new VanillaItem(MC1_13, "REDSTONE_TORCH_ON", "REDSTONE_WALL_TORCH", 76);
    public static final VanillaItem POTTED_POPPY = new VanillaItem(MC1_13, "POTTED_POPPY");
    public static final VanillaItem POTTED_DANDELION = new VanillaItem(MC1_13, "POTTED_DANDELION");
    public static final VanillaItem POTTED_OAK_SAPLING = new VanillaItem(MC1_13, "POTTED_OAK_SAPLING");
    public static final VanillaItem POTTED_SPRUCE_SAPLING = new VanillaItem(MC1_13, "POTTED_SPRUCE_SAPLING");
    public static final VanillaItem POTTED_BIRCH_SAPLING = new VanillaItem(MC1_13, "POTTED_BIRCH_SAPLING");
    public static final VanillaItem POTTED_JUNGLE_SAPLING = new VanillaItem(MC1_13, "POTTED_JUNGLE_SAPLING");
    public static final VanillaItem POTTED_RED_MUSHROOM = new VanillaItem(MC1_13, "POTTED_RED_MUSHROOM");
    public static final VanillaItem POTTED_BROWN_MUSHROOM = new VanillaItem(MC1_13, "POTTED_BROWN_MUSHROOM");
    public static final VanillaItem POTTED_CACTUS = new VanillaItem(MC1_13, "POTTED_CACTUS");
    public static final VanillaItem POTTED_DEAD_BUSH = new VanillaItem(MC1_13, "POTTED_DEAD_BUSH");
    public static final VanillaItem POTTED_FERN = new VanillaItem(MC1_13, "POTTED_FERN");
    public static final VanillaItem POTTED_ACACIA_SAPLING = new VanillaItem(MC1_13, "POTTED_ACACIA_SAPLING");
    public static final VanillaItem POTTED_DARK_OAK_SAPLING = new VanillaItem(MC1_13, "POTTED_DARK_OAK_SAPLING");
    public static final VanillaItem POTTED_BLUE_ORCHID = new VanillaItem(MC1_13, "POTTED_BLUE_ORCHID");
    public static final VanillaItem POTTED_ALLIUM = new VanillaItem(MC1_13, "POTTED_ALLIUM");
    public static final VanillaItem POTTED_AZURE_BLUET = new VanillaItem(MC1_13, "POTTED_AZURE_BLUET");
    public static final VanillaItem POTTED_RED_TULIP = new VanillaItem(MC1_13, "POTTED_RED_TULIP");
    public static final VanillaItem POTTED_ORANGE_TULIP = new VanillaItem(MC1_13, "POTTED_ORANGE_TULIP");
    public static final VanillaItem POTTED_WHITE_TULIP = new VanillaItem(MC1_13, "POTTED_WHITE_TULIP");
    public static final VanillaItem POTTED_PINK_TULIP = new VanillaItem(MC1_13, "POTTED_PINK_TULIP");
    public static final VanillaItem POTTED_OXEYE_DAISY = new VanillaItem(MC1_13, "POTTED_OXEYE_DAISY");
    public static final VanillaItem ATTACHED_PUMPKIN_STEM = new VanillaItem(MC1_8, "PUMPKIN_STEM", "ATTACHED_PUMPKIN_STEM", 104);
    public static final VanillaItem ATTACHED_MELON_STEM = new VanillaItem(MC1_8, "PUMPKIN_STEM", "ATTACHED_MELON_STEM", 105);

    // 1.13.1 BLOCKS
    public static final VanillaItem DEAD_TUBE_CORAL = new VanillaItem(MC1_13_1, "DEAD_TUBE_CORAL");
    public static final VanillaItem DEAD_BRAIN_CORAL = new VanillaItem(MC1_13_1, "DEAD_BRAIN_CORAL");
    public static final VanillaItem DEAD_BUBBLE_CORAL = new VanillaItem(MC1_13_1, "DEAD_BUBBLE_CORAL");
    public static final VanillaItem DEAD_FIRE_CORAL = new VanillaItem(MC1_13_1, "DEAD_FIRE_CORAL");
    public static final VanillaItem DEAD_HORN_CORAL = new VanillaItem(MC1_13_1, "DEAD_HORN_CORAL");

    // ITEMS
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
    public static final VanillaItem OAK_SIGN = new VanillaItem(MC1_8, "SIGN", "SIGN", "OAK_SIGN", 323);
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
    public static final VanillaItem TROPICAL_FISH = new VanillaItem(MC1_8, "RAW_FISH", "TROPICAL_FISH", 349, (short) 2);
    public static final VanillaItem PUFFERFISH = new VanillaItem(MC1_8, "RAW_FISH", "PUFFERFISH", 349, (short) 3);
    public static final VanillaItem COOKED_COD = new VanillaItem(MC1_8, "COOKED_FISH", "COOKED_COD", 350);
    public static final VanillaItem COOKED_SALMON = new VanillaItem(MC1_8, "COOKED_FISH", "COOKED_SALMON", 350, (short) 1);
    public static final VanillaItem INK_SAC = new VanillaItem(MC1_8, "INK_SACK", "INK_SAC", 351);
    public static final VanillaItem RED_DYE = new VanillaItem(MC1_8, "INK_SACK", "ROSE_RED", "RED_DYE", 351, (short) 1);
    public static final VanillaItem GREEN_DYE = new VanillaItem(MC1_8, "INK_SACK", "CACTUS_GREEN", "GREEN_DYE", 351, (short) 2);
    public static final VanillaItem COCOA_BEANS = new VanillaItem(MC1_8, "INK_SACK", "COCOA_BEANS", 351, (short) 3);
    public static final VanillaItem LAPIS_LAZULI = new VanillaItem(MC1_8, "INK_SACK", "LAPIS_LAZULI", 351, (short) 4);
    public static final VanillaItem PURPLE_DYE = new VanillaItem(MC1_8, "INK_SACK", "PURPLE_DYE", 351, (short) 5);
    public static final VanillaItem CYAN_DYE = new VanillaItem(MC1_8, "INK_SACK", "CYAN_DYE", 351, (short) 6);
    public static final VanillaItem LIGHT_GRAY_DYE = new VanillaItem(MC1_8, "INK_SACK", "LIGHT_GRAY_DYE", 351, (short) 7);
    public static final VanillaItem GRAY_DYE = new VanillaItem(MC1_8, "INK_SACK", "GRAY_DYE", 351, (short) 8);
    public static final VanillaItem PINK_DYE = new VanillaItem(MC1_8, "INK_SACK", "PINK_DYE", 351, (short) 9);
    public static final VanillaItem LIME_DYE = new VanillaItem(MC1_8, "INK_SACK", "LIME_DYE", 351, (short) 10);
    public static final VanillaItem YELLOW_DYE = new VanillaItem(MC1_8, "INK_SACK", "DANDELION_YELLOW", "YELLOW_DYE", 351, (short) 11);
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
    public static final VanillaItem MELON_SLICE = new VanillaItem(MC1_8, "MELON", "MELON_SLICE", 360);
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
    public static final VanillaItem GLISTERING_MELON_SLICE = new VanillaItem(MC1_8, "SPECKLED_MELON", "GLISTERING_MELON_SLICE", 382);
    public static final VanillaItem EXPERIENCE_BOTTLE = new VanillaItem(MC1_8, "EXP_BOTTLE", "EXPERIENCE_BOTTLE", 384);
    public static final VanillaItem FIRE_CHARGE = new VanillaItem(MC1_8, "FIREBALL", "FIRE_CHARGE", 385);
    public static final VanillaItem WRITABLE_BOOK = new VanillaItem(MC1_8, "BOOK_AND_QUILL", "WRITABLE_BOOK", 386);
    public static final VanillaItem WRITTEN_BOOK = new VanillaItem(MC1_8, "WRITTEN_BOOK", "WRITTEN_BOOK", 387);
    public static final VanillaItem EMERALD = new VanillaItem(MC1_8, "EMERALD", "EMERALD", 388);
    public static final VanillaItem ITEM_FRAME = new VanillaItem(MC1_8, "ITEM_FRAME", "ITEM_FRAME", 389);
    public static final VanillaItem FLOWER_POT = new VanillaItem(MC1_8, "FLOWER_POT_ITEM", "FLOWER_POT", 390);
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
    public static final VanillaItem SKELETON_WALL_SKULL = new VanillaItem(MC1_13, "SKELETON_WALL_SKULL");
    public static final VanillaItem WITHER_SKELETON_WALL_SKULL = new VanillaItem(MC1_13, "WITHER_SKELETON_WALL_SKULL");
    public static final VanillaItem ZOMBIE_WALL_HEAD = new VanillaItem(MC1_13, "ZOMBIE_WALL_HEAD");
    public static final VanillaItem PLAYER_WALL_HEAD = new VanillaItem(MC1_13, "PLAYER_WALL_HEAD");
    public static final VanillaItem CREEPER_WALL_HEAD = new VanillaItem(MC1_13, "CREEPER_WALL_HEAD");
    public static final VanillaItem DRAGON_WALL_HEAD = new VanillaItem(MC1_13, "DRAGON_WALL_HEAD");
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
    public static final VanillaItem POPPED_CHORUS_FRUIT = new VanillaItem(MC1_9, "CHORUS_FRUIT_POPPED", "POPPED_CHORUS_FRUIT", 433);
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

    // 1.13 ITEMS
    public static final VanillaItem DEBUG_STICK = new VanillaItem(MC1_13, "DEBUG_STICK");
    public static final VanillaItem DRIED_KELP = new VanillaItem(MC1_13, "DRIED_KELP");
    public static final VanillaItem COD_BUCKET = new VanillaItem(MC1_13, "COD_BUCKET");
    public static final VanillaItem SALMON_BUCKET = new VanillaItem(MC1_13, "SALMON_BUCKET");
    public static final VanillaItem PUFFERFISH_BUCKET = new VanillaItem(MC1_13, "PUFFERFISH_BUCKET");
    public static final VanillaItem TROPICAL_FISH_BUCKET = new VanillaItem(MC1_13, "TROPICAL_FISH_BUCKET");
    public static final VanillaItem HEART_OF_THE_SEA = new VanillaItem(MC1_13, "HEART_OF_THE_SEA");
    public static final VanillaItem KELP = new VanillaItem(MC1_13, "KELP");
    public static final VanillaItem NAUTILUS_SHELL = new VanillaItem(MC1_13, "NAUTILUS_SHELL");
    public static final VanillaItem PHANTOM_MEMBRANE = new VanillaItem(MC1_13, "PHANTOM_MEMBRANE");
    public static final VanillaItem SCUTE = new VanillaItem(MC1_13, "SCUTE");
    public static final VanillaItem TRIDENT = new VanillaItem(MC1_13, "TRIDENT");
    public static final VanillaItem TURTLE_HELMET = new VanillaItem(MC1_13, "TURTLE_HELMET");

    // 1.14 ITEMS
    public static final VanillaItem ACACIA_SIGN = new VanillaItem(MC1_14, "ACACIA_SIGN");
    public static final VanillaItem ACACIA_WALL_SIGN = new VanillaItem(MC1_14, "ACACIA_WALL_SIGN");
    public static final VanillaItem ANDESITE_SLAB = new VanillaItem(MC1_14, "ANDESITE_SLAB");
    public static final VanillaItem ANDESITE_STAIRS = new VanillaItem(MC1_14, "ANDESITE_STAIRS");
    public static final VanillaItem ANDESITE_WALL = new VanillaItem(MC1_14, "ANDESITE_WALL");
    public static final VanillaItem BAMBOO = new VanillaItem(MC1_14, "BAMBOO");
    public static final VanillaItem BAMBOO_SAPLING = new VanillaItem(MC1_14, "BAMBOO_SAPLING");
    public static final VanillaItem BARREL = new VanillaItem(MC1_14, "BARREL");
    public static final VanillaItem BELL = new VanillaItem(MC1_14, "BELL");
    public static final VanillaItem BIRCH_SIGN = new VanillaItem(MC1_14, "BIRCH_SIGN");
    public static final VanillaItem BIRCH_WALL_SIGN = new VanillaItem(MC1_14, "BIRCH_WALL_SIGN");
    public static final VanillaItem BLACK_DYE = new VanillaItem(MC1_14, "BLACK_DYE");
    public static final VanillaItem BLAST_FURNACE = new VanillaItem(MC1_14, "BLAST_FURNACE");
    public static final VanillaItem BLUE_DYE = new VanillaItem(MC1_14, "BLUE_DYE");
    public static final VanillaItem BRICK_WALL = new VanillaItem(MC1_14, "BRICK_WALL");
    public static final VanillaItem BROWN_DYE = new VanillaItem(MC1_14, "BROWN_DYE");
    public static final VanillaItem CAMPFIRE = new VanillaItem(MC1_14, "CAMPFIRE");
    public static final VanillaItem CARTOGRAPHY_TABLE = new VanillaItem(MC1_14, "CARTOGRAPHY_TABLE");
    public static final VanillaItem COMPOSTER = new VanillaItem(MC1_14, "COMPOSTER");
    public static final VanillaItem CORNFLOWER = new VanillaItem(MC1_14, "CORNFLOWER");
    public static final VanillaItem CREEPER_BANNER_PATTERN = new VanillaItem(MC1_14, "CREEPER_BANNER_PATTERN");
    public static final VanillaItem CROSSBOW = new VanillaItem(MC1_14, "CROSSBOW");
    public static final VanillaItem CUT_RED_SANDSTONE_SLAB = new VanillaItem(MC1_14, "CUT_RED_SANDSTONE_SLAB");
    public static final VanillaItem CUT_SANDSTONE_SLAB = new VanillaItem(MC1_14, "CUT_SANDSTONE_SLAB");
    public static final VanillaItem DARK_OAK_SIGN = new VanillaItem(MC1_14, "DARK_OAK_SIGN");
    public static final VanillaItem DARK_OAK_WALL_SIGN = new VanillaItem(MC1_14, "DARK_OAK_WALL_SIGN");
    public static final VanillaItem DIORITE_SLAB = new VanillaItem(MC1_14, "DIORITE_SLAB");
    public static final VanillaItem DIORITE_STAIRS = new VanillaItem(MC1_14, "DIORITE_STAIRS");
    public static final VanillaItem DIORITE_WALL = new VanillaItem(MC1_14, "DIORITE_WALL");
    public static final VanillaItem END_STONE_BRICK_SLAB = new VanillaItem(MC1_14, "END_STONE_BRICK_SLAB");
    public static final VanillaItem END_STONE_BRICK_STAIRS = new VanillaItem(MC1_14, "END_STONE_BRICK_STAIRS");
    public static final VanillaItem END_STONE_BRICK_WALL = new VanillaItem(MC1_14, "END_STONE_BRICK_WALL");
    public static final VanillaItem FLETCHING_TABLE = new VanillaItem(MC1_14, "FLETCHING_TABLE");
    public static final VanillaItem FLOWER_BANNER_PATTERN = new VanillaItem(MC1_14, "FLOWER_BANNER_PATTERN");
    public static final VanillaItem GLOBE_BANNER_PATTERN = new VanillaItem(MC1_14, "GLOBE_BANNER_PATTERN");
    public static final VanillaItem GRANITE_SLAB = new VanillaItem(MC1_14, "GRANITE_SLAB");
    public static final VanillaItem GRANITE_STAIRS = new VanillaItem(MC1_14, "GRANITE_STAIRS");
    public static final VanillaItem GRANITE_WALL = new VanillaItem(MC1_14, "GRANITE_WALL");
    public static final VanillaItem GRINDSTONE = new VanillaItem(MC1_14, "GRINDSTONE");
    public static final VanillaItem JIGSAW = new VanillaItem(MC1_14, "JIGSAW");
    public static final VanillaItem JUNGLE_SIGN = new VanillaItem(MC1_14, "JUNGLE_SIGN");
    public static final VanillaItem JUNGLE_WALL_SIGN = new VanillaItem(MC1_14, "JUNGLE_WALL_SIGN");
    public static final VanillaItem LANTERN = new VanillaItem(MC1_14, "LANTERN");
    public static final VanillaItem LEATHER_HORSE_ARMOR = new VanillaItem(MC1_14, "LEATHER_HORSE_ARMOR");
    public static final VanillaItem LECTERN = new VanillaItem(MC1_14, "LECTERN");
    public static final VanillaItem LILY_OF_THE_VALLEY = new VanillaItem(MC1_14, "LILY_OF_THE_VALLEY");
    public static final VanillaItem LOOM = new VanillaItem(MC1_14, "LOOM");
    public static final VanillaItem MOJANG_BANNER_PATTERN = new VanillaItem(MC1_14, "MOJANG_BANNER_PATTERN");
    public static final VanillaItem MOSSY_COBBLESTONE_SLAB = new VanillaItem(MC1_14, "MOSSY_COBBLESTONE_SLAB");
    public static final VanillaItem MOSSY_COBBLESTONE_STAIRS = new VanillaItem(MC1_14, "MOSSY_COBBLESTONE_STAIRS");
    public static final VanillaItem MOSSY_STONE_BRICK_SLAB = new VanillaItem(MC1_14, "MOSSY_STONE_BRICK_SLAB");
    public static final VanillaItem MOSSY_STONE_BRICK_STAIRS = new VanillaItem(MC1_14, "MOSSY_STONE_BRICK_STAIRS");
    public static final VanillaItem MOSSY_STONE_BRICK_WALL = new VanillaItem(MC1_14, "MOSSY_STONE_BRICK_WALL");
    public static final VanillaItem NETHER_BRICK_WALL = new VanillaItem(MC1_14, "NETHER_BRICK_WALL");
    public static final VanillaItem POLISHED_ANDESITE_SLAB = new VanillaItem(MC1_14, "POLISHED_ANDESITE_SLAB");
    public static final VanillaItem POLISHED_ANDESITE_STAIRS = new VanillaItem(MC1_14, "POLISHED_ANDESITE_STAIRS");
    public static final VanillaItem POLISHED_DIORITE_SLAB = new VanillaItem(MC1_14, "POLISHED_DIORITE_SLAB");
    public static final VanillaItem POLISHED_DIORITE_STAIRS = new VanillaItem(MC1_14, "POLISHED_DIORITE_STAIRS");
    public static final VanillaItem POLISHED_GRANITE_SLAB = new VanillaItem(MC1_14, "POLISHED_GRANITE_SLAB");
    public static final VanillaItem POLISHED_GRANITE_STAIRS = new VanillaItem(MC1_14, "POLISHED_GRANITE_STAIRS");
    public static final VanillaItem POTTED_BAMBOO = new VanillaItem(MC1_14, "POTTED_BAMBOO");
    public static final VanillaItem POTTED_CORNFLOWER = new VanillaItem(MC1_14, "POTTED_CORNFLOWER");
    public static final VanillaItem POTTED_LILY_OF_THE_VALLEY = new VanillaItem(MC1_14, "POTTED_LILY_OF_THE_VALLEY");
    public static final VanillaItem POTTED_WITHER_ROSE = new VanillaItem(MC1_14, "POTTED_WITHER_ROSE");
    public static final VanillaItem PRISMARINE_WALL = new VanillaItem(MC1_14, "PRISMARINE_WALL");
    public static final VanillaItem RAVAGER_SPAWN_EGG = new VanillaItem(MC1_14, "RAVAGER_SPAWN_EGG");
    public static final VanillaItem RED_NETHER_BRICK_SLAB = new VanillaItem(MC1_14, "RED_NETHER_BRICK_SLAB");
    public static final VanillaItem RED_NETHER_BRICK_STAIRS = new VanillaItem(MC1_14, "RED_NETHER_BRICK_STAIRS");
    public static final VanillaItem RED_NETHER_BRICK_WALL = new VanillaItem(MC1_14, "RED_NETHER_BRICK_WALL");
    public static final VanillaItem RED_SANDSTONE_WALL = new VanillaItem(MC1_14, "RED_SANDSTONE_WALL");
    public static final VanillaItem SANDSTONE_WALL = new VanillaItem(MC1_14, "SANDSTONE_WALL");
    public static final VanillaItem SCAFFOLDING = new VanillaItem(MC1_14, "SCAFFOLDING");
    public static final VanillaItem SKULL_BANNER_PATTERN = new VanillaItem(MC1_14, "SKULL_BANNER_PATTERN");
    public static final VanillaItem SMITHING_TABLE = new VanillaItem(MC1_14, "SMITHING_TABLE");
    public static final VanillaItem SMOKER = new VanillaItem(MC1_14, "SMOKER");
    public static final VanillaItem SMOOTH_QUARTZ_SLAB = new VanillaItem(MC1_14, "SMOOTH_QUARTZ_SLAB");
    public static final VanillaItem SMOOTH_QUARTZ_STAIRS = new VanillaItem(MC1_14, "SMOOTH_QUARTZ_STAIRS");
    public static final VanillaItem SMOOTH_RED_SANDSTONE_SLAB = new VanillaItem(MC1_14, "SMOOTH_RED_SANDSTONE_SLAB");
    public static final VanillaItem SMOOTH_RED_SANDSTONE_STAIRS = new VanillaItem(MC1_14, "SMOOTH_RED_SANDSTONE_STAIRS");
    public static final VanillaItem SMOOTH_SANDSTONE_SLAB = new VanillaItem(MC1_14, "SMOOTH_SANDSTONE_SLAB");
    public static final VanillaItem SMOOTH_SANDSTONE_STAIRS = new VanillaItem(MC1_14, "SMOOTH_SANDSTONE_STAIRS");
    public static final VanillaItem SMOOTH_STONE_SLAB = new VanillaItem(MC1_14, "SMOOTH_STONE_SLAB");
    public static final VanillaItem SPRUCE_SIGN = new VanillaItem(MC1_14, "SPRUCE_SIGN");
    public static final VanillaItem SPRUCE_WALL_SIGN = new VanillaItem(MC1_14, "SPRUCE_WALL_SIGN");
    public static final VanillaItem STONECUTTER = new VanillaItem(MC1_14, "STONECUTTER");
    public static final VanillaItem STONE_BRICK_WALL = new VanillaItem(MC1_14, "STONE_BRICK_WALL");
    public static final VanillaItem STONE_STAIRS = new VanillaItem(MC1_14, "STONE_STAIRS");
    public static final VanillaItem SUSPICIOUS_STEW = new VanillaItem(MC1_14, "SUSPICIOUS_STEW");
    public static final VanillaItem SWEET_BERRIES = new VanillaItem(MC1_14, "SWEET_BERRIES");
    public static final VanillaItem SWEET_BERRY_BUSH = new VanillaItem(MC1_14, "SWEET_BERRY_BUSH");
    public static final VanillaItem WHITE_DYE = new VanillaItem(MC1_14, "WHITE_DYE");
    public static final VanillaItem WITHER_ROSE = new VanillaItem(MC1_14, "WITHER_ROSE");

    // 1.15 ITEMS
    public static final VanillaItem BEEHIVE = new VanillaItem(MC1_15, "BEEHIVE");
    public static final VanillaItem BEE_NEST = new VanillaItem(MC1_15, "BEE_NEST");
    public static final VanillaItem HONEYCOMB = new VanillaItem(MC1_15, "HONEYCOMB");
    public static final VanillaItem HONEYCOMB_BLOCK = new VanillaItem(MC1_15, "HONEYCOMB_BLOCK");
    public static final VanillaItem HONEY_BLOCK = new VanillaItem(MC1_15, "HONEY_BLOCK");
    public static final VanillaItem HONEY_BOTTLE = new VanillaItem(MC1_15, "HONEY_BOTTLE");

    // SPAWN EGGS
    public static final VanillaItem BAT_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "BAT_SPAWN_EGG", 383);
    public static final VanillaItem BEE_SPAWN_EGG = new VanillaItem(MC1_15, "BEE_SPAWN_EGG");
    public static final VanillaItem BLAZE_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "BLAZE_SPAWN_EGG", 383);
    public static final VanillaItem CAT_SPAWN_EGG = new VanillaItem(MC1_14, "CAT_SPAWN_EGG");
    public static final VanillaItem CAVE_SPIDER_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "CAVE_SPIDER_SPAWN_EGG", 383);
    public static final VanillaItem CHICKEN_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "CHICKEN_SPAWN_EGG", 383);
    public static final VanillaItem COD_SPAWN_EGG = new VanillaItem(MC1_13, "COD_SPAWN_EGG");
    public static final VanillaItem COW_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "COW_SPAWN_EGG", 383);
    public static final VanillaItem CREEPER_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "CREEPER_SPAWN_EGG", 383);
    public static final VanillaItem DOLPHIN_SPAWN_EGG = new VanillaItem(MC1_13, "DOLPHIN_SPAWN_EGG");
    public static final VanillaItem DONKEY_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "DONKEY_SPAWN_EGG", 383);
    public static final VanillaItem DROWNED_SPAWN_EGG = new VanillaItem(MC1_13, "DROWNED_SPAWN_EGG");
    public static final VanillaItem ELDER_GUARDIAN_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "ELDER_GUARDIAN_SPAWN_EGG", 383);
    public static final VanillaItem ENDERMAN_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "ENDERMAN_SPAWN_EGG", 383);
    public static final VanillaItem ENDERMITE_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "ENDERMITE_SPAWN_EGG", 383);
    public static final VanillaItem EVOKER_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "EVOKER_SPAWN_EGG", 383);
    public static final VanillaItem FOX_SPAWN_EGG = new VanillaItem(MC1_14, "FOX_SPAWN_EGG");
    public static final VanillaItem GHAST_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "GHAST_SPAWN_EGG", 383);
    public static final VanillaItem GUARDIAN_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "GUARDIAN_SPAWN_EGG", 383);
    public static final VanillaItem HORSE_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "HORSE_SPAWN_EGG", 383);
    public static final VanillaItem HUSK_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "HUSK_SPAWN_EGG", 383);
    public static final VanillaItem LLAMA_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "LLAMA_SPAWN_EGG", 383);
    public static final VanillaItem MAGMA_CUBE_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "MAGMA_CUBE_SPAWN_EGG", 383);
    public static final VanillaItem MOOSHROOM_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "MOOSHROOM_SPAWN_EGG", 383);
    public static final VanillaItem MULE_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "MULE_SPAWN_EGG", 383);
    public static final VanillaItem OCELOT_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "OCELOT_SPAWN_EGG", 383);
    public static final VanillaItem PANDA_SPAWN_EGG = new VanillaItem(MC1_14, "PANDA_SPAWN_EGG");
    public static final VanillaItem PARROT_SPAWN_EGG = new VanillaItem(MC1_12, "MONSTER_EGG", "PARROT_SPAWN_EGG", 383);
    public static final VanillaItem PIG_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "PIG_SPAWN_EGG", 383);
    public static final VanillaItem PILLAGER_SPAWN_EGG = new VanillaItem(MC1_14, "PILLAGER_SPAWN_EGG");
    public static final VanillaItem PHANTOM_SPAWN_EGG = new VanillaItem(MC1_13, "PHANTOM_SPAWN_EGG");
    public static final VanillaItem POLAR_BEAR_SPAWN_EGG = new VanillaItem(MC1_10, "MONSTER_EGG", "POLAR_BEAR_SPAWN_EGG", 383);
    public static final VanillaItem PUFFERFISH_SPAWN_EGG = new VanillaItem(MC1_13, "PUFFERFISH_SPAWN_EGG");
    public static final VanillaItem RABBIT_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "RABBIT_SPAWN_EGG", 383);
    public static final VanillaItem SALMON_SPAWN_EGG = new VanillaItem(MC1_13, "SALMON_SPAWN_EGG");
    public static final VanillaItem SHEEP_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "SHEEP_SPAWN_EGG", 383);
    public static final VanillaItem SHULKER_SPAWN_EGG = new VanillaItem(MC1_9, "MONSTER_EGG", "SHULKER_SPAWN_EGG", 383);
    public static final VanillaItem SILVERFISH_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "SILVERFISH_SPAWN_EGG", 383);
    public static final VanillaItem SKELETON_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "SKELETON_SPAWN_EGG", 383);
    public static final VanillaItem SKELETON_HORSE_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "SKELETON_HORSE_SPAWN_EGG", 383);
    public static final VanillaItem SLIME_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "SLIME_SPAWN_EGG", 383);
    public static final VanillaItem SPIDER_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "SPIDER_SPAWN_EGG", 383);
    public static final VanillaItem SQUID_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "SQUID_SPAWN_EGG", 383);
    public static final VanillaItem STRAY_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "STRAY_SPAWN_EGG", 383);
    public static final VanillaItem TRADER_LLAMA_SPAWN_EGG = new VanillaItem(MC1_14, "TRADER_LLAMA_SPAWN_EGG");
    public static final VanillaItem TROPICAL_FISH_SPAWN_EGG = new VanillaItem(MC1_13, "TROPICAL_FISH_SPAWN_EGG");
    public static final VanillaItem TURTLE_SPAWN_EGG = new VanillaItem(MC1_13, "TURTLE_SPAWN_EGG");
    public static final VanillaItem VEX_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "VEX_SPAWN_EGG", 383);
    public static final VanillaItem VILLAGER_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "VILLAGER_SPAWN_EGG", 383);
    public static final VanillaItem VINDICATOR_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "VINDICATOR_SPAWN_EGG", 383);
    public static final VanillaItem WANDERING_TRADER_SPAWN_EGG = new VanillaItem(MC1_14, "WANDERING_TRADER_SPAWN_EGG");
    public static final VanillaItem WITCH_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "WITCH_SPAWN_EGG", 383);
    public static final VanillaItem WITHER_SKELETON_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "WITHER_SKELETON_SPAWN_EGG", 383);
    public static final VanillaItem WOLF_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "WOLF_SPAWN_EGG", 383);
    public static final VanillaItem ZOMBIE_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "ZOMBIE_SPAWN_EGG", 383);
    public static final VanillaItem ZOMBIE_HORSE_SPAWN_EGG = new VanillaItem(MC1_11, "MONSTER_EGG", "ZOMBIE_HORSE_SPAWN_EGG", 383);
    public static final VanillaItem ZOMBIE_PIGMAN_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "ZOMBIE_PIGMAN_SPAWN_EGG", 383);
    public static final VanillaItem ZOMBIE_VILLAGER_SPAWN_EGG = new VanillaItem(MC1_8, "MONSTER_EGG", "ZOMBIE_VILLAGER_SPAWN_EGG", 383);

    // OLD / MERGED BLOCKS
    public static final OldBlockItem FLOWING_WATER = new OldBlockItem(MC1_8, "WATER", WATER, 8);
    public static final OldBlockItem FLOWING_LAVA = new OldBlockItem(MC1_8, "LAVA", LAVA, 10);
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
    public static final OldBlockItem SIGN_POST = new OldBlockItem(MC1_8, "SIGN_POST", OAK_SIGN, 63);
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
    public static final OldBlockItem PURPUR_DOUBLE_SLAB = new OldBlockItem(MC1_9, "PURPUR_DOUBLE_SLAB", PURPUR_SLAB, 204);

    private static Collection<VanillaItem> VALUES = new ArrayList<>();
    private static Collection<VanillaItem> LOADED = new ArrayList<>();
    private static Map<Material, VanillaItem> BY_MATERIAL = new HashMap<>();

    static {
        for (Field constant : VanillaItem.class.getFields()) {
            try {
                VALUES.add((VanillaItem) constant.get(null));
            } catch (IllegalArgumentException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }

        bukkitMats:
        for (Material bukkit : Material.values()) {
            for (VanillaItem caliburn : VALUES) {
                if (caliburn.getName().equals(bukkit.name())) {
                    continue bukkitMats;
                }
            }

            MessageUtil.log("&c[WARNING] Caliburn lacks a built-in representation of the material " + bukkit.name() + ". Please update your implementation if possible.");
            VALUES.add(new VanillaItem(NEW, bukkit.name()));
        }

        for (VanillaItem vi : VALUES) {
            if (Material.getMaterial(vi.getName()) == null && vi.isAvailable()) {
                MessageUtil.log("&c[WARNING] Caliburn has a representation of the material " + vi.getName() + " that does not exist in Bukkit.");
                continue;
            }
            if (vi.isAvailable()) {
                LOADED.add(vi);
                BY_MATERIAL.put(vi.getMaterial(), vi);
            }
        }
    }

    /**
     * Returns all vanilla items that are known, including those that don't exist in this version.
     *
     * @return all vanilla items that are known
     */
    public static Collection<VanillaItem> values() {
        return VALUES;
    }

    /**
     * Returns all vanilla items that exist in this runtime environment.
     *
     * @return all vanilla items that exist in this runtime environment
     */
    public static Collection<VanillaItem> getLoaded() {
        return LOADED;
    }

    /**
     * Returns the VanillaItem that wraps the material.
     *
     * @param material a material
     * @return the VanillaItem that wraps the material
     */
    public static VanillaItem get(Material material) {
        if (material == null) {
            return AIR;
        }
        return BY_MATERIAL.get(material);
    }

    private Version firstVersion;
    private Version lastVersion;
    private String id1_8;
    private String id1_13;
    private String id1_14;
    private int numeric;
    private short data;

    protected VanillaItem(String id1_8, String id1_13, int numeric) {
        this(MC1_8, id1_8, id1_13, numeric);
    }

    protected VanillaItem(String id1_8, String id1_13, int numeric, short data) {
        this(MC1_8, id1_8, id1_13, numeric, data);
    }

    protected VanillaItem(Version firstVersion, String id1_8, String id1_13, int numeric) {
        this(firstVersion, id1_8, id1_13, numeric, (short) 0);
    }

    protected VanillaItem(Version firstVersion, String id1_8, String id1_13, String id1_14, int numeric) {
        this(firstVersion, id1_8, id1_13, id1_14, numeric, (short) 0);
    }

    protected VanillaItem(Version firstVersion, String id1_8, String id1_13, int numeric, short data) {
        this(firstVersion, Version.NEW, id1_8, id1_13, numeric, data);
    }

    protected VanillaItem(Version firstVersion, String id1_8, String id1_13, String id1_14, int numeric, short data) {
        this(firstVersion, Version.NEW, id1_8, id1_13, id1_14, numeric, data);
    }

    protected VanillaItem(Version firstVersion, String id1_13) {
        this(firstVersion, Version.NEW, id1_13);
    }

    protected VanillaItem(Version firstVersion, Version lastVersion, String id1_13) {
        this(firstVersion, lastVersion, "", id1_13, -1, (short) 0);
    }

    protected VanillaItem(Version firstVersion, Version lastVersion, String id1_8, String id1_13, int numeric) {
        this(firstVersion, lastVersion, id1_8, id1_13, numeric, (short) 0);
    }

    protected VanillaItem(Version firstVersion, Version lastVersion, String id1_8, String id1_13, int numeric, short data) {
        this(firstVersion, lastVersion, id1_8, id1_13, id1_13, numeric, data);
    }

    protected VanillaItem(Version firstVersion, Version lastVersion, String id1_8, String id1_13, String id1_14, int numeric, short data) {
        this.idType = IdentifierType.VANILLA;
        this.firstVersion = firstVersion;
        this.lastVersion = lastVersion;
        this.id1_8 = id1_8;
        this.id1_13 = id1_13;
        this.id1_14 = id1_14;
        this.numeric = numeric;
        this.data = data;
        if (CompatibilityHandler.getInstance().getVersion().useNewMaterials()) {
            if (Version.isAtMost(Version.MC1_13_2)) {
                id = id1_13;
            } else {
                id = id1_14;
            }
        } else {
            id = id1_8;
        }
    }

    /**
     * Returns the first supported version where this item existed.
     *
     * @return the first supported version where this item existed
     */
    public Version getFirstVersion() {
        return firstVersion;
    }

    /**
     * Returns the last version where this item existed; NEW if the item still exists.
     *
     * @return the last version where this item existed; NEW if the item still exists
     */
    public Version getLastVersion() {
        return lastVersion;
    }

    /**
     * The old name might be ambiguous.
     *
     * @return the Material enum name used before Minecraft 1.13; null if the item didn't exist before 1.13
     */
    public String getOldName() {
        return id1_8;
    }

    /**
     * The new name refers to one VanillaItem exclusively.
     *
     * @return the Material enum name used since Minecraft 1.13; equals the internal String ID but upper case
     */
    public String get1_13Name() {
        return id1_13;
    }

    /**
     * The new name refers to one VanillaItem exclusively.
     *
     * @return the Material enum name used since Minecraft 1.14; equals the internal String ID but upper case
     */
    public String get1_14Name() {
        return id1_14;
    }

    /**
     * Returns the numeric ID used before Minecraft 1.13; -1 if the item didn't exist before 1.13.
     *
     * @return the numeric ID used before Minecraft 1.13; -1 if the item didn't exist before 1.13
     */
    public int getNumericId() {
        return numeric;
    }

    /**
     * Returns the numeric data value used before Minecraft 1.13; 0 if the item didn't exist before 1.13; 0 if the item doesn't need data.
     *
     * @return the numeric data value used before Minecraft 1.13; 0 if the item didn't exist before 1.13; 0 if the item doesn't need data
     */
    public short getData() {
        return data;
    }

    /**
     * Returns the internal ID. The enum name in 1.13+; the numeric ID and the data value if it isn't 0 separated with a ":" before 1.13.
     *
     * @return the internal ID. The enum name in 1.13+; the numeric ID and the data value if it isn't 0 separated with a ":" before 1.13
     */
    public String getInternalId() {
        if (CompatibilityHandler.getInstance().getVersion().useNewMaterials()) {
            return id;
        } else {
            return getOldNameAndData();
        }
    }

    /**
     * Returns the numeric ID + the data value if it exists, separated by a ":".
     *
     * @return the numeric ID + the data value if it exists, separated by a ":"
     */
    public String getNumericIdAndData() {
        if (data != 0) {
            return numeric + ":" + data;
        } else {
            return String.valueOf(numeric);
        }
    }

    /**
     * Returns the old name + the data value if it exists, separated by a ":".
     *
     * @return the old name + the data value if it exists, separated by a ":"
     */
    public String getOldNameAndData() {
        if (data != 0) {
            return id1_8 + ":" + data;
        } else {
            return id1_8;
        }
    }

    @Override
    public String getName() {
        return id;
    }

    /**
     * Returns if the represented mob is available in the current version.
     *
     * @return if the represented mob is available in the current version
     */
    public boolean isAvailable() {
        return Version.isAtLeast(firstVersion) && Version.isAtMost(lastVersion);
    }

    @Override
    public ExItem idMatch(String id) {
        if (id.equals(id1_14) || id.equals(id1_13) || id.equals(getNumericIdAndData()) || id.equals(getOldNameAndData())) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public ExItem idMatch2nd(String id) {
        if (id.equalsIgnoreCase(id1_14) || id.equalsIgnoreCase(id1_13) || id.equalsIgnoreCase(getNumericIdAndData()) || id.equalsIgnoreCase(getOldNameAndData())
                || id.equalsIgnoreCase(id1_8) || id.equalsIgnoreCase(String.valueOf(numeric))) {
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
            material = Material.valueOf(getName());
        }
        return material;
    }

    @Override
    public ItemStack toItemStack(int amount) {
        if (!isAvailable()) {
            return new ItemStack(Material.AIR);
        }
        if (CompatibilityHandler.getInstance().getVersion().useNewMaterials()) {
            return new ItemStack(getMaterial(), amount);
        } else {
            return new ItemStack(getMaterial(), amount, data);
        }
    }

    /**
     * Returns if this VanillaItem wraps the given material.
     *
     * @param material a material
     * @return if this VanillaItem wraps the given material
     */
    public boolean is(Material material) {
        return getMaterial() == material;
    }

    /**
     * Returns if this VanillaItem wraps the material of the item.
     *
     * @param item an item stack
     * @return if this VanillaItem wraps the material of the item
     */
    public boolean is(ItemStack item) {
        if (item == null) {
            return false;
        }
        return is(item.getType());
    }

    /**
     * Returns if this VanillaItem wraps the material of the block.
     *
     * @param block a block
     * @return if this VanillaItem wraps the material of the block
     */
    public boolean is(Block block) {
        if (block == null) {
            return false;
        }
        return is(block.getType());
    }

}
