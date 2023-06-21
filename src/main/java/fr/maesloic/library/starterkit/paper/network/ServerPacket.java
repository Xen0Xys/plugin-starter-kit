package fr.maesloic.library.starterkit.paper.network;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.maesloic.library.starterkit.commons.encryption.Encryption;
import fr.maesloic.library.starterkit.commons.encryption.exceptions.PacketEncryptionException;
import fr.maesloic.library.starterkit.commons.network.Packet;
import fr.maesloic.library.starterkit.commons.utils.GsonUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public abstract class ServerPacket extends Packet {

    public static void sendServer(@NotNull final JavaPlugin plugin, @NotNull final Player player, @NotNull final String channel, @NotNull final String subChannel, @NotNull final Packet packet, @NotNull final String secret){
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(subChannel);
        String encryptedPacket = new Encryption().encryptSymmetric(secret, packet.serialize());
        if(Objects.isNull(encryptedPacket))
            throw new PacketEncryptionException("Failed to encrypt packet !");
        output.writeUTF(encryptedPacket);
        player.sendPluginMessage(plugin, channel, output.toByteArray());
    }

    public static <T extends Packet> T decryptServer(@NotNull final Class<T> packetClass, @NotNull final String encryptedPacket, @NotNull final String secret) throws PacketEncryptionException {
        String decryptedPacket = new Encryption().decryptSymmetric(secret, encryptedPacket);
        if(Objects.isNull(decryptedPacket))
            throw new PacketEncryptionException("Failed to decrypt packet !");
        return GsonUtils.getGson().fromJson(decryptedPacket, packetClass);
    }
}
