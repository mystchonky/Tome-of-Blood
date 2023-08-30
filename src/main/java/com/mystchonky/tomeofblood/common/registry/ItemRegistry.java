package com.mystchonky.tomeofblood.common.registry;

import com.hollingsworth.arsnouveau.api.spell.SpellTier;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.common.items.LivingMageArmorItem;
import com.mystchonky.tomeofblood.common.items.MintTeaItem;
import com.mystchonky.tomeofblood.common.items.TomeOfBloodItem;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import wayoftime.bloodmagic.core.living.LivingStats;

public class ItemRegistry {
    private static final Registrate REGISTRATE = TomeOfBlood.registrate();

    public static final RegistryEntry<CreativeModeTab> CREATIVE_TAB = REGISTRATE.defaultCreativeTab("tome_of_blood",
                    tab -> tab.icon(() -> ItemsRegistry.WAND.get().getDefaultInstance())
                            .build()
            )
            .register();

    public static final ItemEntry<TomeOfBloodItem> NOVICE_TOME = REGISTRATE.item("novice_tome_of_blood", (properties) -> new TomeOfBloodItem(properties, SpellTier.ONE))
            .lang("Novice Tome of Blood")
            .properties((properties -> properties.stacksTo(1)))
            .model((ctx, prov) -> {
            })
            .register();

    public static final ItemEntry<TomeOfBloodItem> APPRENTICE_TOME = REGISTRATE.item("apprentice_tome_of_blood", (properties) -> new TomeOfBloodItem(properties, SpellTier.TWO))
            .lang("Apprentice Tome of Blood")
            .properties((properties -> properties.stacksTo(1)))
            .model((ctx, prov) -> {
            })
            .register();
    public static final ItemEntry<TomeOfBloodItem> ARCHMAGE_TOME = REGISTRATE.item("archmage_tome_of_blood", (properties) -> new TomeOfBloodItem(properties, SpellTier.THREE))
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
    public static final ItemEntry<LivingMageArmorItem> LIVING_MAGE_ROBES = REGISTRATE.item("living_mage_robes", (prop) -> new LivingMageArmorItem(ArmorItem.Type.CHESTPLATE))
            .lang("Living Mage Robes")
            .model((ctx, prov) -> {
            })
            .tab(ItemRegistry.CREATIVE_TAB.getKey(), modifier -> {
                var armor = ItemRegistry.LIVING_MAGE_ROBES.get();
                ItemStack stack = armor.getDefaultInstance();
                armor.updateLivingStats(stack, new LivingStats());
                modifier.accept(stack);
            })
            .register();
    public static final ItemEntry<LivingMageArmorItem> LIVING_MAGE_LEGGINGS = REGISTRATE.item("living_mage_leggings", (prop) -> new LivingMageArmorItem(ArmorItem.Type.LEGGINGS))
            .lang("Living Mage Leggings")
            .model((ctx, prov) -> {
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
