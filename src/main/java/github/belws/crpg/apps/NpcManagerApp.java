package github.belws.crpg.apps;

import github.belws.crpg.CapitalismRpg;
import net.minecraft.resources.ResourceLocation;
import org.jline.utils.Log;

public class NpcManagerApp extends App {
    public NpcManagerApp() {
        super(
                "NPC Manager",
                new ResourceLocation(
                        CapitalismRpg.MOD_ID,
                        "gui/apps/app2-npc-manager.png"
                )
        );
    }

    @Override
    public void open() {
        Log.info("Opening NPC Manager App");
    }
}
