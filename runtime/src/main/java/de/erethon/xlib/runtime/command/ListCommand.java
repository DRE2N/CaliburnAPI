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
package de.erethon.xlib.runtime.command;

import de.erethon.xlib.XLib;
import de.erethon.xlib.category.Categorizable;
import de.erethon.xlib.item.ExItem;
import de.erethon.xlib.item.VanillaItem;
import de.erethon.xlib.loottable.LootTable;
import de.erethon.xlib.mob.VanillaMob;
import de.erethon.xlib.chat.MessageUtil;
import de.erethon.xlib.command.DRECommand;
import de.erethon.xlib.runtime.XLibRuntime;
import de.erethon.xlib.runtime.config.IMessage;
import de.erethon.xlib.util.NumberUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class ListCommand extends DRECommand {

    private XLib api;

    public ListCommand(XLibRuntime plugin) {
        api = plugin.getAPI();
        setCommand("list");
        setMinArgs(0);
        setMaxArgs(2);
        setHelp(IMessage.HELP_LIST.getMessage());
        setPermission("ixl.list");
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        int i = 1;
        Collection<?> objects = null;
        if (args.length > 1) {
            i++;
            if (args[1].equalsIgnoreCase("ci")) {
                objects = api.getCustomItems();
            } else if (args[1].equalsIgnoreCase("vi")) {
                objects = VanillaItem.getLoaded();
            } else if (args[1].equalsIgnoreCase("cm")) {
                objects = api.getCustomMobs();
            } else if (args[1].equalsIgnoreCase("vm")) {
                objects = VanillaMob.getLoaded();
            } else if (args[1].equalsIgnoreCase("m")) {
                objects = api.getExMobs();
            } else if (args[1].equalsIgnoreCase("lt")) {
                objects = api.getLootTables();
            } else {
                objects = api.getExItems();
            }
        } else {
            objects = api.getExItems();
        }
        List<Object> toSend = new ArrayList<>();

        int page = 1;
        if (args.length == i + 1) {
            page = NumberUtil.parseInt(args[i], 1);
        }
        int send = 0;
        int max = 0;
        int min = 0;
        for (Object object : objects) {
            if (sender.hasPermission("ixl.list." + getId(object))) {
                send++;
                if (send >= page * 5 - 4 && send <= page * 5) {
                    min = page * 5 - 4;
                    max = page * 5;
                    toSend.add(object);
                }
            }
        }

        MessageUtil.sendPluginTag(sender, XLibRuntime.getInstance());
        MessageUtil.sendCenteredMessage(sender, "&4&l[ &6" + min + "-" + max + " &4/&6 " + send + " &4|&6 " + page + " &4&l]");
        toSend.forEach(o -> MessageUtil.sendMessage(sender, "&b" + getId(o) + "&7 | &e" + o.getClass().getSimpleName()
                + ((o instanceof ExItem) ? "&7 | &e" + ((ExItem) o).getMaterial() : "")));
    }

    private String getId(Object object) {
        if (object instanceof Categorizable) {
            return ((Categorizable) object).getId();
        } else if (object instanceof LootTable) {
            return ((LootTable) object).getName();
        } else {
            return "";
        }
    }

}
