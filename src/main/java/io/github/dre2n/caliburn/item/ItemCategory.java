/*
 * Copyright (C) 2015-2017 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.caliburn.item;

import io.github.dre2n.caliburn.CaliburnAPI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Saukel
 */
public class ItemCategory {

    CaliburnAPI api;

    private String id;

    private List<UniversalItem> items = new ArrayList<>();

    public ItemCategory(CaliburnAPI api, String id) {
        this.api = api;
        this.id = id;
    }

    public void setup(List<String> items) {
        for (String item : items) {
            this.items.add(api.getItems().getById(item));
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the items
     */
    public List<UniversalItem> getItems() {
        return items;
    }

    /**
     * @param items
     * the items to set
     */
    public void addItem(UniversalItem item) {
        items.add(item);
    }

}
