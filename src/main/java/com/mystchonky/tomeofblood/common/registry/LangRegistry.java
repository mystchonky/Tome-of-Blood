package com.mystchonky.tomeofblood.common.registry;

import com.mystchonky.tomeofblood.TomeOfBlood;
import com.tterrag.registrate.Registrate;
import net.minecraft.network.chat.Component;

public class LangRegistry {

    private static final Registrate REGISTRATE = TomeOfBlood.registrate();

    public static final Component SCHOOL_BLOODMAGIC = REGISTRATE.addRawLang("ars_nouveau.school." + IntegrationRegistry.BLOODMAGIC.getId(), "Blood Magic");
    public static final Component LOW_LP = REGISTRATE.addLang("alert", TomeOfBlood.prefix("no_lp"), "Your soul feels weak..");
    public static final Component SERENE_EFFECT = REGISTRATE.addLang("effect", TomeOfBlood.prefix("serene"), "Serene");
    public static final Component VULNERABLE_EFFECT = REGISTRATE.addLang("effect", TomeOfBlood.prefix("vulnerable"), "Vulnerable");
    public static final Component MANA_BONUS_UPGRADE = REGISTRATE.addLang("living_upgrade", TomeOfBlood.prefix("mana_bonus"), "Mana Attunement");

    public static void register() {
        IntegrationRegistry.registeredSpells.forEach(spell -> {
            REGISTRATE.addRawLang(TomeOfBlood.MODID + ".glyph_name." + spell.getRegistryName().getPath(), spell.getName());
            REGISTRATE.addRawLang(TomeOfBlood.MODID + ".glyph_desc." + spell.getRegistryName().getPath(), spell.getBookDescription());
        });
    }
}
