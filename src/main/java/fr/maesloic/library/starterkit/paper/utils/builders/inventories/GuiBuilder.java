package fr.maesloic.library.starterkit.paper.utils.builders.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GuiBuilder {
    @NotNull Component title();
    @Nullable InventoryType type();
    Integer slots();
    void fill(final @NotNull Player player,
              final @NotNull Inventory inventory);
    Boolean interact(final @NotNull Player player,
                     final @NotNull Inventory inventory,
                     final @NotNull ItemStack item,
                     final Integer slot,
                     final @NotNull ClickType click);
}
