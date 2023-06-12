package com.luiq54.arsoscura.common.glyphs;

import com.hollingsworth.arsnouveau.api.spell.AbstractAugment;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import com.hollingsworth.arsnouveau.api.spell.SpellStats;
import com.hollingsworth.arsnouveau.common.lib.GlyphLib;
import com.luiq54.arsoscura.common.capability.CapabilityRegistry;
import com.luiq54.arsoscura.common.capability.IEssenceCap;
import com.luiq54.arsoscura.common.items.ArsOscuraItems;
import com.luiq54.arsoscura.common.items.Sigil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static com.luiq54.arsoscura.ArsOscura.prefix;

public class EffectSigilGenerate extends AbstractEssenceEffect {
    private static final String id = GlyphLib.prependGlyph("sigil_generate");
    public static EffectSigilGenerate INSTANCE = new EffectSigilGenerate(prefix(id), "Generate sigil");

    public EffectSigilGenerate(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    protected int getEssenceCost() {
        return 50;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @NotNull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        super.onResolveEntity(rayTraceResult, world, shooter, spellStats, spellContext, resolver);

        if (!(rayTraceResult.getEntity() instanceof ItemEntity)) {
            Entity entity = rayTraceResult.getEntity();
            LazyOptional<IEssenceCap> essenceCap = CapabilityRegistry.getEssence(shooter);
            if (essenceCap.isPresent()) {
                essenceCap.ifPresent(essence -> {
                    if (essence.getCurrentEssence() > getEssenceCost()) {
                        Sigil sigil = ArsOscuraItems.SIGIL.get();
                        sigil.setEntity(entity.getUUID());
                        world.addFreshEntity(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(sigil)));
                        essence.removeEssence(getEssenceCost());
                    }
                });
            } else {
                // TODO: Curse :P
                spellContext.setCanceled(true);
            }
        }
    }

    @Override
    public int getDefaultManaCost() {
        return 0;
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return Set.of();
    }
}
