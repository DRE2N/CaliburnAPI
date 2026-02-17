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
import de.erethon.xlib.item.CustomItem;
import de.erethon.xlib.item.VanillaItem;
import de.erethon.xlib.chat.MessageUtil;
import de.erethon.xlib.command.DRECommand;
import de.erethon.xlib.compatibility.CompatibilityHandler;
import de.erethon.xlib.runtime.XLibRuntime;
import de.erethon.xlib.runtime.config.IMessage;
import java.util.Collection;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class ReloadCommand extends DRECommand {

    private XLibRuntime plugin;
    private XLib api;

    public ReloadCommand(XLibRuntime plugin) {
        this.plugin = plugin;
        api = plugin.getAPI();
        setCommand("reload");
        setMinArgs(0);
        setMaxArgs(0);
        setHelp(IMessage.HELP_RELOAD.getMessage());
        setPermission("ixl.reload");
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        Collection<CustomItem> ci = api.getCustomItems();
        Collection<VanillaItem> vi = VanillaItem.getLoaded();

        plugin.reloadMessageHandler();
        plugin.loadIConfig();
        plugin.loadICommandCache();
        plugin.getAPI().reload();

        MessageUtil.sendPluginTag(sender, plugin);
        MessageUtil.sendCenteredMessage(sender, IMessage.COMMAND_RELOAD_SUCCESS.getMessage());
        MessageUtil.sendCenteredMessage(sender, IMessage.COMMAND_MAIN_LOADED.getMessage(String.valueOf(ci.size()), String.valueOf(vi.size())));
        MessageUtil.sendCenteredMessage(sender, IMessage.COMMAND_MAIN_COMPATIBILITY.getMessage(String.valueOf(CompatibilityHandler.getInstance().getInternals())));
    }

}
