package com.rosemods.heart_crystals.core.data.server.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.data.event.GatherDataEvent;

public class HCPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

    public HCPaintingVariantTagsProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(PaintingVariantTags.PLACEABLE).add(HCPaintingVariants.HEARTBEAT.getKey());
    }

}
