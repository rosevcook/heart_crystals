package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.block.HeartLanternBlock;
import com.rosemods.heart_crystals.common.block_entity.HeartLanternBlockEntity;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HeartCrystals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HCBlockEntities {
    public static final BlockEntitySubRegistryHelper HELPER = HeartCrystals.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final RegistryObject<BlockEntityType<HeartLanternBlockEntity>> HEART_LANTERN = HELPER.createBlockEntity("heart_lantern", HeartLanternBlockEntity::new, () -> BlockEntitySubRegistryHelper.collectBlocks(HeartLanternBlock.class));

}
