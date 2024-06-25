package net.nohgz.singularity.core.networking;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.nohgz.singularity.SingularityMod;
import net.nohgz.singularity.core.networking.packet.TextC2SPacket;

public class ModMessages {

    private static SimpleChannel INSTANCE;

    //assign each new packet a new ID
    private static int packetID = 0;
    private static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(SingularityMod.singularityPath("messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(TextC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(TextC2SPacket::new)
                .encoder(TextC2SPacket::toBytes)
                .consumerMainThread(TextC2SPacket::handle)
                .add();

    }

    public static <PKT> void sendToServer(PKT packet) {
        INSTANCE.sendToServer(packet);
    }

    public static <PKT> void sendToPlayer(PKT packet, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player), packet);
    }

}
