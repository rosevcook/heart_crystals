package com.rosemods.heart_crystals.core.data.server.tags;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.data.server.HCDatapackProvider;
import com.rosemods.heart_crystals.core.registry.datapack.HCTrimMaterials;
import com.teamabnormals.blueprint.core.other.tags.BlueprintTrimMaterialTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class HCTrimMaterialTagsProvider extends TagsProvider<TrimMaterial> {

    public HCTrimMaterialTagsProvider(GatherDataEvent event, HCDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), Registries.TRIM_MATERIAL, dataPack.getRegistryProvider(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlueprintTrimMaterialTags.GENERATES_OVERRIDES).add(HCTrimMaterials.HEART_CRYSTAL_SHARD);
    }

}
