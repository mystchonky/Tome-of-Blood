package com.mystchonky.arsoscura.common.init;

import com.mystchonky.arsoscura.ArsOscura;
import com.mystchonky.arsoscura.common.potions.SereneEffect;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;

public class ArsOscuraPotionEffects {

    private static final Registrate REGISTRATE = ArsOscura.registrate();

    public static final RegistryEntry<MobEffect> SERENE_EFFECT = REGISTRATE.simple("serene", ForgeRegistries.MOB_EFFECTS.getRegistryKey(), SereneEffect::new);

    public static void register() {
    }
}
