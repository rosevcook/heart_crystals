package com.rosemods.heart_crystals.core.registry.datapack;

import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;

public final class HCBannerPatterns {
    public static final ResourceKey<BannerPattern> HEART = createKey("heart");

    public static void bootstrap(BootstrapContext<BannerPattern> context) {
        register(context, HEART);
    }

    private static ResourceKey<BannerPattern> createKey(String name) {
        return ResourceKey.create(Registries.BANNER_PATTERN, HeartCrystals.location(name));
    }

    private static void register(BootstrapContext<BannerPattern> context, ResourceKey<BannerPattern> key) {
        ResourceLocation location = key.location();
        context.register(key, new BannerPattern(location, "block.minecraft.banner." + location.getNamespace() + "." + location.getPath()));
    }

}
