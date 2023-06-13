package com.luiq54.arsoscura.common.glyphs;

import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.lib.GlyphLib;
import com.luiq54.arsoscura.common.items.ArsOscuraItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

import static com.luiq54.arsoscura.ArsOscura.prefix;

public class MethodSigil extends AbstractCastMethod implements IEssenceEffect {

    private static final String id = GlyphLib.prependGlyph("sigil_cast");
    public static MethodSigil INSTANCE = new MethodSigil();

    public MethodSigil() {
        super(prefix(id), "Cast on Sigil");
    }

    @Override
    public CastResolveType onCast(@Nullable ItemStack stack, LivingEntity caster, Level world, SpellStats spellStats, SpellContext context, SpellResolver resolver) {
        Entity entity = getEntityFromCasterSigil(caster, world);
        if (entity != null) {
            resolver.onResolveEffect(world, new EntityHitResult(entity));
            return CastResolveType.SUCCESS;
        }

        return CastResolveType.FAILURE;
    }

    @Override
    public CastResolveType onCastOnBlock(UseOnContext context, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {

        Level world = context.getLevel();
        Entity entity = getEntityFromCasterSigil(context.getPlayer(), world);
        if (entity != null) {
            resolver.onResolveEffect(world, new EntityHitResult(entity));
            return CastResolveType.SUCCESS;
        }

        return CastResolveType.FAILURE;
    }

    @Override
    public CastResolveType onCastOnBlock(BlockHitResult blockRayTraceResult, LivingEntity caster, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Level world = caster.level;
        Entity entity = getEntityFromCasterSigil(caster, world);
        if (entity != null) {
            resolver.onResolveEffect(world, new EntityHitResult(entity));
            return CastResolveType.SUCCESS;
        }

        return CastResolveType.FAILURE;
    }

    @Override
    public CastResolveType onCastOnEntity(@Nullable ItemStack stack, LivingEntity playerIn, Entity target, InteractionHand hand, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Level world = playerIn.level;
        Entity entity = getEntityFromCasterSigil(playerIn, world);
        if (entity != null) {
            resolver.onResolveEffect(world, new EntityHitResult(entity));
            return CastResolveType.SUCCESS;
        }

        return CastResolveType.FAILURE;
    }

    @Override
    public int getDefaultManaCost() {
        return 10;
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf();
    }

    @Override
    public int getEssenceCost() {
        return 100;
    }

    @Nullable
    private Entity getEntityFromCasterSigil(LivingEntity caster, Level world) {
        if (world instanceof ServerLevel level) {
            if (caster.isHolding(ArsOscuraItems.SIGIL.get())) {
                ItemStack sigil = getSigilFromCaster(caster);
                CompoundTag tag = sigil.getTag();
                if (tag != null && tag.contains("entity_uuid")) {
                    String uuid = tag.getString("entity_uuid");
                    return level.getEntity(UUID.fromString(uuid));
                }
            }
        }
        return null;
    }

    @NotNull
    private ItemStack getSigilFromCaster(LivingEntity caster) {
        ItemStack main = caster.getItemInHand(InteractionHand.MAIN_HAND);
        if (main.is(ArsOscuraItems.SIGIL.get()))
            return main;
        return caster.getItemInHand(InteractionHand.OFF_HAND);
    }
}
