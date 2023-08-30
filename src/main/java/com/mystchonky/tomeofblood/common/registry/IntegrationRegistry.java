package com.mystchonky.tomeofblood.common.registry;

import com.hollingsworth.arsnouveau.api.familiar.AbstractFamiliarHolder;
import com.hollingsworth.arsnouveau.api.perk.ArmorPerkHolder;
import com.hollingsworth.arsnouveau.api.perk.PerkSlot;
import com.hollingsworth.arsnouveau.api.registry.FamiliarRegistry;
import com.hollingsworth.arsnouveau.api.registry.GlyphRegistry;
import com.hollingsworth.arsnouveau.api.registry.PerkRegistry;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.mystchonky.tomeofblood.common.glyphs.SentientHarmEffect;

import java.util.ArrayList;
import java.util.List;

public class IntegrationRegistry {

    public static SpellSchool BLOODMAGIC = new SpellSchool("bloodmagic");

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>();
    public static List<AbstractFamiliarHolder> registeredFamiliars = new ArrayList<>();

    public static void init() {
        registerGlyphs();
        registerFamiliars();
    }

    public static void postInit() {
        registerSounds();
        registerPerkProviders();
    }

    public static void registerGlyphs() {
        registerSpellPart(SentientHarmEffect.INSTANCE);
    }

    public static void registerFamiliars() {
    }

    public static void registerSounds() {
    }

    public static void registerSpellPart(AbstractSpellPart spellPart) {
        GlyphRegistry.registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

    public static void registerFamiliarHolder(AbstractFamiliarHolder familiarHolder) {
        FamiliarRegistry.registerFamiliar(familiarHolder);
        registeredFamiliars.add(familiarHolder);
    }

    public static void registerPerkProviders() {
        PerkRegistry.registerPerkProvider(ItemRegistry.LIVING_MAGE_HOOD.get(), stack -> new ArmorPerkHolder(stack, List.of(
                List.of(PerkSlot.ONE),
                List.of(PerkSlot.TWO),
                List.of(PerkSlot.TWO, PerkSlot.ONE)
        )));
        PerkRegistry.registerPerkProvider(ItemRegistry.LIVING_MAGE_ROBES.get(), stack -> new ArmorPerkHolder(stack, List.of(
                List.of(PerkSlot.ONE),
                List.of(PerkSlot.TWO),
                List.of(PerkSlot.THREE)
        )));
        PerkRegistry.registerPerkProvider(ItemRegistry.LIVING_MAGE_LEGGINGS.get(), stack -> new ArmorPerkHolder(stack, List.of(
                List.of(PerkSlot.ONE),
                List.of(PerkSlot.ONE, PerkSlot.ONE),
                List.of(PerkSlot.TWO, PerkSlot.ONE)
        )));
        PerkRegistry.registerPerkProvider(ItemRegistry.LIVING_MAGE_BOOTS.get(), stack -> new ArmorPerkHolder(stack, List.of(
                List.of(PerkSlot.ONE),
                List.of(PerkSlot.ONE, PerkSlot.ONE),
                List.of(PerkSlot.TWO, PerkSlot.ONE, PerkSlot.ONE)
        )));
    }

}
