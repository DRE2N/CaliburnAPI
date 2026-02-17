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
import de.erethon.xlib.mob.CustomMob;
import de.erethon.xlib.mob.ExMob;
import de.erethon.xlib.mob.VanillaMob;
import de.erethon.xlib.chat.MessageUtil;
import de.erethon.xlib.command.DRECommand;
import de.erethon.xlib.runtime.XLibRuntime;
import de.erethon.xlib.runtime.config.IMessage;
import de.erethon.xlib.util.EnumUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class RegisterMobCommand extends DRECommand {

    private XLib api;

    public RegisterMobCommand(XLibRuntime plugin) {
        api = plugin.getAPI();
        setCommand("registerMob");
        setAliases("rm");
        setMinArgs(1);
        setMaxArgs(2);
        setHelp(IMessage.HELP_REGISTER_MOB.getMessage());
        setPermission("ixl.register");
        setPlayerCommand(true);
        setConsoleCommand(false);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        ExMob exMob = api.getExMob(args[1]);
        if (exMob instanceof VanillaMob) {
            MessageUtil.sendMessage(sender, IMessage.ERROR_VANILLA_FEATURE.getMessage());
            return;
        }
        Player player = (Player) sender;
        List<Entity> nearby = player.getNearbyEntities(10.0, 10.0, 10.0);
        Entity next = null;
        double nextDistance = 0;
        for (Entity entity : nearby) {
            if (next == null) {
                next = entity;
                nextDistance = player.getLocation().distance(entity.getLocation());
            } else {
                double entityDistance = player.getLocation().distance(entity.getLocation());
                if (entityDistance < nextDistance) {
                    next = entity;
                    nextDistance = entityDistance;
                }
            }
        }
        if (next == null) {
            MessageUtil.sendMessage(sender, IMessage.ERROR_NO_MOB_NEARBY.getMessage());
            return;
        }

        File file = new File(api.getDataFolder() + "/custom/mobs", args[1] + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        IdentifierType idType = IdentifierType.METADATA;
        if (args.length >= 3) {
            idType = EnumUtil.getEnumIgnoreCase(IdentifierType.class, args[2]);
            if (idType == null || idType == IdentifierType.LORE || idType == IdentifierType.VANILLA) {
                MessageUtil.sendMessage(sender, IMessage.ERROR_INVALID_ID_TYPE.getMessage(args[2]));
            }
        }

        CustomMob customMob = new CustomMob(api, idType, args[1], next);
        customMob.serialize().forEach((k, v) -> config.set(k, v));
        if (exMob instanceof CustomMob) {
            api.getExMobs().remove(exMob);
        }
        try {
            config.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        customMob.register();
        MessageUtil.sendMessage(sender, IMessage.COMMAND_REGISTER_MOB_SUCCESS.getMessage(args[1]));
    }

}
