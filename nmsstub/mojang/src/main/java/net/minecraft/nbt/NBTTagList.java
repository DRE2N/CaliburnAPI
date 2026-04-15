package net.minecraft.nbt;

import java.util.Iterator;

public class NBTTagList extends NBTBase implements Iterable<NBTBase> {

    @Override
    public Iterator<NBTBase> iterator() {
        throw new UnsupportedOperationException();
    }

    public void add(NBTBase a) {}

    public NBTBase get(int a) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        throw new UnsupportedOperationException();
    }

}
