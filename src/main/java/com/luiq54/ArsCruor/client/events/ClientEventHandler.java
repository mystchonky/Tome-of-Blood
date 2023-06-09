package com.luiq54.ArsCruor.client.events;

import com.luiq54.ArsCruor.ArsCruor;
import com.luiq54.ArsCruor.client.gui.GuiEssenceHUD;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ArsCruor.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerOverlays(final RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("essence_hud", GuiEssenceHUD.OVERLAY);
    }
}
