package github.belws.crpg.item.custom.event;

import github.belws.crpg.CapitalismRpg;
import github.belws.crpg.item.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = CapitalismRpg.MOD_ID,
        value = Dist.CLIENT
)
public class PhoneEvents {

    @SubscribeEvent
    //This will prevent player from being able to deal damage while holding the phone
    public static void onAttackEntity(AttackEntityEvent event){
        if (event.getEntity().getMainHandItem().is(ModItems.PHONE.get())){
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event){
        if(event.getEntity().getMainHandItem().is(ModItems.PHONE.get())) {
            event.setCanceled(true);
        }
    }
}
