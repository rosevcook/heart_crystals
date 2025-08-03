package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.data.server.HCDatapackProvider;
import com.rosemods.heart_crystals.core.registry.datapack.HCTrimMaterials;
import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.clayworks.core.api.ClayworksTrims;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class HCSpriteSourceProvider extends SpriteSourceProvider {

    public HCSpriteSourceProvider(GatherDataEvent event, HCDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), dataPack.getRegistryProvider(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void gather() {
        this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS).addSource(BlueprintTrims.materialPatternPermutations(HCTrimMaterials.HEART_CRYSTAL_SHARD));
        this.atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(BlueprintTrims.materialPermutationsForItemLayers(HCTrimMaterials.HEART_CRYSTAL_SHARD));
        this.atlas(ClayworksTrims.DECORATED_POT_ATLAS).addSource(ClayworksTrims.materialPatternPermutations(HCTrimMaterials.HEART_CRYSTAL_SHARD));
    }

}
