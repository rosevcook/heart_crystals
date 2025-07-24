package com.rosemods.heart_crystals.core.data.server.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.data.server.HCDatapackBuiltinEntriesProvider;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import com.rosemods.heart_crystals.core.registry.HCItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;

public class HCItemTagProvider extends ItemTagsProvider {
    public HCItemTagProvider(GatherDataEvent event, HCBlockTagProvider blockTags, HCDatapackBuiltinEntriesProvider dataPack) {
        super(event.getGenerator().getPackOutput(), dataPack.getRegistryProvider(), blockTags.contentsGetter(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIM_MATERIALS).add(HCBlocks.HEART_CRYSTAL_SHARD.get().asItem());
        this.tag(Tags.Items.GEMS).add(HCBlocks.HEART_CRYSTAL_SHARD.get().asItem());
        this.tag(ItemTags.ARROWS).add(HCItems.CUPIDS_ARROW.get());
    }

}
