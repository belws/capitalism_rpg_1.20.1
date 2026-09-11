package github.belws.crpg.sleep;

import github.belws.crpg.CapitalismRpg;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.event.level.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CapitalismRpg.MOD_ID)
public class SleepEvents {

    @SubscribeEvent
    public static void onSleepTimeCheck(SleepingTimeCheckEvent event) {
        event.setResult(Event.Result.ALLOW);
    }

    @SubscribeEvent
    public static void onSleepFinished(SleepFinishedTimeEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }

        if (!SleepRules.allowsCustomWakeUp(level.getServer())) {
            return;
        }

        for (ServerPlayer player : level.players()) {
            if (!player.isSleeping()) {
                continue;
            }

            int wakeUpMinutes = WakeUpSettings.getWakeUpMinutes(player);

            if (wakeUpMinutes == -1) {
                continue;
            }

            long currentTime = level.getDayTime();
            long currentDayTick = Math.floorMod(currentTime, 24000L);

            int minutesSinceSix =
                    Math.floorMod(wakeUpMinutes - 360, 1440);

            long targetDayTick =
                    (minutesSinceSix * 1000 + 59L) /60L;

            long ticksUntilWake =
                    Math.floorMod(targetDayTick - currentDayTick, 24000L);

            if (ticksUntilWake == 0) {
                ticksUntilWake = 24000L;
            }

            event.setTimeAddition(currentTime + ticksUntilWake);
            return;
        }
    }
}
