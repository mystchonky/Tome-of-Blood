package com.mystchonky.tomeofblood.common.registry;

import com.hollingsworth.arsnouveau.common.potions.PublicEffect;
import com.mystchonky.tomeofblood.TomeOfBlood;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.ForgeRegistries;

public class MobEffectRegistry {

    private static final Registrate REGISTRATE = TomeOfBlood.registrate();

    //TODO: figure out a way to datagen lang automatically
    public static final RegistryEntry<MobEffect> SERENE = REGISTRATE.simple("serene", ForgeRegistries.MOB_EFFECTS.getRegistryKey(), () -> new PublicEffect(MobEffectCategory.BENEFICIAL, 16435708));
    public static final RegistryEntry<MobEffect> VULNERABLE = REGISTRATE.simple("vulnerable", ForgeRegistries.MOB_EFFECTS.getRegistryKey(), () -> new PublicEffect(MobEffectCategory.BENEFICIAL, 16435708));

    public static void register() {
    }

}
