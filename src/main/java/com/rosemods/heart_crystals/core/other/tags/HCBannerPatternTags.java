package com.rosemods.heart_crystals.core.other.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public final class HCBannerPatternTags {
    public static final TagKey<BannerPattern> HEART = createTag("pattern_item/heart");

    private static TagKey<BannerPattern> createTag(String name) {
        return TagKey.create(Registries.BANNER_PATTERN, HeartCrystals.location(name));
    }

}
