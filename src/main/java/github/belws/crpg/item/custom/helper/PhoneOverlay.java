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

@Mod.EventBusSubscriber(
        modid = CapitalismRpg.MOD_ID,
        value = Dist.CLIENT
)
public class PhoneOverlay {

    private static final ResourceLocation PHONE_TEXTURE =
            new ResourceLocation(CapitalismRpg.MOD_ID, "gui/phone_sprite_demo_0.png");

    @SubscribeEvent
    public static void renderPhone(RenderGuiEvent.Post event){
        GuiGraphics guiGraphics = event.getGuiGraphics();

        if (Minecraft.getInstance().player == null){
            return;
        }
        if (Minecraft.getInstance().player.getMainHandItem().is(ModItems.PHONE.get())) {
            //This is temporary until i figure out how to make it stay in its place:)
            guiGraphics.blit(
                    PHONE_TEXTURE,
                    650,
                    300,
                    0,
                    0,
                    256,
                    256,
                    256,
                    256
            );
        }
    }
}
