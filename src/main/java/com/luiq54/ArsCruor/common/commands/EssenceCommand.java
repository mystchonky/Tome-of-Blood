package com.luiq54.ArsCruor.common.commands;

import com.luiq54.ArsCruor.common.capability.CapabilityRegistry;
import com.luiq54.ArsCruor.common.network.Networking;
import com.luiq54.ArsCruor.common.network.PacketUpdateEssence;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class EssenceCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ars_cruor")
                .then(Commands.literal("essence")
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.literal("set")
                                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg()).executes((context) -> setEssence(context.getSource(), EntityArgument.getPlayer(context, "player"), DoubleArgumentType.getDouble(context, "amount")))))
                                .then(Commands.literal("clear").executes((context) -> setEssence(context.getSource(), EntityArgument.getPlayer(context, "player"), 0)))).requires(sender -> sender.hasPermission(2))));

    }

    public static int setEssence(CommandSourceStack source, ServerPlayer player, double value) {
        CapabilityRegistry.getEssence(player).ifPresent(essence -> {
            essence.setEssence(value);
            Networking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketUpdateEssence(essence.getCurrentEssence(), essence.getMaxEssence()));
        });
        return 1;
    }
}
