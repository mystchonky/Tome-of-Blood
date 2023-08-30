package com.mystchonky.tomeofblood.common.registry;

import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.common.mobeffects.SereneEffect;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;

public class MobEffectRegistry {

    private static final Registrate REGISTRATE = TomeOfBlood.registrate();

    public static final RegistryEntry<MobEffect> SERENE_EFFECT = REGISTRATE.simple("serene", ForgeRegistries.MOB_EFFECTS.getRegistryKey(), SereneEffect::new);

    public static void register() {
    }
}
