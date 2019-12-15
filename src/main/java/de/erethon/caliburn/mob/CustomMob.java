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

import de.erethon.caliburn.mob.actionhandler.AttackHandler;
import de.erethon.caliburn.mob.actionhandler.DamageHandler;
import de.erethon.caliburn.mob.actionhandler.DeathHandler;
import de.erethon.caliburn.mob.actionhandler.InteractHandler;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * @author Daniel Saukel
 */
public class CustomMob extends ExMob {

    private String name;

    private AttackHandler attackHandler;
    private DamageHandler damageHandler;
    private DeathHandler deathHandler;
    private InteractHandler interactHandler;

    public CustomMob(Map<String, Object> args) {
        raw = args;

        Object name = args.get("name");
        if (name instanceof String) {
            setName((String) name);
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

        config.put("species", species.name());
        config.put("name", name);

        if (attackHandler != null) {
            config.put("attackHandler", attackHandler.getClass().getName());
        }
        if (damageHandler != null) {
            config.put("damageHandler", damageHandler.getClass().getName());
        }
        if (deathHandler != null) {
            config.put("deathHandler", deathHandler.getClass().getName());
        }
        if (interactHandler != null) {
            config.put("interactHandler", interactHandler.getClass().getName());
        }

        return config;
    }

    @Override
    public Entity toEntity(Location location) {
        Entity entity = location.getWorld().spawnEntity(location, species);

        if (name != null) {
            entity.setCustomName(name);
            entity.setCustomNameVisible(true);
        }

        return entity;
    }

}
