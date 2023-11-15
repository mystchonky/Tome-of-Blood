package com.mystchonky.tomeofblood.common.events;

import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.common.registry.MobEffectRegistry;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wayoftime.bloodmagic.event.SacrificeKnifeUsedEvent;

@Mod.EventBusSubscriber(modid = TomeOfBlood.MODID)
public class EffectEventHandler {

    @SubscribeEvent
    public static void sacrificeKnifeUsed(SacrificeKnifeUsedEvent event) {
        if (event.player.hasEffect(MobEffectRegistry.SERENE.get())) {
            event.lpAdded *= 1.1;
        }
    }

    @SubscribeEvent
    public static void vulnerableEntity(LivingHurtEvent event) {
        if (event.getEntity().hasEffect(MobEffectRegistry.VULNERABLE.get())) {
            event.setAmount((float) (event.getAmount() * 1.1));
        }
    }
}
