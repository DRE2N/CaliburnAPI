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
package de.erethon.caliburn.mob;

import de.erethon.caliburn.category.IdentifierType;
import de.erethon.caliburn.util.StringUtil;
import de.erethon.bedrock.chat.MessageUtil;
import de.erethon.bedrock.compatibility.Version;
import static de.erethon.bedrock.compatibility.Version.*;
import de.erethon.bedrock.misc.EnumUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * Represents a vanilla mob.
 *
 * @author Daniel Saukel
 */
public class VanillaMob extends ExMob {

    public static final VanillaMob UNKNOWN = new VanillaMob(MC1_8, null, "UNKNOWN");

    public static final VanillaMob ITEM = new VanillaMob(MC1_8, "Item", "item", "DROPPED_ITEM", 1);
    public static final VanillaMob EXPERIENCE_ORB = new VanillaMob(MC1_8, "XPOrb", "xp_orb", "experience_orb", "EXPERIENCE_ORB", 2);
    public static final VanillaMob AREA_EFFECT_CLOUD = new VanillaMob(MC1_9, "AreaEffectCloud", "area_effect_cloud", "AREA_EFFECT_CLOUD", 3);
    public static final VanillaMob EGG = new VanillaMob(MC1_8, "ThrownEgg", "egg", "EGG", 7);
    public static final VanillaMob LEASH_KNOT = new VanillaMob(MC1_8, "LeashKnot", "leash_knot", "LEASH_HITCH", 8);
    public static final VanillaMob PAINTING = new VanillaMob(MC1_8, "Painting", "painting", "PAINTING", 9);
    public static final VanillaMob ARROW = new VanillaMob(MC1_8, "Arrow", "arrow", "ARROW", 10);
    public static final VanillaMob SNOWBALL = new VanillaMob(MC1_8, "Snowball", "snowball", "SNOWBALL", 11);
    public static final VanillaMob FIREBALL = new VanillaMob(MC1_8, "Fireball", "fireball", "FIREBALL", 12);
    public static final VanillaMob SMALL_FIREBALL = new VanillaMob(MC1_8, "SmallFireball", "small_fireball", "SMALL_FIREBALL", 13);
    public static final VanillaMob ENDER_PEARL = new VanillaMob(MC1_8, "ThrownEnderpearl", "ender_pearl", "ENDER_PEARL", 14);
    public static final VanillaMob EYE_OF_ENDER = new VanillaMob(MC1_8, "EyeOfEnderSignal", "eye_of_ender_signal", "eye_of_ender", "ENDER_SIGNAL", 15);
    public static final VanillaMob POTION = new VanillaMob(MC1_8, "ThrownPotion", "potion", "SPLASH_POTION", 16);
    public static final VanillaMob EXPERIENCE_BOTTLE = new VanillaMob(MC1_8, "ThrownExpBottle", "xp_bottle", "experience_bottle", "THROWN_EXP_BOTTLE", 17);
    public static final VanillaMob ITEM_FRAME = new VanillaMob(MC1_8, "ItemFrame", "item_frame", "ITEM_FRAME", 18);
    public static final VanillaMob WITHER_SKULL = new VanillaMob(MC1_8, "WitherSkull", "wither_skull", "WITHER_SKULL", 19);
    public static final VanillaMob TNT = new VanillaMob(MC1_8, "PrimedTNT", "tnt", "PRIMED_TNT", 20);
    public static final VanillaMob FALLING_BLOCK = new VanillaMob(MC1_8, "FallingSand", "falling_block", "FALLING_BLOCK", 21);
    public static final VanillaMob FIREWORK_ROCKET = new VanillaMob(MC1_8, "FireworksRocketEntity", "fireworks_rocket", "firework_rocket", "FIREWORK", 22);
    public static final VanillaMob TIPPED_ARROW = new VanillaMob(MC1_8, MC1_13, "TippedArrow", "TippedArrow", "tipped_arrow", "TIPPED_ARROW", 23) {
        @Override
        public EntityType getSpecies() {
            if (Version.isAtLeast(MC1_14)) {
                return ARROW.getSpecies();
            } else {
                return species;
            }
        }
    };
    public static final VanillaMob SPECTRAL_ARROW = new VanillaMob(MC1_9, "SpectralArrow", "spectral_arrow", "SPECTRAL_ARROW", 24);
    public static final VanillaMob SHULKER_BULLET = new VanillaMob(MC1_9, "ShulkerBullet", "shulker_bullet", "SHULKER_BULLET", 25);
    public static final VanillaMob DRAGON_FIREBALL = new VanillaMob(MC1_9, "DragonFireball", "dragon_fireball", "DRAGON_FIREBALL", 26);
    public static final VanillaMob ARMOR_STAND = new VanillaMob(MC1_8, "ArmorStand", "armor_stand", "ARMOR_STAND", 30);
    public static final VanillaMob EVOKER_FANGS = new VanillaMob(MC1_11, null, "evocation_fangs", "evoker_fangs", "EVOKER_FANGS", 33);
    public static final VanillaMob EVOKER = new VanillaMob(MC1_11, null, "evocation_illager", "evoker", "EVOKER", 34);
    public static final VanillaMob VEX = new VanillaMob(MC1_11, null, "vex", "VEX", 35);
    public static final VanillaMob VINDICATOR = new VanillaMob(MC1_11, null, "vindication_illager", "vindicator", "VINDICATOR", 36);
    public static final VanillaMob ILLUSIONER = new VanillaMob(MC1_12, null, "illusion_illager", "illusioner", "ILLUSIONER", 37);
    public static final VanillaMob COMMAND_BLOCK_MINECART = new VanillaMob(MC1_8, "MinecartCommandBlock", "commandblock_minecart", "command_block_minecart", "MINECART_COMMAND", 40);
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
    public static final VanillaMob ZOMBIE_PIGMAN = new VanillaMob(MC1_8, "PigZombie", "zombie_pigman", "ZOMBIFIED_PIGLIN", 57) {
        @Override
        public String getId1_16() {
            return "zombified_piglin";
        }

        @Override
        public String getBukkitName() {
            if (Version.isAtLeast(MC1_16)) {
                return super.getBukkitName();
            } else {
                return "PIG_ZOMBIE";
            }
        }
    };
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
    public static final VanillaMob SNOW_GOLEM = new VanillaMob(MC1_8, "SnowMan", "snowman", "snow_golem", "SNOWMAN", 97);
    public static final VanillaMob OCELOT = new VanillaMob(MC1_8, "Ozelot", "ocelot", "OCELOT", 98);
    public static final VanillaMob IRON_GOLEM = new VanillaMob(MC1_8, "VillagerGolem", "villager_golem", "iron_golem", "IRON_GOLEM", 99);
    public static final VanillaMob HORSE = new VanillaMob(MC1_8, "EntityHorse", "horse", "HORSE", 100);
    public static final VanillaMob RABBIT = new VanillaMob(MC1_8, "Rabbit", "rabbit", "RABBIT", 101);
    public static final VanillaMob POLAR_BEAR = new VanillaMob(MC1_10, "PolarBear", "polar_bear", "POLAR_BEAR", 102);
    public static final VanillaMob LLAMA = new VanillaMob(MC1_11, null, "llama", "LLAMA", 103);
    public static final VanillaMob LLAMA_SPIT = new VanillaMob(MC1_11, null, "llama_spit", "LLAMA_SPIT", 104);
    public static final VanillaMob PARROT = new VanillaMob(MC1_12, null, "parrot", "PARROT", 105);
    public static final VanillaMob VILLAGER = new VanillaMob(MC1_8, "Villager", "villager", "VILLAGER", 120);
    public static final VanillaMob END_CRYSTAL = new VanillaMob(MC1_8, "EnderCrystal", "ender_crystal", "end_crystal", "ENDER_CRYSTAL", 200);
    public static final VanillaMob DOLPHIN = new VanillaMob(MC1_13, "dolphin", "DOLPHIN");
    public static final VanillaMob DROWNED = new VanillaMob(MC1_13, "drowned", "DROWNED");
    public static final VanillaMob COD = new VanillaMob(MC1_13, "cod", "COD");
    public static final VanillaMob SALMON = new VanillaMob(MC1_13, "salmon", "SALMON");
    public static final VanillaMob PUFFERFISH = new VanillaMob(MC1_13, "pufferfish", "PUFFERFISH");
    public static final VanillaMob TROPICAL_FISH = new VanillaMob(MC1_13, "tropical_fish", "TROPICAL_FISH");
    public static final VanillaMob PHANTOM = new VanillaMob(MC1_13, "phantom", "PHANTOM");
    public static final VanillaMob TURTLE = new VanillaMob(MC1_13, "turtle", "TURTLE");
    public static final VanillaMob TRIDENT = new VanillaMob(MC1_13, "trident", "TRIDENT");
    public static final VanillaMob PANDA = new VanillaMob(MC1_14, "panda", "PANDA");
    public static final VanillaMob PILLAGER = new VanillaMob(MC1_14, "pillager", "PILLAGER");
    public static final VanillaMob RAVAGER = new VanillaMob(MC1_14, "ravager", "RAVAGER");
    public static final VanillaMob TRADER_LLAMA = new VanillaMob(MC1_14, "trader_llama", "TRADER_LLAMA");
    public static final VanillaMob WANDERING_TRADER = new VanillaMob(MC1_14, "wandering_trader", "WANDERING_TRADER");
    public static final VanillaMob FOX = new VanillaMob(MC1_14, "fox", "FOX");
    public static final VanillaMob BEE = new VanillaMob(MC1_15, "bee", "BEE");
    public static final VanillaMob HOGLIN = new VanillaMob(MC1_16, "hoglin", "HOGLIN");
    public static final VanillaMob PIGLIN = new VanillaMob(MC1_16, "piglin", "PIGLIN");
    public static final VanillaMob PIGLIN_BRUTE = new VanillaMob(MC1_16_2, "piglin_brute", "PIGLIN_BRUTE");
    public static final VanillaMob ZOGLIN = new VanillaMob(MC1_16, "zoglin", "ZOGLIN");
    public static final VanillaMob STRIDER = new VanillaMob(MC1_16, "strider", "STRIDER");
    public static final VanillaMob AXOLOTL = new VanillaMob(MC1_17, "axolotl", "AXOLOTL");
    public static final VanillaMob GLOW_ITEM_FRAME = new VanillaMob(MC1_17, "glow_item_frame", "GLOW_ITEM_FRAME");
    public static final VanillaMob GLOW_SQUID = new VanillaMob(MC1_17, "glow_squid", "GLOW_SQUID");
    public static final VanillaMob GOAT = new VanillaMob(MC1_17, "goat", "GOAT");
    public static final VanillaMob MARKER = new VanillaMob(MC1_17, "markert", "MARKER");

