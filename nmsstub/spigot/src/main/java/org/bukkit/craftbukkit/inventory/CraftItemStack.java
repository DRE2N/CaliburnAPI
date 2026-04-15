package org.bukkit.craftbukkit.inventory;

public class CraftItemStack extends org.bukkit.inventory.ItemStack {

    public static org.bukkit.inventory.ItemStack asBukkitCopy(net.minecraft.server.ItemStack a) {
        throw new UnsupportedOperationException();
    }

    public static net.minecraft.server.ItemStack asNMSCopy(org.bukkit.inventory.ItemStack a) {
        throw new UnsupportedOperationException();
    }

}
