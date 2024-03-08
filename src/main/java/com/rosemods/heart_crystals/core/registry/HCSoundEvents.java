package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HeartCrystals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HCSoundEvents {
    public static final SoundSubRegistryHelper HELPER = HeartCrystals.REGISTRY_HELPER.getSoundSubHelper();

    public static final RegistryObject<SoundEvent> HEART_CRYSTAL_USE = HELPER.createSoundEvent("block.heart_crystal.use");
}
