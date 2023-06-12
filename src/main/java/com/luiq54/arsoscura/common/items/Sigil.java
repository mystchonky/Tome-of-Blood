package com.luiq54.arsoscura.common.items;

import com.luiq54.arsoscura.common.lang.ArsOscuraLang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Sigil extends Item {

    private Optional<UUID> entity = Optional.empty();

    public Sigil(Properties pProperties) {
        super(pProperties);
    }

    public Sigil() {
        this(new Properties());
    }

    public Optional<UUID> getEntity() {
        return entity;
    }

    public void setEntity(UUID entity) {
        this.entity = Optional.of(entity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (entity.isPresent()) {
            pTooltipComponents.add(Component.translatable(ArsOscuraLang.SIGIL_WITH_ENTITY.getString(), "mango"));
        } else {
            pTooltipComponents.add(Component.translatable(ArsOscuraLang.SIGIL_EMPTY.getString()));
        }
    }
}
