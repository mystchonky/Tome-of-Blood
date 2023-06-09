package com.luiq54.ArsCruor.common.glyphs;

import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import java.util.Set;

import static com.luiq54.ArsCruor.ArsCruor.prefix;

public class SuckEffect extends AbstractEffect implements IDamageEffect {

    public static SuckEffect INSTANCE = new SuckEffect(prefix("suck"), "Sucks the life out of things");

    public SuckEffect(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    public int getDefaultManaCost() {
        return 10;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (!(rayTraceResult.getEntity() instanceof ItemEntity)) {
            // TODO: Move to configs
            double damage = DAMAGE.get() + AMP_VALUE.get() * spellStats.getAmpMultiplier();
            attemptDamage(world, shooter, spellStats, spellContext, resolver, rayTraceResult.getEntity(), DamageSource.playerAttack(getPlayer(shooter, (ServerLevel) world)), (float) damage);
        }

    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 5.0);
        addAmpConfig(builder, 2.0);
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE);
    }
}
