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
package de.erethon.caliburn.mob;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.loottable.LootTable;
import de.erethon.caliburn.mob.actionhandler.AttackHandler;
import de.erethon.caliburn.mob.actionhandler.DamageHandler;
import de.erethon.caliburn.mob.actionhandler.DeathHandler;
import de.erethon.caliburn.mob.actionhandler.InteractHandler;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

/**
 * TODO: META DATA, ATTRIBUTES, PERSISTENT DATA CONTAINER
 *
 * @author Daniel Saukel
 */
public class CustomMob extends ExMob {

    /* Entity */
    private VanillaMob base;
    private String name;
    private Boolean customNameVisible;
    private Boolean glowing;
    private Boolean gravity;
    private Boolean invulnerable;
    private Boolean silent;
    private Boolean persistent;

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

    public CustomMob(Map<String, Object> args) {
        raw = args;

        /* Entity */
        Object species = args.get("species");
        if (species instanceof String) {
            ExMob base = CaliburnAPI.getInstance().getExMob((String) species);
            if (base instanceof VanillaMob) {
                setBase((VanillaMob) base);
                this.species = base.getSpecies();
            }
        }

        Object name = args.get("name");
        if (name instanceof String) {
            setName((String) name);
        }
        Object customNameVisible = args.get("isCustomNameVisible");
        if (customNameVisible instanceof Boolean) {
            setCustomNameVisible((Boolean) customNameVisible);
        }
        Object glowing = args.get("isGlowing");
        if (glowing instanceof Boolean) {
            setGlowing((Boolean) glowing);
        }
        Object gravity = args.get("hasGravity");
        if (gravity instanceof Boolean) {
            setGravity((Boolean) gravity);
        }
        Object invulnerable = args.get("isInvulnerable");
        if (invulnerable instanceof Boolean) {
            setInvulnerable((Boolean) invulnerable);
        }
        Object silent = args.get("isSilent");
        if (silent instanceof Boolean) {
            setSilent((Boolean) silent);
        }
        Object persistent = args.get("isPersistent");
        if (persistent instanceof Boolean) {
            setPersistent((Boolean) persistent);
        }

        /* LivingEntity */
        Object potionEffects = args.get("potionEffects");
        if (potionEffects instanceof Collection) {
            try {
                setPotionEffects((Collection<PotionEffect>) potionEffects);
            } catch (ClassCastException exception) {
            }
        }
        Object equipment = args.get("equipment");
        if (equipment instanceof String) {
            setEquipment(CaliburnAPI.getInstance().getLootTable((String) equipment));
        } else if (equipment instanceof Map) {

        }
        Object removeWhenFarAway = args.get("removeWhenFarAway");
        if (removeWhenFarAway instanceof Boolean) {
            setRemoveWhenFarAway((Boolean) removeWhenFarAway);
        }
        Object ai = args.get("hasAI");
        if (ai instanceof Boolean) {
            setAI((Boolean) ai);
        }
        Object collidable = args.get("isCollidable");
        if (collidable instanceof Boolean) {
            setCollidable((Boolean) collidable);
        }
        Object pickupItems = args.get("canPickupItems");
        if (pickupItems instanceof Boolean) {
            setPickupItems((Boolean) pickupItems);
        }
        Object maxAir = args.get("maxAir");
        if (maxAir instanceof Number) {
            setMaxAir(((Number) maxAir).intValue());
        }

        Object drops = args.get("drops");
        if (drops instanceof String) {
            setDrops(CaliburnAPI.getInstance().getLootTable((String) drops));
        } else if (equipment instanceof Map) {

        }

        Object attackHandler = args.get("attackHandler");
        if (attackHandler instanceof String) {
            setAttackHandler(AttackHandler.create((String) attackHandler));
        }
        Object damageHandler = args.get("damageHandler");
        if (damageHandler instanceof String) {
            setDamageHandler(DamageHandler.create((String) damageHandler));
        }
        Object deathHandler = args.get("deathHandler");
        if (deathHandler instanceof String) {
            setDeathHandler(DeathHandler.create((String) deathHandler));
        }
        Object interactHandler = args.get("interactHandler");
        if (interactHandler instanceof String) {
            setInteractHandler(InteractHandler.create((String) interactHandler));
        }
    }

    public CustomMob(String id, EntityType type) {
        this.id = id;
        species = type;
    }

    /* Getters and setters */
    public VanillaMob getBase() {
        return base;
    }

    public void setBase(VanillaMob base) {
        this.base = base;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the custom name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public Boolean isCustomNameVisible() {
        return customNameVisible;
    }

    public void setCustomNameVisible(boolean customNameVisible) {
        this.customNameVisible = customNameVisible;
    }

    public Boolean isGlowing() {
        return glowing;
    }

    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
    }

