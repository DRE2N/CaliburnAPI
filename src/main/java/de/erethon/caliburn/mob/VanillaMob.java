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
package de.erethon.caliburn.mob;

import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.compatibility.Version;
import static de.erethon.commons.compatibility.Version.*;
import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * @author Daniel Saukel
 */
public class VanillaMob extends ExMob {

    private static Collection<VanillaMob> values = new ArrayList<>();
    private static Collection<VanillaMob> loaded = new ArrayList<>();

    public static final VanillaMob ITEM = new VanillaMob(MC1_8, "Item", "item", "DROPPED_ITEM", 1);
    public static final VanillaMob XP_ORB = new VanillaMob(MC1_8, "XPOrb", "xp_orb", "EXPERIENCE_ORB", 2);
    public static final VanillaMob AREA_EFFECT_CLOUD = new VanillaMob(MC1_9, "AreaEffectCloud", "area_effect_cloud", "AREA_EFFECT_CLOUD", 3);
    public static final VanillaMob EGG = new VanillaMob(MC1_8, "ThrownEgg", "egg", "EGG", 7);
    public static final VanillaMob LEASH_KNOT = new VanillaMob(MC1_8, "LeashKnot", "leash_knot", "LEASH_HITCH", 8);
    public static final VanillaMob PAINTING = new VanillaMob(MC1_8, "Painting", "painting", "PAINTING", 9);
    public static final VanillaMob ARROW = new VanillaMob(MC1_8, "Arrow", "arrow", "ARROW", 10);
    public static final VanillaMob SNOWBALL = new VanillaMob(MC1_8, "Snowball", "snowball", "SNOWBALL", 11);
    public static final VanillaMob FIREBALL = new VanillaMob(MC1_8, "Fireball", "fireball", "FIREBALL", 12);
    public static final VanillaMob SMALL_FIREBALL = new VanillaMob(MC1_8, "SmallFireball", "small_fireball", "SMALL_FIREBALL", 13);
    public static final VanillaMob ENDER_PEARL = new VanillaMob(MC1_8, "ThrownEnderpearl", "ender_pearl", "ENDER_PEARL", 14);
    public static final VanillaMob EYE_OF_ENDER_SIGNAL = new VanillaMob(MC1_8, "EyeOfEnderSignal", "eye_of_ender_signal", "ENDER_SIGNAL", 15);
    public static final VanillaMob POTION = new VanillaMob(MC1_8, "ThrownPotion", "potion", "SPLASH_POTION", 16);
    public static final VanillaMob XP_BOTTLE = new VanillaMob(MC1_8, "ThrownExpBottle", "xp_bottle", "THROWN_EXP_BOTTLE", 17);
    public static final VanillaMob ITEM_FRAME = new VanillaMob(MC1_8, "ItemFrame", "item_frame", "ITEM_FRAME", 18);
    public static final VanillaMob WITHER_SKULL = new VanillaMob(MC1_8, "WitherSkull", "wither_skull", "WITHER_SKULL", 19);
    public static final VanillaMob TNT = new VanillaMob(MC1_8, "PrimedTNT", "tnt", "PRIMED_TNT", 20);
    public static final VanillaMob FALLING_BLOCK = new VanillaMob(MC1_8, "FallingSand", "falling_block", "FALLING_BLOCK", 21);
    public static final VanillaMob FIREWORKS_ROCKET = new VanillaMob(MC1_8, "FireworksRocketEntity", "fireworks_rocket", "FIREWORK", 22);
    public static final VanillaMob SPECTRAL_ARROW = new VanillaMob(MC1_9, "SpectralArrow", "spectral_arrow", "SPECTRAL_ARROW", 24);
    public static final VanillaMob SHULKER_BULLET = new VanillaMob(MC1_9, "ShulkerBullet", "shulker_bullet", "SHULKER_BULLET", 25);
    public static final VanillaMob DRAGON_FIREBALL = new VanillaMob(MC1_9, "DragonFireball", "dragon_fireball", "DRAGON_FIREBALL", 26);
    public static final VanillaMob ARMOR_STAND = new VanillaMob(MC1_8, "ArmorStand", "armor_stand", "ARMOR_STAND", 30);
    public static final VanillaMob EVOCATION_FANGS = new VanillaMob(MC1_11, "evocation_fangs", "EVOKER_FANGS", 33);
    public static final VanillaMob EVOCATION_ILLAGER = new VanillaMob(MC1_11, "evocation_illager", "EVOKER", 34);
    public static final VanillaMob VEX = new VanillaMob(MC1_11, "vex", "VEX", 35);
    public static final VanillaMob VINDICATION_ILLAGER = new VanillaMob(MC1_11, "vindication_illager", "VINDICATOR", 36);
    public static final VanillaMob ILLUSION_ILLAGER = new VanillaMob(MC1_12, "illusion_illager", "ILLUSIONER", 37);
    public static final VanillaMob COMMANDBLOCK_MINECART = new VanillaMob(MC1_8, "MinecartCommandBlock", "commandblock_minecart", "MINECART_COMMAND", 40);
    public static final VanillaMob BOAT = new VanillaMob(MC1_8, "Boat", "boat", "BOAT", 41);
    public static final VanillaMob MINECART = new VanillaMob(MC1_8, "MinecartRideable", "minecart", "MINECART", 42);
    public static final VanillaMob CHEST_MINECART = new VanillaMob(MC1_8, "MinecartChest", "chest_minecart", "MINECART_CHEST", 43);
    public static final VanillaMob FURNACE_MINECART = new VanillaMob(MC1_8, "MinecartFurnace", "furnace_minecart", "MINECART_FURNACE", 44);
    public static final VanillaMob TNT_MINECART = new VanillaMob(MC1_8, "MinecartTNT", "tnt_minecart", "MINECART_TNT", 45);
    public static final VanillaMob HOPPER_MINECART = new VanillaMob(MC1_8, "MinecartHopper", "hopper_minecart", "MINECART_HOPPER", 46);
    public static final VanillaMob SPAWNER_MINECART = new VanillaMob(MC1_8, "MinecartSpawner", "spawner_minecart", "MINECART_MOB_SPAWNER", 47);
    public static final VanillaMob CREEPER = new VanillaMob(MC1_8, "Creeper", "creeper", "CREEPER", 50);
    public static final VanillaMob SKELETON = new VanillaMob(MC1_8, "Skeleton", "skeleton", "SKELETON", 51);
    public static final VanillaMob SPIDER = new VanillaMob(MC1_8, "Spider", "spider", "SPIDER", 52);
    public static final VanillaMob GIANT = new VanillaMob(MC1_8, "Giant", "giant", "GIANT", 53);
    public static final VanillaMob ZOMBIE = new VanillaMob(MC1_8, "Zombie", "zombie", "ZOMBIE", 54);
    public static final VanillaMob SLIME = new VanillaMob(MC1_8, "Slime", "slime", "SLIME", 55);
    public static final VanillaMob GHAST = new VanillaMob(MC1_8, "Ghast", "ghast", "GHAST", 56);
    public static final VanillaMob ZOMBIE_PIGMAN = new VanillaMob(MC1_8, "PigZombie", "zombie_pigman", "PIG_ZOMBIE", 57);
    public static final VanillaMob ENDERMAN = new VanillaMob(MC1_8, "Enderman", "enderman", "ENDERMAN", 58);
    public static final VanillaMob CAVE_SPIDER = new VanillaMob(MC1_8, "CaveSpider", "cave_spider", "CAVE_SPIDER", 59);
    public static final VanillaMob SILVERFISH = new VanillaMob(MC1_8, "Silverfish", "silverfish", "SILVERFISH", 60);
    public static final VanillaMob BLAZE = new VanillaMob(MC1_8, "Blaze", "blaze", "BLAZE", 61);
    public static final VanillaMob MAGMA_CUBE = new VanillaMob(MC1_8, "LavaSlime", "magma_cube", "MAGMA_CUBE", 62);
    public static final VanillaMob ENDER_DRAGON = new VanillaMob(MC1_8, "EnderDragon", "ender_dragon", "ENDER_DRAGON", 63);
    public static final VanillaMob WITHER = new VanillaMob(MC1_8, "WitherBoss", "wither", "WITHER", 64);
    public static final VanillaMob BAT = new VanillaMob(MC1_8, "Bat", "bat", "BAT", 65);
    public static final VanillaMob WITCH = new VanillaMob(MC1_8, "Witch", "witch", "WITCH", 66);
    public static final VanillaMob ENDERMITE = new VanillaMob(MC1_8, "Endermite", "endermite", "ENDERMITE", 67);
    public static final VanillaMob GUARDIAN = new VanillaMob(MC1_8, "Guardian", "guardian", "GUARDIAN", 68);
    public static final VanillaMob SHULKER = new VanillaMob(MC1_9, "Shulker", "shulker", "SHULKER", 69);
    public static final VanillaMob PIG = new VanillaMob(MC1_8, "Pig", "pig", "PIG", 90);
    public static final VanillaMob SHEEP = new VanillaMob(MC1_8, "Sheep", "sheep", "SHEEP", 91);
    public static final VanillaMob COW = new VanillaMob(MC1_8, "Cow", "cow", "COW", 92);
    public static final VanillaMob CHICKEN = new VanillaMob(MC1_8, "Chicken", "chicken", "CHICKEN", 93);
    public static final VanillaMob SQUID = new VanillaMob(MC1_8, "Squid", "squid", "SQUID", 94);
    public static final VanillaMob WOLF = new VanillaMob(MC1_8, "Wolf", "wolf", "WOLF", 95);
    public static final VanillaMob MOOSHROOM = new VanillaMob(MC1_8, "MushroomCow", "mooshroom", "MUSHROOM_COW", 96);
    public static final VanillaMob SNOWMAN = new VanillaMob(MC1_8, "SnowMan", "snowman", "SNOWMAN", 97);
    public static final VanillaMob OCELOT = new VanillaMob(MC1_8, "Ozelot", "ocelot", "OCELOT", 98);
    public static final VanillaMob VILLAGER_GOLEM = new VanillaMob(MC1_8, "VillagerGolem", "villager_golem", "IRON_GOLEM", 99);
    public static final VanillaMob HORSE = new VanillaMob(MC1_8, "EntityHorse", "horse", "HORSE", 100);
    public static final VanillaMob RABBIT = new VanillaMob(MC1_8, "Rabbit", "rabbit", "RABBIT", 101);
    public static final VanillaMob POLAR_BEAR = new VanillaMob(MC1_10, "PolarBear", "polar_bear", "POLAR_BEAR", 102);
    public static final VanillaMob LLAMA = new VanillaMob(MC1_11, "llama", "LLAMA", 103);
    public static final VanillaMob LLAMA_SPIT = new VanillaMob(MC1_11, "llama_spit", "LLAMA_SPIT", 104);
    public static final VanillaMob PARROT = new VanillaMob(MC1_12, "parrot", "PARROT", 105);
    public static final VanillaMob VILLAGER = new VanillaMob(MC1_8, "Villager", "villager", "VILLAGER", 120);
    public static final VanillaMob ENDER_CRYSTAL = new VanillaMob(MC1_8, "EnderCrystal", "ender_crystal", "ENDER_CRYSTAL", 200);

