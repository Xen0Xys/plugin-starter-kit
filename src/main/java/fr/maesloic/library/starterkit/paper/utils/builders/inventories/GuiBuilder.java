package fr.maesloic.library.starterkit.paper.utils.builders.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public abstract class GuiBuilder {
    public abstract @NotNull Component title();
    public abstract @Nullable InventoryType type();
    public abstract Integer slots();
    public abstract void fill(final @NotNull Player player,
              final @NotNull Inventory inventory);
    public abstract Boolean interact(final @NotNull Player player,
                     final @NotNull Inventory inventory,
                     final @NotNull ItemStack item,
                     final Integer slot,
                     final @NotNull ClickType click);
}
