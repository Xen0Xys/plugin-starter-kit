package fr.maesloic.library.starterkit.paper.utils.tools.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public abstract class CommandAction {
    public abstract @NotNull String name();
    public abstract @NotNull String[] aliases();
    public abstract @Nullable String permission();
    public abstract @Nullable List<CommandAction> actions();
    public abstract Boolean execute(final @NotNull CommandSender commandSender, final @NotNull String label, final @NotNull String... strings);
    public @NotNull List<String> tabComplete(final @NotNull CommandSender commandSender, final @NotNull String label, final @NotNull String... strings) {
        final List<String> tab = new ArrayList<>();

        if (Objects.isNull(this.actions()) || Objects.requireNonNull(this.actions()).size() == 0 || strings.length == 0) return tab;

        if (strings.length > 1) {
            final Optional<CommandAction> action = action(commandSender, strings[0]);
            if (action.isEmpty()) return tab;

            tab.addAll(action.get().tabComplete(commandSender, label, Arrays.asList(strings).subList(1, strings.length - 1).toArray(String[]::new)));
            return tab;
        }

        actions(commandSender, strings[0]).forEach(action -> tab.add(action.name().toLowerCase()));
        return tab;
    }

    public final List<CommandAction> actions(final @NotNull CommandSender commandSender, final @NotNull String part) {
        return Objects.requireNonNull(this.actions()).stream().filter(ac ->
                        (Objects.isNull(ac.permission()) ||
                                commandSender.hasPermission(Objects.requireNonNull(ac.permission()))) &&
                                (ac.name().toLowerCase().startsWith(part.toLowerCase()) ||
                                        Arrays.stream(ac.aliases()).anyMatch(alias -> alias.toLowerCase().startsWith(part.toLowerCase()))))
                .collect(Collectors.toList());
    }

    public final Optional<CommandAction> action(final @NotNull CommandSender commandSender, final @NotNull String part) {
        return Objects.requireNonNull(this.actions()).stream().filter(ac ->
                        (Objects.isNull(ac.permission()) ||
                                commandSender.hasPermission(Objects.requireNonNull(ac.permission()))) &&
                                (ac.name().toLowerCase().startsWith(part.toLowerCase()) ||
                                        Arrays.stream(ac.aliases()).anyMatch(alias -> alias.toLowerCase().startsWith(part.toLowerCase()))))
                .findFirst();
    }

    public final Boolean sendMessage(final @NotNull CommandSender commandSender, final @NotNull Component component) {
        commandSender.sendMessage(component);
        return true;
    }
}
