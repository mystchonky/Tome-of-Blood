package com.mystchonky.tomeofblood.common.glyphs;

import com.hollingsworth.arsnouveau.api.spell.AbstractAugment;
import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;
import com.hollingsworth.arsnouveau.api.spell.IDamageEffect;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.spell.SpellStats;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentDampen;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentDurationDown;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentExtendTime;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentFortune;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.common.registry.IntegrationRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;
import wayoftime.bloodmagic.api.compat.EnumDemonWillType;
import wayoftime.bloodmagic.common.item.soul.ItemSentientSword;
import wayoftime.bloodmagic.potion.BloodMagicPotions;
import wayoftime.bloodmagic.will.PlayerDemonWillHandler;

import java.util.Map;
import java.util.Set;

public class SentientHarmEffect extends AbstractEffect implements IDamageEffect {

    public static SentientHarmEffect INSTANCE = new SentientHarmEffect();

    public SentientHarmEffect() {
        super(TomeOfBlood.prefix(GlyphLibrary.EffectSentientHarm), "Sentient Harm");
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @NotNull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (shooter instanceof Player player) {
            EnumDemonWillType type = PlayerDemonWillHandler.getLargestWillType(player);
            int souls = (int) PlayerDemonWillHandler.getTotalDemonWill(type, player);

            int bracket = getBracket(type, souls);
            Entity entity = rayTraceResult.getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                int time = spellStats.getDurationInTicks();
                float damage = (float) (DAMAGE.get() + getExtraDamage(spellContext, type, souls) + (AMP_VALUE.get() * spellStats.getAmpMultiplier()));
                livingEntity.addEffect(new MobEffectInstance(BloodMagicPotions.SOUL_SNARE.get(), 300, 0));

                switch (type) {
                    case CORROSIVE:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, (time > 0) ? (ItemSentientSword.poisonTime[bracket] * time) : (ItemSentientSword.poisonTime[bracket]), ItemSentientSword.poisonLevel[bracket] + 1));
                        break;
                    case DEFAULT:
                        break;
                    case DESTRUCTIVE:
                        break;
                    case VENGEFUL:
                        if ((livingEntity.getHealth() < damage)) {
                            player.addEffect(new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT.get(), (time > 0) ? (ItemSentientSword.absorptionTime[bracket] * time) : (ItemSentientSword.absorptionTime[bracket]), ItemSentientSword.absorptionTime[bracket], false, false));
                        }
                        break;
                    case STEADFAST:
                        if ((livingEntity).getHealth() < damage) {
                            float absorption = player.getAbsorptionAmount();
                            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, (time > 0) ? (ItemSentientSword.absorptionTime[bracket] * time) : (ItemSentientSword.absorptionTime[bracket]), 127, false, false));
                            player.setAbsorptionAmount((float) Math.min(absorption + ((LivingEntity) entity).getMaxHealth() * 0.25f, ItemSentientSword.maxAbsorptionHearts));
                        }
                        break;
                }

                // TODO: Change to bloodmagic damage source later
                boolean damaged = attemptDamage(world, shooter, spellStats, spellContext, resolver, entity, buildDamageSource(world, shooter), damage);
                if (damaged) {
                    PlayerDemonWillHandler.consumeDemonWill(type, player, ItemSentientSword.soulDrainPerSwing[bracket]);
                }
            }
        }

    }

    public float getExtraDamage(SpellContext spellContext, EnumDemonWillType type, int souls) {
        int bracket = getBracket(type, souls);
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

    public int getBracket(EnumDemonWillType type, int souls) {
        int bracket = -1;
        for (int i = 0; i < ItemSentientSword.soulBracket.length; i++) {
            if (souls >= ItemSentientSword.soulBracket[i]) {
                bracket = i;
            }
        }
        return bracket;
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 4.0);
        addAmpConfig(builder, 2.0);
        addPotionConfig(builder, 5);
        addExtendTimeConfig(builder, 5);
    }

    @Override
    protected void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(), 2);
    }

    @Override
    public int getDefaultManaCost() {
        return 30;
    }

    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(
                AugmentAmplify.INSTANCE, AugmentDampen.INSTANCE,
                AugmentExtendTime.INSTANCE, AugmentDurationDown.INSTANCE,
                AugmentFortune.INSTANCE
        );
    }

    @Override
    public String getBookDescription() {
        return "An advanced spell, that utilizes your collected demonic will to improve your damage output.";
    }

    public Set<SpellSchool> getSchools() {
        return setOf(IntegrationRegistry.BLOODMAGIC);
    }

}
