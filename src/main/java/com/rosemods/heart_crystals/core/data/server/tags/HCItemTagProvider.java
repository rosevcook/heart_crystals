package com.rosemods.heart_crystals.core.data.server.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.data.event.GatherDataEvent;

public class HCItemTagProvider extends ItemTagsProvider {
    public HCItemTagProvider(GatherDataEvent event, HCBlockTagProvider blockTags) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), blockTags.contentsGetter(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIM_MATERIALS).add(HCBlocks.HEART_CRYSTAL_SHARD.get().asItem());
    }

}
