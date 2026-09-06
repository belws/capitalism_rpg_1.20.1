package github.belws.crpg.item.custom.helper;

import github.belws.crpg.CapitalismRpg;
import github.belws.crpg.item.ModItems;
import github.belws.crpg.keybinds.ModKeybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
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

    private static int selectedApp = 0;

    private static final ResourceLocation PHONE_TEXTURE =
            new ResourceLocation(
                    CapitalismRpg.MOD_ID,
                    "gui/phone_on_idle.png"
            );

    private static final ResourceLocation PHONE_OFF_TEXTURE =
            new ResourceLocation(
                    CapitalismRpg.MOD_ID,
                    "gui/phone_off_idle.png"
            );

    private static final ResourceLocation[] APP_TEXTURES = {
            new ResourceLocation(
                    CapitalismRpg.MOD_ID,
                    "gui/apps/app1-clock.png"
            ),
            new ResourceLocation(
                    CapitalismRpg.MOD_ID,
                    "gui/apps/app2-npc-manager.png"
            )
    };



    @SubscribeEvent
    public static void renderPhone(RenderGuiEvent.Post event) throws InterruptedException {
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;


        GuiGraphics guiGraphics = event.getGuiGraphics();

        if (Minecraft.getInstance().player == null){
            return;
        }

        boolean phoneHeld =
                Minecraft.getInstance().player
                        .getMainHandItem()
                        .is(ModItems.PHONE.get()
                        );

        if (!phoneHeld) {
            phoneWasHeld = false;
            animationProgress = 0.0f;
            animationStartTime = 0;
            return;
        }

        if (!phoneWasHeld) {
            phoneWasHeld = true;
            animationStartTime =
                    System.currentTimeMillis();
        }

        if (Minecraft.getInstance().screen == null) {
            if (ModKeybinds.PHONE_LEFT.consumeClick()) {
                Log.info("Left Key pressed!");
                selectedApp--;

                if (selectedApp < 0) {
                    selectedApp = 0;
                }
            }
            if (ModKeybinds.PHONE_RIGHT.consumeClick()) {

                Log.info("Right Key pressed!");
                selectedApp++;
                //Number of current apps
                if (selectedApp > 1) {
                    selectedApp = 1;
                }
            }
        }

        int phoneWidth = 256;
        int phoneHeight = 256;

        int screenWidth = guiGraphics.guiWidth();
        int screenHeight = guiGraphics.guiHeight();

        int targetX = screenWidth - phoneWidth;
        int targetY = screenHeight - phoneHeight;

        int startY = targetY + 300; // I will play around to test this

        //How much time has passed since the animation started
        long elapsedTime =
                System.currentTimeMillis() - animationStartTime;

        //Convert elapsed time into 0.0 -> 1.0 progress
        animationProgress =
                Math.min(
                        (float) elapsedTime / animationDuration,
                        1.0f
                );

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

        // Draw time
        String time = getGameTime();

        int timeX = targetX + phoneWidth - 75;
        int timeY = currentY + 50;

        guiGraphics.drawString(
                font,
                time,
                timeX,
                timeY,
                0xFFFFFF
        );


        //APP
        int appX = targetX + 64;
        int appY = currentY + 80;
        int appSpacing = 48;

        for (int i = 0; i < APP_TEXTURES.length; i++) {
            int x = appX + i * appSpacing;

            ResourceLocation appTexture = APP_TEXTURES[i];

            guiGraphics.blit(
                    appTexture,
                    x,
                    appY,
                    0,
                    0,
                    32,
                    32,
                    32,
                    32
            );
            if (i == selectedApp) {
                guiGraphics.renderOutline(
                        x-2,
                        appY -2,
                        36,
                36,
                0xFFFFFFFF
                );
            }

        }
    }
    private static String getGameTime(){
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.level == null) {
            return "00:00";
        }

        long timeOfDay = minecraft.level.getDayTime() % 24000;

        int hours = (int) ((timeOfDay / 1000 + 6) % 24);
        int minutes = (int) ((timeOfDay % 1000) * 60 / 1000);

        return String.format("%02d:%02d", hours, minutes);
    }
}
