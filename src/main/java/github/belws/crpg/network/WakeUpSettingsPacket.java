package github.belws.crpg.network;

import github.belws.crpg.client.ClientWakeUpState;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record WakeUpSettingsPacket(boolean allowed, int minutes) {

    public static void encode(
            WakeUpSettingsPacket packet,
            FriendlyByteBuf buffer
    ) {
        buffer.writeBoolean(packet.allowed());
        buffer.writeInt(packet.minutes());
    }

    public static WakeUpSettingsPacket decode(FriendlyByteBuf buffer) {
        return new WakeUpSettingsPacket(
                buffer.readBoolean(),
                buffer.readInt()
        );
    }

    public static void handle(
            WakeUpSettingsPacket packet,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(
                        Dist.CLIENT,
                        () -> () -> ClientWakeUpState.update(
                                packet.allowed(),
                                packet.minutes()
                        )
                )
        );

        context.setPacketHandled(true);
    }
}
