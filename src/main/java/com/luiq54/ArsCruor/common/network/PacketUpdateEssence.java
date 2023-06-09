package com.luiq54.ArsCruor.common.network;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.luiq54.ArsCruor.ArsCruor;
import com.luiq54.ArsCruor.common.capability.CapabilityRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateEssence {
    public double essence;

    public int maxEssence;


    //Decoder
    public PacketUpdateEssence(FriendlyByteBuf buf) {
        essence = buf.readDouble();
        maxEssence = buf.readInt();
    }

    //Encoder
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeDouble(essence);
        buf.writeInt(maxEssence);
    }

    public PacketUpdateEssence(double mana, int maxMana) {
        this.essence = mana;
        this.maxEssence = maxMana;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ArsNouveau.proxy.getPlayer() == null)
                return;
            CapabilityRegistry.getEssence(ArsCruor.proxy.getPlayer()).ifPresent(essence -> {
                essence.setEssence(this.essence);
                essence.setMaxEssence(this.maxEssence);
            });
        });
        ctx.get().setPacketHandled(true);
    }
}
