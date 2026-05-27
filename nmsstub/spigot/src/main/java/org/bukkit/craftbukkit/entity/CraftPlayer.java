package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityPlayer;
import org.bukkit.entity.Player;

public abstract class CraftPlayer implements Player {

    public EntityPlayer getHandle() {
        throw new UnsupportedOperationException();
    }

}
