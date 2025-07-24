package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import com.rosemods.heart_crystals.common.block.HeartCrystalShardBlock;
import com.rosemods.heart_crystals.common.block.HeartLanternBlock;
import com.rosemods.heart_crystals.common.item.HeartCrystalItem;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HeartCrystals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HCBlocks {
    public static final BlockSubRegistryHelper HELPER = HeartCrystals.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> HEART_CRYSTAL = HELPER.createBlockWithItem("heart_crystal", () -> new HeartCrystalBlock(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.AMETHYST).lightLevel(s -> 5)), () -> new HeartCrystalItem(HCBlocks.HEART_CRYSTAL.get(), new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(16)));
    public static final RegistryObject<Block> HEART_CRYSTAL_SHARD = HELPER.createBlock("heart_crystal_shard", () -> new HeartCrystalShardBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel(s -> 2)));
    public static final RegistryObject<Block> HEART_LANTERN = HELPER.createBlock("heart_lantern", () -> new HeartLanternBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5f).sound(SoundType.LANTERN).lightLevel(s -> 8)), new Item.Properties().rarity(Rarity.UNCOMMON));

    public static void setupTabEditors() {
        CreativeModeTabContentsPopulator.mod(HeartCrystals.MOD_ID)
                .tab(CreativeModeTabs.INGREDIENTS)
                .addItemsAfter(Ingredient.of(Items.DIAMOND), HEART_CRYSTAL)
                .addItemsAfter(Ingredient.of(Items.AMETHYST_SHARD), HEART_CRYSTAL_SHARD)
                .tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .addItemsBefore(Ingredient.of(Items.CHAIN), HEART_LANTERN);
    }

}