    public static final VanillaMob LINGERING_POTION = new VanillaMob(MC1_9, MC1_14, "lingering_potion", "LINGERING_POTION");
    public static final VanillaMob FISHING_HOOK = new VanillaMob(MC1_8, "fishing_bobber", "FISHING_HOOK");
    public static final VanillaMob LIGHTNING = new VanillaMob(MC1_8, "LightningBolt", "lightning_bolt", "LIGHTNING", -1);
    public static final VanillaMob WEATHER = new VanillaMob(MC1_8, MC1_14, "weather", "WEATHER");
    public static final VanillaMob PLAYER = new VanillaMob(MC1_8, "Player", "player", "PLAYER", -1);
    public static final VanillaMob COMPLEX_PART = new VanillaMob(MC1_8, MC1_14, "complex_part", "COMPLEX_PART");

    public static final SplitMob ELDER_GUARDIAN = new SplitMob(MC1_8, GUARDIAN, "elder_guardian", "ELDER_GUARDIAN", 4);
    public static final SplitMob WITHER_SKELETON = new SplitMob(MC1_8, SKELETON, "wither_skeleton", "WITHER_SKELETON", 5);
    public static final SplitMob STRAY = new SplitMob(MC1_10, SKELETON, "stray", "STRAY", 6);
    public static final SplitMob HUSK = new SplitMob(MC1_10, ZOMBIE, "husk", "HUSK", 23);
    public static final SplitMob ZOMBIE_VILLAGER = new SplitMob(MC1_8, ZOMBIE, "zombie_villager", "ZOMBIE_VILLAGER", 27);
    public static final SplitMob SKELETON_HORSE = new SplitMob(MC1_8, HORSE, "skeleton_horse", "SKELETON_HORSE", 28);
    public static final SplitMob ZOMBIE_HORSE = new SplitMob(MC1_8, HORSE, "zombie_horse", "ZOMBIE_HORSE", 29);
    public static final SplitMob DONKEY = new SplitMob(MC1_8, HORSE, "donkey", "DONKEY", 31);
    public static final SplitMob MULE = new SplitMob(MC1_8, HORSE, "mule", "MULE", 32);
    public static final SplitMob CAT = new SplitMob(MC1_8, OCELOT, "cat", "CAT", -1) {
        @Override
        public String getBukkitName() {
            if (!Version.isAtLeast(Version.MC1_14)) {
                return OCELOT.getBukkitName();
            } else {
                return super.getBukkitName();
            }
        }
    };

