package github.belws.crpg.sleep;

import net.minecraft.server.MinecraftServer;

public final class SleepRules {
    private SleepRules() {
    }

    public static boolean allowsCustomWakeUp(MinecraftServer server) {
        return server != null
                && server.isSingleplayer()
                && server.isPublished();
    }
}
