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

import de.erethon.xlib.plugin.DREPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Note that DRE2N plugins are usually designed to have just one instance of DRECommandCache.
 * One instance of DRECommandCache represents one command and contains all of its subcommands.
 *
 * @author Daniel Saukel, Fyreum
 */
public class DRECommandCache extends CommandCache implements TabCompleter {

    private final String label;
    private final CommandExecutor executor;
    private boolean tabCompletion = true;

    public DRECommandCache(String label, DREPlugin plugin, Set<DRECommand> commands) {
        super(commands);
        this.label = label;
        this.executor = new DRECommandExecutor(plugin);
    }

    public DRECommandCache(String label, DREPlugin plugin, DRECommand... commands) {
        super(commands);
        this.label = label;
        this.executor = new DRECommandExecutor(plugin);
    }

    public DRECommandCache(String label, CommandExecutor executor, Set<DRECommand> commands) {
        super(commands);
        this.label = label;
        this.executor = executor;
    }

    public DRECommandCache(String label, CommandExecutor executor, DRECommand... commands) {
        super(commands);
        this.label = label;
        this.executor = executor;
    }

    /**
     * @return the command label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return true if TabCompletion is enabled, false otherwise
     */
    public boolean isTabCompletion() {
        return tabCompletion;
    }

    /**
     * @param tabCompletion the boolean to set
     */
    public void setTabCompletion(boolean tabCompletion) {
        this.tabCompletion = tabCompletion;
    }

    /**
     * @param plugin the plugin that registers the command.
     */
    public void register(JavaPlugin plugin) {
        plugin.getCommand(label).setExecutor(executor);
        if (tabCompletion) {
            plugin.getCommand(label).setTabCompleter(this);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command unused1, String unused2, String[] args) {
        List<String> completes = new ArrayList<>();
        String cmd = args[0];

        if(args.length == 1) {
            List<String> cmds = new ArrayList<>();
            for (DRECommand command : commands) {
                if (command.senderHasPermissions(sender)) {
                    cmds.add(command.getCommand());
                }
            }
            for(String string : cmds) {
                if(string.toLowerCase().startsWith(cmd.toLowerCase())) {
                    completes.add(string);
                }
            }
            return completes;
        }
        for (DRECommand command : commands) {
            if (command.matches(cmd)) {
                completes.addAll(command.tabComplete(sender, args));
            }
        }
        return completes;
    }
}
