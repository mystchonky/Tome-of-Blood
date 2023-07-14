package com.mystchonky.arsoscura.common.lang;

import com.mystchonky.arsoscura.ArsNouveauRegistry;
import com.mystchonky.arsoscura.ArsOscura;
import com.tterrag.registrate.Registrate;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class ArsOscuraLang {

    private static final Registrate REGISTRATE = ArsOscura.registrate();

    public static final Component ITEM_GROUP = REGISTRATE.addRawLang("itemGroup." + ArsOscura.MODID, "Ars Oscura");
    public static final Component SCHOOL_BLOODMAGIC = REGISTRATE.addRawLang("ars_nouveau.school." + ArsNouveauRegistry.BLOODMAGIC.getId(), "Blood Magic");
    public static final Component LOW_LP = REGISTRATE.addLang("alert", ArsOscura.prefix("no_lp"), "Your soul feels weak..");
    public static final Component SIGIL_EMPTY = REGISTRATE.addLang("tooltip", ArsOscura.prefix("sigil_empty"), "No entity stored");
    public static final MutableComponent SIGIL_WITH_ENTITY = REGISTRATE.addLang("tooltip", ArsOscura.prefix("sigil_with_entity"), "Entity stored: %s");

    public static void register() {
        ArsNouveauRegistry.registeredSpells.forEach(spell -> {
            REGISTRATE.addRawLang(ArsOscura.MODID + ".glyph_name." + spell.getRegistryName().getPath(), spell.getName());
            REGISTRATE.addRawLang(ArsOscura.MODID + ".glyph_desc." + spell.getRegistryName().getPath(), spell.getBookDescription());
        });
    }
}
