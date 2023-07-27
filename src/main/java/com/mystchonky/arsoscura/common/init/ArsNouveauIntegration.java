package com.mystchonky.arsoscura.common.init;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.mystchonky.arsoscura.common.integration.bloodmagic.BloodMagicIntegration;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauIntegration {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>();

    public static void init() {
        registerGlyphs();
    }

    public static void postInit() {
        registerSounds();
    }

    public static void registerGlyphs() {
        if (ModList.get().isLoaded("bloodmagic"))
            BloodMagicIntegration.registerGlyphs(ArsNouveauIntegration::registerSpellPart);
    }

    public static void registerSounds() {
    }

    public static void registerSpellPart(AbstractSpellPart spellPart) {
        ArsNouveauAPI.getInstance().registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

}
