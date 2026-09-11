package github.belws.crpg.sleep;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class WakeUpSettings {

    private static final String WAKE_UP_KEY = "capitalismrpg:wake_up_minutes";

    private WakeUpSettings() {
    }

    public static void setWakeUpTime(
            ServerPlayer player,
            int hour,
            int minute
    ) {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Invalid wake-up time");
        }

        CompoundTag playerData = player.getPersistentData();
        CompoundTag savedData =
                playerData.getCompound(ServerPlayer.PERSISTED_NBT_TAG);

        savedData.putInt(WAKE_UP_KEY, hour * 60 + minute);

        playerData.put(ServerPlayer.PERSISTED_NBT_TAG, savedData);
    }

    public static int getWakeUpMinutes(ServerPlayer player) {
        CompoundTag savedData = player.getPersistentData()
                .getCompound(Player.PERSISTED_NBT_TAG);

        if (!savedData.contains(WAKE_UP_KEY, Tag.TAG_INT)) {
            return -1;
        }
        int minutes = savedData.getInt(WAKE_UP_KEY);

        return minutes >= 0 && minutes < 1440 ? minutes : -1;
    }
}
