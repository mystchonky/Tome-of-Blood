package com.mystchonky.arsoscura.common.events;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.spell.AbstractAugment;
import com.mystchonky.arsoscura.ArsOscura;
import com.mystchonky.arsoscura.client.ClientInfo;
import com.mystchonky.arsoscura.common.glyphs.AugmentMimic;
import com.mystchonky.arsoscura.common.init.ArsOscuraPotionEffects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import wayoftime.bloodmagic.event.SacrificeKnifeUsedEvent;

@Mod.EventBusSubscriber(modid = ArsOscura.MODID)
public class EventHandler {
    @SubscribeEvent
    public static void clientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ClientInfo.ticksInGame++;
        }
    }

    @SubscribeEvent
    public static void updateAugmentsforMimic(FMLLoadCompleteEvent event) {
        ArsNouveauAPI.getInstance()
                .getGlyphItemMap()
                .values()
                .stream()
                .filter(spell -> spell instanceof AbstractAugment)
                .forEach(spell -> {
                    ((AbstractAugment) spell).getCompatibleAugments().add(AugmentMimic.INSTANCE);
                });
    }

    @SubscribeEvent
    public static void sacrificeKnifeUsed(SacrificeKnifeUsedEvent event) {
        if (event.player.hasEffect(ArsOscuraPotionEffects.SERENE_EFFECT.get())) {
            event.lpAdded *= 1.1;
        }

    }
}
