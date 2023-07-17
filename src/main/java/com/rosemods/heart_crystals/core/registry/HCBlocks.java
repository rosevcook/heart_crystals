package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import com.rosemods.heart_crystals.common.block.HeartLanternBlock;
import com.rosemods.heart_crystals.common.item.HeartCrystalItem;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.common.item.InjectedBlockItem;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HeartCrystals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HCBlocks {
    public static final BlockSubRegistryHelper HELPER = HeartCrystals.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> HEART_CRYSTAL = HELPER.createBlockWithItem("heart_crystal", () -> new HeartCrystalBlock(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.COLOR_PINK).strength(1.25f).sound(SoundType.AMETHYST).lightLevel(s -> 6)), () -> new HeartCrystalItem(HCBlocks.HEART_CRYSTAL.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.UNCOMMON).stacksTo(16)));
    public static final RegistryObject<Block> HEART_LANTERN = HELPER.createBlockWithItem("heart_lantern", () -> new HeartLanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel(s -> 6)), () -> new InjectedBlockItem(Items.END_CRYSTAL, HCBlocks.HEART_LANTERN.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).rarity(Rarity.UNCOMMON)));

}
