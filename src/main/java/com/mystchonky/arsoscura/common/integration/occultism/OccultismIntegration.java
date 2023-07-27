package com.mystchonky.arsoscura.common.integration.occultism;

import com.hollingsworth.arsnouveau.api.mob_jar.JarBehaviorRegistry;
import com.klikli_dev.occultism.registry.OccultismEntities;
import com.mystchonky.arsoscura.common.integration.occultism.mob_jar.SpiritBehaviour;
import net.minecraftforge.common.MinecraftForge;

public class OccultismIntegration {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
    }

    public static void postInit() {
        registerJarBehaviours();
    }


    public static void registerJarBehaviours() {
        JarBehaviorRegistry.register(OccultismEntities.FOLIOT.get(), new SpiritBehaviour<>());
        JarBehaviorRegistry.register(OccultismEntities.DJINNI.get(), new SpiritBehaviour<>());
        JarBehaviorRegistry.register(OccultismEntities.AFRIT.get(), new SpiritBehaviour<>());
        JarBehaviorRegistry.register(OccultismEntities.MARID.get(), new SpiritBehaviour<>());
    }
}
