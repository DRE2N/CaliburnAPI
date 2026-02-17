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
package de.erethon.xlib.gui.action;

import de.erethon.xlib.gui.InventoryGUI;
import de.erethon.xlib.gui.component.InventoryButton;
import de.erethon.xlib.gui.layout.InventoryLayout;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class MoveItemStackEvent {

    private static final Set<InventoryAction> REMOVE_ACTIONS = new HashSet<>();

    static {
        REMOVE_ACTIONS.add(InventoryAction.COLLECT_TO_CURSOR);
        REMOVE_ACTIONS.add(InventoryAction.HOTBAR_MOVE_AND_READD);
        REMOVE_ACTIONS.add(InventoryAction.HOTBAR_SWAP);
        REMOVE_ACTIONS.add(InventoryAction.MOVE_TO_OTHER_INVENTORY);
        REMOVE_ACTIONS.add(InventoryAction.PICKUP_ALL);
        REMOVE_ACTIONS.add(InventoryAction.PICKUP_HALF);
        REMOVE_ACTIONS.add(InventoryAction.PICKUP_ONE);
        REMOVE_ACTIONS.add(InventoryAction.PICKUP_SOME);
    }

    private InventoryGUI gui;
    private InventoryAction invAction;
    private ItemStack itemStack;
    private int slot;
    private Player player;

    public MoveItemStackEvent(InventoryGUI gui, InventoryAction invAction, ItemStack itemStack, int slot, Player player) {
        this.gui = gui;
        this.invAction = invAction;
        this.itemStack = itemStack;
        this.slot = slot;
        this.player = player;
    }

    /**
     * Returns the GUI that was interacted with.
     *
     * @return the GUI that was interacted with
     */
    public InventoryGUI getGUI() {
        return gui;
    }

    /**
     * Returns how the ItemStack was moved.
     *
     * @return how the ItemStack was moved
     */
    public InventoryAction getInventoryAction() {
        return invAction;
    }

    /**
     * Returns the ItemStack that was moved.
     *
     * @return the ItemStack that was moved
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Returns the slot where the ItemStack was added or taken from.
     *
     * @return the slot where the ItemStack was added or taken from
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Returns the player who interacted with the GUI.
     *
     * @return the player who interacted with the GUI
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the added ItemStack wrapped in an InventoryButton or returns null if the buttonnwas taken away.
     *
     * @return the added item as a button; null if the item was removed
     */
    public InventoryButton confirmAsButton() {
        if (REMOVE_ACTIONS.contains(invAction) || itemStack == null || itemStack.getType() == Material.AIR) {
            return null;
        }
        InventoryLayout layout = (InventoryLayout) gui.getLayout();
        InventoryButton button = new InventoryButton(itemStack);
        layout.set(slot, button);
        return button;
    }

}
