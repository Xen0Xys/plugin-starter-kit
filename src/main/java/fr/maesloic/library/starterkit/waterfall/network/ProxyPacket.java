package fr.maesloic.library.starterkit.waterfall.network;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.maesloic.library.starterkit.commons.encryption.Encryption;
import fr.maesloic.library.starterkit.commons.encryption.exceptions.PacketEncryptionException;
import fr.maesloic.library.starterkit.commons.network.Packet;
import fr.maesloic.library.starterkit.commons.utils.GsonUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public abstract class ProxyPacket extends Packet {

    public static void sendProxy(@NotNull final Plugin plugin, @NotNull final ProxiedPlayer player, @NotNull final String channel, @NotNull final String subChannel, @NotNull final Packet packet, @NotNull final String secret) throws PacketEncryptionException{
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(subChannel);
        output.writeUTF(player.getUniqueId().toString());
        String encryptedPacket = new Encryption().encryptSymmetric(secret, packet.serialize());
        if(Objects.isNull(encryptedPacket))
            throw new PacketEncryptionException("Failed to encrypt packet !");
        output.writeUTF(encryptedPacket);
        player.getServer().getInfo().sendData(channel, output.toByteArray());
    }

    @Nullable
    public static <T extends Packet> T decryptProxy(@NotNull final Class<T> packetClass, @NotNull final String encryptedPacket, @NotNull final String secret) throws PacketEncryptionException{
        String decryptedPacket = new Encryption().decryptSymmetric(secret, encryptedPacket);
        if(Objects.isNull(decryptedPacket))
            throw new PacketEncryptionException("Failed to decrypt packet !");
        return GsonUtils.getGson().fromJson(decryptedPacket, packetClass);
    }
}
