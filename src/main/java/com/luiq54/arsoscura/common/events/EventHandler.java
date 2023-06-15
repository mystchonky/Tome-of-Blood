package com.luiq54.arsoscura.common.events;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.spell.AbstractAugment;
import com.luiq54.arsoscura.ArsOscura;
import com.luiq54.arsoscura.client.ClientInfo;
import com.luiq54.arsoscura.common.glyphs.AugmentMimic;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

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
}
