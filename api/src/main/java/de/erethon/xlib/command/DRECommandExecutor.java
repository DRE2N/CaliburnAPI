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
package de.erethon.xlib.command;

import de.erethon.xlib.chat.MessageUtil;
import de.erethon.xlib.config.CommonMessage;
import de.erethon.xlib.plugin.DREPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * The default CommandExecutor for all DRECommandCache.
 *
 * @author Frank Baumann, Daniel Saukel, Fyreum
 */
public class DRECommandExecutor implements CommandExecutor {

    protected DREPlugin plugin;

    public DRECommandExecutor(DREPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command unused1, String unused2, String[] args) {
        DRECommand command;

        if (args.length > 0) {
            command = plugin.getCommandCache().getCommand(args[0]);

            if (command != null) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if (!command.isPlayerCommand()) {
                        MessageUtil.sendMessage(player, CommonMessage.CMD_NO_PLAYER_COMMAND.getMessage());
                        return false;

                    } else if (command.getPermission() != null) {
                        if (!command.senderHasPermissions(player)) {
                            MessageUtil.sendMessage(player, CommonMessage.CMD_NO_PERMISSION.getMessage());
                            return false;
                        }
                    }
                } else {
                    if (!command.isConsoleCommand()) {
                        MessageUtil.log(CommonMessage.CMD_NO_CONSOLE_COMMAND.getMessage());
                        return false;
                    }
                }

                if (command.getMinArgs() <= args.length - 1 & command.getMaxArgs() >= args.length - 1 || command.getMinArgs() == -1) {
                    command.execute(args, sender);
                } else {
                    command.displayHelp(sender);
                }
                return true;
            }
        }

        command = plugin.getCommandCache().getCommand("main");
        if (command != null) {
            String[] argsCopy = new String[args.length + 1];
            argsCopy[0] = "main";

            if (args.length != 0) {
                System.arraycopy(args, 0, argsCopy, 1, args.length);
            }
            command.execute(argsCopy, sender);
        } else {
            MessageUtil.sendMessage(sender, CommonMessage.CMD_DOES_NOT_EXIST.getMessage());
        }
        return true;
    }

}
