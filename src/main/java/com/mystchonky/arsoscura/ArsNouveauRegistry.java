package com.mystchonky.arsoscura;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.mystchonky.arsoscura.common.glyphs.EffectSentientHarm;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static SpellSchool BLOODMAGIC = new SpellSchool("bloodmagic");
    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>(); //this will come handy for datagen

    public static void registerGlyphs() {
        register(EffectSentientHarm.INSTANCE);
//        register(EffectLifeSuck.INSTANCE);
//        register(EffectSigilGenerate.INSTANCE);
//        register(MethodSigil.INSTANCE);
//        register(AugmentMimic.INSTANCE);
    }

    public static void registerSounds() {
    }

    public static void register(AbstractSpellPart spellPart) {
        ArsNouveauAPI.getInstance().registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

}
