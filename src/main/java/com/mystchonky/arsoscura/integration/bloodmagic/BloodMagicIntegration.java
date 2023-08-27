package com.mystchonky.arsoscura.integration.bloodmagic;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.perk.ArmorPerkHolder;
import com.hollingsworth.arsnouveau.api.perk.PerkSlot;
import com.hollingsworth.arsnouveau.api.registry.PerkRegistry;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.mystchonky.arsoscura.integration.bloodmagic.client.ClientEventHandler;
import com.mystchonky.arsoscura.integration.bloodmagic.glyphs.EffectSentientHarm;
import net.minecraftforge.common.MinecraftForge;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class BloodMagicIntegration {
    public static SpellSchool BLOODMAGIC = new SpellSchool("bloodmagic");

    public static void init() {
        BloodMagicItems.register();
        LivingUpgradeRegistry.register();
        BloodMagicMobEffects.register();

        MinecraftForge.EVENT_BUS.register(EventHandler.class);
        MinecraftForge.EVENT_BUS.register(ClientEventHandler.class);
    }

    public static void registerGlyphs(Consumer<AbstractSpellPart> register) {
        register.accept(EffectSentientHarm.INSTANCE);
    }

    public static void registerPerkProviders() {
        PerkRegistry.registerPerkProvider(BloodMagicItems.LIVING_MAGE_HOOD.get(), stack -> new ArmorPerkHolder(stack, List.of(
                List.of(PerkSlot.ONE),
                List.of(PerkSlot.TWO),
                List.of(PerkSlot.THREE)
        )));
        PerkRegistry.registerPerkProvider(BloodMagicItems.LIVING_MAGE_LEGGINGS.get(), stack -> new ArmorPerkHolder(stack, List.of(
                List.of(PerkSlot.ONE),
                List.of(PerkSlot.ONE, PerkSlot.ONE),
                List.of(PerkSlot.TWO, PerkSlot.ONE)
        )));
        PerkRegistry.registerPerkProvider(BloodMagicItems.LIVING_MAGE_BOOTS.get(), stack -> new ArmorPerkHolder(stack, List.of(
                List.of(PerkSlot.ONE),
                List.of(PerkSlot.ONE, PerkSlot.ONE),
                List.of(PerkSlot.TWO, PerkSlot.ONE, PerkSlot.ONE)
        )));
    }
}
