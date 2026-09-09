package github.belws.crpg.item.custom.phone.apps;

import github.belws.crpg.item.custom.phone.helper.PhoneAction;
import github.belws.crpg.item.custom.phone.helper.PhoneController;
import github.belws.crpg.item.custom.phone.helper.PhoneOverlay;
import github.belws.crpg.item.custom.phone.ui.ScreenElement;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public abstract class App {

    private final String name;
    private  final ResourceLocation iconTexture;
    private final ResourceLocation appScreen;
    private final List<ScreenElement> elements = new ArrayList<>();
    private int selectedElementIndex = 0;

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

    public void onOpen() {
        // Optional setup when this app is opened
    }

    public void handleAction(PhoneAction action) {
        //Apps can override this to handle their own behavior
    }

    protected void addElement(ScreenElement element) {
        elements.add(element);
    }
}
