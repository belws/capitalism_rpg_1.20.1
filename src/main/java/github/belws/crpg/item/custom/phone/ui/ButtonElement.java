package github.belws.crpg.item.custom.phone.ui;

import github.belws.crpg.item.custom.phone.helper.PhoneAction;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class ButtonElement extends ScreenElement {
    private final String label;
    private final Runnable onPress;

    public ButtonElement(
            int x,
            int y,
            int width,
            int height,
            String label,
            Runnable onPress
    ) {
        super( x, y, width, height);
        this.label = label;
        this.onPress = onPress;
    }

    @Override
    public void render(
            GuiGraphics graphics,
            Font font,
            int phoneX,
            int phoneY,
            boolean selected
    ) {
        int drawX = phoneX + x;
        int drawY = phoneY + y;
        if (selected) {
            graphics.renderOutline(
                    drawX,
                    drawY,
                    width,
                    height,
                    0xFFFFFF
            );
        }

        graphics.drawCenteredString(
                font,
                label,
                drawX + width / 2,
                drawY + (height - font.lineHeight) / 2,
                selected ? 0xFFFFFF : 0xFFFFFF
        );
    }

    @Override
    public void handleAction(PhoneAction action) {
        if (action == PhoneAction.CONFIRM) {
            onPress.run();
        }
    }
}
