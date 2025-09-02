package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCPaintingVariants;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

    private void painting(RegistryObject<PaintingVariant> painting) {
        String name = ForgeRegistries.PAINTING_VARIANTS.getKey(painting.get()).getPath();
        this.itemModels().withExistingParent("item/painting/" + name, "item/generated").texture("layer0", this.modLoc("item/painting/" + name));
    }

    private void generatedItem(RegistryObject<? extends ItemLike> item) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc("item/" + name));
    }

    private static String getItemName(RegistryObject<? extends ItemLike> item) {
        return ForgeRegistries.ITEMS.getKey(item.get().asItem()).getPath();
    }

}
