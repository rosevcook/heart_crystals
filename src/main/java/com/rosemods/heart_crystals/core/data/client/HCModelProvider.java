package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HCModelProvider extends BlockStateProvider {
    public HCModelProvider(GatherDataEvent event) {
        super(event.getGenerator(), HeartCrystals.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        this.heartCrystal(HCBlocks.HEART_CRYSTAL);
    }

    // Blocks //

    private void heartCrystal(RegistryObject<Block> block) {
        this.horizontalBlock(block.get(),  this.models().getExistingFile(this.modLoc("block/heart_crystal")));
        //this.generatedItem(block.get(), TextureFolder.Item);
    }

    //  Util //

    private void generatedItem(ItemLike item, TextureFolder folder) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc(folder.format(name)));
    }

    private static String getItemName(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
    }

    private static String getBlockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    private enum TextureFolder {
        Item, Block;

        public String format(String itemName) {
            return this.name().toLowerCase() + '/' + itemName;
        }
    }
}
