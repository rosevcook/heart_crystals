package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class HCBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HeartCrystals.MODID);

    public static final RegistryObject<Block> HEART_CRYSTAL = BLOCKS.register("heart_crystal", () -> new HeartCrystalBlock(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.COLOR_PINK).strength(1.25f).sound(SoundType.AMETHYST)));
}
