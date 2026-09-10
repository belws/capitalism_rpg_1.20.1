package github.belws.crpg.item.custom.phone.apps;

import github.belws.crpg.CapitalismRpg;
import github.belws.crpg.item.custom.phone.ui.ButtonElement;
import github.belws.crpg.item.custom.phone.ui.NumberElement;
import net.minecraft.resources.ResourceLocation;
import org.jline.utils.Log;

public class ClockApp extends App {

    private final NumberElement hourElement;
    private final NumberElement minuteElement;

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

        hourElement = new NumberElement(
                40, 100, 70, 20,
                "Hour",
                0, 23, 6
        );

        minuteElement = new NumberElement(
                100, 100, 70, 20,
                "Minute",
                0,59,0
        );

        addElement(hourElement);
        addElement(minuteElement);
    }
}
