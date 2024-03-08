package com.rosemods.heart_crystals.core.other;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HeartCrystals.MOD_ID)
public class HCCommands {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(createCommand("reset_hearts", context -> {
            Entity entity = context.getSource().getEntity();

            if (entity instanceof Player player) {
                HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
                info.heartCount = HCConfig.COMMON.minimum.get();
                info.syncHealthInfo(player);
                HCEvents.setMaxHealthAttribute(info.heartCount * 2, player);
                player.setHealth(info.heartCount * 2);

                return 1;
            } else {
                context.getSource().sendFailure(Component.literal("Command must be run by a player"));
                return 0;
            }
        }));
    }

    private static LiteralArgumentBuilder<CommandSourceStack> createCommand(String name, Command<CommandSourceStack> command) {
        return Commands.literal(HeartCrystals.MOD_ID).then(Commands.literal(name).requires(stack -> stack.hasPermission(1)).executes(command));
    }

}
