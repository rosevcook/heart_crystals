package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.datapack.HCPaintingVariants;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.rosemods.heart_crystals.core.registry.HCBlocks.*;
import static com.rosemods.heart_crystals.core.registry.HCItems.*;

public class HCModelProvider extends BlockStateProvider {
    public HCModelProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), HeartCrystals.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        //items
        this.generatedItem(HEART_CRYSTAL);
        this.generatedItem(HEART_CRYSTAL_SHARD);
        this.generatedItem(HEART_LANTERN);
        this.generatedItem(HEART_BANNER_PATTERN);
        this.generatedItem(CUPIDS_ARROW);

        //paintings
        this.painting(HCPaintingVariants.HEARTBEAT);

        //blocks
        this.directionalBlock(HEART_CRYSTAL_SHARD.get(), this.models().cross(getItemName(HEART_CRYSTAL_SHARD), this.blockTexture(HEART_CRYSTAL_SHARD.get())).renderType("cutout"));
        this.simpleBlock(HEART_LANTERN.get(), this.models().getExistingFile(this.modLoc("block/heart_lantern")));
        this.getVariantBuilder(HEART_CRYSTAL.get()).forAllStatesExcept(state -> ConfiguredModel.builder()
                .modelFile(this.models().cross(getItemName(HEART_CRYSTAL), this.blockTexture(HEART_CRYSTAL.get())).renderType("cutout"))
                .rotationX(state.getValue(HeartCrystalBlock.HANGING) ? 180 : 0)
                .build(), BlockStateProperties.WATERLOGGED);
    }

    private void painting(ResourceKey<PaintingVariant> painting) {
        String name = painting.location().getPath();
        this.itemModels().withExistingParent("item/painting/" + name, "item/generated").texture("layer0", this.modLoc("item/painting/" + name));
    }

    private void generatedItem(DeferredHolder<? extends ItemLike, ? extends ItemLike> item) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc("item/" + name));
    }

    private static String getItemName(DeferredHolder<? extends ItemLike, ? extends ItemLike> item) {
        return item.getId().getPath();
    }

}
