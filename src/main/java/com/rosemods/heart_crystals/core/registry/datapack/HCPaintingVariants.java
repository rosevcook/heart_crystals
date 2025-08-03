package com.rosemods.heart_crystals.core.registry.datapack;

import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public final class HCPaintingVariants {
    public static final ResourceKey<PaintingVariant> HEARTBEAT = createKey("heartbeat");

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, HEARTBEAT, 2, 2);
    }

    private static ResourceKey<PaintingVariant> createKey(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, HeartCrystals.location(name));
    }

    private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
        context.register(key, new PaintingVariant(width, height, key.location()));
    }

}
