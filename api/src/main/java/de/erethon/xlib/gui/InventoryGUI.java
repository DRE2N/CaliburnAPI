/*
 * Copyright (C) 2015-2026 Daniel Saukel.
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
package de.erethon.xlib.gui;

import de.erethon.xlib.gui.action.MoveItemStackListener;
import de.erethon.xlib.gui.component.InventoryButton;
import de.erethon.xlib.gui.layout.InventoryLayout;
import de.erethon.xlib.gui.layout.Layout;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Shared code of chest inventory based GUIs.
 *
 * @author Daniel Saukel
 */
public abstract class InventoryGUI extends AbstractGUI<InventoryGUI> {

    private MoveItemStackListener moveItemStackListener;

    protected InventoryGUI() {
        super();
    }

    /**
     * @param title the title
     */
    protected InventoryGUI(String title) {
        super(title);
    }

    protected InventoryGUI(InventoryGUI gui) {
        super(gui);
        moveItemStackListener = gui.moveItemStackListener;
    }

    @Override
    public void setLayout(Layout<InventoryGUI> layout) {
        if (!(layout instanceof InventoryLayout)) {
            throw new IllegalArgumentException(layout.getClass().getName() + " is not an instance of " + InventoryLayout.class.getName());
        }
        super.setLayout(layout);
    }

    /**
     * Returns the amount of slots in the inventory.
     * <p>
     * The size is set through an {@link de.erethon.xlib.gui.layout.InventoryLayout}.
     *
     * @return the amount of slots in the inventory
     */
    public int getSize() {
        return ((InventoryLayout) getLayout()).getSize();
    }

    /**
     * Removes a player from the viewers list without closing the inventory.
     *
     * @param player the player to remove from the viewers list
     * @deprecated for internal use only; use {@link #close(org.bukkit.entity.Player...)} instead.
     */
    @Deprecated
    public void removeViewer(Player player) {
        viewers.remove(player);
    }

    /**
     * Returns the first InventoryButton which represents the {@link org.bukkit.inventory.ItemStack}.
     *
     * @param itemStack the ItemStack
     * @return the first InventoryButton that is equal to the ItemStack
     */
    public InventoryButton getButton(ItemStack itemStack) {
        return ((InventoryLayout) getLayout()).getButton(itemStack);
    }

    /**
     * Returns the first InventoryButton which represents the {@link org.bukkit.inventory.ItemStack}.
     * <p>
     * Also checks versions of the button modified by {@link de.erethon.xlib.gui.context.ContextModifier}s.
     *
     * @param itemStack     the ItemStack
     * @param contextPlayer the Player that is used to check the button's form modified by {@link de.erethon.xlib.gui.context.ContextModifier}s.
     * @return the first InventoryButton that is equal to the ItemStack
     */
    public InventoryButton getButton(ItemStack itemStack, Player contextPlayer) {
        return ((InventoryLayout) getLayout()).getButton(itemStack, contextPlayer);
    }

    /**
     * Returns the MoveItemStackListener attached to this GUI.
     *
     * @return the MoveItemStackListener attached to this GUI
     */
    public MoveItemStackListener getMoveItemStackListener() {
        return moveItemStackListener;
    }

    /**
     * Sets the MoveItemStackListener attached to this GUI.
     *
     * @param listener the listener to set
     */
    public void setMoveItemStackListener(MoveItemStackListener listener) {
        moveItemStackListener = listener;
    }

    /**
     * Checks if the GUI is a representation of an {@link org.bukkit.inventory.Inventory}.
     *
     * @param rawInventory an Inventory
     * @return if the Inventory is a representation of this GUI
     */
    public abstract boolean is(Inventory rawInventory);

    /**
     * Checks if the GUI is a representation of an {@link org.bukkit.inventory.Inventory}.
     * <p>
     * If the GUI has one or more {@link de.erethon.xlib.gui.context.ContextModifier}s and if it is not a representation of the Inventory
     * in the GUI's form not modified by its ContextModifiers, a copy of the GUI is made and the modifiers are applied to this copy for reference
     * to check if the modified form is a representation of the Inventory.
     *
     * @param rawInventory  an Inventory
     * @param contextPlayer the Player
     * @return if the Inventory is a representation of this GUI
     */
    public boolean is(Inventory rawInventory, Player contextPlayer) {
        boolean is = is(rawInventory);
        if (is) {
            return true;

        } else if (!getContextModifiers().isEmpty()) {
            InventoryGUI modified = copy();
            modified.applyAllContextModifiers(contextPlayer);
            return modified.is(rawInventory);

        } else {
            return false;
        }
    }

}
