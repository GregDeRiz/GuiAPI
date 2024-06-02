package fr.gregderiz.guiapi;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class MenuGui implements Listener {
    private final int size;
    private final String name;
    private final Map<ItemStack, Integer> items = new HashMap<>();

    private final Inventory inventory;

    public MenuGui(int size, String name) {
        this.size = size;
        this.name = name;
        this.inventory = Bukkit.createInventory(null, size, MiniMessage.miniMessage().deserialize(name));
    }

    protected int getSize() {
        return this.size;
    }

    protected String getName() {
        return this.name;
    }

    protected Map<ItemStack, Integer> getItems() {
        return this.items;
    }

    protected void setItem(ItemStack item, int slot) {
        this.inventory.setItem(slot, item);
        this.items.put(item, slot);
    }

    protected void addItem(ItemStack item) {
        this.inventory.addItem(item);
        this.items.put(item, this.inventory.first(item));
    }

    protected void open(Player player) {
        player.openInventory(this.inventory);
    }

    protected Inventory getInventory() {
        return this.inventory;
    }
}
