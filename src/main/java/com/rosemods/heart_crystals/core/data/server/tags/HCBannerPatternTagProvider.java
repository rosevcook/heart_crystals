package com.rosemods.heart_crystals.core.data.server.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.other.tags.HCBannerPatternTags;
import com.rosemods.heart_crystals.core.registry.HCBannerPatterns;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class HCBannerPatternTagProvider extends BannerPatternTagsProvider {
    public HCBannerPatternTagProvider(GatherDataEvent event) {
        super(event.getGenerator(), HeartCrystals.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags() {
        this.tag(HCBannerPatternTags.HEART).add(HCBannerPatterns.HEART.get());
    }

}
