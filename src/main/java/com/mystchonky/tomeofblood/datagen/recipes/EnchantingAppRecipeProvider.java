package com.mystchonky.tomeofblood.datagen.recipes;

import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.common.registry.ItemRegistry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import wayoftime.bloodmagic.common.item.BloodMagicItems;

import java.nio.file.Path;

public class EnchantingAppRecipeProvider extends ApparatusRecipeProvider {
    public EnchantingAppRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput pOutput) {
        recipes.add(builder()
                .withReagent(BloodMagicItems.LIVING_HELMET)
                .withPedestalItem(4, ItemsRegistry.MAGE_FIBER)
                .withResult(ItemRegistry.LIVING_MAGE_HOOD)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withReagent(BloodMagicItems.LIVING_PLATE)
                .withPedestalItem(4, ItemsRegistry.MAGE_FIBER)
                .withResult(ItemRegistry.LIVING_MAGE_ROBES)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withReagent(BloodMagicItems.LIVING_LEGGINGS)
                .withPedestalItem(4, ItemsRegistry.MAGE_FIBER)
                .withResult(ItemRegistry.LIVING_MAGE_LEGGINGS)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withReagent(BloodMagicItems.LIVING_BOOTS)
                .withPedestalItem(4, ItemsRegistry.MAGE_FIBER)
                .withResult(ItemRegistry.LIVING_MAGE_BOOTS)
                .keepNbtOfReagent(true)
                .build()
        );
        for (EnchantingApparatusRecipe g : recipes) {
            if (g != null) {
                Path path = getRecipePath(output, g.getId().getPath());
                saveStable(pOutput, g.asRecipe(), path);
            }
        }

    }

    protected static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/" + TomeOfBlood.MODID + "/recipes/" + str + ".json");
    }

    @Override
    public String getName() {
        return "ToB Apparatus";
    }
}
