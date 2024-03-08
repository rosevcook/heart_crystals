package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class HCPaintingVariants {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, HeartCrystals.MOD_ID);

    public static final RegistryObject<PaintingVariant> HEARTBEAT = PAINTING_VARIANTS.register("heartbeat", () -> new PaintingVariant(32, 32));
}
