package github.belws.crpg.item.custom.phone.apps;

import github.belws.crpg.CapitalismRpg;
import github.belws.crpg.item.custom.phone.ui.ButtonElement;
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
        addElement(new ButtonElement(
                70,180,110,20,
                "Add Alarm",
                () -> Log.info("Test Add Alarm Pressed")
        ));
    }
}