    public Boolean hasGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public Boolean isInvulnerable() {
        return invulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public Boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    @Deprecated
    public Boolean isPersistent() {
        return persistent;
    }

    @Deprecated
    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public Collection<PotionEffect> getPotionEffects() {
        return potionEffects;
    }

    public void setPotionEffects(Collection<PotionEffect> potionEffects) {
        this.potionEffects = potionEffects;
    }

    public LootTable getEquipment() {
        return equipment;
    }

    public void setEquipment(LootTable equipment) {
        this.equipment = equipment;
    }

    public Boolean getRemoveWhenFarAway() {
        return removeWhenFarAway;
    }

    public void setRemoveWhenFarAway(boolean removeWhenFarAway) {
        this.removeWhenFarAway = removeWhenFarAway;
    }

    public Boolean hasAI() {
        return ai;
    }

    public void setAI(boolean ai) {
        this.ai = ai;
    }

    public Boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    public Boolean canPickupItems() {
        return pickupItems;
    }

    public void setPickupItems(boolean pickupItems) {
        this.pickupItems = pickupItems;
    }

    public Integer getMaxAir() {
        return maxAir;
    }

    public void setMaxAir(int maxAir) {
        this.maxAir = maxAir;
    }

    public LootTable getDrops() {
        return drops;
    }

    public void setDrops(LootTable drops) {
        this.drops = drops;
    }

    /**
     * @return if the custom item has an AttackHandler
     */
    public boolean hasAttackHandler() {
        return attackHandler != null;
    }

    /**
     * @return the AttackHandler
     */
    public AttackHandler getAttackHandler() {
        return attackHandler;
    }

    /**
     * @param attackHandler the handler to set
     */
    public void setAttackHandler(AttackHandler attackHandler) {
        this.attackHandler = attackHandler;
    }

    /**
     * @return if the custom mob has a DamageHandler
     */
    public boolean hasDamageHandler() {
        return damageHandler != null;
    }

    /**
     * @return the DamageHandler
     */
    public DamageHandler getDamageHandler() {
        return damageHandler;
    }

    /**
     * @param damageHandler the handler to set
     */
    public void setDamageHandler(DamageHandler damageHandler) {
        this.damageHandler = damageHandler;
    }

    /**
     * @return if the custom mob has a DeathHandler
     */
    public boolean hasDeathHandler() {
        return deathHandler != null;
    }

    /**
     * @return the DeathHandler
     */
    public DeathHandler getDeathHandler() {
        return deathHandler;
    }

    /**
     * @param deathHandler the handler to set
     */
    public void setDeathHandler(DeathHandler deathHandler) {
        this.deathHandler = deathHandler;
    }

    /**
     * @return if the custom mob has a InteractHandler
     */
    public boolean hasInteractHandler() {
        return interactHandler != null;
    }

    /**
     * @return the InteractHandler
     */
    public InteractHandler getInteractHandler() {
        return interactHandler;
    }

    /**
     * @param interactHandler the handler to set
     */
    public void setInteractHandler(InteractHandler interactHandler) {
        this.interactHandler = interactHandler;
    }

    /* Actions */
    /**
     * Registers the mob sothat it can be fetched through the getter methods
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
     * Registers the mob sothat it can be fetched through the getter methods
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

        if (getPotionEffects() != null || getPotionEffects().isEmpty()) {
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
        if (isGlowing() != null) {
            entity.setGlowing(isGlowing());
        }
        if (hasGravity() != null) {
            entity.setGravity(hasGravity());
        }
        if (isInvulnerable() != null) {
            entity.setInvulnerable(isInvulnerable());
        }
        if (isSilent() != null) {
            entity.setSilent(isSilent());
        }
        if (isPersistent() != null) {
            entity.setPersistent(isPersistent());
        }

        if (!(entity instanceof LivingEntity)) {
            return entity;
        }
        LivingEntity living = (LivingEntity) entity;
        if (getPotionEffects() != null || getPotionEffects().isEmpty()) {
            getPotionEffects().forEach(living::addPotionEffect);
        }
        if (getEquipment() != null) {
            getEquipment().setEntityEquipment(living.getEquipment());
        }
        if (getRemoveWhenFarAway() != null) {
            living.setRemoveWhenFarAway(getRemoveWhenFarAway());
        }
        if (hasAI() != null) {
            living.setAI(hasAI());
        }
        if (isCollidable() != null) {
            living.setCollidable(isCollidable());
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
