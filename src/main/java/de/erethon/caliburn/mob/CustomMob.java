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
package de.erethon.caliburn.mob;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.category.IdentifierType;
import de.erethon.caliburn.loottable.LootTable;
import de.erethon.caliburn.mob.actionhandler.AttackHandler;
import de.erethon.caliburn.mob.actionhandler.DamageHandler;
import de.erethon.caliburn.mob.actionhandler.DeathHandler;
import de.erethon.caliburn.mob.actionhandler.InteractHandler;
import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.compatibility.Internals;
import de.erethon.commons.misc.EnumUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

/**
 * A mob that has by default changed properties compared to Minecraft's vanilla mobs.
 * TODO: META DATA, ATTRIBUTES, PERSISTENT DATA CONTAINER
 *
 * @author Daniel Saukel
 */
public class CustomMob extends ExMob {

    private static Set<Internals> higher = Internals.andHigher(CompatibilityHandler.getInstance().getInternals());

    /* Entity */
    private VanillaMob base;
    private String name;
    private Boolean customNameVisible;
    private Boolean glowing;
    private Boolean gravity;
    private Boolean invulnerable;
    private Boolean silent;
    private Boolean persistent;
    private List<String> passengers = new ArrayList<>();

    /* LivingEntity */
    private Collection<PotionEffect> potionEffects;
    private LootTable equipment;
    private Boolean removeWhenFarAway;
    private Boolean ai;
    private Boolean collidable;
    private Boolean pickupItems;
    private Integer maxAir;

    private LootTable drops;

    private AttackHandler attackHandler;
    private DamageHandler damageHandler;
    private DeathHandler deathHandler;
    private InteractHandler interactHandler;

    public CustomMob(CaliburnAPI api, IdentifierType idType, String id, Entity entity) {
        this.api = api;
        this.idType = idType;
        this.id = id;

        name = entity.getCustomName();
        customNameVisible = entity.isCustomNameVisible();
        if (!higher.contains(Internals.v1_8_R3)) {// 1.9+
            glowing = entity.isGlowing();
            invulnerable = entity.isInvulnerable();
            silent = entity.isSilent();
            if (!higher.contains(Internals.v1_9_R2)) {
                gravity = entity.hasGravity();
                if (!higher.contains(Internals.v1_10_R1)) {
                    entity.getPassengers().forEach(e -> passengers.add(api.getExMob(e).getId()));
                    if (!higher.contains(Internals.v1_12_R1)) {
                        persistent = entity.isPersistent();
                    }
                }
            }
        }

        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entity;
            potionEffects = living.getActivePotionEffects();
            equipment = new LootTable(api, id + "_loot");
            equipment.readEntityEquipment(living.getEquipment());
            removeWhenFarAway = living.getRemoveWhenFarAway();
            if (!higher.contains(Internals.v1_8_R3)) {
                ai = living.hasAI();
                collidable = living.isCollidable();
            }
            pickupItems = living.getCanPickupItems();
            maxAir = living.getMaximumAir();
        }