    public static final SplitMob ELDER_GUARDIAN = new SplitMob(MC1_8, GUARDIAN, "elder_guardian", "ELDER_GUARDIAN", 4);
    public static final SplitMob WITHER_SKELETON = new SplitMob(MC1_8, SKELETON, "wither_skeleton", "WITHER_SKELETON", 5);
    public static final SplitMob STRAY = new SplitMob(MC1_10, SKELETON, "stray", "STRAY", 6);
    public static final SplitMob HUSK = new SplitMob(MC1_10, ZOMBIE, "husk", "HUSK", 23);
    public static final SplitMob ZOMBIE_VILLAGER = new SplitMob(MC1_8, ZOMBIE, "zombie_villager", "ZOMBIE_VILLAGER", 27);
    public static final SplitMob SKELETON_HORSE = new SplitMob(MC1_8, HORSE, "skeleton_horse", "SKELETON_HORSE", 28);
    public static final SplitMob ZOMBIE_HORSE = new SplitMob(MC1_8, HORSE, "zombie_horse", "ZOMBIE_HORSE", 29);
    public static final SplitMob DONKEY = new SplitMob(MC1_8, HORSE, "donkey", "DONKEY", 31);
    public static final SplitMob MULE = new SplitMob(MC1_8, HORSE, "mule", "MULE", 32);

