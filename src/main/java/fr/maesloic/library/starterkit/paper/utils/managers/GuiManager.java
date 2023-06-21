package fr.maesloic.library.starterkit.paper.utils.managers;

import fr.maesloic.library.starterkit.paper.utils.builders.inventories.GuiBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GuiManager implements Listener {
    // FIELDS
    private final Map<Class<? extends GuiBuilder>, GuiBuilder> guis = new HashMap<>();

    // METHODS
    public final void register(final @NotNull GuiBuilder gui) {
        this.guis.put(gui.getClass(), gui);
    }
    public final void open(final @NotNull Player player, final @NotNull Class<? extends GuiBuilder> _class) {
        final GuiBuilder gui      = this.gui(_class);
        assert Objects.nonNull(gui);
        final Inventory inventory = Objects.isNull(gui.type()) ?
                Bukkit.createInventory(null, gui.slots(), gui.title()) :
                Bukkit.createInventory(null, Objects.requireNonNull(gui.type()), gui.title());

        player.closeInventory(InventoryCloseEvent.Reason.OPEN_NEW);
        gui.fill(player, inventory);
        player.openInventory(inventory);
    }

    // GETTERS
    public final @NotNull Map<Class<? extends GuiBuilder>, GuiBuilder> guis() {
        return this.guis;
    }
    public final @Nullable GuiBuilder gui(final @NotNull Class<? extends GuiBuilder> _class) {
        return this.guis.get(_class);
    }

    // EVENTS
    @EventHandler
    public final void inventoryClick(final @NotNull InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final Inventory inventory = event.getClickedInventory();
        final InventoryView view = event.getView();
        final ItemStack item = event.getCurrentItem();
        final Integer slot = event.getSlot();
        final ClickType click = event.getClick();

        if (Objects.isNull(inventory) || Objects.isNull(item)) return;

        this.guis.values().stream()
                .filter(gui -> view.title().equals(gui.title()))
                .forEach(gui -> event.setCancelled(gui.interact(player, inventory, item, slot, click)));
    }
}
