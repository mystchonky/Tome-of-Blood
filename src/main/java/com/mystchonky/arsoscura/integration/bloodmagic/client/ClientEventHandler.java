package com.mystchonky.arsoscura.integration.bloodmagic.client;

import com.hollingsworth.arsnouveau.api.perk.ArmorPerkHolder;
import com.hollingsworth.arsnouveau.api.perk.IPerkHolder;
import com.hollingsworth.arsnouveau.api.util.PerkUtil;
import com.mystchonky.arsoscura.integration.bloodmagic.BloodMagicItems;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.hollingsworth.arsnouveau.api.util.PerkUtil.*;

@OnlyIn(Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void initItemColors(final RegisterColorHandlersEvent.Item event) {
        event.register((stack, color) -> color > 0 ? -1 : colorFromArmor(stack), BloodMagicItems.LIVING_MAGE_HOOD);
        event.register((stack, color) -> color > 0 ? -1 : colorFromArmor(stack), BloodMagicItems.LIVING_MAGE_ROBES);
        event.register((stack, color) -> color > 0 ? -1 : colorFromArmor(stack), BloodMagicItems.LIVING_MAGE_LEGGINGS);
        event.register((stack, color) -> color > 0 ? -1 : colorFromArmor(stack), BloodMagicItems.LIVING_MAGE_BOOTS);
    }


    public static int colorFromArmor(ItemStack stack) {
        IPerkHolder<ItemStack> holder = getPerkHolder(stack);
        if (!(holder instanceof ArmorPerkHolder armorPerkHolder))
            return DyeColor.PURPLE.getTextColor();
        return DyeColor.byName(armorPerkHolder.getColor(), DyeColor.PURPLE).getTextColor();
    }
}
