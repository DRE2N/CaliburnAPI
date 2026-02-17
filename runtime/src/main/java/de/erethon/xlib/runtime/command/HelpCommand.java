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

import de.erethon.xlib.chat.MessageUtil;
import de.erethon.xlib.command.DRECommand;
import de.erethon.xlib.runtime.XLibRuntime;
import de.erethon.xlib.runtime.config.IMessage;
import de.erethon.xlib.util.NumberUtil;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class HelpCommand extends DRECommand {

    private XLibRuntime plugin;

    public HelpCommand(XLibRuntime plugin) {
        this.plugin = plugin;
        setCommand("help");
        setMinArgs(0);
        setMaxArgs(1);
        setHelp(IMessage.HELP_HELP.getMessage());
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        Set<DRECommand> commandList = plugin.getCommandCache().getCommands();
        Set<DRECommand> toSend = new HashSet<>();

        int page = 1;
        if (args.length == 2) {
            page = NumberUtil.parseInt(args[1], 1);
        }
        int send = 0;
        int max = 0;
        int min = 0;
        for (DRECommand command : commandList) {
            send++;
            if (send >= page * 5 - 4 && send <= page * 5) {
                min = page * 5 - 4;
                max = page * 5;
                toSend.add(command);
            }
        }

        MessageUtil.sendPluginTag(sender, plugin);
        MessageUtil.sendCenteredMessage(sender, "&4&l[ &6" + min + "-" + max + " &4/&6 " + send + " &4|&6 " + page + " &4&l]");

        for (DRECommand command : toSend) {
            MessageUtil.sendMessage(sender, "&b" + command.getCommand() + "&7 - " + command.getHelp());
        }
    }

}
