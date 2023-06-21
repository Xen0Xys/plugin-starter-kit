package fr.maesloic.library.starterkit.paper.utils.builders.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public class BookBuilder extends ItemBuilder {
    // CONSTRUCTORS
    public BookBuilder(final @NotNull ItemStack item) {
        super(item);
    }
    public BookBuilder() {
        super(Material.WRITTEN_BOOK);
    }
    public BookBuilder(final Integer amount) {
        super(Material.WRITTEN_BOOK, amount);
    }

    // METHODS
    @Override
    public final @NotNull BookBuilder clone() {
        return (BookBuilder) super.clone();
    }
    public final @NotNull BookBuilder apply(final @NotNull ItemMeta meta) {
        return (BookBuilder) super.apply(meta);
    }
    public final @NotNull BookBuilder addPages(final @NotNull Component... pages) {
        final BookMeta meta = this.meta();
        meta.addPages(pages);
        return this.apply(meta);
    }

    // SETTERS
    public final @NotNull BookBuilder page(final Integer page, final @NotNull Component text) {
        final BookMeta meta = this.meta();
        meta.page(page, text);
        return this.apply(meta);
    }
    public final @NotNull BookBuilder author(final @NotNull Component author) {
        final BookMeta meta = this.meta();
        meta.author(author);
        return this.apply(meta);
    }
    public final @NotNull BookBuilder generation(final @NotNull BookMeta.Generation generation) {
        final BookMeta meta = this.meta();
        meta.setGeneration(generation);
        return this.apply(meta);
    }
    public final @NotNull BookBuilder title(final @NotNull Component title) {
        final BookMeta meta = this.meta();
        meta.title(title);
        return this.apply(meta);
    }

    // CHECKS
    public final Boolean hasAuthor() {
        return this.meta().hasAuthor();
    }
    public final Boolean hasGeneration() {
        return this.meta().hasGeneration();
    }
    public final Boolean hasTitle() {
        return this.meta().hasTitle();
    }
    public final Boolean hasPages() {
        return this.meta().hasPages();
    }

    // GETTERS
    public final @NotNull BookMeta meta() {
        return (BookMeta) super.meta();
    }
    public final Integer pageCount() {
        return this.meta().getPageCount();
    }
    public final @NotNull List<Component> pages() {
        return this.meta().pages();
    }
    public final @Nullable Component author() {
        return this.meta().author();
    }
    public final @NotNull Component page(final Integer page) {
        return this.meta().page(page);
    }
    public final @Nullable Component title() {
        return this.meta().title();
    }
    public final @Nullable BookMeta.Generation generation() {
        return this.meta().getGeneration();
    }
}
