package github.belws.crpg.client;

import github.belws.crpg.CapitalismRpg;
import github.belws.crpg.item.custom.phone.helper.PhoneController;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = CapitalismRpg.MOD_ID,
        value = Dist.CLIENT
)
public class ClientPhoneEvent {

    @SubscribeEvent
    public static void onLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        ClientWakeUpState.reset();
        PhoneController.closeApp();
    }
}
