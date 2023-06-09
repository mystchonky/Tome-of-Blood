package com.luiq54.ArsCruor.common.events;

import com.hollingsworth.arsnouveau.api.event.SpellDamageEvent;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.luiq54.ArsCruor.ArsCruor;
import com.luiq54.ArsCruor.client.ClientInfo;
import com.luiq54.ArsCruor.common.capability.CapabilityRegistry;
import com.luiq54.ArsCruor.common.commands.EssenceCommand;
import com.luiq54.ArsCruor.common.glyphs.SuckEffect;
import com.luiq54.ArsCruor.common.network.Networking;
import com.luiq54.ArsCruor.common.network.PacketUpdateEssence;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = ArsCruor.MODID)
public class EventHandler {
    @SubscribeEvent
    public static void clientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ClientInfo.ticksInGame++;
        }
    }

    @SubscribeEvent
    public static void commandRegister(RegisterCommandsEvent event) {
        EssenceCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void spellDamage(SpellDamageEvent.Post event) {
        Spell spell = event.context.getSpell();
        if (spell.recipe.get(event.context.getCurrentIndex() - 1) == SuckEffect.INSTANCE) {
            CapabilityRegistry.getEssence(event.caster).ifPresent(essence -> {
                essence.addEssence(event.damage * 10);
                Networking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.caster), new PacketUpdateEssence(essence.getCurrentEssence(), essence.getMaxEssence()));
            });
        }

    }
}
