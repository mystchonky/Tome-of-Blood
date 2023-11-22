package com.mystchonky.tomeofblood.client.registry;

import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.client.particle.WrathSlashParticle;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TomeOfBlood.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleRegistry {

    private static final Registrate REGISTRATE = TomeOfBlood.registrate();
    public static final RegistryEntry<SimpleParticleType> WRATH_SLASH = REGISTRATE.simple("wrath_slash", ForgeRegistries.PARTICLE_TYPES.getRegistryKey(), () -> new SimpleParticleType(false));

    @SubscribeEvent
    public static void registerProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WRATH_SLASH.get(), WrathSlashParticle.Provider::new);
    }

    public static void register() {
    }
}
