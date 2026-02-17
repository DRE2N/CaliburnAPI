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
package de.erethon.xlib.config;

import de.erethon.xlib.plugin.DREPlugin;

/**
 * Messages used by this library.
 *
 * @author Daniel Saukel
 */
public enum CommonMessage implements Message {

    CMD_DOES_NOT_EXIST("cmd.doesNotExist"),
    CMD_NO_CONSOLE_COMMAND("cmd.noConsoleCommand"),
    CMD_NO_PERMISSION("cmd.noPermission"),
    CMD_NO_PLAYER_COMMAND("cmd.noPlayerCommand");

    private String path;

    CommonMessage(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public MessageHandler getMessageHandler() {
        return DREPlugin.getInstance().getMessageHandler();
    }

}
