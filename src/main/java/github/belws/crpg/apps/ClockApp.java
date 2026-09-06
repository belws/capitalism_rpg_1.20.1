package github.belws.crpg.apps;

import github.belws.crpg.CapitalismRpg;
import net.minecraft.client.multiplayer.ClientRegistryLayer;
import net.minecraft.resources.ResourceLocation;
import org.jline.utils.Log;

import java.util.Locale;

public class ClockApp extends App {
    public ClockApp() {
        super(
                "Clock",
                new ResourceLocation(
                        CapitalismRpg.MOD_ID,
                        "gui/apps/app1-clock.png"
                ),
                new ResourceLocation(
                        CapitalismRpg.MOD_ID,
                        "gui/appScreens/clock-app-screen.png"
                )
        );
    }

    @Override
    public void open() {
        Log.info("Opening Clock App");
    }
}
