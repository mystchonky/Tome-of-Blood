package com.mystchonky.arsoscura.datagen;

import com.hollingsworth.arsnouveau.setup.ItemsRegistry;
import com.mystchonky.arsoscura.ArsOscura;
import com.mystchonky.arsoscura.common.integration.bloodmagic.BloodMagicItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.registries.ForgeRegistries;
import wayoftime.bloodmagic.BloodMagic;
import wayoftime.bloodmagic.altar.AltarTier;
import wayoftime.bloodmagic.common.data.recipe.builder.AlchemyTableRecipeBuilder;
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
            BloodAltarRecipeBuilder.altar(Ingredient.of(ItemsRegistry.NOVICE_SPELLBOOK.get()), new ItemStack(BloodMagicItems.NOVICE_TOME.get()), AltarTier.TWO.ordinal(), 10000, 20, 20).build(consumer, new ResourceLocation(ArsOscura.MODID, basePath + "novice_blood_tome"));
            BloodAltarRecipeBuilder.altar(Ingredient.of(ItemsRegistry.APPRENTICE_SPELLBOOK.get()), new ItemStack(BloodMagicItems.APPRENTICE_TOME.get()), AltarTier.THREE.ordinal(), 25000, 20, 20).build(consumer, new ResourceLocation(ArsOscura.MODID, basePath + "apprentice_blood_tome"));
            BloodAltarRecipeBuilder.altar(Ingredient.of(ItemsRegistry.ARCHMAGE_SPELLBOOK.get()), new ItemStack(BloodMagicItems.ARCHMAGE_TOME.get()), AltarTier.FOUR.ordinal(), 50000, 20, 20).build(consumer, new ResourceLocation(ArsOscura.MODID, basePath + "archmage_blood_tome"));
        }
    }

    public static class AlchemyTableProvider extends RecipeProvider {
        String basePath = "alchemytable/";

        public AlchemyTableProvider(DataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @Override
        protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
            CompoundTag tag = new CompoundTag();
            tag.putString("Potion", ForgeRegistries.POTIONS.getKey(Potions.WATER).toString());
            AlchemyTableRecipeBuilder.alchemyTable(new ItemStack(BloodMagicItems.MINT_TEA.get()), 100, 100, 1).addIngredient(Ingredient.of(Items.SUGAR)).addIngredient(Ingredient.of(Items.SEAGRASS)).addIngredient(Ingredient.of(Items.SEAGRASS)).addIngredient(PartialNBTIngredient.of(Items.POTION, tag)).build(consumer, BloodMagic.rl(basePath + "mint_tea"));
        }
    }
}
