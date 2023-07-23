package com.mystchonky.arsoscura.common.integration.occultism;

import net.minecraftforge.common.MinecraftForge;

public class OccultismIntegration {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
    }
}
