package com.luiq54.ArsCruor.common.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IEssenceCap extends INBTSerializable<CompoundTag> {

    double getCurrentEssence();

    int getMaxEssence();

    void setMaxEssence(int max);

    double setEssence(final double essence);

    double addEssence(final double essenceToAdd);

    double removeEssence(final double essenceToRemove);
}
