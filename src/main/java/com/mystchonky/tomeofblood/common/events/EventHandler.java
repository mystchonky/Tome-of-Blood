package com.mystchonky.tomeofblood.common.events;

import com.hollingsworth.arsnouveau.api.event.SpellCastEvent;
import com.hollingsworth.arsnouveau.api.event.SpellCostCalcEvent;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.client.ClientInfo;
import com.mystchonky.tomeofblood.common.registry.LivingUpgradeRegistry;
import com.mystchonky.tomeofblood.common.registry.MobEffectRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wayoftime.bloodmagic.core.living.LivingStats;
import wayoftime.bloodmagic.core.living.LivingUtil;
import wayoftime.bloodmagic.event.SacrificeKnifeUsedEvent;

import static com.hollingsworth.arsnouveau.api.util.ManaUtil.getPlayerDiscounts;

@Mod.EventBusSubscriber(modid = TomeOfBlood.MODID)
public class EventHandler {
    @SubscribeEvent
    public static void clientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ClientInfo.ticksInGame++;
        }
    }

    @SubscribeEvent
    public static void sacrificeKnifeUsed(SacrificeKnifeUsedEvent event) {
        if (event.player.hasEffect(MobEffectRegistry.SERENE_EFFECT.get())) {
            event.lpAdded *= 1.1;
        }
    }

    @SubscribeEvent
    public static void spellDiscount(SpellCostCalcEvent event) {
        if (event.context.getUnwrappedCaster() instanceof Player player) {
            if (LivingUtil.hasFullSet(player)) {
                LivingStats stats = LivingStats.fromPlayer(player);
                int level = stats.getLevel(LivingUpgradeRegistry.MANA_UPGRADE.getKey());
                double discount = level * 0.05;
                event.currentCost = (int) ((1 - discount) * event.currentCost);
            }
        }

    }

    @SubscribeEvent
    public static void awardXPForSpellCast(SpellCastEvent event) {
        if (event.context.getUnwrappedCaster() instanceof Player player) {
            if (LivingUtil.hasFullSet(player)) {
                Spell spell = event.spell;
                SpellContext spellContext = event.context;

                int cost = spellContext.getSpell().getCost() - getPlayerDiscounts(spellContext.getUnwrappedCaster(), spell, spellContext.getCasterTool());
                cost = Math.max(cost, 0);
                int xpAward = cost / 100;
                LivingUtil.applyNewExperience(player, LivingUpgradeRegistry.MANA_UPGRADE, xpAward);
            }
        }
    }

}
