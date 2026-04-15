package org.bukkit.craftbukkit.entity;

import net.minecraft.server.PlayerConnection;
import org.bukkit.entity.Player;

public abstract class CraftPlayer implements Player {

    public Handle getHandle() {
        throw new UnsupportedOperationException();
    }

    public static class Handle {
        public PlayerConnection playerConnection;
    }

}
