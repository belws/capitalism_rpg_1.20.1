package github.belws.crpg.keybinds;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static final KeyMapping PHONE_UP = new KeyMapping(
            "key.capitalismrpg.phone_up",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_UP,
            "key.categories.capitalismrpg"
    );

    public static final KeyMapping PHONE_DOWN = new KeyMapping(
            "key.capitalismrpg.phone_down",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_DOWN,
            "key.categories.capitalismrpg"
    );

    public static final KeyMapping PHONE_LEFT = new KeyMapping(
            "key.capitalismrpg.phone_left",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT,
            "key.categories.capitalismrpg"
    );

    public static final KeyMapping PHONE_RIGHT = new KeyMapping(
            "key.capitalismrpg.phone_right",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT,
            "key.categories.capitalismrpg"
    );

    public static final KeyMapping APP_OPEN = new KeyMapping(
            "key.capitalismrpg.app_open",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_ENTER,
            "key.categories.capitalismrpg"
    );

    public static final KeyMapping APP_EXIT = new KeyMapping(
            "key.capitalismrpg.app_exit",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_BACKSPACE,
            "key.categories.capitalismrpg"
    );

    public static void register() {

    }

}
