package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.other.HCTrimMaterials;
import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.clayworks.core.api.ClayworksTrims;
import net.minecraftforge.common.data.SpriteSourceProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class HCSpriteSourceProvider extends SpriteSourceProvider {

    public HCSpriteSourceProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getExistingFileHelper(), HeartCrystals.MOD_ID);
    }

    @Override
    protected void addSources() {
        this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS).addSource(BlueprintTrims.materialPatternPermutations(HCTrimMaterials.HEART_CRYSTAL_SHARD));
        this.atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(BlueprintTrims.materialPermutationsForItemLayers(HCTrimMaterials.HEART_CRYSTAL_SHARD));
        this.atlas(ClayworksTrims.DECORATED_POT_ATLAS).addSource(ClayworksTrims.materialPatternPermutations(HCTrimMaterials.HEART_CRYSTAL_SHARD));
    }

}
