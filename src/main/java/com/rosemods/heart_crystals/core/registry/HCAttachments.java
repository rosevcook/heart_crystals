package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.other.HCPlayerInfo;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class HCAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, HeartCrystals.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<HCPlayerInfo.PlayerHealthInfo>> HEALTH_INFO = ATTACHMENT_TYPES.register("health_info", () ->
            AttachmentType.builder(HCPlayerInfo.PlayerHealthInfo::new)
                    .serialize(HCPlayerInfo.PlayerHealthInfo.CODEC)
                    .copyOnDeath()
                    .build()
    );

}
