package fr.maesloic.library.starterkit.paper.utils.tools.commands;

import fr.maesloic.library.starterkit.paper.utils.builders.texts.TextBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public abstract class Command implements CommandExecutor, TabCompleter {
    public abstract @Nullable List<CommandAction> actions();
    public abstract @NotNull String syntax();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length < 1) return this.sendMessage(commandSender, new TextBuilder("&cInvalid command syntax! ").extra(new TextBuilder("&4" + this.syntax()).click(ClickEvent.suggestCommand(this.syntax())).build()).build());

        final String actionName              = strings[0].toLowerCase();
        final Optional<CommandAction> action = this.action(commandSender, actionName);

        if (action.isEmpty()) return this.sendMessage(commandSender, new TextBuilder("&cNo action is related to &4%s&c!".formatted(actionName)).build());
        return action.get().execute(commandSender, s, Arrays.asList(strings).subList(1, strings.length - 1).toArray(String[]::new));
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        final List<String> tab = new ArrayList<>();

        if (Objects.isNull(this.actions()) || Objects.requireNonNull(this.actions()).size() == 0 || strings.length == 0) return tab;

        if (strings.length > 1) {
            final Optional<CommandAction> action = action(commandSender, strings[0]);
            if (action.isEmpty()) return tab;

            tab.addAll(action.get().tabComplete(commandSender, s, Arrays.asList(strings).subList(1, strings.length - 1).toArray(String[]::new)));
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
