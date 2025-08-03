package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.block_entity.HeartLanternBlockEntity;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;

public final class HCBlockEntities {
    public static final BlockEntitySubRegistryHelper BLOCK_ENTITIES = HeartCrystals.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HeartLanternBlockEntity>> HEART_LANTERN = BLOCK_ENTITIES.createBlockEntity("heart_lantern", HeartLanternBlockEntity::new, () -> Set.of(HCBlocks.HEART_LANTERN.get()));

}
