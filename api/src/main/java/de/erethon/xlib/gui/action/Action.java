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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GUI interaction types. Subject to change.
 *
 * @deprecated draft
 * @author Daniel Saukel
 */
@Deprecated
public enum Action {

    CLICK,
    LEFT_CLICK(CLICK),
    RIGHT_CLICK(CLICK),
    WHEEL_CLICK(CLICK),
    HOVER;

    private Set<Action> parents;

    Action(Action... parents) {
        this.parents = Stream.of(parents).collect(Collectors.toSet());
    }

    public boolean isSubsumable(Action other) {
        return this == other || parents.contains(other);
    }

}
