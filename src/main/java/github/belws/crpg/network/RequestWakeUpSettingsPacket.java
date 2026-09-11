package github.belws.crpg.network;

import github.belws.crpg.sleep.SleepRules;
import github.belws.crpg.sleep.WakeUpSettings;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public record RequestWakeUpSettingsPacket() {

    public static void encode(
            RequestWakeUpSettingsPacket packet,
            FriendlyByteBuf buffer
    ) {
        // No data in request
    }

    public static RequestWakeUpSettingsPacket decode(FriendlyByteBuf buffer) {
        return new RequestWakeUpSettingsPacket();
    }

    public static void handle(
            RequestWakeUpSettingsPacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            if (player == null) {
                return;
            }

            ModNetwork.CHANNEL.send(
                    PacketDistributor.PLAYER.with(() -> player),
                    new WakeUpSettingsPacket(
                            SleepRules.allowsCustomWakeUp(player.getServer()),
                            WakeUpSettings.getWakeUpMinutes(player)
                    )
            );
        });
    }
}
