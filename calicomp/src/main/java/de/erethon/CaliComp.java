/*
 * Written from 2015-2022 by Daniel Saukel
 *
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software
 * to the public domain worldwide.
 *
 * This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication
 * along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package de.erethon;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Daniel Saukel
 */
public class CaliComp extends JavaPlugin {

    private static BukkitAudiences adventure;

    public static BukkitAudiences adventure() {
        return adventure;
    }

    @Override
    public void onEnable() {
        try {
            adventure = BukkitAudiences.create(this);
        } catch (Exception exception) {
        }
    }

    @Override
    public void onDisable() {
        if (adventure != null) {
            adventure.close();
        }
    }

}
