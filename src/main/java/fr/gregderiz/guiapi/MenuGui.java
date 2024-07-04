package fr.gregderiz.guiapi;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class MenuGui implements Listener {
    private final int size;
    private final String name;
    private final Map<ItemStack, Integer> items = new HashMap<>();

    private final Inventory inventory;
    private boolean isMenuOpen;

    public MenuGui(int size, String name) {
        this.size = size;
        this.name = name;
        this.inventory = Bukkit.createInventory(null, size, MiniMessage.miniMessage().deserialize(name));
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    public Map<ItemStack, Integer> getItems() {
        return this.items;
    }

    public void setItem(ItemStack item, int slot) {
        this.inventory.setItem(slot, item);
        this.items.put(item, slot);
    }

    public void addItem(ItemStack item) {
        this.inventory.addItem(item);
        this.items.put(item, this.inventory.first(item));
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    @EventHandler
    private void InventoryOpen(InventoryOpenEvent event) {
        if (!event.getView().getOriginalTitle().equalsIgnoreCase(this.name)) return;
        this.isMenuOpen = true;
    }

    @EventHandler
    private void InventoryClose(InventoryCloseEvent event) {
        if (!event.getView().getOriginalTitle().equalsIgnoreCase(this.name)) return;
        this.isMenuOpen = false;
    }

    public Boolean isOpen() {
        return this.isMenuOpen;
    }
}
