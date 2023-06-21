package fr.maesloic.library.starterkit.paper.utils.builders.items;

import com.destroystokyo.paper.Namespaced;
import com.google.common.collect.Multimap;
import fr.maesloic.library.starterkit.paper.utils.builders.texts.TextBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class ItemBuilder implements Cloneable {
    // FIELDS
    private final ItemStack item;

    // CONSTRUCTORS
    public ItemBuilder(final @NotNull ItemStack item) {
        this.item = item.clone();
    }
    public ItemBuilder(final @NotNull Material material, final Integer amount) {
        this(new ItemStack(material, amount));
    }
    public ItemBuilder(final @NotNull Material material) {
        this(material, 1);
    }

    // METHODS
    @Override
    public final @NotNull ItemBuilder clone() {
        try {
            return (ItemBuilder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    public final @NotNull ItemBuilder apply(final @NotNull ItemMeta meta) {
        this.item.setItemMeta(meta);
        return this;
    }
    public final @NotNull ItemBuilder enchant(final @NotNull Enchantment enchantment, final Integer level, final Boolean legacy) {
        final ItemMeta meta = this.meta();
        meta.addEnchant(enchantment, level, legacy);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder removeEnchant(final @NotNull Enchantment enchantment) {
        final ItemMeta meta = this.meta();
        meta.removeEnchant(enchantment);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder unEnchant() {
        this.meta().getEnchants().keySet().forEach(this::removeEnchant);
        return this;
    }
    public final @NotNull ItemBuilder addFlags(final @NotNull ItemFlag... flags) {
        final ItemMeta meta = this.meta();
        meta.addItemFlags(flags);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder removeFlags(final @NotNull ItemFlag... flags) {
        final ItemMeta meta = this.meta();
        meta.removeItemFlags(flags);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder addModifier(final @NotNull Attribute attribute, final @NotNull AttributeModifier modifier) {
        final ItemMeta meta = this.meta();
        meta.addAttributeModifier(attribute, modifier);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder removeModifier(final @NotNull Attribute attribute) {
        final ItemMeta meta = this.meta();
        meta.removeAttributeModifier(attribute);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder removeModifier(final @NotNull Attribute attribute, final @NotNull AttributeModifier modifier) {
        final ItemMeta meta = this.meta();
        meta.removeAttributeModifier(attribute, modifier);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder removeModifier(final @NotNull EquipmentSlot slot) {
        final ItemMeta meta = this.meta();
        meta.removeAttributeModifier(slot);
        return this.apply(meta);
    }

    // SETTERS
    public final @NotNull ItemBuilder displayName(final @Nullable Component name) {
        final ItemMeta meta = this.meta();
        meta.displayName(Objects.isNull(name) ? new TextBuilder().build() : new TextBuilder(name).decorate(TextDecoration.ITALIC, false).build());
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder lore(final @NotNull Component... lore) {
        final ItemMeta meta = this.meta();
        meta.lore(Arrays.asList(lore));
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder customModelData(final Integer data) {
        final ItemMeta meta = this.meta();
        meta.setCustomModelData(data);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder unbreakable(final Boolean state) {
        final ItemMeta meta = this.meta();
        meta.setUnbreakable(state);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder unbreakable() {
        return this.unbreakable(true);
    }
    public final @NotNull ItemBuilder modifier(final @NotNull Multimap<Attribute, AttributeModifier> modifiers) {
        final ItemMeta meta = this.meta();
        meta.setAttributeModifiers(modifiers);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder destroyableKeys(final @NotNull Collection<Namespaced> keys) {
        final ItemMeta meta = this.meta();
        meta.setDestroyableKeys(keys);
        return this.apply(meta);
    }
    public final @NotNull ItemBuilder placeableKeys(final @NotNull Collection<Namespaced> keys) {
        final ItemMeta meta = this.meta();
        meta.setPlaceableKeys(keys);
        return this.apply(meta);
    }

    // CHECKS
    public final Boolean hasDisplayName() {
        return this.meta().hasDisplayName();
    }
    public final Boolean hasLore() {
        return this.meta().hasLore();
    }
    public final Boolean isUnbreakable() {
        return this.meta().isUnbreakable();
    }
    public final Boolean hasFlag(final @NotNull ItemFlag flag) {
        return this.meta().hasItemFlag(flag);
    }
    public final Boolean hasEnchantment(final @NotNull Enchantment enchantment) {
        return this.meta().hasEnchant(enchantment);
    }
    public final Boolean hasConflictingEnchant(final @NotNull Enchantment enchantment) {
        return this.meta().hasConflictingEnchant(enchantment);
    }
    public final Boolean hasModifiers() {
        return this.meta().hasAttributeModifiers();
    }
    public final Boolean hasDestroyableKeys() {
        return this.meta().hasDestroyableKeys();
    }
    public final Boolean hasPlaceableKeys() {
        return this.meta().hasPlaceableKeys();
    }

    // GETTERS
    public @NotNull ItemMeta meta() {
        return this.item.getItemMeta();
    }
    public final @NotNull ItemStack build() {
        return this.item;
    }
}
