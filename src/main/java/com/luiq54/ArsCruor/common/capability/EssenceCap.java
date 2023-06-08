package com.luiq54.ArsCruor.common.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class EssenceCap implements IEssenceCap {

    private final LivingEntity livingEntity;

    public EssenceCap(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    private double essence;
    private int maxEssence;

    @Override
    public double getCurrentEssence() {
        return essence;
    }

    @Override
    public int getMaxEssence() {
        return maxEssence;
    }

    @Override
    public void setMaxEssence(int max) {
        this.maxEssence = max;
    }

    @Override
    public double setEssence(double essence) {
        if (essence > getMaxEssence()) {
            this.essence = getMaxEssence();
        } else if (essence < 0) {
            this.essence = 0;
        } else {
            this.essence = essence;
        }
        return this.getCurrentEssence();
    }

    @Override
    public double addEssence(double essenceToAdd) {
        this.setEssence(this.getCurrentEssence() + essenceToAdd);
        return this.getCurrentEssence();
    }

    @Override
    public double removeEssence(double essenceToRemove) {
        if (essenceToRemove < 0)
            essenceToRemove = 0;
        this.setEssence(this.getCurrentEssence() - essenceToRemove);
        return this.getCurrentEssence();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("current", getCurrentEssence());
        tag.putInt("max", getMaxEssence());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        setMaxEssence(tag.getInt("max"));
        setEssence(tag.getDouble("current"));
    }
}
