package github.belws.crpg.network;

import github.belws.crpg.sleep.SleepRules;
import github.belws.crpg.sleep.WakeUpSettings;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record SetWakeUpTimePacket(int hour, int minute) {

    public static void encode(
            SetWakeUpTimePacket packet,
            FriendlyByteBuf buffer
    ) {
        buffer.writeInt(packet.hour());
        buffer.writeInt(packet.minute());
    }

    public static SetWakeUpTimePacket decode(FriendlyByteBuf buffer) {
        return new SetWakeUpTimePacket(
                buffer.readInt(),
                buffer.readInt()
        );
    }

    public static void handle(
            SetWakeUpTimePacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            if (player == null) {
                return;
            }

            if (!SleepRules.allowsCustomWakeUp(player.getServer())) {
                return;
            }

            if (packet.hour() < 0 || packet.hour() > 23
                    || packet.minute() < 0 || packet.minute() > 59) {
                return;
            }

            WakeUpSettings.setWakeUpTime(
                    player,
                    packet.hour(),
                    packet.minute()
            );
        });

        context.setPacketHandled(true);
    }
}
