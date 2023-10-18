package com.mystchonky.tomeofblood.common.config.common;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {

    public final ForgeConfigSpec.ConfigValue<Integer> CONVERSION_RATE;
    public final ForgeConfigSpec.ConfigValue<Double> LIVING_MAGE_SCALE;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        CONVERSION_RATE = builder.comment("Conversion rate of LP into player mana").define("conversionRate", 10);
        LIVING_MAGE_SCALE = builder.comment("Scale at which Living Mage Armor gains experience compared to normal Living Armor").define("livingExpScale", 0.8);
    }
}
