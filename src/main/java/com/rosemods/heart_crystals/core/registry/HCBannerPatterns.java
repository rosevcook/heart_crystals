package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class HCBannerPatterns {
    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, HeartCrystals.MOD_ID);

    public static final RegistryObject<BannerPattern> HEART = BANNER_PATTERNS.register("heart", () -> new BannerPattern("hch"));

}
