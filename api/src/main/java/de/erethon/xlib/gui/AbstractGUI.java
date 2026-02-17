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
package de.erethon.xlib.gui;

import de.erethon.xlib.gui.action.CloseListener;
import de.erethon.xlib.gui.context.ContextModifier;
import de.erethon.xlib.gui.context.StatusModifier;
import de.erethon.xlib.gui.layout.Layout;
import de.erethon.xlib.player.PlayerCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.entity.Player;

/**
 * Provides a skeletal implementation of {@link GUI}.
 *
 * @param <T> the type itself
 * @author Daniel Saukel
 */
public abstract class AbstractGUI<T extends AbstractGUI<T>> implements GUI<T> {

    private String title;
    private Layout<T> layout;
    private CloseListener closeListener;
    private List<ContextModifier<T>> contextModifiers = new ArrayList<>();
    private Set<StatusModifier<?>> statusModifiers = new HashSet<>();
    private boolean isTransient;

    protected PlayerCollection viewers = new PlayerCollection();

    protected AbstractGUI() {
        this("");
    }

    protected AbstractGUI(String title) {
        this.title = title;
    }

    protected AbstractGUI(AbstractGUI gui) {
        this(gui.title);
        layout = gui.layout.copy(this);
        closeListener = gui.closeListener;
        contextModifiers = gui.contextModifiers;
        statusModifiers = gui.statusModifiers;
        isTransient = gui.isTransient;
        if (gui.isRegistered()) {
            register();
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String text) {
        title = text;
    }

    @Override
    public Layout<T> getLayout() {
        return layout;
    }

    @Override
    public void setLayout(Layout<T> layout) {
        this.layout = layout;
    }

    @Override
    public CloseListener getCloseListener() {
        return closeListener;
    }

    @Override
    public void setCloseListener(CloseListener listener) {
        closeListener = listener;
    }

    @Override
    public List<ContextModifier<T>> getContextModifiers() {
        return contextModifiers;
    }

    @Override
    public void addContextModifier(ContextModifier<T> ctxt) {
        contextModifiers.add(ctxt);
    }

    @Override
    public void removeContextModifier(ContextModifier<T> ctxt) {
        contextModifiers.remove(ctxt);
    }

    @Override
    public void setContextModifiers(List<ContextModifier<T>> ctxts) {
        if (ctxts == null) {
            contextModifiers.clear();
        } else {
            contextModifiers = ctxts;
        }
    }

    @Override
    public boolean hasStatusModifier(StatusModifier<?> status) {
        return statusModifiers.contains(status);
    }

    @Override
    public Set<StatusModifier<?>> getStatusModifiers() {
        return statusModifiers;
    }

    @Override
    public void addStatusModifier(StatusModifier<?> status) {
        statusModifiers.add(status);
    }

    @Override
    public void removeStatusModifier(StatusModifier<?> status) {
        statusModifiers.remove(status);
    }

    @Override
    public void setStatusModifiers(Set<StatusModifier<?>> status) {
        if (status == null) {
            statusModifiers.clear();
        } else {
            statusModifiers = status;
        }
    }

    @Override
    public Collection<Player> getViewers() {
        return viewers.getOnlinePlayers();
    }

    @Override
    public boolean isTransient() {
        return isTransient;
    }

    @Override
    public void setTransient(boolean isTransient) {
        this.isTransient = isTransient;
    }

    @Override
    public AbstractGUI getContextualizedCopy(Player viewer) {
        if (!getContextModifiers().isEmpty()) {
            AbstractGUI gui = copy();
            gui.setTransient(true);
            gui.applyAllContextModifiers(viewer);
            return gui;
        } else {
            return this;
        }
    }

}
