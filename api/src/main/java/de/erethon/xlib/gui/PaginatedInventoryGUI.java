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
import de.erethon.xlib.gui.layout.PaginatedInventoryLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * A chest inventory based paginated GUI.
 *
 * @author Daniel Saukel
 */
public class PaginatedInventoryGUI extends InventoryGUI implements Paginated<InventoryGUI> {

    /**
     * @deprecated for internal use only
     */
    @Deprecated
    public static Player exclude;

    private List<Inventory> openedInventories = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private Map<Player, Integer> openedPage = new HashMap<>();
    private boolean componentMoveUpEnabled;

    public PaginatedInventoryGUI() {
        super();
    }

    /**
     * @param title the title
     */
    public PaginatedInventoryGUI(String title) {
        super(title);
    }

    public PaginatedInventoryGUI(PaginatedInventoryGUI gui) {
        super(gui);
    }

    @Override
    public int getPages() {
        return ((PaginatedInventoryLayout) getLayout()).getPages();
    }

    @Override
    public String getTitle(int page) {
        if (titles.size() > page && page > 0) {
            String title = titles.get(page);
            if (title != null) {
                return title;
            }
        }
        return getTitle();
    }

    @Override
    public void setTitle(int page, String title) {
        if (titles.size() <= page) {
            titles = Arrays.asList(titles.toArray(new String[page + 1]));
        }
        titles.set(page, title);
    }

    @Override
    public boolean isComponentMoveUpEnabled() {
        return componentMoveUpEnabled;
    }

    @Override
    public void setComponentMoveUpEnabled(boolean moveUp) {
        componentMoveUpEnabled = moveUp;
    }

    @Override
    public PaginatedInventoryGUI open(int page, Player player) {
        if (!isRegistered()) {
            throw new IllegalStateException("The GUI " + toString() + " is not registered");
        }
        if (page >= getPages()) {
            page = 0;
        } else if (page < 0) {
            page = getPages() - 1;
        }
        if (viewers.contains(player)) {
            openedPage.put(player, page);
            exclude = player;
            player.openInventory(openedInventories.get(page));
            exclude = null;
            return this;
        } else {
            PaginatedInventoryGUI copy = ((PaginatedInventoryGUI) getContextualizedCopy(player));
            copy.viewers.add(player);
            copy.openedPage.put(player, page);
            player.openInventory(copy.createInventories(player).get(page));
            return copy;
        }
    }

    @Override
    public void close(Player... players) {
        for (Player player : players) {
            if (viewers.contains(player)) {
                openedPage.remove(player);
                viewers.remove(player);
                player.closeInventory();
            }
        }
    }

    @Override
    public void removeViewer(Player player) {
        super.removeViewer(player);
        openedPage.remove(player);
    }

    /**
     * Creates the inventories for each page.
     * <p>
     * This should only be done if the inventory is a {@link #getContextualizedCopy(Player) contextualized copy}.
     *
     * @param viewer the viewer
     * @return the inventory
     */
    private List<Inventory> createInventories(Player viewer) {
        PaginatedInventoryLayout layout = (PaginatedInventoryLayout) getLayout();
        for (int page = 0; page < getPages(); page++) {
            openedInventories.add(Bukkit.createInventory(null, getSize(), getTitle(page)));
            for (int slot = 0; slot < getSize(); slot++) {
                Component<?, InventoryGUI> comp = layout.getComponent(page, slot);
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
                openedInventories.get(page).setItem(slot, button.createItemStack());
            }
        }
        return openedInventories;
    }

    /**
     * Returns a List of each {@link org.bukkit.inventory.Inventory} created per page from this GUI.
     *
     * @return a List of each {@link org.bukkit.inventory.Inventory} created per page from this GUI
     */
    public List<Inventory> getOpenedInventories() {
        return openedInventories;
    }

    @Override
    public Integer getOpenedPage(Player player) {
        return openedPage.get(player);
    }

    @Override
    public boolean is(Inventory rawInventory) {
        if (rawInventory == null) {
            return false;
        }
        return openedInventories.contains(rawInventory);
    }

    @Override
    public PaginatedInventoryGUI copy() {
        return new PaginatedInventoryGUI(this);
    }

}
