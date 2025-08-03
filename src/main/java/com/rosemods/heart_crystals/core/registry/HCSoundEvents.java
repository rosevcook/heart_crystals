package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class HCSoundEvents {
    public static final SoundSubRegistryHelper SOUNDS = HeartCrystals.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> HEART_CRYSTAL_USE = SOUNDS.createSoundEvent("block.heart_crystal.use");

}
