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
package de.erethon.xlib.exampleplugin;

import de.erethon.xlib.XLib;
import de.erethon.xlib.category.IdentifierType;
import de.erethon.xlib.gui.GUI;
import de.erethon.xlib.gui.InventoryGUI;
import de.erethon.xlib.gui.Paginated;
import de.erethon.xlib.gui.PaginatedInventoryGUI;
import de.erethon.xlib.gui.SingleInventoryGUI;
import de.erethon.xlib.gui.component.InventoryButton;
import de.erethon.xlib.gui.component.InventoryButtonBuilder;
import de.erethon.xlib.gui.context.StatusModifier;
import de.erethon.xlib.gui.layout.CenteredInventoryLayout;
import de.erethon.xlib.gui.layout.FlowInventoryLayout;
import de.erethon.xlib.gui.layout.PaginatedFlowInventoryLayout;
import de.erethon.xlib.gui.layout.PaginatedInventoryLayout;
import de.erethon.xlib.item.CustomAttribute;
import de.erethon.xlib.item.CustomItem;
import de.erethon.xlib.item.TrackedItemStack;
import de.erethon.xlib.item.VanillaItem;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Daniel Saukel
 */
public class ExamplePlugin extends JavaPlugin {

    XLib api;

    static final String LEGENDARY = ChatColor.GOLD + "legendary";
    
    
    public static final InventoryGUI[] GUI = new InventoryGUI[]{
        new SingleInventoryGUI("Title"),
        new PaginatedInventoryGUI(ChatColor.DARK_RED + "Subsidiary title / pagination Test"),
        new SingleInventoryGUI(ChatColor.DARK_RED + "Centered GUI Test"),
        new SingleInventoryGUI(ChatColor.GOLD + "Overloaded centered GUI Test"),
        new SingleInventoryGUI(ChatColor.BLUE + "Context Modifier Test"),
        new PaginatedInventoryGUI(ChatColor.BLUE + "Context Modifier + Pagination Test"),
        new SingleInventoryGUI(ChatColor.BLUE + "SingleInventoryGUI Clear Test"),
        new PaginatedInventoryGUI(ChatColor.BLUE + "PaginatedInventoryGUI Clear Test"),
        new SingleInventoryGUI(ChatColor.GOLD + "CloseListener / StatusModifier Test"),
        new SingleInventoryGUI(ChatColor.RED + "NullPointerException")
    };

    static {
        GUI[0].setLayout(new FlowInventoryLayout(GUI[0], 9));
        GUI[0].add(new InventoryButton("Test"));
        GUI[0].add(new InventoryButtonBuilder()
                .title(ChatColor.DARK_GREEN + "Test")
                .lines("Lore1", "Lore2")
                .icon(Material.RED_MUSHROOM)
                .sound("ui.button.click")
                .onInteract(e -> e.getPlayer().sendMessage(e.getAction().toString()))
                .build()
        );

        GUI[1].setLayout(new PaginatedFlowInventoryLayout((PaginatedInventoryGUI) GUI[1], 9, PaginatedInventoryLayout.PaginationButtonPosition.CENTER));
        GUI[1].add(new InventoryButton(Material.MINECART, ChatColor.GOLD + "This is a button", ChatColor.GOLD + "with multiple", ChatColor.GOLD + "lines of text"));
        GUI[1].add(new InventoryButtonBuilder()
                .icon(Material.CLOCK)
                .lines("This item is stealable")
                .stealable(true)
                .build()
        );
        for (int page = 0, slot = 3; slot < 9 && page < 2; slot++) {
            if (slot == 8) {
                slot = 1;
                page++;
            }
            GUI[1].add(new InventoryButton(Material.DIAMOND, ChatColor.AQUA + "Page " + page + " - Slot " + slot));
        }
        ((Paginated) GUI[1]).setTitle(1, "This is a title for page 1");

        GUI[2].setLayout(new CenteredInventoryLayout(GUI[2], 18));
        GUI[2].add(new InventoryButton("0-0"));
        GUI[2].add(new InventoryButton("0-1"));
        GUI[2].add(new InventoryButton("0-2"));
        GUI[2].add(new InventoryButton("0-3"));
        GUI[2].add(new InventoryButton("0-4"));
        GUI[2].add(new InventoryButton("0-5"));
        GUI[2].add(new InventoryButton("0-6"));
        GUI[2].add(new InventoryButton("0-7"));
        GUI[2].add(new InventoryButton("0-8"));
        GUI[2].add(new InventoryButton("1-0"));
        GUI[2].add(new InventoryButton("1-1"));
        GUI[2].add(new InventoryButton("1-2"));
        GUI[2].add(new InventoryButton("1-3"));

        GUI[3].setLayout(new CenteredInventoryLayout(GUI[3], 9));
        for (int i = 0; i <= 12; i++) {
            GUI[3].add(new InventoryButton("Test"));
        }

        GUI[4].setLayout(new FlowInventoryLayout(GUI[4], 9));
        GUI[4].addContextModifier((t, p) -> t.setTitle("You are" + (p.isOp() ? " " : " NOT ") + "OP"));
        GUI[4].add(new InventoryButtonBuilder()
                .contextModifier((t, p) -> t.setTitle("You are" + (p.isOp() ? " " : " NOT ") + "OP"))
                .build()
        );

        GUI[5].setLayout(new PaginatedFlowInventoryLayout((PaginatedInventoryGUI) GUI[5], 45, PaginatedInventoryLayout.PaginationButtonPosition.BOTTOM));
        for (int page = 0, slot = 0; slot < 44 && page < 3; slot++) {
            if (slot == 43) {
                slot = 0;
                page++;
            }
            GUI[5].add(new InventoryButton(Material.DIAMOND, ChatColor.AQUA + "Page " + page + " - Slot " + slot));
        }
        GUI[5].addContextModifier((t, p) -> t.setTitle("You are" + (p.isOp() ? " " : " NOT ") + "OP"));
        GUI[5].add(new InventoryButtonBuilder()
                .contextModifier((t, p) -> t.setTitle("You are" + (p.isOp() ? " " : " NOT ") + "OP"))
                .build()
        );

        GUI[6].setLayout(new CenteredInventoryLayout((SingleInventoryGUI) GUI[6], 9));
        GUI[6].add(new InventoryButton(Material.DIAMOND, ChatColor.AQUA + "3"));
        GUI[6].add(new InventoryButton(Material.DIAMOND, ChatColor.AQUA + "5"));
        GUI[6].clear();
        GUI[6].add(new InventoryButton(Material.DIAMOND, ChatColor.AQUA + "4"));

        GUI[7].setLayout(new PaginatedFlowInventoryLayout((PaginatedInventoryGUI) GUI[7], 27, PaginatedInventoryLayout.PaginationButtonPosition.CENTER));
        for (int i = 0; i < 100; i++) {
            GUI[7].add(new InventoryButton(Material.DIAMOND, ChatColor.AQUA.toString() + i));
        }
        GUI[7].clear();
        GUI[7].add(new InventoryButton(Material.DIAMOND, ChatColor.AQUA + "0"));

        GUI[8].setLayout(new FlowInventoryLayout(GUI[8], 9));
        GUI[8].setCloseListener(e -> e.getPlayer().sendMessage("You closed the GUI!"));
        StatusModifier[] status = new StatusModifier[]{
            new StatusModifier<String>("SM1 (String)", ChatColor.DARK_RED + "SM1 Value"),
            new StatusModifier("SM2 (null)"),
            new StatusModifier<UUID>("SM3 (UUID)", UUID.randomUUID())
        };
        for (StatusModifier mod : status) {
            GUI[8].add(new InventoryButtonBuilder()
                    .lines(mod.getKey(), mod.getValue() != null ? mod.getValue().toString() : "null")
                    .build());
        }

        GUI[9].setLayout(new FlowInventoryLayout(GUI[9], 9));
        GUI[9].add(new InventoryButtonBuilder()
                .icon(Material.ANVIL)
                .title("Click")
                .onInteract(e -> {
                    String s = null;
                    s.indexOf(0);
                })
                .build()
        );
    }

