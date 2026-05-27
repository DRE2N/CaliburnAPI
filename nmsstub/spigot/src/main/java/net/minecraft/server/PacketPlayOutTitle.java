package net.minecraft.server;

public class PacketPlayOutTitle implements Packet {

    public enum EnumTitleAction {
        TITLE, SUBTITLE
    }

    public PacketPlayOutTitle(EnumTitleAction a, IChatBaseComponent b) {}

    public PacketPlayOutTitle(int a, int b, int c) {}

}
