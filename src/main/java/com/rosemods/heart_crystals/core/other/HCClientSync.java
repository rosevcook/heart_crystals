package com.rosemods.heart_crystals.core.other;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HCClientSync {

    private static void syncInfo(HCPlayerInfo.PlayerHealthInfoSync sync) {
        Player player =  Minecraft.getInstance().player;
        if(player == null) return;

        HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
        info.heartCount = sync.getHealthInfo().heartCount;
        info.healthSet = sync.getHealthInfo().healthSet;
    }

    public static void receivePacket(HCPlayerInfo.PlayerHealthInfoSync sync, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isClient()) {
                syncInfo(sync);
            }
        });

        context.setPacketHandled(true);
    }

}
