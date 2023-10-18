package com.mystchonky.tomeofblood.common.registry;

import com.mystchonky.tomeofblood.TomeOfBlood;
import wayoftime.bloodmagic.core.LivingArmorRegistrar;
import wayoftime.bloodmagic.core.living.LivingUpgrade;

public class LivingUpgradeRegistry {

    // TODO: change `bonus` to `discount`
    public static LivingUpgrade MANA_UPGRADE = new LivingUpgrade(TomeOfBlood.prefix("mana_bonus"), levels -> {
        levels.add(new LivingUpgrade.Level(30, 4));
        levels.add(new LivingUpgrade.Level(120, 10));
        levels.add(new LivingUpgrade.Level(480, 22));
        levels.add(new LivingUpgrade.Level(960, 40));
    });

    public static void register() {
        LivingArmorRegistrar.registerUpgrade(MANA_UPGRADE);
    }
}
