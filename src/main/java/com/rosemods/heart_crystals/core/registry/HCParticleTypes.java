package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.client.particle.CupidsArrowParticle;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HeartCrystals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HCParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HeartCrystals.MOD_ID);

    public static final RegistryObject<SimpleParticleType> CUPIDS_ARROW = PARTICLE_TYPES.register("cupids_arrow", () -> new SimpleParticleType(true));


    @SubscribeEvent
    public static void register(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(CUPIDS_ARROW.get(), CupidsArrowParticle.Provider::new);
    }

}
