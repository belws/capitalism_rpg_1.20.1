package github.belws.crpg.network;

import github.belws.crpg.CapitalismRpg;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class ModNetwork {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL =
            NetworkRegistry.newSimpleChannel(
                    new ResourceLocation(CapitalismRpg.MOD_ID, "main"),
                    () -> PROTOCOL_VERSION,
                    PROTOCOL_VERSION::equals,
                    PROTOCOL_VERSION::equals
            );

    private ModNetwork() {
    }

    public static void register() {
        CHANNEL.registerMessage(
                0,
                SetWakeUpTimePacket.class,
                SetWakeUpTimePacket::encode,
                SetWakeUpTimePacket::decode,
                SetWakeUpTimePacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
    }
}
