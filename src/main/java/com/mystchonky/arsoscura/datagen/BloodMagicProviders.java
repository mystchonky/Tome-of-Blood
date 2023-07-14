package com.mystchonky.arsoscura.datagen;

import com.hollingsworth.arsnouveau.setup.ItemsRegistry;
import com.mystchonky.arsoscura.ArsOscura;
import com.mystchonky.arsoscura.common.items.ArsOscuraItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import wayoftime.bloodmagic.altar.AltarTier;
import wayoftime.bloodmagic.common.data.recipe.builder.BloodAltarRecipeBuilder;

import java.util.function.Consumer;

public class BloodMagicProviders {

    public static class AltarProvider extends RecipeProvider {

        String basePath = "altar/";

        public AltarProvider(DataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @Override
        protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
            BloodAltarRecipeBuilder.altar(Ingredient.of(ItemsRegistry.NOVICE_SPELLBOOK.get()), new ItemStack(ArsOscuraItems.NOVICE_TOME.get()), AltarTier.TWO.ordinal(), 10000, 20, 20).build(consumer, new ResourceLocation(ArsOscura.MODID, basePath + "novice_blood_tome"));
            BloodAltarRecipeBuilder.altar(Ingredient.of(ItemsRegistry.APPRENTICE_SPELLBOOK.get()), new ItemStack(ArsOscuraItems.APPRENTICE_TOME.get()), AltarTier.THREE.ordinal(), 25000, 20, 20).build(consumer, new ResourceLocation(ArsOscura.MODID, basePath + "apprentice_blood_tome"));
            BloodAltarRecipeBuilder.altar(Ingredient.of(ItemsRegistry.ARCHMAGE_SPELLBOOK.get()), new ItemStack(ArsOscuraItems.ARCHMAGE_TOME.get()), AltarTier.FOUR.ordinal(), 50000, 20, 20).build(consumer, new ResourceLocation(ArsOscura.MODID, basePath + "archmage_blood_tome"));
        }
    }
}
