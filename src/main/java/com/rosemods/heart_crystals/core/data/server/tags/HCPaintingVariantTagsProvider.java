package com.rosemods.heart_crystals.core.data.server.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.data.server.HCDatapackProvider;
import com.rosemods.heart_crystals.core.registry.datapack.HCPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class HCPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

    public HCPaintingVariantTagsProvider(GatherDataEvent event, HCDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), dataPack.getRegistryProvider(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(PaintingVariantTags.PLACEABLE).add(HCPaintingVariants.HEARTBEAT);
    }

}
