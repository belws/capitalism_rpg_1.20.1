package github.belws.crpg.item.custom.phone.event;

import github.belws.crpg.CapitalismRpg;
import github.belws.crpg.item.ModItems;
import github.belws.crpg.item.custom.phone.helper.PhoneAction;
import github.belws.crpg.item.custom.phone.helper.PhoneController;
import github.belws.crpg.keybinds.ModKeybinds;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = CapitalismRpg.MOD_ID,
        value = Dist.CLIENT
)
public class PhoneInputHandler {

    @SubscribeEvent
    public static void onClickTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();

        boolean canUsePhone =
                minecraft.player != null
                && minecraft.screen == null
                && minecraft.isWindowActive()
                && minecraft.player.getMainHandItem().is(ModItems.PHONE.get());

        consumeAction(ModKeybinds.PHONE_LEFT, PhoneAction.LEFT, canUsePhone);
        consumeAction(ModKeybinds.PHONE_RIGHT, PhoneAction.RIGHT, canUsePhone);
        consumeAction(ModKeybinds.PHONE_UP, PhoneAction.UP, canUsePhone);
        consumeAction(ModKeybinds.PHONE_DOWN, PhoneAction.DOWN, canUsePhone);
        consumeAction(ModKeybinds.PHONE_CONFIRM, PhoneAction.CONFIRM, canUsePhone);
        consumeAction(ModKeybinds.PHONE_BACK, PhoneAction.BACK, canUsePhone);
    }

    private static void consumeAction(
            KeyMapping key,
            PhoneAction action,
            boolean canUsePhone
    ) {
        boolean pressed = false;

        while (key.consumeClick()) {
            pressed = true;
        }

        if (pressed && canUsePhone) {
            PhoneController.handleAction(action);
        }
    }
}
