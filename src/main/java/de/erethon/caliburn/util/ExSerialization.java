/*
 * Copyright (C) 2018 Daniel Saukel.
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
package de.erethon.caliburn.util;

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.CustomItem;
import de.erethon.caliburn.item.ExItem;
import java.util.Map;
import java.util.UUID;
import org.bukkit.inventory.ItemStack;

/**
 * An ExItem based serialization format
 *
 * @author Daniel Saukel
 */
public class ExSerialization {

    private CaliburnAPI api;

    public ExSerialization(CaliburnAPI api) {
        this.api = api;
    }

    public Map<String, Object> serialize(ItemStack item) {
        ExItem exItem = api.getExItem(item);
        if (exItem == null) {
            exItem = new CustomItem(UUID.randomUUID().toString());
        }
        Map<String, Object> serialized = exItem.serialize();
        serialized.put("amount", item.getAmount());
        return serialized;
    }

    public ItemStack deserialize(Map<String, Object> objects) {
        ExItem item = ExItem.deserialize(objects);
        if (item == null) {
            return null;
        }
        item.load(api);
        Integer amount = (Integer) objects.get("amount");
        return item.toItemStack(amount != null ? amount : 1);
    }

}
