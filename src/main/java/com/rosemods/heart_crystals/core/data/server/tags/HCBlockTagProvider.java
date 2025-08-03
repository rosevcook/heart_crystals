package com.rosemods.heart_crystals.core.data.server.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class HCBlockTagProvider extends BlockTagsProvider {
    public HCBlockTagProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(HCBlocks.HEART_CRYSTAL.get(), HCBlocks.HEART_CRYSTAL_SHARD.get(), HCBlocks.HEART_LANTERN.get());
    }

}
