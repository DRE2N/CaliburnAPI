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

import de.erethon.xlib.gui.component.Component;
import de.erethon.xlib.gui.component.InventoryButton;
import de.erethon.xlib.gui.layout.InventoryLayout;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author Daniel Saukel
 */
public class SingleInventoryGUI extends InventoryGUI {

    private Inventory openedInventory;

    public SingleInventoryGUI() {
        super();
    }

    /**
     * @param title the title
     */
    public SingleInventoryGUI(String title) {
        super(title);
    }

    public SingleInventoryGUI(SingleInventoryGUI gui) {
        super(gui);
    }

    @Override
    public SingleInventoryGUI open(Player player) {
        if (!isRegistered()) {
            throw new IllegalStateException("The GUI " + toString() + " is not registered");
        }
        SingleInventoryGUI copy = ((SingleInventoryGUI) getContextualizedCopy(player));
        copy.viewers.add(player);
        player.openInventory(copy.createInventory(player));
        return copy;
    }

    @Override
    public void close(Player... players) {
        for (Player player : players) {
            if (viewers.contains(player)) {
                viewers.remove(player);
                player.closeInventory();
            }
        }
    }

    /**
     * Creates the inventory.
     * <p>
     * This should only be done if the inventory is a {@link #getContextualizedCopy(Player) contextualized copy}.
     *
     * @param viewer the viewer
     * @return the inventory
     */
    private Inventory createInventory(Player viewer) {
        openedInventory = Bukkit.createInventory(null, getSize(), getTitle());
        InventoryLayout layout = (InventoryLayout) getLayout();
        for (int i = 0; i < getSize(); i++) {
            Component<?, InventoryGUI> comp = layout.getComponent(i);
            if (!(comp instanceof InventoryButton)) {
                continue;
            }
            InventoryButton button = (InventoryButton) comp;
            if (!button.getContextModifiers().isEmpty()) {
                // If the GUI is just a transient copy anyway, there is no need to copy the buttons.
                if (!isTransient()) {
                    button = ((InventoryButton) comp).copy();
                }
                button.applyAllContextModifiers(viewer);
            }
            openedInventory.setItem(i, button.createItemStack());
        }
        return openedInventory;
    }

    /**
     * Returns the last {@link org.bukkit.inventory.Inventory} created from this GUI.
     *
     * @return the last {@link org.bukkit.inventory.Inventory} created from this GUI
     */
    public Inventory getOpenedInventory() {
        return openedInventory;
    }

    @Override
    public boolean is(Inventory rawInventory) {
        if (rawInventory == null) {
            return false;
        }
        return rawInventory.equals(openedInventory);
    }

    @Override
    public SingleInventoryGUI copy() {
        return new SingleInventoryGUI(this);
    }

}
