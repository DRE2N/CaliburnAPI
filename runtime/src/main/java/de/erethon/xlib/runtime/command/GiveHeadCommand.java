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

import de.erethon.xlib.item.CustomHead;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * @author Daniel Saukel
 */
public class GiveHeadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 || (args.length == 1 && !(sender instanceof Player))) {
            return false;
        }

        Player player = null;
        CustomHead head = null;
        int amount = 1;
        UUID uuid = null;
        String textureValue = null;
        String displayName = null;
        List<String> lore = new ArrayList<>();
        int i = 0;

        if (args.length >= 2) {
            Player argPlayer = Bukkit.getPlayer(args[0]);
            if (argPlayer != null) {
                player = argPlayer;
                i++;
            }
        }
        if (player == null) {
            if (!(sender instanceof Player)) {
                return false;
            } else {
                player = (Player) sender;
            }
        }

        while (i < args.length) {
            String arg = args[i].toUpperCase();

            if (arg.startsWith("HEAD:")) {
                arg = arg.replace("HEAD:", "");
                try {
                    head = CustomHead.valueOf(arg);
                } catch (IllegalArgumentException exception) {
                    sender.sendMessage("The head \"" + arg + "\" does not exist.");
                    return false;
                }

            } else if (arg.startsWith("AMOUNT:")) {
                arg = arg.replace("AMOUNT:", "");
                try {
                    amount = Integer.parseInt(arg);
                    if (amount < 1) {
                        amount = 1;
                    }
                } catch (NumberFormatException exception) {
                    sender.sendMessage("\"" + arg + "\" is not a valid number.");
                    return false;
                }

            } else if (arg.startsWith("UUID:")) {
                arg = arg.replace("UUID:", "");
                try {
                    uuid = UUID.fromString(arg);
                } catch (IllegalArgumentException exception) {
                    sender.sendMessage("\"" + arg + "\" is not a valid UUID.");
                }

            } else if (arg.startsWith("TEXTUREVALUE:")) {
                textureValue = arg.replace("TEXTUREVALUE:", "");

            } else if (displayName == null) {
                displayName = ChatColor.translateAlternateColorCodes('&', arg);

            } else {
                lore.add(ChatColor.translateAlternateColorCodes('&', arg));
            }

            i++;
        }

        if (head != null) {
            head.give(player, amount, displayName, lore.toArray(new String[lore.size()]));
            return true;

        } else if (uuid != null) {
            ItemStack stack = CustomHead.newPlayerHead(amount);
            SkullMeta meta = (SkullMeta) stack.getItemMeta();
            if (displayName != null) {
                meta.setDisplayName(displayName);
                meta.setLore(lore);
            }
            if (textureValue == null) {
                meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
            }
            stack.setItemMeta(meta);
            if (textureValue != null) {
                CustomHead.setSkullOwner(stack, uuid, textureValue);
            }
            player.getInventory().addItem(stack);
            return true;

        } else {
            return false;
        }
    }

}
