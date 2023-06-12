package com.luiq54.arsoscura.common.glyphs;


import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractEssenceEffect extends AbstractEffect {


    public AbstractEssenceEffect(String tag, String description) {
        super(tag, description);
    }

    public AbstractEssenceEffect(ResourceLocation tag, String description) {
        super(tag, description);
    }


    protected abstract int getEssenceCost();
}
