package github.belws.crpg.sleep;

import github.belws.crpg.CapitalismRpg;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CapitalismRpg.MOD_ID)
public class SleepEvents {

    @SubscribeEvent
    public static void onSleepTimeCheck(SleepingTimeCheckEvent event) {
        event.setResult(Event.Result.ALLOW);
    }
}
