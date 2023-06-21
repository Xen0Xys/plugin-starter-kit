package fr.maesloic.library.starterkit.paper.utils.builders.texts;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class TextBuilder {
    // FIELDS
    private Component component;

    // CONSTRUCTORS
    public TextBuilder(final @NotNull Component component) {
        this.component = component;
    }
    public TextBuilder() {
        this(Component.empty());
    }
    public TextBuilder(final @NotNull String text) {
        this(LegacyComponentSerializer.legacyAmpersand().deserialize(text));
    }

    // METHODS
    public final @NotNull TextBuilder extra(final @NotNull Component... components) {
        for (Component c : components) this.component = this.component.append(c);
        return this;
    }
    public final @NotNull TextBuilder decorate(final @NotNull TextDecoration decoration, final boolean state) {
        this.component = this.component.decoration(decoration, state);
        return this;
    }

    // SETTERS
    public final @NotNull TextBuilder click(final @NotNull ClickEvent event) {
        this.component = this.component.clickEvent(event);
        return this;
    }
    public final @NotNull TextBuilder hover(final @NotNull HoverEventSource<?> eventSource) {
        this.component = this.component.hoverEvent(eventSource);
        return this;
    }

    // GETTERS
    public final @NotNull Component build() {
        return this.component;
    }
}
