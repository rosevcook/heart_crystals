package com.rosemods.heart_crystals.core.data.server.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.data.server.HCDatapackProvider;
import com.rosemods.heart_crystals.core.other.tags.HCBannerPatternTags;
import com.rosemods.heart_crystals.core.registry.datapack.HCBannerPatterns;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class HCBannerPatternTagProvider extends BannerPatternTagsProvider {
    public HCBannerPatternTagProvider(GatherDataEvent event, HCDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), dataPack.getRegistryProvider(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(HCBannerPatternTags.HEART).add(HCBannerPatterns.HEART);
    }

}
