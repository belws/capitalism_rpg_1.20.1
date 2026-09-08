package github.belws.crpg.handlers;

import github.belws.crpg.keybinds.ModKeybinds;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "capitalismrpg",
        bus = Mod.EventBusSubscriber.Bus.MOD
)

public class ModClientEvents {
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ModKeybinds.PHONE_UP);
        event.register(ModKeybinds.PHONE_DOWN);
        event.register(ModKeybinds.PHONE_LEFT);
        event.register(ModKeybinds.PHONE_RIGHT);
        event.register((ModKeybinds.PHONE_CONFIRM));
        event.register((ModKeybinds.APP_EXIT));
    }
}