    static {
        values.add(ITEM);
        values.add(XP_ORB);
        values.add(AREA_EFFECT_CLOUD);
        values.add(EGG);
        values.add(LEASH_KNOT);
        values.add(PAINTING);
        values.add(ARROW);
        values.add(SNOWBALL);
        values.add(FIREBALL);
        values.add(SMALL_FIREBALL);
        values.add(ENDER_PEARL);
        values.add(EYE_OF_ENDER_SIGNAL);
        values.add(POTION);
        values.add(XP_BOTTLE);
        values.add(ITEM_FRAME);
        values.add(WITHER_SKULL);
        values.add(TNT);
        values.add(FALLING_BLOCK);
        values.add(FIREWORKS_ROCKET);
        values.add(SPECTRAL_ARROW);
        values.add(SHULKER_BULLET);
        values.add(DRAGON_FIREBALL);
        values.add(ARMOR_STAND);
        values.add(EVOCATION_FANGS);
        values.add(EVOCATION_ILLAGER);
        values.add(VEX);
        values.add(VINDICATION_ILLAGER);
        values.add(ILLUSION_ILLAGER);
        values.add(COMMANDBLOCK_MINECART);
        values.add(BOAT);
        values.add(MINECART);
        values.add(CHEST_MINECART);
        values.add(FURNACE_MINECART);
        values.add(TNT_MINECART);
        values.add(HOPPER_MINECART);
        values.add(SPAWNER_MINECART);
        values.add(CREEPER);
        values.add(SKELETON);
        values.add(SPIDER);
        values.add(GIANT);
        values.add(ZOMBIE);
        values.add(SLIME);
        values.add(GHAST);
        values.add(ZOMBIE_PIGMAN);
        values.add(ENDERMAN);
        values.add(CAVE_SPIDER);
        values.add(SILVERFISH);
        values.add(BLAZE);
        values.add(MAGMA_CUBE);
        values.add(ENDER_DRAGON);
        values.add(WITHER);
        values.add(BAT);
        values.add(WITCH);
        values.add(ENDERMITE);
        values.add(GUARDIAN);
        values.add(SHULKER);
        values.add(PIG);
        values.add(SHEEP);
        values.add(COW);
        values.add(CHICKEN);
        values.add(SQUID);
        values.add(WOLF);
        values.add(MOOSHROOM);
        values.add(SNOWMAN);
        values.add(OCELOT);
        values.add(VILLAGER_GOLEM);
        values.add(HORSE);
        values.add(RABBIT);
        values.add(POLAR_BEAR);
        values.add(LLAMA);
        values.add(LLAMA_SPIT);
        values.add(PARROT);
        values.add(VILLAGER);
        values.add(ENDER_CRYSTAL);
        values.add(ELDER_GUARDIAN);
        values.add(WITHER_SKELETON);
        values.add(STRAY);
        values.add(HUSK);
        values.add(ZOMBIE_VILLAGER);
        values.add(SKELETON_HORSE);
        values.add(ZOMBIE_HORSE);
        values.add(DONKEY);
        values.add(MULE);
        for (VanillaMob vm : values) {
            if (vm.isAvailable()) {
                loaded.add(vm);
            }
        }
    }

