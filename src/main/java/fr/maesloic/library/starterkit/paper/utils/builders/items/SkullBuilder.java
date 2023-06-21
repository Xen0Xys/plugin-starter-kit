package fr.maesloic.library.starterkit.paper.utils.builders.items;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@SuppressWarnings("unused")
public class SkullBuilder extends ItemBuilder {
    // CONSTRUCTORS
    public SkullBuilder(final @NotNull ItemStack item) {
        super(item);
    }
    public SkullBuilder() {
        super(Material.PLAYER_HEAD);
    }
    public SkullBuilder(final Integer amount) {
        super(Material.PLAYER_HEAD, amount);
    }

    // METHODS
    @Override
    public final @NotNull SkullBuilder clone() {
        return (SkullBuilder) super.clone();
    }
    public final @NotNull SkullBuilder apply(final @NotNull ItemMeta meta) {
        return (SkullBuilder) super.apply(meta);
    }

    // SETTERS
    public final @NotNull SkullBuilder owner(final @NotNull OfflinePlayer player) {
        final SkullMeta meta = this.meta();
        meta.setOwningPlayer(player);
        return this.apply(meta);
    }
    public final @NotNull SkullBuilder texture(final @NotNull String texture) {
        final SkullMeta meta = this.meta();

        final PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID(), null);
        meta.setPlayerProfile(profile);

        return this.apply(meta);
    }

    // CHECKS
    public final Boolean hasOwner() {
        return this.meta().hasOwner();
    }

    // GETTERS
    public final @NotNull SkullMeta meta() {
        return (SkullMeta) super.meta();
    }
    public final @Nullable OfflinePlayer owner() {
        return this.meta().getOwningPlayer();
    }
    public final @Nullable PlayerProfile profile() {
        return this.meta().getPlayerProfile();
    }
}
