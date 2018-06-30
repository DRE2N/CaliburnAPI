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
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

/**
 * This class represents a vanilla mob that has been a sub type of a different mob
 * until Minecraft 1.11.
 *
 * @author Daniel Saukel
 */
public class SplitMob extends VanillaMob {

    private VanillaMob oldSuperType;

    protected SplitMob(Version firstVersion, VanillaMob oldSuperType, String id1_13, String bukkit, int numeric) {
        this(firstVersion, null, oldSuperType, id1_13, bukkit, numeric);
    }

    protected SplitMob(Version firstVersion, Version lastVersion, VanillaMob oldSuperType, String id1_13, String bukkit, int numeric) {
        super(firstVersion, lastVersion, oldSuperType.getId1_8(), id1_13, id1_13, bukkit, numeric);
        this.oldSuperType = oldSuperType;
    }

    /**
     * @return
     * the mob that used to be the super type of this mob
     */
    public VanillaMob getOldSuperType() {
        return oldSuperType;
    }

    @Override
    public EntityType getSpecies() {
        return oldSuperType.getSpecies();
    }

    @Override
    public Entity toEntity(Location location) {
        Entity entity = super.toEntity(location);
        if (!CompatibilityHandler.getInstance().getVersion().useNewMobNames()) {
            if (ELDER_GUARDIAN.getId1_13().equals(getId1_13())) {
                ((Guardian) entity).setElder(true);
            } else if (WITHER_SKELETON.getId1_13().equals(getId1_13())) {
                ((Skeleton) entity).setSkeletonType(SkeletonType.WITHER);
            } else if (STRAY.getId1_13().equals(getId1_13())) {
                ((Skeleton) entity).setSkeletonType(SkeletonType.STRAY);
            } else if (HUSK.getId1_13().equals(getId1_13())) {
                ((Zombie) entity).setVillagerProfession(Villager.Profession.HUSK);
            } else if (ZOMBIE_VILLAGER.getId1_13().equals(getId1_13())) {
                ((Zombie) entity).setVillager(true);
            } else if (SKELETON_HORSE.getId1_13().equals(getId1_13())) {
                ((Horse) entity).setVariant(Horse.Variant.SKELETON_HORSE);
            } else if (ZOMBIE_HORSE.getId1_13().equals(getId1_13())) {
                ((Horse) entity).setVariant(Horse.Variant.UNDEAD_HORSE);
            } else if (DONKEY.getId1_13().equals(getId1_13())) {
                ((Horse) entity).setVariant(Horse.Variant.DONKEY);
            } else if (MULE.getId1_13().equals(getId1_13())) {
                ((Horse) entity).setVariant(Horse.Variant.MULE);
            }
        }
        return entity;
    }

}
