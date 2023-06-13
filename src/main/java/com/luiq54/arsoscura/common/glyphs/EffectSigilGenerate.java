package com.luiq54.arsoscura.common.glyphs;

import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.lib.GlyphLib;
import com.luiq54.arsoscura.common.capability.CapabilityRegistry;
import com.luiq54.arsoscura.common.capability.IEssenceCap;
import com.luiq54.arsoscura.common.items.ArsOscuraItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static com.luiq54.arsoscura.ArsOscura.prefix;

public class EffectSigilGenerate extends AbstractEffect implements IEssenceEffect {
    private static final String id = GlyphLib.prependGlyph("sigil_generate");
    public static EffectSigilGenerate INSTANCE = new EffectSigilGenerate(prefix(id), "Generate sigil");

    public EffectSigilGenerate(ResourceLocation tag, String description) {
        super(tag, description);
    }


    public int getEssenceCost() {
        return 50;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @NotNull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        super.onResolveEntity(rayTraceResult, world, shooter, spellStats, spellContext, resolver);

        if (rayTraceResult.getEntity() instanceof LivingEntity entity) {
            LazyOptional<IEssenceCap> essenceCap = CapabilityRegistry.getEssence(shooter);
            if (essenceCap.isPresent()) {
                essenceCap.ifPresent(essence -> {
                    if (essence.getCurrentEssence() > getEssenceCost()) {
                        ItemStack sigil = new ItemStack(ArsOscuraItems.SIGIL.get());
                        CompoundTag tag = sigil.getOrCreateTag();
                        String type = entity instanceof Player player ? player.getGameProfile().getName() : entity.getName().getString();
                        tag.putString("entity_type", type);
                        tag.putString("entity_uuid", entity.getStringUUID());
                        sigil.setTag(tag);
                        world.addFreshEntity(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), sigil));
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
        return 10;
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf();
    }
}
