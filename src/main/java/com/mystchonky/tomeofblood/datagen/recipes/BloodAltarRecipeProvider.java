package com.mystchonky.tomeofblood.datagen.recipes;

import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.common.registry.ItemRegistry;
import com.mystchonky.tomeofblood.datagen.RecipeUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import wayoftime.bloodmagic.altar.AltarTier;
import wayoftime.bloodmagic.common.data.recipe.builder.BloodAltarRecipeBuilder;

import java.util.function.Consumer;

public class BloodAltarRecipeProvider extends RecipeProvider {

    String basePath = "altar/";

    public BloodAltarRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "Altar recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        RecipeUtil.bloodmagicRecipe(BloodAltarRecipeBuilder.altar(Ingredient.of(ItemsRegistry.NOVICE_SPELLBOOK.get()), new ItemStack(ItemRegistry.NOVICE_TOME.get()), AltarTier.ONE.ordinal(), 5000, 20, 20)::build, consumer, new ResourceLocation(TomeOfBlood.MODID, basePath + "novice_blood_tome"));
        RecipeUtil.bloodmagicRecipe(BloodAltarRecipeBuilder.altar(Ingredient.of(ItemsRegistry.APPRENTICE_SPELLBOOK.get()), new ItemStack(ItemRegistry.APPRENTICE_TOME.get()), AltarTier.TWO.ordinal(), 10000, 20, 20)::build, consumer, new ResourceLocation(TomeOfBlood.MODID, basePath + "apprentice_blood_tome"));
        RecipeUtil.bloodmagicRecipe(BloodAltarRecipeBuilder.altar(Ingredient.of(ItemsRegistry.ARCHMAGE_SPELLBOOK.get()), new ItemStack(ItemRegistry.ARCHMAGE_TOME.get()), AltarTier.THREE.ordinal(), 25000, 20, 20)::build, consumer, new ResourceLocation(TomeOfBlood.MODID, basePath + "archmage_blood_tome"));
    }

}
