package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
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

    public static final RegistryObject<Block> HEART_CRYSTAL = HELPER.createBlockNoItem("heart_crystal", () -> new HeartCrystalBlock(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.COLOR_PINK).strength(1.25f).sound(SoundType.AMETHYST)));
}
