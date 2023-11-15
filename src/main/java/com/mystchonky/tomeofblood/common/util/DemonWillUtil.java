package com.mystchonky.tomeofblood.common.util;

import wayoftime.bloodmagic.api.compat.EnumDemonWillType;
import wayoftime.bloodmagic.common.item.soul.ItemSentientSword;

public class DemonWillUtil {

    public static int getBracket(EnumDemonWillType type, int souls) {
        int bracket = -1;
        for (int i = 0; i < ItemSentientSword.soulBracket.length; i++) {
            if (souls >= ItemSentientSword.soulBracket[i]) {
                bracket = i;
            }
        }
        return bracket;
    }

    public static float getExtraDamage(EnumDemonWillType type, int bracket) {
        if (bracket < 0) {
            return 0;
        }
        return switch (type) {
            case CORROSIVE, DEFAULT -> (float) ItemSentientSword.defaultDamageAdded[bracket];
            case DESTRUCTIVE -> (float) ItemSentientSword.destructiveDamageAdded[bracket];
            case VENGEFUL -> (float) ItemSentientSword.vengefulDamageAdded[bracket];
            case STEADFAST -> (float) ItemSentientSword.steadfastDamageAdded[bracket];
        };
    }
}
