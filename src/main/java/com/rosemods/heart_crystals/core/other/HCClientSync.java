package com.rosemods.heart_crystals.core.other;

import com.rosemods.heart_crystals.core.registry.HCAttachments;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class HCClientSync {

    public static void receivePacket(HCPlayerInfo.PlayerHealthInfoSync sync, IPayloadContext context) {
        context.enqueueWork(() -> {
            context.player().setData(HCAttachments.HEALTH_INFO, sync.info());
        });
    }

}
