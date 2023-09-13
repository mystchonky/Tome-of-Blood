package com.mystchonky.tomeofblood.datagen.recipes;

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
    }
}
