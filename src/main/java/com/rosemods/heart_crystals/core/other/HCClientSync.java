package com.rosemods.heart_crystals.core.other;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class HCClientSync {

    public static void receivePacket(HCPlayerInfo.PlayerHealthInfoSync sync, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;

            if (player != null) {
                HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
                info.heartCount = sync.getHealthInfo().heartCount;
                info.healthSet = sync.getHealthInfo().healthSet;
            }
        });
    }

}
