package com.mystchonky.tomeofblood.common.util;

import com.hollingsworth.arsnouveau.api.item.inv.InteractType;
import com.hollingsworth.arsnouveau.api.item.inv.InventoryManager;
import com.hollingsworth.arsnouveau.api.item.inv.SlotReference;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import net.minecraft.world.item.ItemStack;
import wayoftime.bloodmagic.api.compat.EnumDemonWillType;
import wayoftime.bloodmagic.common.item.soul.ItemSentientSword;
import wayoftime.bloodmagic.common.item.soul.ItemSoulGem;

public class DemonWillUtil {

    public static EnumDemonWillType getActiveTypeFromPlayer(SpellContext context) {
        InventoryManager manager = new InventoryManager(context.getCaster());
        SlotReference reference = manager.findItem(it -> it.getItem() instanceof ItemSoulGem, InteractType.EXTRACT);
        if (!reference.isEmpty()) {
            ItemStack stack = reference.getHandler().getStackInSlot(reference.getSlot());
            return ((ItemSoulGem) stack.getItem()).getCurrentType(stack);
        }
        return EnumDemonWillType.DEFAULT;
    }

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
