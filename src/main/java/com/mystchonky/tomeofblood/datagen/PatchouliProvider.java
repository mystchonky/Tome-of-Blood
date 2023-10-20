package com.mystchonky.tomeofblood.datagen;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.spell.AbstractCastMethod;
import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.GlyphScribePage;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.PatchouliBuilder;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.TextPage;
import com.mystchonky.tomeofblood.common.registry.IntegrationRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

public class PatchouliProvider extends com.hollingsworth.arsnouveau.common.datagen.PatchouliProvider {

    public static ResourceLocation TomeOfBlood = new ResourceLocation(ArsNouveau.MODID, "tomeofblood");

    public PatchouliProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void addEntries() {
        for (AbstractSpellPart spell : IntegrationRegistry.registeredSpells) {
            addGlyphPage(spell);
        }
    }

    @Override
    public void addGlyphPage(AbstractSpellPart spellPart) {
        ResourceLocation category = switch (spellPart.defaultTier().value) {
            case 1 -> GLYPHS_1;
            case 2 -> GLYPHS_2;
            default -> GLYPHS_3;
        };
        PatchouliBuilder arsBuilder = new PatchouliBuilder(category, spellPart.getName())
                .withName("tomeofblood.glyph_name." + spellPart.getRegistryName().getPath())
                .withIcon(spellPart.getRegistryName().toString())
                .withSortNum(spellPart instanceof AbstractCastMethod ? 1 : spellPart instanceof AbstractEffect ? 2 : 3)
                .withPage(new TextPage("tomeofblood.glyph_desc." + spellPart.getRegistryName().getPath()))
                .withPage(new GlyphScribePage(spellPart));
        this.pages.add(new PatchouliPage(arsBuilder, getPath(category, spellPart.getRegistryName().getPath())));

        PatchouliBuilder tobBuilder = new PatchouliBuilder(TomeOfBlood, spellPart.getName())
                .withName("tomeofblood.glyph_name." + spellPart.getRegistryName().getPath())
                .withIcon(spellPart.getRegistryName().toString())
                .withSortNum(spellPart instanceof AbstractCastMethod ? 1 : spellPart instanceof AbstractEffect ? 2 : 3)
                .withPage(new TextPage("tomeofblood.glyph_desc." + spellPart.getRegistryName().getPath()))
                .withPage(new GlyphScribePage(spellPart));
        this.pages.add(new PatchouliPage(tobBuilder, getPath(TomeOfBlood, spellPart.getRegistryName().getPath())));
    }

    /**
     * Gets a name for this provider, to use in logging.
     */
    @Override
    public String getName() {
        return "ToB Patchouli Datagen";
    }
}
