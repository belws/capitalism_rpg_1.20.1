package github.belws.crpg.item.custom.phone.apps;

import github.belws.crpg.CapitalismRpg;
import net.minecraft.resources.ResourceLocation;
import org.jline.utils.Log;

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
                        "gui/clock-app-screen.png"
                )
        );
    }

    @Override
    public void open() {
        super.open();
        Log.info("Opening Clock App");
    }
}
