package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HCParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, HeartCrystals.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CUPIDS_ARROW = PARTICLE_TYPES.register("cupids_arrow", () -> new SimpleParticleType(true));

}
