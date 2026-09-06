package github.belws.crpg.apps;

import github.belws.crpg.CapitalismRpg;
import net.minecraft.client.multiplayer.ClientRegistryLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public class ClockApp extends App {
    public ClockApp() {
        super(
                "Clock",
                new ResourceLocation(
                        CapitalismRpg.MOD_ID,
                        "gui/apps/app1-clock.png"
                )
        );
    }

    @Override
    public void open() {
        System.out.println("Opening Clock App");
    }
}
