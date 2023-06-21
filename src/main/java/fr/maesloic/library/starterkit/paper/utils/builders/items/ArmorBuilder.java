package fr.maesloic.library.starterkit.paper.utils.builders.items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class ArmorBuilder extends ItemBuilder {
    // FIELDS
    public ArmorBuilder(final @NotNull ItemStack item) {
        super(item);
    }
    public ArmorBuilder(final @NotNull Part part, final @NotNull Type type) {
        super(switch (type) {
            case LEATHER -> part.leather();
            case CHAIN_MAIL -> part.chainmail();
            case IRON -> part.iron();
            case GOLD -> part.gold();
            case DIAMOND -> part.diamond();
            case NETHERITE -> part.netherite();
        });
    }

    // METHODS
    @Override
    public final @NotNull ArmorBuilder clone() {
        return (ArmorBuilder) super.clone();
    }
    public final @NotNull ArmorBuilder apply(final @NotNull ItemMeta meta) {
        return (ArmorBuilder) super.apply(meta);
    }

    // SETTERS
    public final @NotNull ArmorBuilder trim(final @NotNull ArmorTrim trim) {
        final ArmorMeta meta = this.meta();
        meta.setTrim(trim);
        return this.apply(meta);
    }
    public final @NotNull ArmorBuilder color(final @NotNull Color color) {
        if (!this.build().getType().name().contains("LEATHER_")) return this;

        final LeatherArmorMeta meta = (LeatherArmorMeta) super.meta();
        meta.setColor(color);
        return this.apply(meta);
    }

    // CHECKS
    public final Boolean hasTrim() {
        return this.meta().hasTrim();
    }

    // GETTERS
    public final @NotNull ArmorMeta meta() {
        return (ArmorMeta) super.meta();
    }
    public final @Nullable ArmorTrim trim() {
        return this.meta().getTrim();
    }

    // INNER CLASS
    public enum Part {
        HELMET(Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.GOLDEN_HELMET, Material.DIAMOND_HELMET, Material.NETHERITE_HELMET),
        CHEST_PLATE(Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE),
        LEGGINGS(Material.LEATHER_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS),
        BOOTS(Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS);

        private final Material leather, chainmail, iron, gold, diamond, netherite;

        Part(final @NotNull Material leather,
                final @NotNull Material chainmail,
                final @NotNull Material iron,
                final @NotNull Material gold,
                final @NotNull Material diamond,
                final @NotNull Material netherite) {
            this.leather   = leather;
            this.chainmail = chainmail;
            this.iron      = iron;
            this.gold      = gold;
            this.diamond   = diamond;
            this.netherite = netherite;
        }

        public final @NotNull Material leather() {
            return this.leather;
        }
        public final @NotNull Material chainmail() {
            return this.chainmail;
        }
        public final @NotNull Material iron() {
            return this.iron;
        }
        public final @NotNull Material gold() {
            return this.gold;
        }
        public final @NotNull Material diamond() {
            return this.diamond;
        }
        public final @NotNull Material netherite() {
            return this.netherite;
        }
    }
    public enum Type {
        LEATHER,
        CHAIN_MAIL,
        IRON,
        GOLD,
        DIAMOND,
        NETHERITE
    }
}