    public static Collection<VanillaMob> values() {
        return values;
    }

    public static Collection<VanillaMob> getLoaded() {
        return loaded;
    }

    private Version firstVersion;
    private Version lastVersion;
    private String oldId;
    private String newId;
    private int numeric;
    private String bukkit;

    protected VanillaMob(String oldId, String newId, String bukkit, int numeric) {
        this(MC1_8, oldId, newId, bukkit, numeric);
    }

    protected VanillaMob(Version firstVersion, String oldId, String newId, String bukkit, int numeric) {
        this(firstVersion, null, oldId, newId, bukkit, numeric);
    }

    protected VanillaMob(Version firstVersion, String newId, String bukkit) {
        this(firstVersion, newId, bukkit, -1);
    }

    protected VanillaMob(Version firstVersion, String newId, String bukkit, int numeric) {
        this(firstVersion, null, newId, bukkit);
    }

    protected VanillaMob(Version firstVersion, Version lastVersion, String newId, String bukkit) {
        this(firstVersion, lastVersion, new String(), newId, bukkit, -1);
    }

    protected VanillaMob(Version firstVersion, Version lastVersion, String oldId, String newId, String bukkit, int numeric) {
        this.firstVersion = firstVersion;
        this.lastVersion = lastVersion;
        this.oldId = oldId;
        this.newId = newId;
        this.bukkit = bukkit;
        this.numeric = numeric;
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
     * @return
     * the old String ID used before Minecraft 1.11
     */
    public String getOldId() {
        return oldId;
    }

    /**
     * @return
     * the new String ID used since Minecraft 1.11
     */
    public String getNewId() {
        return newId;
    }

    /**
     * @return
     * the Bukkit enum name
     */
    public String getBukkitName() {
        return bukkit;
    }

    /**
     * @return
     * the numeric ID used before Minecraft 1.13;
     * -1 if the item didn't exist before 1.13
     */
    public int getNumericId() {
        return numeric;
    }

    @Override
    public String getName() {
        return getNewId();
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
    public ExMob idMatch(String id) {
        if (id.toUpperCase().equals(bukkit) || id.equals(newId) || id.equals(oldId)) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public EntityType getSpecies() {
        if (isAvailable()) {
            return EntityType.valueOf(bukkit);
        } else {
            return EntityType.UNKNOWN;
        }
    }

    @Override
    public Entity toEntity(Location location) {
        if (!isAvailable()) {
            return location.getWorld().spawn(location, getSpecies().getEntityClass());
        }
        return location.getWorld().spawn(location, getSpecies().getEntityClass());
    }

}