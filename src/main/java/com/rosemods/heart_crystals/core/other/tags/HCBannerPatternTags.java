package com.rosemods.heart_crystals.core.other.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public final class HCBannerPatternTags {
    public static final TagKey<BannerPattern> HEART = createTag("pattern_item/heart");

    private static TagKey<BannerPattern> createTag(String name) {
        return TagKey.create(Registry.BANNER_PATTERN_REGISTRY, HeartCrystals.REGISTRY_HELPER.prefix(name));
    }

}
