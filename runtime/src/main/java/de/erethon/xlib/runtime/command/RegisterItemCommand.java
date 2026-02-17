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
import de.erethon.xlib.category.IdentifierType;
import de.erethon.xlib.item.CustomItem;
import de.erethon.xlib.item.ExItem;
import de.erethon.xlib.item.VanillaItem;
import de.erethon.xlib.chat.MessageUtil;
import de.erethon.xlib.command.DRECommand;
import de.erethon.xlib.compatibility.Version;
import de.erethon.xlib.runtime.XLibRuntime;
import de.erethon.xlib.runtime.config.IMessage;
import de.erethon.xlib.util.EnumUtil;
import java.io.File;
import java.io.IOException;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class RegisterItemCommand extends DRECommand {

    private XLib api;

    public RegisterItemCommand(XLibRuntime plugin) {
        api = plugin.getAPI();
        setCommand("registerItem");
        setAliases("ri");
        setMinArgs(1);
        setMaxArgs(2);
        setHelp(IMessage.HELP_REGISTER_ITEM.getMessage());
        setPermission("ixl.register");
        setPlayerCommand(true);
        setConsoleCommand(false);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        ExItem exItem = api.getExItem(args[1]);
        if (exItem instanceof VanillaItem) {
            MessageUtil.sendMessage(sender, IMessage.ERROR_VANILLA_FEATURE.getMessage());
            return;
        }
        Player player = (Player) sender;
        ItemStack itemStack;
        if (Version.isAtLeast(Version.MC1_9)) {
            itemStack = player.getInventory().getItemInMainHand();
        } else {
            itemStack = player.getInventory().getItemInHand();
        }
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            MessageUtil.sendMessage(sender, IMessage.ERROR_NO_ITEM_IN_HAND.getMessage());
            return;
        }

        File file = new File(api.getDataFolder() + "/custom/items", args[1] + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
                MessageUtil.sendMessage(sender, IMessage.ERROR_IO.getMessage());
                return;
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        IdentifierType idType = IdentifierType.LORE;
        if (args.length >= 3) {
            idType = EnumUtil.getEnumIgnoreCase(IdentifierType.class, args[2]);
            if (idType == null || idType == IdentifierType.METADATA || idType == IdentifierType.VANILLA) {
                MessageUtil.sendMessage(sender, IMessage.ERROR_INVALID_ID_TYPE.getMessage(args[2]));
                return;
            }
        }

        CustomItem customItem = new CustomItem(api, idType, args[1], itemStack);
        customItem.serialize().forEach((k, v) -> config.set(k, v));
        if (exItem instanceof CustomItem) {
            api.getExItems().remove(exItem);
        }
        try {
            config.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }
        customItem.register();
        MessageUtil.sendMessage(sender, IMessage.COMMAND_REGISTER_ITEM_SUCCESS.getMessage(args[1]));
    }

}
