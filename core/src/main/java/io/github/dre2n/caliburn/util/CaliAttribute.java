/*
 * Copyright (C) 2015-2016 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.caliburn.util;

import org.bukkit.attribute.Attribute;

/**
 * @author Daniel Saukel
 */
public class CaliAttribute {

    private String name;

    public CaliAttribute(String name) {
        setName(name);
    }

    public CaliAttribute(Attribute attribute) {
        setName(attribute);
    }

    /**
     * @return
     * the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     * the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * a Bukkit attribute or null if none exists
     */
    public Attribute asBukkitAttribute() {
        return ItemUtil.getBukkitAttribute(name);
    }

    /**
     * @param attribute
     * the Bukkit Attribute that provides the name
     */
    public void setName(Attribute attribute) {
        name = ItemUtil.getInternalAttributeName(attribute);
    }

}
