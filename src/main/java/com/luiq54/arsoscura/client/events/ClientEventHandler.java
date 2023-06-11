package com.luiq54.arsoscura.client.events;

import com.luiq54.arsoscura.ArsOscura;
import com.luiq54.arsoscura.client.gui.GuiEssenceHUD;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ArsOscura.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerOverlays(final RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("essence_hud", GuiEssenceHUD.OVERLAY);
    }
}
