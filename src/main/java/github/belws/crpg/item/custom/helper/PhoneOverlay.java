package github.belws.crpg.item.custom.helper;

import github.belws.crpg.CapitalismRpg;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = CapitalismRpg.MOD_ID,
        value = Dist.CLIENT
)
public class PhoneOverlay {

    @SubscribeEvent
    public static void renderPhone(RenderGuiEvent.Post event){

    }
}