        raw = serialize();
    }

    public CustomMob(CaliburnAPI api, IdentifierType idType, String id, VanillaMob base) {
        this.api = api;
        this.idType = idType;
        this.id = id;
        setBase(base);
        raw = serialize();
    }

    private CustomMob() {
    }

    public static CustomMob deserialize(Map<String, Object> args) {
        if (args == null) {
            throw new IllegalArgumentException("args must not be null");
        }
        CustomMob deserialized = new CustomMob();
        deserialized.raw = args;

        Object species = args.get("species");
        if (species instanceof String) {
            ExMob base = CaliburnAPI.getInstance().getExMob((String) species);
            if (base instanceof VanillaMob) {
                deserialized.setBase((VanillaMob) base);
            }
        }
        if (deserialized.base == null) {
            throw new IllegalArgumentException("Custom item does not have valid material");
        }

        Object idType = args.get("idType");
        if (idType instanceof String) {
            IdentifierType idTypeValue = EnumUtil.getEnumIgnoreCase(IdentifierType.class, (String) idType);
            if (idTypeValue != null) {
                deserialized.idType = idTypeValue;
            }
        } else {
            deserialized.idType = IdentifierType.DISPLAY_NAME;
        }

        /* Entity */
        Object name = args.get("name");
        if (name instanceof String) {
            deserialized.setName((String) name);
        }
        Object customNameVisible = args.get("isCustomNameVisible");
        if (customNameVisible instanceof Boolean) {
            deserialized.setCustomNameVisible((Boolean) customNameVisible);
        }
        Object glowing = args.get("isGlowing");
        if (glowing instanceof Boolean) {
            deserialized.setGlowing((Boolean) glowing);
        }
        Object gravity = args.get("hasGravity");
        if (gravity instanceof Boolean) {
            deserialized.setGravity((Boolean) gravity);
        }
        Object invulnerable = args.get("isInvulnerable");
        if (invulnerable instanceof Boolean) {
            deserialized.setInvulnerable((Boolean) invulnerable);
        }
        Object silent = args.get("isSilent");
        if (silent instanceof Boolean) {
            deserialized.setSilent((Boolean) silent);
        }
        Object persistent = args.get("isPersistent");
        if (persistent instanceof Boolean) {
            deserialized.setPersistent((Boolean) persistent);
        }
        Object passengers = args.get("passengers");
        if (passengers instanceof List) {
            for (Object passenger : (List) passengers) {
                if (passenger instanceof String) {
                    deserialized.getPassengers().add((String) passenger);
                }
            }
        }

        /* LivingEntity */
        Object potionEffects = args.get("potionEffects");
        if (potionEffects instanceof Collection) {
            try {
                deserialized.setPotionEffects((Collection<PotionEffect>) potionEffects);
            } catch (ClassCastException exception) {
            }
        }
        Object equipment = args.get("equipment");
        if (equipment instanceof String) {
            deserialized.setEquipment(CaliburnAPI.getInstance().getLootTable((String) equipment));
        } else if (equipment instanceof LootTable) {
            deserialized.setEquipment((LootTable) equipment);
        }
        Object removeWhenFarAway = args.get("removeWhenFarAway");
        if (removeWhenFarAway instanceof Boolean) {
            deserialized.setRemoveWhenFarAway((Boolean) removeWhenFarAway);
        }
        Object ai = args.get("hasAI");
        if (ai instanceof Boolean) {
            deserialized.setAI((Boolean) ai);
        }
        Object collidable = args.get("isCollidable");
        if (collidable instanceof Boolean) {
            deserialized.setCollidable((Boolean) collidable);
        }
        Object pickupItems = args.get("canPickupItems");
        if (pickupItems instanceof Boolean) {
            deserialized.setPickupItems((Boolean) pickupItems);
        }
        Object maxAir = args.get("maxAir");
        if (maxAir instanceof Number) {
            deserialized.setMaxAir(((Number) maxAir).intValue());
        }

        Object drops = args.get("drops");
        if (drops instanceof String) {
            deserialized.setDrops(CaliburnAPI.getInstance().getLootTable((String) drops));
        } else if (equipment instanceof Map) {
            deserialized.setDrops((LootTable) drops);
        }

        Object attackHandler = args.get("attackHandler");
        if (attackHandler instanceof String) {
            deserialized.setAttackHandler(AttackHandler.create((String) attackHandler));
        }
        Object damageHandler = args.get("damageHandler");
        if (damageHandler instanceof String) {
            deserialized.setDamageHandler(DamageHandler.create((String) damageHandler));
        }
        Object deathHandler = args.get("deathHandler");
        if (deathHandler instanceof String) {
            deserialized.setDeathHandler(DeathHandler.create((String) deathHandler));
        }
        Object interactHandler = args.get("interactHandler");
        if (interactHandler instanceof String) {
            deserialized.setInteractHandler(InteractHandler.create((String) interactHandler));
        }

        return deserialized;
    }

    /* Getters and setters */
    /**
     * Returns the mob that this one is based on.
     *
     * @return the mob that this one is based on
     */
    public VanillaMob getBase() {
        return base;
    }

    /**
     * Sets the mob that this one is based on.
     *
     * @param base the mob that this one is based on
     */
    public void setBase(VanillaMob base) {
        this.base = base;
        this.species = base.getSpecies();
    }

    /**
     * Returns the display name of this mob.
     *
     * @return the display name of this mob
     */
    @Override
    public String getName() {
        return name != null ? name : super.getName();
    }

    /**
     * Sets the display name.
     * <p>
     * Supports color codes.
     *
     * @param name the display name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns if the custom name is visible; null = Minecraft default.
     *
     * @return if the custom name is visible; null = Minecraft default
     */
    public Boolean isCustomNameVisible() {
        return customNameVisible;
    }

    /**
     * Set if the custom name is visible.
     *
     * @param customNameVisible if the custom name is visible
     */
    public void setCustomNameVisible(boolean customNameVisible) {
        this.customNameVisible = customNameVisible;
    }

    /**
     * Returns if the mob is glowing; null = Minecraft default.
     *
     * @return if the mob is glowing; null = Minecraft default
     */
    public Boolean isGlowing() {
        return glowing;
    }

    /**
     * Set if the mob is glowing.
     *
     * @param glowing if the mob is glowing
     */
    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
    }

    /**
     * Returns if the mob has gravity; null = Minecraft default.
     *
     * @return if the mob has gravity; null = Minecraft default
     */
    public Boolean hasGravity() {
        return gravity;
    }

    /**
     * Sets if the mob has gravity
     *
     * @param gravity if the mob has gravity
     */
    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    /**
     * Returns if the mob is invulnerable; null = Minecraft default.
     *
     * @return if the mob is invulnerable; null = Minecraft default
     */
    public Boolean isInvulnerable() {
        return invulnerable;
    }

    /**
     * Sets if the mob is invulnerable.
     *
     * @param invulnerable if the mob is invulnerable
     */
    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    /**
     * Returns if the mob is silent; null = Minecraft default.
     *
     * @return if the mob is silent; null = Minecraft default
     */
    public Boolean isSilent() {
        return silent;
    }

    /**
     * Sets if the mob is silent.
     *
     * @param silent if the mob is silent
     */
    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    /**
     * Returns if the mob is persistent; null = Minecraft default.
     *
     * @return if the mob is persistent; null = Minecraft default
     * @deprecated the underlying Bukkit API is deprecated.
     */
    @Deprecated
    public Boolean isPersistent() {
        return persistent;
    }

    /**
     * Sets if the mob is persistent.
     *
     * @param persistent if the mob is persistent
     * @deprecated the underlying Bukkit API is deprecated.
     */
    @Deprecated
    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    /**
     * Returns a List of the IDs of the passengers the mob is spawned with.
     *
     * @return a List of the IDs of the passengers the mob is spawned with
     */
    public List<String> getPassengers() {
        return passengers;
    }

    /**
     * Sets the IDS of the passengers the mob is spawned with.
     *
     * @param passengers the IDS of the passengers the mob is spawned with
     */
    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }

    /**
     * Returns a Collection of the potion effects the mob is spawned with.
     *
     * @return a Collection of the potion effects the mob is spawned with
     */
    public Collection<PotionEffect> getPotionEffects() {
        return potionEffects;
    }

    /**
     * Sets the potion effects the mob is spawned with.
     *
     * @param potionEffects the potion effects
     */
    public void setPotionEffects(Collection<PotionEffect> potionEffects) {
        this.potionEffects = potionEffects;
    }

    /**
     * Returns the mob's equipment in a loot table where the slot names of the entries define the position of the respective item.
     * These are taken from the constants in {@link LootTable}, like e.g. {@link LootTable#MAIN_HAND}.
     * <p>
     * This value may be null instead if the mob doesn't have any equipment.
     *
     * @return the mob's equipment in a loot table; this value may be null instead if the mob doesn't have any equipment
     */
    public LootTable getEquipment() {
        return equipment;
    }

    /**
     * Sets the mob's equipment.
     *
     * @param equipment the equipment loot table (see explanation in {@link #getEquipment()}
     */
    public void setEquipment(LootTable equipment) {
        this.equipment = equipment;
    }

    /**
     * Returns if the mob is removed when it is far awawy; null = Minecraft default.
     *
     * @return if the mob is removed when it is far awawy; null = Minecraft default
     */
    public Boolean getRemoveWhenFarAway() {
        return removeWhenFarAway;
    }

    /**
     * Sets if the mob is removed when it is far away.
     *
     * @param removeWhenFarAway if the mob is removed when it is far away
     */
    public void setRemoveWhenFarAway(boolean removeWhenFarAway) {
        this.removeWhenFarAway = removeWhenFarAway;
    }

    /**
     * Returns if the mob has an AI; null = Minecraft default.
     *
     * @return if the mob has an AI; null = Minecraft default
     */
    public Boolean hasAI() {
        return ai;
    }

    /**
     * Sets if the mob has an AI.
     *
     * @param ai if the mob has an AI
     */
    public void setAI(boolean ai) {
        this.ai = ai;
    }

    /**
     * Returns if the mob is collidable; null = Minecraft default.
     * <p>
     * Mobs that are set not to be collidable still collide with mobs that are collidable.
     *
     * @return if the mob is collidable; null = Minecraft default
     */
    public Boolean isCollidable() {
        return collidable;
    }

    /**
     * Sets if the mob is collidable.
     * <p>
     * Mobs that are set not to be collidable still collide with mobs that are collidable.
     *
     * @param collidable if the mob is collidable
     */
    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    /**
     * Returns if the mob can pickup items; null = Minecraft default.
     *
     * @return if the mob can pickup items; null = Minecraft default
     */
    public Boolean canPickupItems() {
        return pickupItems;
    }

    /**
     * Set if the mob can pickup items.
     *
     * @param pickupItems if the mob can pickup items
     */
    public void setPickupItems(boolean pickupItems) {
        this.pickupItems = pickupItems;
    }

    /**
     * Returns the maximum amount of air the mob has; null = Minecraft default.
     *
     * @return the maximum amount of air the mob has; null = Minecraft default
     */
    public Integer getMaxAir() {
        return maxAir;
    }

    /**
     * Sets the maximum amount of air the mob has.
     *
     * @param maxAir the maximum amount of air the mob has
     */
    public void setMaxAir(int maxAir) {
        this.maxAir = maxAir;
    }

    /**
     * Returns a loot table of the mob's drops; null = Minecraft default.
     *
     * @return a loot table of the mob's drops; null = Minecraft default
     */
    public LootTable getDrops() {
        return drops;
    }

    /**
     * Sets the drops; null = Minecraft default.
     *
     * @param drops the drops or null to use the default values
     */
    public void setDrops(LootTable drops) {
        this.drops = drops;
    }

    /* Events */
    /**
     * Returns if the custom mob has an AttackHandler.
     *
     * @return if the custom mob has an AttackHandler
     */
    public boolean hasAttackHandler() {
        return attackHandler != null;
    }

    /**
     * Returns the AttackHandler.
     *
     * @return the AttackHandler
     */
    public AttackHandler getAttackHandler() {
        return attackHandler;
    }

    /**
     * Sets the AttackHandler.
     *
     * @param attackHandler the handler to set
     */
    public void setAttackHandler(AttackHandler attackHandler) {
        this.attackHandler = attackHandler;
    }

    /**
     * Returns if the custom mob has a DamageHandler.
     *
     * @return if the custom mob has a DamageHandler
     */
    public boolean hasDamageHandler() {
        return damageHandler != null;
    }

    /**
     * Returns the DamageHandler.
     *
     * @return the DamageHandler
     */
    public DamageHandler getDamageHandler() {
        return damageHandler;
    }

    /**
     * Sets the DamageHandler.
     *
     * @param damageHandler the handler to set
     */
    public void setDamageHandler(DamageHandler damageHandler) {
        this.damageHandler = damageHandler;
    }

    /**
     * Returns if the custom mob has a DeathHandler.
     *
     * @return if the custom mob has a DeathHandler
     */
    public boolean hasDeathHandler() {
        return deathHandler != null;
    }

    /**
     * Returns the DeathHandler.
     *
     * @return the DeathHandler
     */
    public DeathHandler getDeathHandler() {
        return deathHandler;
    }

    /**
     * Sets the DeathHandler.
     *
     * @param deathHandler the handler to set
     */
    public void setDeathHandler(DeathHandler deathHandler) {
        this.deathHandler = deathHandler;
    }

    /**
     * Returns if the custom mob has an InteractHandler.
     *
     * @return if the custom mob has an InteractHandler
     */
    public boolean hasInteractHandler() {
        return interactHandler != null;
    }

    /**
     * Returns the InteractHandler.
     *
     * @return the InteractHandler
     */
    public InteractHandler getInteractHandler() {
        return interactHandler;
    }

    /**
     * Sets the InteractHandler.
     *
     * @param interactHandler the handler to set
     */
    public void setInteractHandler(InteractHandler interactHandler) {
        this.interactHandler = interactHandler;
    }

    /* Actions */
    /**
     * Registers the mob sothat it can be fetched through the getter methods.
     *
     * @return itself
     */
    public CustomMob register() {
        if (api.getExMobs().contains(this) || api.getExMob(id) != null) {
            throw new IllegalStateException("Mob already registered");
        }
        if (id == null) {
            throw new IllegalStateException("No ID specified");
        }
        api.getExMobs().add((CustomMob) id(id));
        return this;
    }

    /**
     * Registers the mob sothat it can be fetched through the getter methods.
     *
     * @param id the ID to set
     * @return itself
     */
    public CustomMob register(String id) {
        if (api.getExMobs().contains(this) || api.getExMob(id) != null) {
            throw new IllegalStateException("Mob already registered");
        }
        api.getExMobs().add((CustomMob) id(id));
        return this;
    }

    @Override
    public Map<String, Object> serialize() {
        if (raw != null) {
            return new HashMap<>(raw);
        }
        Map<String, Object> config = new HashMap<>();

        config.put("species", base.getId());

        if (getName() != null) {
            config.put("name", getName());
        }
        if (isCustomNameVisible() != null) {
            config.put("isCustomNameVisible", isCustomNameVisible());
        }
        if (isGlowing() != null) {
            config.put("isGlowing", isGlowing());
        }
        if (hasGravity() != null) {
            config.put("hasGravity", hasGravity());
        }
        if (isInvulnerable() != null) {
            config.put("isInvulnerable", isInvulnerable());
        }
        if (isSilent() != null) {
            config.put("isSilent", isSilent());
        }
        if (isPersistent() != null) {
            config.put("isPersistent", isPersistent());
        }
        if (getPassengers() != null && !getPassengers().isEmpty()) {
            config.put("passengers", getPassengers());
        }

        if (getPotionEffects() != null && !getPotionEffects().isEmpty()) {
            config.put("potionEffects", getPotionEffects());
        }
        if (getEquipment() != null) {
            config.put("equipment", getEquipment());
        }
        if (getRemoveWhenFarAway() != null) {
            config.put("removeWhenFarAway", getRemoveWhenFarAway());
        }
        if (hasAI() != null) {
            config.put("hasAI", hasAI());
        }
        if (isCollidable() != null) {
            config.put("isCollidable", isCollidable());
        }
        if (canPickupItems() != null) {
            config.put("canPickupItems", canPickupItems());
        }
        if (getMaxAir() != null) {
            config.put("maxAir", getMaxAir());
        }

        if (getDrops() != null) {
            config.put("drops", getDrops());
        }

        if (hasAttackHandler()) {
            config.put("attackHandler", getAttackHandler().getClass().getName());
        }
        if (hasDamageHandler()) {
            config.put("damageHandler", getDamageHandler().getClass().getName());
        }
        if (hasDeathHandler()) {
            config.put("deathHandler", getDeathHandler().getClass().getName());
        }
        if (hasInteractHandler()) {
            config.put("interactHandler", getInteractHandler().getClass().getName());
        }

        return config;
    }

    @Override
    public Entity toEntity(Location location) {
        Entity entity = location.getWorld().spawnEntity(location, species);

        if (getName() != null) {
            entity.setCustomName(getName());
        }
        if (isCustomNameVisible() != null) {
            entity.setCustomNameVisible(isCustomNameVisible());
        }
        if (!higher.contains(Internals.v1_8_R3)) {// 1.9+
            if (isGlowing() != null) {
                entity.setGlowing(isGlowing());
            }
            if (isInvulnerable() != null) {
                entity.setInvulnerable(isInvulnerable());
            }
            if (isSilent() != null) {
                entity.setSilent(isSilent());
            }
            if (!higher.contains(Internals.v1_9_R2)) {
                if (hasGravity() != null) {
                    entity.setGravity(hasGravity());
                }
                if (!higher.contains(Internals.v1_10_R1)) {
                    if (getPassengers() != null) {
                        getPassengers().forEach(p -> entity.addPassenger(api.getExMob(p).toEntity(location)));
                    }
                    if (!higher.contains(Internals.v1_12_R1)) {
                        if (isPersistent() != null) {
                            entity.setPersistent(isPersistent());
                        }
                    }
                }
            }
        }

        if (!(entity instanceof LivingEntity)) {
            return entity;
        }
        LivingEntity living = (LivingEntity) entity;
        if (getPotionEffects() != null) {
            getPotionEffects().forEach(living::addPotionEffect);
        }
        if (getEquipment() != null) {
            getEquipment().setEntityEquipment(living.getEquipment());
        }
        if (getRemoveWhenFarAway() != null) {
            living.setRemoveWhenFarAway(getRemoveWhenFarAway());
        }
        if (!higher.contains(Internals.v1_8_R3)) {
            if (hasAI() != null) {
                living.setAI(hasAI());
            }
            if (isCollidable() != null) {
                living.setCollidable(isCollidable());
            }
        }
        if (canPickupItems() != null) {
            living.setCanPickupItems(canPickupItems());
        }
        if (getMaxAir() != null) {
            living.setMaximumAir(getMaxAir());
        }
        return entity;
    }

}
