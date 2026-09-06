package github.belws.crpg.apps;

import net.minecraft.resources.ResourceLocation;

public abstract class App {

    private final String name;
    private  final ResourceLocation iconTexture;

    public App(String name, ResourceLocation iconTexture) {
        this.name = name;
        this.iconTexture = iconTexture;
    }

    // Getters
    public String getName() {
        return name;
    }

    public ResourceLocation getIconTexture() {
        return iconTexture;
    }

    public abstract void open();
}
