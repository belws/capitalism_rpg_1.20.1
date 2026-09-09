package github.belws.crpg.item.custom.phone.ui;

import github.belws.crpg.item.custom.phone.helper.PhoneAction;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public abstract class ScreenElement {
    protected final int x;
    protected final int y;
    protected final int width;
    protected final int height;

    protected ScreenElement(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render(
            GuiGraphics graphics,
            Font font,
            int phoneX,
            int phoneY,
            boolean selected
    );

    public void handleAction(PhoneAction action) {
        // Elements override this
    }
}
