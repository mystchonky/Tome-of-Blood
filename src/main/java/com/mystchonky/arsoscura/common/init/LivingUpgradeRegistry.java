package com.mystchonky.arsoscura.common.init;

import com.mystchonky.arsoscura.ArsOscura;
import wayoftime.bloodmagic.core.LivingArmorRegistrar;
import wayoftime.bloodmagic.core.living.LivingUpgrade;

public class LivingUpgradeRegistry {

    public static LivingUpgrade MANA_UPGRADE = new LivingUpgrade(ArsOscura.prefix("mana_bonus"), levels -> {
        levels.add(new LivingUpgrade.Level(30, 4));
        levels.add(new LivingUpgrade.Level(120, 7));
        levels.add(new LivingUpgrade.Level(480, 8));
        levels.add(new LivingUpgrade.Level(960, 12));
    });

    public static void register() {
        LivingArmorRegistrar.registerUpgrade(MANA_UPGRADE);
    }
}
