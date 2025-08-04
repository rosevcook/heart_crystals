package com.rosemods.heart_crystals.core.other;

import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class HCClientSync {

    public static void receivePacket(HCPlayerInfo.PlayerHealthInfoSync sync, IPayloadContext context) {
        context.enqueueWork(() -> {
            HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(context.player());
            info.heartCount = sync.getHealthInfo().heartCount;
            info.healthSet = sync.getHealthInfo().healthSet;
        });
    }

}
