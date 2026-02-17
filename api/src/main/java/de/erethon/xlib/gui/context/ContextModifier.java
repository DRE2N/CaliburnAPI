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
package de.erethon.xlib.gui.context;

import org.bukkit.entity.Player;

/**
 * Context modifiers can be used to set contents that cannot be
 * statically added but depend on the player who requests them
 * and the state of the game environment.
 * <p>
 * Usage example for a button:
 * <pre>{@code
 * InventoryButton myButton = new InventoryButtonBuilder()
 *         .contextModifier((t, p) -> t.setTitle("You are" + (p.isOp() ? " " : " NOT ") + "OP"))
 *         .build();
 * }</pre>
 * <p>
 * In this case, the {@link de.erethon.xlib.gui.component.Button} "myButton" is only
 * shown to players that are server operators.
 *
 * Usage example for a GUI:
 * <pre>{@code
 * InventoryGUI myGUI = new InventoryGUI(ChatColor.BLUE + "Context Modifier Test");
 * myGUI.setLayout(new FlowInventoryLayout(myGUI, 9));
 * myGUI.addContextModifier((t, p) -> t.setTitle("You are" + (p.isOp() ? " " : " NOT ") + "OP"));
 * }</pre>
 * <p>
 * As ContextModifiers may modify their {@link Contextualized}
 * containers, it should be considered to copy the Contextualized
 * before applying the modifiers.
 *
 * @param <T> the type to modify
 *
 * @author Daniel Saukel
 */
@FunctionalInterface
public interface ContextModifier<T extends Contextualized> {

    /**
     * Applies the modifier to a {@link Contextualized} object.
     *
     * @param target the Contextualized object
     * @param player the player requesting content
     */
    void apply(T target, Player player);

}
