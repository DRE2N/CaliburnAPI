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
package de.erethon.caliburn.category;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Saukel
 */
public abstract class Categorizable {

    protected String id;

    protected List<Category<Categorizable>> categories = new ArrayList<>();

    /**
     * Sets the ID of the Categorizable. Fails if an ID has already been set.
     * Intended to be used with a deserialization constructor
     *
     * @param id
     * the ID to set
     * @return
     * this object
     */
    public Categorizable id(String id) {
        if (this.id == null) {
            this.id = id;
        }
        return this;
    }

    /* Getters */
    /**
     * @return
     * the ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     * an ID to compare to the one of this item
     * @return
     * this object if the ID String refers to this item exclusively;
     * null if not
     */
    public Categorizable idMatch(String id) {
        return id.equals(this.id) ? this : null;
    }

    public List<Category<Categorizable>> getCategories() {
        return categories;
    }

}
