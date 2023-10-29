package com.rosemods.heart_crystals.common.level.gen.feature;

import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

public class HeartCrystalFeature extends Feature<NoneFeatureConfiguration> {
    public HeartCrystalFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        BlockState state = HCBlocks.HEART_CRYSTAL.get().defaultBlockState();

        for (int x = -4; x <= 4; ++x)
            for (int z = -4; z <= 4; ++z)
                for (int y = -4; y <= 4; ++y) {
                    BlockPos pos = origin.offset(x, y, z);

                    if (pos.getY() < HCConfig.COMMON.maxYLevel.get() && (level.getBlockState(pos).getMaterial().isReplaceable() || level.isWaterAt(pos)) && state.canSurvive(level, pos)) {
                        level.setBlock(pos, state.setValue(HeartCrystalBlock.WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER)), 2);
                        return true;
                    }
                }

        return false;
    }

}
