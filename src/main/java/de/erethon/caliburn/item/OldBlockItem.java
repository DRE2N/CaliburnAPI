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
import org.bukkit.inventory.ItemStack;

/**
 * This class represents Materials that used to represent blocks that are merged with others as of Minecraft 1.13.
 *
 * @author Daniel Saukel
 */
public class OldBlockItem extends VanillaItem {

    private VanillaItem replacement;

    protected OldBlockItem(Version firstVersion, String oldName, VanillaItem replacement, int numeric) {
        this(firstVersion, oldName, replacement, numeric, (short) 0);
    }

    protected OldBlockItem(Version firstVersion, String oldName, VanillaItem replacement, int numeric, short data) {
        super(firstVersion, Version.MC1_12_2, oldName, replacement.getNewName(), numeric, data);
        this.replacement = replacement;
    }

    /**
     * @return the item that represents this block in 1.13+
     */
    public VanillaItem getReplacement() {
        return replacement;
    }

    @Override
    public ItemStack toItemStack(int amount) {
        if (!CompatibilityHandler.getInstance().getVersion().useNewMaterials()) {
            return super.toItemStack(amount);
        } else {
            return replacement.toItemStack(amount);
        }
    }

}
