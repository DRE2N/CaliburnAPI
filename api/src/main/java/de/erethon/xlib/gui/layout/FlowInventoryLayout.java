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
package de.erethon.xlib.gui.layout;

import de.erethon.xlib.gui.InventoryGUI;

/**
 * A basic {@link InventoryLayout} that adds {@link de.erethon.xlib.gui.component.Component}s in dextrograde reading direction.
 *
 * @author Daniel Saukel
 */
public class FlowInventoryLayout extends SingleInventoryLayout {

    public FlowInventoryLayout(InventoryGUI gui, int size) {
        super(gui, size);
    }

    protected FlowInventoryLayout(InventoryGUI gui, FlowInventoryLayout layout) {
        super(gui, layout);
    }

    @Override
    public int nextSlot() {
        slot++;
        if (slot >= getSize()) {
            slot = -1;
        }
        return slot;
    }

    @Override
    public FlowInventoryLayout copy(InventoryGUI gui) {
        return new FlowInventoryLayout(gui, this);
    }

}
