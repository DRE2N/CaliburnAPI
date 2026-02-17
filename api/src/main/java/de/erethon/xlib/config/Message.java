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

import de.erethon.xlib.chat.MessageUtil;
import de.erethon.xlib.plugin.DREPlugin;

/**
 * @author Daniel Saukel
 */
public interface Message {

    /**
     * Returns the configuration path where the message is loaded from.
     *
     * @return the configuration path where the message is loaded from
     */
    String getPath();

    /**
     * The MessageHandler loaded by the plugin.
     *
     * @return the MessageHandler loaded by the plugin.
     */
    default MessageHandler getMessageHandler() {
        return DREPlugin.getInstance().getMessageHandler();
    }

    /**
     * Returns the formatted message String.
     *
     * @return the formatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    default String getMessage() {
        return getMessageHandler().getMessage(this);
    }

    /**
     * Returns the formatted message String.
     *
     * @param args Strings to replace possible variables in the message
     * @return the formatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    default String getMessage(String... args) {
        return getMessageHandler().getMessage(this, args);
    }

    /**
     * Sends the message to the console.
     */
    default void debug() {
        MessageUtil.log(DREPlugin.getInstance(), getMessage());
    }

}
