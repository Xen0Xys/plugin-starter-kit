package fr.maesloic.library.starterkit.paper.utils.managers;

import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("unused")
public class Registration {
    // FIELDS
    private final Server server;
    private final JavaPlugin plugin;
    private final PluginManager pluginManager;

    // CONSTRUCTORS
    public Registration(final @NotNull JavaPlugin plugin) {
        this.plugin        = plugin;
        this.server        = plugin.getServer();
        this.pluginManager = server.getPluginManager();
    }

    // METHODS
    public final void registerCommand(final @NotNull String command, final @NotNull CommandExecutor executor) {
        Objects.requireNonNull(this.plugin.getCommand(command)).setExecutor(executor);
    }
    public final void registerEvent(final @NotNull Listener event) {
        this.pluginManager.registerEvents(event, this.plugin);
    }
    public final void registerIncomingChannel(final @NotNull String channel, final @NotNull PluginMessageListener listener) {
        this.server.getMessenger().registerIncomingPluginChannel(this.plugin, channel, listener);
    }
    public final void registerOutgoingChannel(final @NotNull String channel) {
        this.server.getMessenger().registerOutgoingPluginChannel(this.plugin, channel);
    }
}
