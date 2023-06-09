package com.luiq54.ArsCruor.common.events;

import com.luiq54.ArsCruor.ArsCruor;
import com.luiq54.ArsCruor.common.capability.CapabilityRegistry;
import com.luiq54.ArsCruor.common.network.Networking;
import com.luiq54.ArsCruor.common.network.PacketUpdateEssence;
import com.luiq54.ArsCruor.common.util.EssenceUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = ArsCruor.MODID)
public class EssenceCapEvents {
    public static double MEAN_TPS = 20.0;

    @SubscribeEvent
    public static void playerOnTick(TickEvent.PlayerTickEvent e) {
        // Essence Regen and max
    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent e) {
        syncPlayerEvent(e.getEntity());
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone e) {
        if (e.getOriginal().level.isClientSide)
            return;

        CapabilityRegistry.getEssence(e.getEntity()).ifPresent(newEssence -> CapabilityRegistry.getEssence(e.getOriginal()).ifPresent(origEssence -> {
            newEssence.setMaxEssence(origEssence.getMaxEssence());
            Networking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) e.getEntity()), new PacketUpdateEssence(newEssence.getCurrentEssence(), newEssence.getMaxEssence()));
        }));
    }

    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.StartTracking e) {
        syncPlayerEvent(e.getEntity());
    }

    @SubscribeEvent
    public static void playerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent e) {
        syncPlayerEvent(e.getEntity());
    }

    public static void syncPlayerEvent(Player playerEntity) {
        if (playerEntity instanceof ServerPlayer) {
            CapabilityRegistry.getEssence(playerEntity).ifPresent(essence -> {
                essence.setMaxEssence(EssenceUtil.getMaxEssence(playerEntity));
                Networking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) playerEntity), new PacketUpdateEssence(essence.getCurrentEssence(), essence.getMaxEssence()));
            });
        }
    }
}
