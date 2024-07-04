package fr.gregderiz.guiapi;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

public final class MenuManager {
    private final Set<MenuGui> menuGuis;
    private final Plugin plugin;

    public MenuManager(Plugin plugin) {
        this.plugin = plugin;
        this.menuGuis = new HashSet<>();
    }

    public void register(MenuGui menuGui) {
        if (this.plugin == null) return;
        if (this.menuGuis.contains(menuGui)) return;

        this.menuGuis.add(menuGui);
        Bukkit.getPluginManager().registerEvents(menuGui, this.plugin);
    }

    public void unregister(MenuGui menuGui) {
        if (!this.menuGuis.contains(menuGui)) return;

        this.menuGuis.remove(menuGui);
        HandlerList.unregisterAll(menuGui);
    }

    public void unregisterMenus() {
        this.menuGuis.stream().filter(menuGui -> !menuGui.isOpen()).forEach(HandlerList::unregisterAll);
    }

    public void unregisterAll() {
        this.menuGuis.forEach(HandlerList::unregisterAll);
    }

    public void destroy() {
        unregisterAll();
        this.menuGuis.clear();
    }

    public Set<MenuGui> getMenuGuis() {
        return this.menuGuis;
    }
}
