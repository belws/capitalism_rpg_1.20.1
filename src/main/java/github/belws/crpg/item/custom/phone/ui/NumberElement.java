package github.belws.crpg.item.custom.phone.ui;

import github.belws.crpg.item.custom.phone.helper.PhoneAction;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class NumberElement extends ScreenElement {

    private final String label;
    private final int minimum;
    private final int maximum;
    private int value;

    public NumberElement(
            int x,
            int y,
            int width,
            int height,
            String label,
            int minimum,
            int maximum,
            int initialValue
    ) {
        super(x, y, width, height);

        if(minimum > maximum) {
            throw new IllegalArgumentException("Minimum exceeds maximum");
        }

        if (initialValue < minimum || initialValue > maximum) {
            throw new IllegalArgumentException("Initial value is outside the range");
        }

        this.label = label;
        this.minimum = minimum;
        this.maximum = maximum;
        this.value = initialValue;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void handleAction(PhoneAction action) {
        if (action == PhoneAction.UP) {
            value = value == maximum ? minimum : value + 1;
        } else if (action == PhoneAction.DOWN) {
            value = value == minimum ? maximum : value - 1;
        }
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
                    0xFFFFFFFF
            );
        }

        String text = label + ": " + String.format("%02d", value);

        graphics.drawCenteredString(
                font,
                text,
                drawX + width / 2,
                drawY + (height - font.lineHeight) / 2,
                selected ? 0xFFFF55 : 0xFFFFFF
        );
    }
}
