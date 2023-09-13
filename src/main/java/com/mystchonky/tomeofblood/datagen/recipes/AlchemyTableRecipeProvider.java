package com.mystchonky.tomeofblood.datagen.recipes;

import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.common.registry.ItemRegistry;
import com.mystchonky.tomeofblood.datagen.RecipeUtil;
import net.minecraft.data.PackOutput;
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
import wayoftime.bloodmagic.common.data.recipe.builder.AlchemyTableRecipeBuilder;

import java.util.function.Consumer;

public class AlchemyTableRecipeProvider extends RecipeProvider {

    String basePath = "alchemytable/";

    public AlchemyTableRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "Alchemy Table recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        CompoundTag tag = new CompoundTag();
        tag.putString("Potion", ForgeRegistries.POTIONS.getKey(Potions.WATER).toString());

        RecipeUtil.bloodmagicRecipe(AlchemyTableRecipeBuilder.alchemyTable(new ItemStack(ItemRegistry.MINT_TEA.get()), 100, 100, 1).addIngredient(Ingredient.of(Items.SUGAR)).addIngredient(Ingredient.of(Items.SEAGRASS)).addIngredient(Ingredient.of(Items.SEAGRASS)).addIngredient(PartialNBTIngredient.of(Items.POTION, tag))::build, consumer, new ResourceLocation(TomeOfBlood.MODID, basePath + "mint_tea"));

        RecipeUtil.bloodmagicRecipe(AlchemyTableRecipeBuilder.alchemyTable(new ItemStack(ItemRegistry.APPRENTICE_TOME.get()), 5000, 100, 2).addIngredient(Ingredient.of(ItemRegistry.NOVICE_TOME)).addIngredient(Ingredient.of(Items.OBSIDIAN)).addIngredient(Ingredient.of(Items.DIAMOND)).addIngredient(Ingredient.of(Items.QUARTZ_BLOCK)).addIngredient(Ingredient.of(Items.BLAZE_ROD))::build, consumer, new ResourceLocation(TomeOfBlood.MODID, basePath + "apprentice_tome"));
        RecipeUtil.bloodmagicRecipe(AlchemyTableRecipeBuilder.alchemyTable(new ItemStack(ItemRegistry.ARCHMAGE_TOME.get()), 15000, 100, 3).addIngredient(Ingredient.of(ItemRegistry.APPRENTICE_TOME)).addIngredient(Ingredient.of(Items.NETHER_STAR)).addIngredient(Ingredient.of(Items.EMERALD)).addIngredient(Ingredient.of(Items.ENDER_PEARL)).addIngredient(Ingredient.of(Items.TOTEM_OF_UNDYING)).addIngredient(Ingredient.of(ItemsRegistry.WILDEN_TRIBUTE))::build, consumer, new ResourceLocation(TomeOfBlood.MODID, basePath + "archmage_tome"));
    }
}
