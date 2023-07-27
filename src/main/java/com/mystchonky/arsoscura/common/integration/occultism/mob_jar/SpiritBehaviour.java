package com.mystchonky.arsoscura.common.integration.occultism.mob_jar;

import com.hollingsworth.arsnouveau.api.mob_jar.JarBehavior;
import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.api.OccultismAPI;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.klikli_dev.occultism.exceptions.ItemHandlerMissingException;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class SpiritBehaviour<T extends SpiritEntity> extends JarBehavior<T> {

    @Override
    public void use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, MobJarTile tile) {
        if (world.isClientSide)
            return;
        ItemStack heldStack = player.getItemInHand(handIn);
        SpiritEntity spirit = entityFromJar(tile);
        OccultismAPI api = OccultismAPI.get();

        api.getItemsToPickUp(spirit).ifPresent(ingredients -> {
            if (ingredients.stream().anyMatch(item -> item.test(heldStack))) {
                ItemStack duplicate = heldStack.copy();
                ItemStackHandler handler = spirit.itemStackHandler.orElseThrow(ItemHandlerMissingException::new);
                if (ItemHandlerHelper.insertItemStacked(handler, duplicate, true).getCount() < duplicate.getCount()) {
                    ItemStack remaining = ItemHandlerHelper.insertItemStacked(handler, duplicate, false);
                    heldStack.setCount(remaining.getCount());
                }
            }
        });

    }

    @Override
    public void tick(MobJarTile tile) {
        if (tile.getLevel().isClientSide)
            return;
        SpiritEntity spirit = entityFromJar(tile);
        if (!spirit.isInitialized())
            spirit.init();
        spirit.getJob().ifPresent(spiritJob -> {
            spiritJob.update();
            tile.updateBlock();
        });
    }

}