    CustomItem legendarySword;
    CustomAttribute<String> rarity;
    CustomAttribute chicken;

    CustomItem legendarySword() {
        if (legendarySword == null) {
            legendarySword = new CustomItem(api, IdentifierType.PERSISTENT_DATA_CONTAINER, "legendary_sword", VanillaItem.DIAMOND_SWORD);
            legendarySword.setCustomModelData(1);
            legendarySword.addLore(ChatColor.ITALIC + "*glisten*");
            // Static attribute
            legendarySword.addAttribute(rarity(), LEGENDARY);
        }
        return legendarySword;
    }

    CustomAttribute<String> rarity() {
        if (rarity == null) {
            rarity = new CustomAttribute<>((i, s) -> {
                ItemStack wrapped = s.getWrapped();
                ItemMeta meta = wrapped.getItemMeta();
                List<String> lore = meta.getLore();
                lore.add(ChatColor.GREEN + "Rarity: " + ChatColor.GRAY + i.getValue());
                meta.setLore(lore);
                wrapped.setItemMeta(meta);
            });
            rarity.setHitHandler((a, w, d, e) -> {
                if (!a.getValue().equals(LEGENDARY)) {
                    return;
                }
                Location location = e.getEntity().getLocation();
                World world = location.getWorld();
                double r = 0f, g = 1f, b = 0f;
                world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 2, 0), 0, r, g, b);
                world.spawnParticle(Particle.REDSTONE, location.clone().add(1, 1.75, 0), 0, r, g, b);
                world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 1.5, 1), 0, r, g, b);
                world.spawnParticle(Particle.REDSTONE, location.clone().add(-1, 1.25, 0), 0, r, g, b);
                world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 1, -1), 0, r, g, b);
            });
        }
        return rarity;
    }

    CustomAttribute chicken() {
        if (chicken == null) {
            chicken = new CustomAttribute<>((i, s) -> {
                ItemStack wrapped = s.getWrapped();
                ItemMeta meta = wrapped.getItemMeta();
                List<String> lore = meta.getLore();
                lore.add(ChatColor.ITALIC + "*suspicious cock-a-doodle-doo*");
                meta.setLore(lore);
                wrapped.setItemMeta(meta);
            });
            chicken.setDropHandler((a, e) -> {
                Item drop = e.getItemDrop();
                drop.remove();
                drop.getLocation().getWorld().spawnEntity(drop.getLocation(), EntityType.CHICKEN);
            });
        }
        return chicken;
    }

    @Override
    public void onEnable() {
        api = XLib.getInstance();
        for (GUI gui : GUI) {
            gui.register();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command may only be used by an in-game player.");
            return true;
        }

        if (command.getName().equals("giveexample")) {
            ItemStack item = legendarySword().toItemStack();
            TrackedItemStack tracked = api.wrap(legendarySword(), item);
            tracked.addAttribute(chicken(), null);
            ((Player) sender).getInventory().addItem(item);

        } else if (command.getName().equals("guiexample")) {
            GUI gui = GUI[0];
            if (args.length > 0) {
                int i = 0;
                try {
                    i = Integer.parseInt(args[0]);
                } catch (NumberFormatException exception) {
                }
                if (i < GUI.length && i >= 0) {
                    gui = GUI[i];
                }
            }
            gui.open((Player) sender);
        }
        return true;
    }

}
