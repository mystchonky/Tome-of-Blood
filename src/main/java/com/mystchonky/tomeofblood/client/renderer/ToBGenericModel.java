package com.mystchonky.tomeofblood.client.renderer;

import com.hollingsworth.arsnouveau.client.renderer.tile.GenericModel;
import com.mystchonky.tomeofblood.TomeOfBlood;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;

public class ToBGenericModel<T extends GeoAnimatable> extends GenericModel<T> {
    public ToBGenericModel(String name) {
        super(name);
        this.modelLocation = new ResourceLocation(TomeOfBlood.MODID, "geo/" + name + ".geo.json");
        this.textLoc = new ResourceLocation(TomeOfBlood.MODID, "textures/" + textPathRoot + "/" + name + ".png");
        this.animationLoc = new ResourceLocation(TomeOfBlood.MODID, "animations/" + name + "_animations.json");
        this.name = name;
    }

    public ToBGenericModel(String name, String textPath) {
        this(name);
        this.textPathRoot = textPath;
        this.textLoc = new ResourceLocation(TomeOfBlood.MODID, "textures/" + textPathRoot + "/" + name + ".png");
    }
}
