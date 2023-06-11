package com.luiq54.arsoscura.common.capability;

import com.luiq54.arsoscura.ArsOscura;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CapabilityRegistry {
    public static final Capability<IEssenceCap> ESSENCE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static LazyOptional<IEssenceCap> getEssence(final LivingEntity entity) {
        if (entity == null)
            return LazyOptional.empty();
        return entity.getCapability(ESSENCE_CAPABILITY);
    }

    @Mod.EventBusSubscriber(modid = ArsOscura.MODID)
    public static class EventHandler {

        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                EssenceCapAttacher.attach(event);
            }
        }

        @SubscribeEvent
        public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
            event.register(IEssenceCap.class);
        }

        @SubscribeEvent
        public static void playerClone(PlayerEvent.Clone event) {
            Player oldPlayer = event.getOriginal();
            oldPlayer.revive();
            getEssence(oldPlayer).ifPresent(oldMaxEssence -> getEssence(event.getEntity()).ifPresent(newMaxEssence -> {
                newMaxEssence.setMaxEssence(oldMaxEssence.getMaxEssence());
                newMaxEssence.setEssence(oldMaxEssence.getCurrentEssence());
            }));

            event.getOriginal().invalidateCaps();
        }

    }
}
