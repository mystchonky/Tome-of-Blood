package com.mystchonky.arsoscura.datagen;

import com.mystchonky.arsoscura.ArsNouveauRegistry;
import com.mystchonky.arsoscura.ArsOscura;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ArsOscura.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ArsNouveauRegistry.registeredSpells.forEach(spell -> basicItem(spell.getRegistryName()));
    }
}
