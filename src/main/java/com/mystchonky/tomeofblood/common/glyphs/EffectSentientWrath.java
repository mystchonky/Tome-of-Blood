package com.mystchonky.tomeofblood.common.glyphs;

import com.hollingsworth.arsnouveau.api.spell.AbstractAugment;
import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;
import com.hollingsworth.arsnouveau.api.spell.IDamageEffect;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.spell.SpellStats;
import com.hollingsworth.arsnouveau.api.spell.SpellTier;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentDampen;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentDurationDown;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentExtendTime;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentFortune;
import com.mystchonky.tomeofblood.TomeOfBlood;
import com.mystchonky.tomeofblood.client.registry.ParticleRegistry;
import com.mystchonky.tomeofblood.common.registry.IntegrationRegistry;
import com.mystchonky.tomeofblood.common.registry.MobEffectRegistry;
import com.mystchonky.tomeofblood.common.util.DemonWillUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;
import wayoftime.bloodmagic.api.compat.EnumDemonWillType;
import wayoftime.bloodmagic.will.PlayerDemonWillHandler;

import java.util.Map;
import java.util.Set;

public class EffectSentientWrath extends AbstractEffect implements IDamageEffect {

    public static EffectSentientWrath INSTANCE = new EffectSentientWrath();

    public EffectSentientWrath() {
        super(TomeOfBlood.prefix(GlyphLibrary.EffectSentientWrath), "Sentient Wrath");
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @NotNull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (!(rayTraceResult.getEntity() instanceof LivingEntity target && world instanceof ServerLevel level))
            return;
        if (!(shooter instanceof Player player))
            return;
        EnumDemonWillType type = PlayerDemonWillHandler.getLargestWillType(player);
        int souls = (int) PlayerDemonWillHandler.getTotalDemonWill(type, player);
        int bracket = DemonWillUtil.getBracket(type, souls);
        Vec3 vec = safelyGetHitPos(rayTraceResult);
        float damage = (float) (DAMAGE.get() + AMP_VALUE.get() * spellStats.getAmpMultiplier());
        double range = 3 + spellStats.getAoeMultiplier();
        int duration = (int) (POTION_TIME.get() + EXTEND_TIME.get() * spellStats.getDurationMultiplier());

        if (!target.hasEffect(MobEffectRegistry.CURSED.get())) {
            target.addEffect(new MobEffectInstance(MobEffectRegistry.CURSED.get(), 20 * duration, 1));
            attemptDamage(level, shooter, spellStats, spellContext, resolver, target, buildDamageSource(level, shooter), damage);
        } else {
            heavyAttack(vec, level, shooter, target, spellStats, spellContext, resolver, duration, damage);
            for (LivingEntity mob : world.getEntitiesOfClass(LivingEntity.class, new AABB(target.position().add(range, range, range), target.position().subtract(range, range, range)))) {
                if (mob.equals(target) || mob.equals(shooter))
                    continue;
                vec = mob.position();
                heavyAttack(vec, level, shooter, mob, spellStats, spellContext, resolver, duration, damage);
            }
        }

        //trigger on hit

    }

    public void heavyAttack(Vec3 vec, ServerLevel level, LivingEntity shooter, LivingEntity target, SpellStats stats, SpellContext context, SpellResolver resolver, int duration, float baseDamage) {
        // calculate heavy damage
        if (attemptDamage(level, shooter, stats, context, resolver, target, buildDamageSource(level, shooter), baseDamage)) {
            level.sendParticles(ParticleRegistry.WRATH_SLASH.get(), vec.x, vec.y + 0.5, vec.z, 1,
                    ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), 0.3);
            target.addEffect(new MobEffectInstance(MobEffectRegistry.CURSED.get(), 20 * duration));
        }

    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 6.0);
        addAmpConfig(builder, 2.5);
        addPotionConfig(builder, 10);
        addExtendTimeConfig(builder, 5);
    }

    @Override
    protected void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(), 2);
    }

    @Override
    protected int getDefaultManaCost() {
        return 40;
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(
                AugmentAmplify.INSTANCE, AugmentDampen.INSTANCE,
                AugmentExtendTime.INSTANCE, AugmentDurationDown.INSTANCE,
                AugmentAOE.INSTANCE,
                AugmentFortune.INSTANCE
        );
    }

    @Override
    public String getBookDescription() {
        return "Curse your target and unleash demon's wrath";
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.TWO;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return setOf(IntegrationRegistry.BLOODMAGIC);
    }

    //                        case VENGEFUL -> {
//        if (target.getHealth() < damage) {
//            player.addEffect(new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT.get(), time, 1, false, false));
//        }
//    }
//                        case STEADFAST -> {
//        if (target.getHealth() < damage) {
//            float absorption = player.getAbsorptionAmount();
//            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, time, 127, false, false));
//            player.setAbsorptionAmount((float) Math.min(absorption + target.getMaxHealth() * 0.25f, ItemSentientSword.maxAbsorptionHearts));
//        }
//    }
}