    private static final Collection<VanillaMob> VALUES = new ArrayList<>();
    private static final Collection<VanillaMob> LOADED = new ArrayList<>();
    private static final Map<EntityType, VanillaMob> BY_ENTITY_TYPE = new HashMap<>();

    static {
        for (Field constant : VanillaMob.class.getFields()) {
            try {
                VALUES.add((VanillaMob) constant.get(null));
            } catch (IllegalArgumentException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }

        StringBuilder sb = new StringBuilder("&c[WARNING] Caliburn lacks a built-in representation of the following mobs: ");
        boolean send = false, first = true;
        bukkitMobs:
        for (EntityType bukkit : EntityType.values()) {
            for (VanillaMob caliburn : VALUES) {
                if (caliburn.getBukkitName().equals(bukkit.name())) {
                    continue bukkitMobs;
                }
            }

            if (!first) {
                sb.append(", ");
            } else {
                first = false;
            }
            sb.append(bukkit.name());
            send = true;
            VALUES.add(new VanillaMob(NEW, bukkit.name().toLowerCase(), bukkit.name()));
        }
        if (send) {
            MessageUtil.log(sb.append(". Please update your implementation if possible.").toString());
        }

        sb = new StringBuilder("&c[WARNING] Caliburn has a representation of the following mobs that do not exist in Bukkit: ");
        send = false;
        first = true;
        for (VanillaMob vm : VALUES) {
            if (EnumUtil.isValidEnum(EntityType.class, vm.getName()) && vm.isAvailable()) {
                if (!first) {
                    sb.append(", ");
                } else {
                    first = false;
                }
                sb.append(vm.getName());
                send = true;
                continue;
            }
            if (vm.isAvailable()) {
                LOADED.add(vm);
                BY_ENTITY_TYPE.put(vm.getSpecies(), vm);
            }
        }
        if (send) {
            MessageUtil.log(sb.toString());
        }
    }

    /**
     * Returns all vanilla mobs that are known, including those that don't exist in this version.
     *
     * @return all vanilla mobs that are known
     */
    public static Collection<VanillaMob> values() {
        return VALUES;
    }

    /**
     * Returns all vanilla mobs that exist in this runtime environment.
     *
     * @return all vanilla mobs that exist in this runtime environment
     */
    public static Collection<VanillaMob> getLoaded() {
        return LOADED;
    }

    /**
     * Returns the VanillaMob that wraps the entity type.
     *
     * @param entityType an entity type
     * @return the VanillaItem that wraps the material
     */
    public static VanillaMob get(EntityType entityType) {
        if (entityType == null) {
            return UNKNOWN;
        }
        return BY_ENTITY_TYPE.get(entityType);
    }

    private Version firstVersion;
    private Version lastVersion;
    private String id1_8;
    private String id1_11;
    private String id1_13;
    private int numeric;
    private String bukkit;
    protected EntityType species;
    private String name;

    protected VanillaMob(Version firstVersion, String id1_13, String bukkit) {
        this(firstVersion, "", id1_13, bukkit, -1);
    }

    protected VanillaMob(Version firstVersion, String id1_18, String id1_13, String bukkit, int numeric) {
        this(firstVersion, Version.NEW, id1_18, id1_13, id1_13, bukkit, numeric);
    }

    protected VanillaMob(Version firstVersion, String id1_8, String id1_11, String id1_13, String bukkit, int numeric) {
        this(firstVersion, Version.NEW, id1_8, id1_11, id1_13, bukkit, numeric);
    }

    protected VanillaMob(Version firstVersion, Version lastVersion, String id1_13, String bukkit) {
        this(firstVersion, lastVersion, "", "", id1_13, bukkit, -1);
    }

    protected VanillaMob(Version firstVersion, Version lastVersion, String id1_11, String id1_13, String bukkit) {
        this(firstVersion, lastVersion, "", id1_11, id1_13, bukkit, -1);
    }

    protected VanillaMob(Version firstVersion, Version lastVersion, String id1_8, String id1_11, String id1_13, String bukkit, int numeric) {
        this.idType = IdentifierType.VANILLA;
        this.firstVersion = firstVersion;
        this.lastVersion = lastVersion;
        this.id1_8 = id1_8;
        this.id1_11 = id1_11;
        this.id1_13 = id1_13;
        this.bukkit = bukkit;
        this.numeric = numeric;
        name = StringUtil.formatId(getBukkitName());
    }

    /**
     * Returns the first supported version where this mob existed.
     *
     * @return the first supported version where this mob existed
     */
    public Version getFirstVersion() {
        return firstVersion;
    }

    /**
     * Returns the last version where this mob existed; NEW if the mob still exists.
     *
     * @return the last version where this mob existed; NEW if the mob still exists
     */
    public Version getLastVersion() {
        return lastVersion;
    }

    /**
     * Returns the old String ID used before Minecraft 1.11.
     *
     * @return the old String ID used before Minecraft 1.11
     */
    public String getId1_8() {
        return id1_8;
    }

    /**
     * Returns the new String ID used since Minecraft 1.11.
     *
     * @return the new String ID used since Minecraft 1.11
     */
    public String getId1_11() {
        return id1_11;
    }

    /**
     * Returns the new String ID used since Minecraft 1.13.
     *
     * @return the new String ID used since Minecraft 1.13
     */
    public String getId1_13() {
        return id1_13;
    }

    /**
     * Returns the new String ID used since Minecraft 1.16.
     * <p>
     * This is the same as {@link #getId()}.
     *
     * @return the new String ID used since Minecraft 1.16
     */
    public String getId1_16() {
        return id1_13;
    }

    @Override
    public String getId() {
        return getId1_16();
    }

    /**
     * Return the Bukkit enum name
     *
     * @return the Bukkit enum name
     */
    public String getBukkitName() {
        return bukkit;
    }

    /**
     * Returns the numeric ID used before Minecraft 1.13; -1 if the item didn't exist before 1.13.
     *
     * @return the numeric ID used before Minecraft 1.13; -1 if the item didn't exist before 1.13
     */
    public int getNumericId() {
        return numeric;
    }

    @Override
    public String getName() {
        return name;
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
    public ExMob idMatch(String id) {
        if (id.toUpperCase().equals(getBukkitName()) || id.equals(id1_13) || id.equals(id1_11) || id.equals(id1_8)) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public EntityType getSpecies() {
        if (species == null) {
            if (isAvailable() && EnumUtil.isValidEnum(EntityType.class, getBukkitName())){
                species = EntityType.valueOf(getBukkitName());
            } else {
                MessageUtil.log("&c[WARNING] Invalid species: " + getBukkitName());
                species = EntityType.UNKNOWN;
            }
        }
        return species;
    }

    @Override
    public Entity toEntity(Location location) {
        if (!isAvailable()) {
            return location.getWorld().spawn(location, getSpecies().getEntityClass());
        }
        return location.getWorld().spawn(location, getSpecies().getEntityClass());
    }

}
