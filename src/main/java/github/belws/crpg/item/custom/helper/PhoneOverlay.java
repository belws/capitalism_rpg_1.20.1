package github.belws.crpg.item.custom.helper;

import github.belws.crpg.CapitalismRpg;
import github.belws.crpg.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jline.utils.Log;

import static java.lang.Thread.sleep;

@Mod.EventBusSubscriber(
        modid = CapitalismRpg.MOD_ID,
        value = Dist.CLIENT
)
public class PhoneOverlay {
    private static boolean phoneWasHeld = false;
    private static float animationProgress = 0.0f;
    private static long animationStartTime = 0;
    private static final long animationDuration = 300;

    private static final ResourceLocation PHONE_TEXTURE =
            new ResourceLocation(CapitalismRpg.MOD_ID, "gui/phone_on_idle.png");

    private static final ResourceLocation PHONE_OFF_TEXTURE =
            new ResourceLocation(CapitalismRpg.MOD_ID, "gui/phone_off_idle.png");

    @SubscribeEvent
    public static void renderPhone(RenderGuiEvent.Post event) throws InterruptedException {
        GuiGraphics guiGraphics = event.getGuiGraphics();

        if (Minecraft.getInstance().player == null){
            return;
        }

        boolean phoneHeld =
                Minecraft.getInstance().player
                        .getMainHandItem()
                        .is(ModItems.PHONE.get());
        if (!phoneHeld) {
            phoneWasHeld = false;
            animationProgress = 0.0f;
            animationStartTime = 0;
            return;
        }
        if (!phoneWasHeld) {
            phoneWasHeld = true;
            animationStartTime = System.currentTimeMillis();
        }

        int phoneWidth = 256;
        int phoneHeight = 256;

        int screenWidth = guiGraphics.guiWidth();
        int screenHeight = guiGraphics.guiHeight();

        int targetX = screenWidth - phoneWidth;
        int targetY = screenHeight - phoneHeight;

        int startY = targetY + 300; // I will play around to test this

        //How much time has passed since the animation started
        long elapsedTime = System.currentTimeMillis() - animationStartTime;

        //Convert elapsed time into 0.0 -> 1.0 progress
        animationProgress = Math.min((float) elapsedTime / animationDuration, 1.0f);

        //Calculate the phones current Y pos
        int currentY =
                (int) (startY +
                        (targetY - startY) * animationProgress);

        ResourceLocation texture;

        if (animationProgress < 1.0f) {
            texture = PHONE_OFF_TEXTURE;
        }
        else {
            texture = PHONE_TEXTURE;
        }

        guiGraphics.blit(
                texture,
                targetX,
                currentY,
                0,
                0,
                phoneWidth,
                phoneHeight
        );
    }
}
