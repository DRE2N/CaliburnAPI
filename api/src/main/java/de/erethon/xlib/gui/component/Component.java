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
package de.erethon.xlib.gui.component;

import de.erethon.xlib.gui.GUI;
import de.erethon.xlib.gui.context.Contextualized;

/**
 * A GUI Component.
 *
 * @param <THIS> the type itself
 * @param <TYPE> the GUI implementation
 * @author Daniel Saukel
 */
public interface Component<THIS extends Component<THIS, TYPE>, TYPE extends GUI> extends Contextualized<THIS> {
}
