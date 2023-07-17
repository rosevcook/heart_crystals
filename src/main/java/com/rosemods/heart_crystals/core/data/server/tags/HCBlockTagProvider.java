package com.rosemods.heart_crystals.core.data.server.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.data.event.GatherDataEvent;

public class HCBlockTagProvider extends BlockTagsProvider {
    public HCBlockTagProvider(GatherDataEvent event) {
        super(event.getGenerator(), HeartCrystals.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(HCBlocks.HEART_CRYSTAL.get(), HCBlocks.HEART_LANTERN.get());
    }

}
