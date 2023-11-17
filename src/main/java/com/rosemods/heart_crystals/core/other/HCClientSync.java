package com.rosemods.heart_crystals.core.other;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public final class HCClientSync {

    public static void receivePacket(HCPlayerInfo.PlayerHealthInfoSync sync, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isClient()) {
                Player player =  Minecraft.getInstance().player;

                if (player != null) {
                    HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
                    info.heartCount = sync.getHealthInfo().heartCount;
                    info.healthSet = sync.getHealthInfo().healthSet;
                }
            }
        });

        context.setPacketHandled(true);
    }

}
