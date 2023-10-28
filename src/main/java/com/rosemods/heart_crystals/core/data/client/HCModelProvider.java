package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.rosemods.heart_crystals.core.registry.HCBlocks.*;
import static com.rosemods.heart_crystals.core.registry.HCItems.*;

public class HCModelProvider extends BlockStateProvider {
    public HCModelProvider(GatherDataEvent event) {
        super(event.getGenerator(), HeartCrystals.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        //items
        this.generatedItem(HEART_CRYSTAL);
        this.generatedItem(HEART_CRYSTAL_SHARD);
        this.generatedItem(HEART_LANTERN);
        this.generatedItem(HEART_BANNER_PATTERN);

        //blocks
        this.simpleBlock(HEART_CRYSTAL.get(), this.models().cross(getItemName(HEART_CRYSTAL), this.blockTexture(HEART_CRYSTAL.get())).renderType("cutout"));
        this.directionalBlock(HEART_CRYSTAL_SHARD.get(), this.models().cross(getItemName(HEART_CRYSTAL_SHARD), this.blockTexture(HEART_CRYSTAL_SHARD.get())).renderType("cutout"));
        this.simpleBlock(HEART_LANTERN.get(), this.models().getExistingFile(new ResourceLocation(HeartCrystals.MODID, "block/heart_lantern")));
    }

    private void generatedItem(RegistryObject<? extends ItemLike> item) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc("item/" + name));
    }

    private static String getItemName(RegistryObject<? extends ItemLike> item) {
        return ForgeRegistries.ITEMS.getKey(item.get().asItem()).getPath();
    }

}
