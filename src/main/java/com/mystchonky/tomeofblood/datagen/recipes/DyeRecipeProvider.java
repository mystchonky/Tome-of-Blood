package com.mystchonky.tomeofblood.datagen.recipes;


import com.google.gson.JsonElement;
import com.hollingsworth.arsnouveau.common.crafting.recipes.DyeRecipe;
import com.hollingsworth.arsnouveau.common.datagen.SimpleDataProvider;
import com.mystchonky.tomeofblood.common.registry.ItemRegistry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.ItemLike;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.hollingsworth.arsnouveau.setup.registry.RegistryHelper.getRegistryName;

public class DyeRecipeProvider extends SimpleDataProvider {
    List<FileObj> files = new ArrayList<>();

    public DyeRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput pOutput) {
        addDyeRecipe(ItemRegistry.LIVING_MAGE_HOOD);
        addDyeRecipe(ItemRegistry.LIVING_MAGE_ROBES);
        addDyeRecipe(ItemRegistry.LIVING_MAGE_LEGGINGS);
        addDyeRecipe(ItemRegistry.LIVING_MAGE_BOOTS);
        for (FileObj fileObj : files) {
            saveStable(pOutput, fileObj.element, fileObj.path);
        }
    }


    public void add(FileObj fileObj) {
        files.add(fileObj);
    }

    public void addDyeRecipe(ItemLike inputItem) {
        JsonElement dyeRecipe = DyeRecipe.asRecipe(inputItem.asItem());
        add(new FileObj(output.resolve("data/ars_nouveau/recipes/dye_" + getRegistryName(inputItem.asItem()).getPath() + ".json"), dyeRecipe));
    }

    @Override
    public String getName() {
        return "ArsNouveau: Json Datagen";
    }

    public record FileObj(Path path, JsonElement element) {

    }
}
