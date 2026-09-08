package github.belws.crpg.item.custom.phone.apps;

import github.belws.crpg.item.custom.phone.helper.PhoneOverlay;
import net.minecraft.resources.ResourceLocation;

public abstract class App {

    private final String name;
    private  final ResourceLocation iconTexture;
    private final ResourceLocation appScreen;

    public App(String name, ResourceLocation iconTexture, ResourceLocation appScreen) {
        this.name = name;
        this.iconTexture = iconTexture;
        this.appScreen = appScreen;
    }

    // Getters
    public String getName() {
        return name;
    }

    public ResourceLocation getIconTexture() {
        return iconTexture;
    }

    public ResourceLocation getAppScreen() {return appScreen;}

    public void open(){
        PhoneOverlay.openApp(this);
    }
}
