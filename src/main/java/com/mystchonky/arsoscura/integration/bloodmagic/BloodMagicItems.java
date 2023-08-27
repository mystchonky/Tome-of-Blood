package com.mystchonky.arsoscura.integration.bloodmagic;

import com.hollingsworth.arsnouveau.api.spell.SpellTier;
import com.mystchonky.arsoscura.ArsOscura;
import com.mystchonky.arsoscura.common.init.ArsOscuraItems;
import com.mystchonky.arsoscura.integration.bloodmagic.items.LivingMageArmorItem;
import com.mystchonky.arsoscura.integration.bloodmagic.items.MintTeaItem;
import com.mystchonky.arsoscura.integration.bloodmagic.items.TomeOfBlood;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import wayoftime.bloodmagic.core.living.LivingStats;

public class BloodMagicItems {


    private static final Registrate REGISTRATE = ArsOscura.registrate();


    public static final ItemEntry<TomeOfBlood> NOVICE_TOME = REGISTRATE.item("novice_tome_of_blood", (properties) -> new TomeOfBlood(properties, SpellTier.ONE))
            .lang("Novice Tome of Blood")
            .properties((properties -> properties.stacksTo(1)))
            .model((ctx, prov) -> {
            })
            .register();

    public static final ItemEntry<TomeOfBlood> APPRENTICE_TOME = REGISTRATE.item("apprentice_tome_of_blood", (properties) -> new TomeOfBlood(properties, SpellTier.TWO))
            .lang("Apprentice Tome of Blood")
            .properties((properties -> properties.stacksTo(1)))
            .model((ctx, prov) -> {
            })
            .register();
    public static final ItemEntry<TomeOfBlood> ARCHMAGE_TOME = REGISTRATE.item("archmage_tome_of_blood", (properties) -> new TomeOfBlood(properties, SpellTier.THREE))
            .lang("Archmage Tome of Blood")
            .properties((properties -> properties.stacksTo(1)))
            .model((ctx, prov) -> {
            })
            .register();

    public static final ItemEntry<MintTeaItem> MINT_TEA = REGISTRATE.item("mint_tea", MintTeaItem::new)
            .lang("Mint Tea")
            .properties((properties -> properties.stacksTo(1)))
            .register();

    public static final ItemEntry<LivingMageArmorItem> LIVING_MAGE_HOOD = REGISTRATE.item("living_mage_hood", (prop) -> new LivingMageArmorItem(ArmorItem.Type.HELMET))
            .lang("Living Mage Hood")
            .model((ctx, prov) -> {
            })
            .register();
    public static final ItemEntry<LivingMageArmorItem> LIVING_MAGE_LEGGINGS = REGISTRATE.item("living_mage_leggings", (prop) -> new LivingMageArmorItem(ArmorItem.Type.LEGGINGS))
            .lang("Living Mage Leggings")
            .model((ctx, prov) -> {
            })
            .register();
    public static final ItemEntry<LivingMageArmorItem> LIVING_MAGE_ROBES = REGISTRATE.item("living_mage_robes", (prop) -> new LivingMageArmorItem(ArmorItem.Type.CHESTPLATE))
            .lang("Living Mage Robes")
            .model((ctx, prov) -> {
            })
            .tab(ArsOscuraItems.ARS_OSCURA_TAB.getKey(), modifier -> {
                var armor = BloodMagicItems.LIVING_MAGE_ROBES.get();
                ItemStack stack = armor.getDefaultInstance();
                armor.updateLivingStats(stack, new LivingStats());
                modifier.accept(stack);
            })
            .register();
    public static final ItemEntry<LivingMageArmorItem> LIVING_MAGE_BOOTS = REGISTRATE.item("living_mage_boots", (prop) -> new LivingMageArmorItem(ArmorItem.Type.BOOTS))
            .lang("Living Mage Boots")
            .model((ctx, prov) -> {
            })
            .register();

    public static void register() {
    }


}
